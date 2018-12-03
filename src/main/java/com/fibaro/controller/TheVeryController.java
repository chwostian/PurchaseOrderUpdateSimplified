package com.fibaro.controller;

import com.fibaro.model.FileContent;
import com.fibaro.model.FullContent;
import com.fibaro.model.PurchaseOrders;
import com.fibaro.service.FileContentDao;
import com.fibaro.service.FullContentDao;
import com.fibaro.service.PurchaseOrdersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import java.util.function.Predicate;

@Controller
public class TheVeryController {

    private FullContentDao fullContentDao;

    @Autowired
    public TheVeryController(FullContentDao fullContentDao) {
        this.fullContentDao = fullContentDao;
           }
    @RequestMapping("/")
    public String showHome() {
        return "home";
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String submit(@RequestParam("file") MultipartFile file, Model model) throws IOException, ParseException, SQLException {
        List<FullContent> list = new ArrayList<>();
        list.addAll(fullContentDao.fullContentSortedSet(file));
        model.addAttribute("fullContent", list);

        return "check";
    }

}
