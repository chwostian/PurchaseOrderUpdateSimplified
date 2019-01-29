package com.fibaro.controller;

import com.fibaro.model.*;
import com.fibaro.service.DBConnector;
import com.fibaro.service.FullContentDao;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.functions.Rows;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@SessionAttributes({"user", "password"})
public class TheVeryController {

    private FullContentDao fullContentDao;


    @Autowired
    public TheVeryController(FullContentDao fullContentDao) {
        this.fullContentDao = fullContentDao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showHome(Model model, HttpSession ses) throws SQLException {
        Connection conn = DBConnector.getConnection((String) ses.getAttribute("user"), (String) ses.getAttribute("password"));
        if (conn != null) {
            model.addAttribute("hide_or_show", true);
            return "home";
        } else {
            model.addAttribute("hide_or_show", false);
            return "home";
        }
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String submitFileViaPost(@RequestParam("file") MultipartFile file, Model model, HttpSession ses) throws IOException, ParseException, SQLException, InvalidFormatException {
        List<FullContent> list = new ArrayList<>();

        Connection conn = DBConnector.getConnection((String) ses.getAttribute("user"), (String) ses.getAttribute("password"));
        if (conn != null) {
            list.addAll(fullContentDao.fullContentSortedSet(file, conn));
            model.addAttribute("fullContent", list);
            model.addAttribute("size", file.getSize());
            model.addAttribute("fileName", file.getOriginalFilename());
            return "check";
        } else {
            model.addAttribute("sqlException", "Błąd połączenia do bazy. Błędny login lub hasło lub baza nie jest dostępna");
            model.addAttribute("hide_or_show", false);
            return "home";
        }

    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.GET)
    public String submitFileViaGet(Model model, HttpSession ses) throws IOException, ParseException, SQLException {

        Connection conn = DBConnector.getConnection((String) ses.getAttribute("user"), (String) ses.getAttribute("password"));
        if (conn != null) {
            model.addAttribute("hide_or_show", true);
            return "home";
        } else {
            model.addAttribute("sqlException", "Błąd połączenia do bazy. Błędny login lub hasło lub baza nie jest dostępna");
            model.addAttribute("hide_or_show", false);
            return "home";
        }


    }

    @RequestMapping(value = "/createSessionAttributes", method = RequestMethod.POST)
    public String submitLogging(@RequestParam("user") String user, @RequestParam("password") String password, Model model) throws SQLException {
        Connection conn = DBConnector.getConnection(user, password);

        if (conn != null) {
            model.addAttribute("user", user);
            model.addAttribute("password", password);
            model.addAttribute("hide_or_show", true);
            return "home";
        } else {
            model.addAttribute("sqlException", "Błąd połączenia do bazy. Błędny login lub hasło lub baza nie jest dostępna");
            model.addAttribute("hide_or_show", false);
            return "home";
        }

    }

    @RequestMapping(value = "/createSessionAttributes", method = RequestMethod.GET)
    public String submitLoggingviaGet() {
        return "home";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ReturnPurchaseOrdersFakeDTO updatePurchaseOrders(@RequestBody PurchaseOrdersFakeDTO purchaseOrdersFakeDTO, HttpServletResponse response, HttpSession ses) throws SQLException {
        List<PurchaseOrders> purchaseOrdersList = new ArrayList<>();


        for (PurchaseOrdersFake fake : purchaseOrdersFakeDTO.getPurchaseOrdersFakeList()) {
            PurchaseOrders p = new PurchaseOrders();
            p.setNumer_zamowienia(fake.getNumer_zamowienia());
            p.setNumer_pozycji(Integer.parseInt(fake.getNumer_pozycji()));
            p.setIndeks_czesci(fake.getIndeks_czesci());
            p.setNazwa_czesci(fake.getNazwa_czesci());
            p.setKl_termin(fake.getKl_termin().length() == 0 ? null : LocalDate.parse(fake.getKl_termin()));
            p.setPr_termin(fake.getPr_termin().length() == 0 ? null : LocalDate.parse(fake.getPr_termin()));
            p.setIlosc_zlecona(fake.getIlosc_zlecona().length() == 0 ? null : Integer.parseInt(fake.getIlosc_zlecona().replaceAll("[^\\d]", "")));
            p.setIlosc_do_przyjecia(fake.getIlosc_do_przyjecia().length() == 0 ? null : Integer.parseInt(fake.getIlosc_do_przyjecia().replaceAll("[^\\d]", "")));
            p.setUwagi(fake.getUwagi());
            p.setNumer_kontrahenta(Long.parseLong(fake.getNumer_kontrahenta().replaceAll("[^\\d]", "")));
            purchaseOrdersList.add(p);

        }

        Connection conn = DBConnector.getConnection((String) ses.getAttribute("user"), (String) ses.getAttribute("password"));
        int i = 0;

        List<ReturnPurchaseOrdersFake> returnPurchaseOrdersFakeList = new ArrayList<>();

        for (PurchaseOrders purchaseOrders : purchaseOrdersList) {
            i = PurchaseOrdersUpdater.update(conn, purchaseOrders);
            ReturnPurchaseOrdersFake returnPurchaseOrdersFake = new ReturnPurchaseOrdersFake();
            returnPurchaseOrdersFake.setPurchaseOrders(purchaseOrders);
            returnPurchaseOrdersFake.setUpdated(i);
            returnPurchaseOrdersFakeList.add(returnPurchaseOrdersFake);
            i = 0;
        }

        ReturnPurchaseOrdersFakeDTO returnPurchaseOrdersFakeDTO = new ReturnPurchaseOrdersFakeDTO();
        returnPurchaseOrdersFakeDTO.setReturnPurchaseOrdersFakeList(returnPurchaseOrdersFakeList);
        return returnPurchaseOrdersFakeDTO;
    }

    @RequestMapping(value = "/createExcel", method = RequestMethod.POST)
    @ResponseBody
    public String createExcel(@RequestBody PurchaseOrdersFakeDTO purchaseOrdersFakeDTO, HttpServletResponse response, HttpSession ses) throws SQLException, IOException {
        List<PurchaseOrders> purchaseOrdersList = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("backlog_comparison");

        //Ustawiamy style dla nagłówków
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerStyle.setWrapText(true);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);

        //Ustawimy czcionkę
        XSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 9);
        font.setFontName("Calibri");

        CellStyle fontStyle = workbook.createCellStyle();
        fontStyle.setFont(font);

        //Ustawimy formatowanie daty
        CreationHelper createHelper = workbook.getCreationHelper();
        CellStyle dateStyle = workbook.createCellStyle();
        dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-mm-dd"));
        dateStyle.setFont(font);

        //Tworzymy nagłówki
        Row firstHeader = sheet.createRow(0);
        Row secondHeader = sheet.createRow(1);

        for (int i = 0; i < 5; i++) {
            Cell cell = firstHeader.createCell(i);
            cell.setCellValue("Common data");
            cell.setCellStyle(headerStyle);
        }

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));

