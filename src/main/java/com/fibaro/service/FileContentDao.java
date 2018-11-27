package com.fibaro.service;


import com.fibaro.model.FileContent;
import com.fibaro.model.FileHeaders;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import java.text.ParseException;
import java.time.LocalDate;


import java.util.Collections;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

@Component
public class FileContentDao {
    private static SortedSet<FileContent> fileContentSet = new TreeSet<>(FileContent::compareTo);
    private MultipartFile file;

    public static SortedSet<FileContent> loadFileContent(MultipartFile file) throws IOException, ParseException {
        File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+file.getOriginalFilename());

        if (convFile.exists()) {
            convFile.delete();
        } else {
            file.transferTo(convFile);
            Reader in = new FileReader(convFile);

            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader(FileHeaders.class)
                    .withDelimiter(';')
                    .withFirstRecordAsHeader()
                    .parse(in);

            for (CSVRecord record : records) {
                FileContent fileContent = new FileContent();
                fileContent.setSUPPLIER(record.get(FileHeaders.SUPPLIER));
                fileContent.setCODE(Long.valueOf(record.get(FileHeaders.CODE)));
                fileContent.setPO(record.get(FileHeaders.PO));
                fileContent.setLINE(Integer.valueOf(record.get(FileHeaders.LINE)));
                fileContent.setSKU(record.get(FileHeaders.SKU));
                fileContent.setCONFIRMED_DELIVERY_DATE(LocalDate.parse(record.get(FileHeaders.CONFIRMED_DELIVERY_DATE)));
                fileContent.setREMAINING_QUANTITY(Integer.valueOf(record.get(FileHeaders.REMAINING_QUANTITY)));

                fileContentSet.add(fileContent);
                System.out.println(fileContent.toString());
            }

        }

        return fileContentSet;
    }
}
