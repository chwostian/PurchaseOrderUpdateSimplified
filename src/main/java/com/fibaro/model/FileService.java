package com.fibaro.model;

import java.io.File;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

public class FileService {

    public static void main(String[] args) throws ParseException {
        String path = "C:\\";
        printDirectories(path);
    }

    public static void printDirectories(String path) throws ParseException {

       final List<File> list = Arrays.asList(new File(path).listFiles());
       list.stream().filter(p->p.isDirectory()).forEach(System.out::println);

        String now = "2016-11-09 10:30";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime formatDateTime = LocalDateTime.parse(now, formatter);

        System.out.println("Before : " + now);

        System.out.println("After : " + formatDateTime);
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
       // System.out.println(format.parse(String.valueOf(new Date())));
        //System.out.println("After : " + formatDateTime.format(formatter));
        String input = "2012/01/20 12:05:10.321";
        DateFormat inputFormatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
        Date date = inputFormatter.parse(input);

        DateFormat outputFormatter = new SimpleDateFormat("yyyy-mm-dd");
        String output = outputFormatter.format(date); // Output : 01/20/2012
        System.out.println(date);
        System.out.println(output);
        System.out.println(new BigDecimal(Double.parseDouble("1,00E+11")).toBigInteger());
    }
}
