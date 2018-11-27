package com.fibaro.controller;

import com.fibaro.model.FileContent;
import com.fibaro.model.PurchaseOrders;
import com.fibaro.service.DBConnector;
import com.fibaro.service.FileContentDao;
import com.fibaro.service.PurchaseOrdersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.SortedSet;

@Controller
public class TheVeryController {

    private FileContentDao fileContentDao;
    private PurchaseOrdersDao purchaseOrdersDao;
    @Autowired
    public TheVeryController(FileContentDao fileContentDao, PurchaseOrdersDao purchaseOrdersDao) {
        this.fileContentDao = fileContentDao;
        this.purchaseOrdersDao = purchaseOrdersDao;
           }
    @RequestMapping("/")
    public String showHome() {
        return "home";
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String submit(@RequestParam("file") MultipartFile file, Model model) throws IOException, ParseException, SQLException {
        SortedSet<FileContent> fileContentSet = fileContentDao.loadFileContent(file);
        SortedSet<PurchaseOrders> purchaseOrdersSortedSet = purchaseOrdersDao.loadAllOrders(fileContentSet.first().getCODE());

        model.addAttribute("fileContent", fileContentSet);
        model.addAttribute("purchases", purchaseOrdersSortedSet);
        return "check";
    }

}
