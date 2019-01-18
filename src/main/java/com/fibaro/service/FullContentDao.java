package com.fibaro.service;

import com.fibaro.model.FileContent;
import com.fibaro.model.FullContent;
import com.fibaro.model.MissingInfo;
import com.fibaro.model.PurchaseOrders;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import java.util.function.Predicate;

@Component
public class FullContentDao {
  private MultipartFile file;

 public static List<FullContent> fullContentSortedSet(MultipartFile file, Connection conn) throws IOException, ParseException, SQLException, InvalidFormatException {
    Boolean daty_sie_zgadzaja;
    Boolean ilosci_sie_zgadzaja;

    SortedSet<FileContent> fileContentSet = FileContentDao.loadFileContent(file);
    SortedSet<FullContent> fullContentSortedSet = new TreeSet<>(FullContent::compareTo);
    SortedSet<PurchaseOrders> purchaseOrdersSortedSet = PurchaseOrdersDao.loadAllOrders(fileContentSet.first().getNumer_kontrahenta(), conn);
    List<MissingInfo> missingInfoList = MissingInfoDao.loadMissingInfoFromIndeksy(conn);

    /*Upewnijmy się, że kombinacja numer zamówienia, pozycji, numer kontrahenta i indeks jest unikalna. Musi być bez powtórzeń */
    for(FileContent fileContent : fileContentSet) {
      FullContent fullContent = new FullContent();
      fullContent.setNumer_kontrahenta(fileContent.getNumer_kontrahenta());
      fullContent.setNumer_zamowienia(fileContent.getNumer_zamowienia());
      fullContent.setNumer_pozycji(fileContent.getNumer_pozycji());
      fullContent.setIndeks(fileContent.getIndeks());
      fullContent.setTermin_dostawcy(fileContent.getPr_termin());
      fullContent.setIlosc_do_przyjecia_wg_dostawcy(fileContent.getIlosc_do_przyjecia());
      fullContentSortedSet.add(fullContent);
    }


    for (PurchaseOrders purchaseOrders: purchaseOrdersSortedSet) {
      FullContent fullContent = new FullContent();
      fullContent.setNumer_kontrahenta(purchaseOrders.getNumer_kontrahenta());
      fullContent.setNumer_zamowienia(purchaseOrders.getNumer_zamowienia());
      fullContent.setNumer_pozycji(purchaseOrders.getNumer_pozycji());
      fullContent.setIndeks(purchaseOrders.getIndeks());
      fullContentSortedSet.add(fullContent);

    }

    // Uzupełnijmy fullContentSortedSet o brakujące dane z obu setów.

    for (FullContent fullyFullContent: fullContentSortedSet)  {

      Predicate<PurchaseOrders> p1 = p -> p.getNumer_kontrahenta().equals(fullyFullContent.getNumer_kontrahenta()) ;
      Predicate<PurchaseOrders> p2 = p -> p.getIndeks().equals(fullyFullContent.getIndeks());
      Predicate<PurchaseOrders> p3 = p -> p.getNumer_zamowienia().equals(fullyFullContent.getNumer_zamowienia());
      Predicate<PurchaseOrders> p4 = p -> p.getNumer_pozycji().equals(fullyFullContent.getNumer_pozycji());
      Predicate<MissingInfo> p5 = p-> p.getIndeks().equals(fullyFullContent.getIndeks());


      Optional<MissingInfo> optionalMissingInfo = missingInfoList.stream().filter(p5).findFirst();

      Optional<PurchaseOrders> purchaseOrders = purchaseOrdersSortedSet.stream().filter(p1.and(p2.and(p3.and(p4)))).findFirst();
      if (purchaseOrders.isPresent()) {
        PurchaseOrders p = purchaseOrders.get();
        fullyFullContent.setIlosc_zlecona(p.getIlosc_zlecona());
        fullyFullContent.setIlosc_do_przyjecia(p.getIlosc_do_przyjecia());
        fullyFullContent.setKl_termin(p.getKl_termin());
        fullyFullContent.setPr_termin(p.getPr_termin());
        fullyFullContent.setUwagi(p.getUwagi());
      }

      if (optionalMissingInfo.isPresent()) {
          MissingInfo mi = optionalMissingInfo.get();
          fullyFullContent.setIndeks_czesci(mi.getIndeks_czesci());
          fullyFullContent.setNazwa_czesci(mi.getNazwa_czesci());
      }

      if (fullyFullContent.getPr_termin() == null || fullyFullContent.getTermin_dostawcy() == null ) {
          daty_sie_zgadzaja = false;

      } else {
          daty_sie_zgadzaja = fullyFullContent.getPr_termin().equals(fullyFullContent.getTermin_dostawcy()) ? true : false;
          }

      if (fullyFullContent.getIlosc_do_przyjecia() == null || fullyFullContent.getIlosc_do_przyjecia_wg_dostawcy() == null) {
          ilosci_sie_zgadzaja = false;
      } else {
          ilosci_sie_zgadzaja = fullyFullContent.getIlosc_do_przyjecia().equals(fullyFullContent.getIlosc_do_przyjecia_wg_dostawcy()) ? true : false;
      }


      fullyFullContent.setDaty_sie_zgadzaja(daty_sie_zgadzaja);
      fullyFullContent.setIlosci_sie_zgadzaja(ilosci_sie_zgadzaja);

    }
    List<FullContent> list = new ArrayList<>();
    list.addAll(fullContentSortedSet);
    conn.close();
    return list;
  }
}