        for (int i = 5; i < 9; i++) {
            Cell cell = firstHeader.createCell(i);
            cell.setCellValue("Fibaro data");
            cell.setCellStyle(headerStyle);
        }

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 5, 8));

        for (int i = 9; i < 11; i++) {
            Cell cell = firstHeader.createCell(i);
            cell.setCellValue("Supplier data");
            cell.setCellStyle(headerStyle);
        }

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 9, 10));

        Cell cell = firstHeader.createCell(11);
        cell.setCellValue("COMMENT");
        cell.setCellStyle(headerStyle);

        Cell secondHeaderCell = secondHeader.createCell(0);
        secondHeaderCell.setCellValue("Code");
        secondHeaderCell.setCellStyle(headerStyle);

        secondHeaderCell = secondHeader.createCell(1);
        secondHeaderCell.setCellValue("Purchase order");
        secondHeaderCell.setCellStyle(headerStyle);

        secondHeaderCell = secondHeader.createCell(2);
        secondHeaderCell.setCellValue("Line");
        secondHeaderCell.setCellStyle(headerStyle);

        secondHeaderCell = secondHeader.createCell(3);
        secondHeaderCell.setCellValue("SKU");
        secondHeaderCell.setCellStyle(headerStyle);

        secondHeaderCell = secondHeader.createCell(4);
        secondHeaderCell.setCellValue("Description");
        secondHeaderCell.setCellStyle(headerStyle);

        secondHeaderCell = secondHeader.createCell(5);
        secondHeaderCell.setCellValue("Requested delivery date");
        secondHeaderCell.setCellStyle(headerStyle);

        secondHeaderCell = secondHeader.createCell(6);
        secondHeaderCell.setCellValue("Confirmed delivery date");
        secondHeaderCell.setCellStyle(headerStyle);

        secondHeaderCell = secondHeader.createCell(7);
        secondHeaderCell.setCellValue("Original quantity");
        secondHeaderCell.setCellStyle(headerStyle);

        secondHeaderCell = secondHeader.createCell(8);
        secondHeaderCell.setCellValue("Remaining quantity");
        secondHeaderCell.setCellStyle(headerStyle);

        secondHeaderCell = secondHeader.createCell(9);
        secondHeaderCell.setCellValue("Confirmed delivery date");
        secondHeaderCell.setCellStyle(headerStyle);

        secondHeaderCell = secondHeader.createCell(10);
        secondHeaderCell.setCellValue("Remaining quantity");
        secondHeaderCell.setCellStyle(headerStyle);

        secondHeaderCell = secondHeader.createCell(11);
        secondHeaderCell.setCellValue("Comment");
        secondHeaderCell.setCellStyle(headerStyle);


        sheet.createFreezePane(0, 2);


        for (PurchaseOrdersFake purchaseOrdersFake : purchaseOrdersFakeDTO.getPurchaseOrdersFakeList()) {
            int lastRow = sheet.getLastRowNum();
            Row anotherRow = sheet.createRow(lastRow + 1);
            Cell anotherCell = anotherRow.createCell(0);
            anotherCell.setCellValue(Double.parseDouble(purchaseOrdersFake.getNumer_kontrahenta()));
            anotherCell.setCellStyle(fontStyle);

            anotherCell = anotherRow.createCell(1);
            anotherCell.setCellValue(purchaseOrdersFake.getNumer_zamowienia());
            anotherCell.setCellStyle(fontStyle);

            anotherCell = anotherRow.createCell(2);
            anotherCell.setCellValue(Double.parseDouble(purchaseOrdersFake.getNumer_pozycji()));
            anotherCell.setCellStyle(fontStyle);

            anotherCell = anotherRow.createCell(3);
            anotherCell.setCellValue(purchaseOrdersFake.getIndeks());
            anotherCell.setCellStyle(fontStyle);

            anotherCell = anotherRow.createCell(4);
            anotherCell.setCellValue(purchaseOrdersFake.getNazwa_czesci());
            anotherCell.setCellStyle(fontStyle);

            anotherCell = anotherRow.createCell(5);
            if (purchaseOrdersFake.getKl_termin().length() > 0) {
                anotherCell.setCellValue(java.sql.Date.valueOf((purchaseOrdersFake.getKl_termin())));
            }
            anotherCell.setCellStyle(fontStyle);
            anotherCell.setCellStyle(dateStyle);


            anotherCell = anotherRow.createCell(6);
            if (purchaseOrdersFake.getPr_termin().length() > 0) {
                anotherCell.setCellValue(java.sql.Date.valueOf(purchaseOrdersFake.getPr_termin()));
            }
            anotherCell.setCellStyle(fontStyle);
            anotherCell.setCellStyle(dateStyle);

            anotherCell = anotherRow.createCell(7);
            if (purchaseOrdersFake.getIlosc_zlecona().length()>0) {
                anotherCell.setCellValue(Double.parseDouble(purchaseOrdersFake.getIlosc_zlecona().replaceAll("[^\\d]", "")));
            }
            anotherCell.setCellStyle(fontStyle);

            anotherCell = anotherRow.createCell(8);
            if (purchaseOrdersFake.getIlosc_do_przyjecia().length()>0) {
                anotherCell.setCellValue(Double.parseDouble(purchaseOrdersFake.getIlosc_do_przyjecia().replaceAll("[^\\d]", "")));
            }
            anotherCell.setCellStyle(fontStyle);

            anotherCell = anotherRow.createCell(9);
            if (purchaseOrdersFake.getTermin_dostawcy().length()>0) {
                anotherCell.setCellValue(java.sql.Date.valueOf(purchaseOrdersFake.getTermin_dostawcy()));
            }
            anotherCell.setCellStyle(fontStyle);
            anotherCell.setCellStyle(dateStyle);

            anotherCell = anotherRow.createCell(10);
            if (purchaseOrdersFake.getIlosc_do_przyjecia_wg_dostawcy().length()>0) {
                anotherCell.setCellValue(Double.parseDouble(purchaseOrdersFake.getIlosc_do_przyjecia_wg_dostawcy().replaceAll("[^\\d]", "")));
            }
            anotherCell.setCellStyle(fontStyle);

            anotherCell = anotherRow.createCell(11);
            anotherCell.setCellValue(purchaseOrdersFake.getUwagi());
            anotherCell.setCellStyle(fontStyle);

        }

        for (int i = 0; i < secondHeader.getLastCellNum(); i++) {
            sheet.autoSizeColumn(i);
        }

        //zapis do pliku
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "temp.xlsx";

        FileOutputStream outputStream = new FileOutputStream(fileLocation);

        workbook.write(outputStream);
        outputStream.close();
        workbook.close();


        return "coś";
    }

}
