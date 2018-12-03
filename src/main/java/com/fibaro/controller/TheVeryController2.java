//package com.fibaro.controller;
//
//import com.fibaro.model.FileContent;
//import com.fibaro.model.FullContent;
//import com.fibaro.model.PurchaseOrders;
//import com.fibaro.service.FileContentDao;
//import com.fibaro.service.PurchaseOrdersDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.text.ParseException;
//import java.util.*;
//import java.util.function.Predicate;
//
//@Controller
//public class TheVeryController2 {
//
//    private FileContentDao fileContentDao;
//    private PurchaseOrdersDao purchaseOrdersDao;
//    @Autowired
//    public TheVeryController2(FileContentDao fileContentDao, PurchaseOrdersDao purchaseOrdersDao) {
//        this.fileContentDao = fileContentDao;
//        this.purchaseOrdersDao = purchaseOrdersDao;
//           }
//    @RequestMapping("/")
//    public String showHome() {
//        return "home";
//    }
//
//    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
//    public String submit(@RequestParam("file") MultipartFile file, Model model) throws IOException, ParseException, SQLException {
//        SortedSet<FileContent> fileContentSet = fileContentDao.loadFileContent(file);
//        System.out.println(fileContentSet.size());
//        SortedSet<PurchaseOrders> purchaseOrdersSortedSet = purchaseOrdersDao.loadAllOrders(fileContentSet.first().getNumer_kontrahenta());
//        SortedSet<FullContent> fullContentSortedSet = new TreeSet<>(FullContent::compareTo);
//        /*Upewnijmy się, że kombinacja numer zamówienia, pozycji, numer kontrahenta i indeks jest unikalna. Musi być bez powtórzeń */
//        for(FileContent fileContent : fileContentSet) {
//            FullContent fullContent = new FullContent();
//            fullContent.setNumer_kontrahenta(fileContent.getNumer_kontrahenta());
//            fullContent.setNumer_zamowienia(fileContent.getNumer_zamowienia());
//            fullContent.setNumer_pozycji(fileContent.getNumer_pozycji());
//            fullContent.setIndeks(fileContent.getIndeks());
//            fullContent.setTermin_dostawcy(fileContent.getPr_termin());
//            fullContent.setIlosc_do_przyjecia_wg_dostawcy(fileContent.getIlosc_do_przyjecia());
//            fullContentSortedSet.add(fullContent);
//        }
//
//
//        for (PurchaseOrders purchaseOrders: purchaseOrdersSortedSet) {
//            FullContent fullContent = new FullContent();
//            fullContent.setNumer_kontrahenta(purchaseOrders.getNumer_kontrahenta());
//            fullContent.setNumer_zamowienia(purchaseOrders.getNumer_zamowienia());
//            fullContent.setNumer_pozycji(purchaseOrders.getNumer_pozycji());
//            fullContent.setIndeks(purchaseOrders.getIndeks());
//            fullContentSortedSet.add(fullContent);
//
//        }
//
//            // Uzupełnijmy fullContentSortedSet o brakujące dane z obu setów.
//
//        for (FullContent fullyFullContent: fullContentSortedSet)  {
//
//            Predicate<PurchaseOrders> p1 = p -> p.getNumer_kontrahenta().equals(fullyFullContent.getNumer_kontrahenta()) ;
//            Predicate<PurchaseOrders> p2 = p -> p.getIndeks().equals(fullyFullContent.getIndeks());
//            Predicate<PurchaseOrders> p3 = p -> p.getNumer_zamowienia().equals(fullyFullContent.getNumer_zamowienia());
//            Predicate<PurchaseOrders> p4 = p -> p.getNumer_pozycji().equals(fullyFullContent.getNumer_pozycji());
//
//
//
//            Optional<PurchaseOrders> purchaseOrders = (Optional<PurchaseOrders>) purchaseOrdersSortedSet.stream().filter(p1.and(p2.and(p3.and(p4)))).findFirst();
//            if (purchaseOrders.isPresent()) {
//                PurchaseOrders p = purchaseOrders.get();
//                fullyFullContent.setIlosc_zlecona(p.getIlosc_zlecona());
//                fullyFullContent.setIlosc_do_przyjecia(p.getIlosc_do_przyjecia());
//                fullyFullContent.setKl_termin(p.getKl_termin());
//                fullyFullContent.setPr_termin(p.getPr_termin());
//                fullyFullContent.setUwagi(p.getUwagi());
//            }
//
//
//
//
//        }
//
//        List<FullContent> list = new ArrayList<>();
//        list.addAll(fullContentSortedSet);
//        model.addAttribute("fullContent", list);
//
//        return "check";
//    }
//
//}
