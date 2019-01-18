//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.Iterator;
//import java.util.TreeMap;
//
//import static java.sql.Types.NUMERIC;
//
//public class test {
//
//
//    public static void main(String[] args) throws IOException {
////        XSSFRow row;
////        XSSFRow topRow;
////        XSSFCell topCell;
////
////        int po=-1;
////        int supplier=-1;
////        int code=-1;
////        int line=-1;
////        int sku=-1;
////        int confirmed_delivery_date=-1;
////        int remaining_quantity=-1;
////
////        String path = "C:\\Users\\JKONST~1\\AppData\\Local\\Temp\\backlog_masters_03-01-2019.csv";
////        FileInputStream excelFile = new FileInputStream(new File(path));
////        Workbook workbook = new XSSFWorkbook(excelFile);
////        Sheet sheet = workbook.getSheetAt(0);
////
////        Iterator<Row> rowIterator = sheet.iterator();
////
////        topRow = (XSSFRow) sheet.getRow(0);
////
////        Iterator<Cell> topRowCellIterator = topRow.cellIterator();
////
////        while (topRowCellIterator.hasNext()) {
////            topCell = (XSSFCell) topRowCellIterator.next();
////
////            po = topCell.getStringCellValue().equalsIgnoreCase("po") ? topCell.getColumnIndex(): po;
////            line = topCell.getStringCellValue().equalsIgnoreCase("line") ? topCell.getColumnIndex() : line;
////            supplier = topCell.getStringCellValue().equalsIgnoreCase("supplier") ? topCell.getColumnIndex() : supplier;
////            sku = topCell.getStringCellValue().equalsIgnoreCase("sku") ? topCell.getColumnIndex() : sku;
////            confirmed_delivery_date = topCell.getStringCellValue().equalsIgnoreCase("confirmed_delivery_date") ? topCell.getColumnIndex() : confirmed_delivery_date;
////            remaining_quantity = topCell.getStringCellValue().equalsIgnoreCase("remaining_quantity") ? topCell.getColumnIndex() : remaining_quantity;
////            code = topCell.getStringCellValue().equalsIgnoreCase("code") ? topCell.getColumnIndex() : code;
////
////
////        }
////
////        while (rowIterator.hasNext()) {
////            row = (XSSFRow) rowIterator.next();
////
////            System.out.println(row.getCell(po));
////            System.out.println(row.getCell(line));
////            System.out.println(row.getCell(supplier));
////            System.out.println(row.getCell(sku));
////            System.out.println(row.getCell(confirmed_delivery_date));
////            System.out.println(row.getCell(remaining_quantity));
////            System.out.println(row.getCell(code));
////
////                }
////            }
//        }
//
