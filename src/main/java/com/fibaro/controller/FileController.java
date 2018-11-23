package com.fibaro.controller;

import com.fibaro.model.FileContent;
import com.fibaro.service.FileContentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.Set;

@Controller
public class FileController {

    private FileContentDao fileContentDao;
    @Autowired
    public FileController(FileContentDao fileContentDao) {
        this.fileContentDao = fileContentDao;
    }
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String submit(@RequestParam("file") MultipartFile file, Model model) throws IOException, ParseException {
        Set<FileContent> fileContentSet = fileContentDao.loadFileContent(file);
        model.addAttribute("fileContent", fileContentSet);
        return "check";
    }
}
