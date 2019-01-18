package com.fibaro.service;


import com.fibaro.model.FileContent;
import com.fibaro.model.FileHeaders;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import java.text.ParseException;
import java.time.LocalDate;


import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.SortedSet;
import java.util.TreeSet;

@Component
public class FileContentDao {
//    private SortedSet<FileContent> fileContentSet = new TreeSet<>(FileContent::compareTo);
    private MultipartFile file;

    public static SortedSet<FileContent> loadFileContent(MultipartFile file) throws IOException, ParseException {
//        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
        SortedSet<FileContent> fileContentSet = new TreeSet<>(FileContent::compareTo);
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        File convFile = File.createTempFile("import", "." + extension);
        fileContentSet.clear();
        file.transferTo(convFile);

        //Odczyt csv

        if (extension.equals("csv")) {

                    Reader in = new FileReader(convFile);

                    Iterable<CSVRecord> records = CSVFormat.DEFAULT
                            .withHeader(FileHeaders.class)
                            .withDelimiter(';')
                            .withFirstRecordAsHeader()
                            .parse(in);


                    for (CSVRecord record : records) {
                        FileContent fileContent = new FileContent();
                        fileContent.setNumer_kontrahenta(Long.valueOf(record.get(FileHeaders.CODE)));
                        fileContent.setNumer_zamowienia(record.get(FileHeaders.PO));
                        fileContent.setNumer_pozycji(Integer.valueOf(record.get(FileHeaders.LINE)));
                        fileContent.setIndeks(record.get(FileHeaders.SKU));
                        fileContent.setPr_termin(LocalDate.parse(record.get(FileHeaders.CONFIRMED_DELIVERY_DATE)));
                        fileContent.setIlosc_do_przyjecia(Integer.valueOf(record.get(FileHeaders.REMAINING_QUANTITY)));

                        fileContentSet.add(fileContent);
                    }

                    in.close();

        }
        //Koniec odczytu csv
        //Odczyt xlsx
        if (extension.equals("xlsx")) {

            XSSFRow row;
            XSSFRow topRow;
            XSSFCell topCell;

            int po=-1;
            int supplier=-1;
            int code=-1;
            int line=-1;
            int sku=-1;
            int confirmed_delivery_date=-1;
            int remaining_quantity=-1;


            FileInputStream excelFile = new FileInputStream(convFile);
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();

            topRow = (XSSFRow) sheet.getRow(0);

            Iterator<Cell> topRowCellIterator = topRow.cellIterator();

            while (topRowCellIterator.hasNext()) {
                topCell = (XSSFCell) topRowCellIterator.next();

                po = topCell.getStringCellValue().equalsIgnoreCase("po") ? topCell.getColumnIndex(): po;
                line = topCell.getStringCellValue().equalsIgnoreCase("line") ? topCell.getColumnIndex() : line;
                supplier = topCell.getStringCellValue().equalsIgnoreCase("supplier") ? topCell.getColumnIndex() : supplier;
                sku = topCell.getStringCellValue().equalsIgnoreCase("sku") ? topCell.getColumnIndex() : sku;
                confirmed_delivery_date = topCell.getStringCellValue().equalsIgnoreCase("confirmed_delivery_date") ? topCell.getColumnIndex() : confirmed_delivery_date;
                remaining_quantity = topCell.getStringCellValue().equalsIgnoreCase("remaining_quantity") ? topCell.getColumnIndex() : remaining_quantity;
                code = topCell.getStringCellValue().equalsIgnoreCase("code") ? topCell.getColumnIndex() : code;


            }

            for (int i=1; i< sheet.getLastRowNum(); i++) {
                row = (XSSFRow) sheet.getRow(i);
                FileContent fileContent = new FileContent();
                fileContent.setNumer_kontrahenta((long) row.getCell(code).getNumericCellValue());
                fileContent.setNumer_zamowienia(row.getCell(po).getStringCellValue());
                fileContent.setNumer_pozycji((int) row.getCell(line).getNumericCellValue());
                fileContent.setIndeks(row.getCell(sku).getStringCellValue());

                if (row.getCell(confirmed_delivery_date).toString().length() > 0) {
                    fileContent.setPr_termin(new java.sql.Date(HSSFDateUtil.getJavaDate(row.getCell(confirmed_delivery_date).getNumericCellValue()).getTime()).toLocalDate());
                }
                fileContent.setIlosc_do_przyjecia((int) row.getCell(remaining_quantity).getNumericCellValue());


                fileContentSet.add(fileContent);


            }

        }

        //koniec odczytu xlsx

        convFile.delete();
        return fileContentSet;
    }}

