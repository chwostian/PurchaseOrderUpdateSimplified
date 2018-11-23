package com.fibaro.model;

import java.io.File;

import java.util.Arrays;
import java.util.List;

public class FileService {

    public static void main(String[] args) {
        String path = "C:\\";
        printDirectories(path);
    }

    public static void printDirectories(String path) {

       final List<File> list = Arrays.asList(new File(path).listFiles());
       list.stream().filter(p->p.isDirectory()).forEach(System.out::println);


    }
}
