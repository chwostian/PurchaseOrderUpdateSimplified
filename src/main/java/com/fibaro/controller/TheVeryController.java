package com.fibaro.controller;

import com.fibaro.model.FileContent;
import com.fibaro.model.FullContent;
import com.fibaro.model.PurchaseOrders;
import com.fibaro.service.DBConnector;
import com.fibaro.service.FileContentDao;
import com.fibaro.service.FullContentDao;
import com.fibaro.service.PurchaseOrdersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import java.util.function.Predicate;

@Controller
@SessionAttributes({"user","password"})
public class TheVeryController {

    private FullContentDao fullContentDao;

    @Autowired
    public TheVeryController(FullContentDao fullContentDao) {
        this.fullContentDao = fullContentDao;
           }
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String showHome() {
        return "home";
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String submitFile(@RequestParam("file") MultipartFile file, Model model) throws IOException, ParseException, SQLException {
        List<FullContent> list = new ArrayList<>();
        list.addAll(fullContentDao.fullContentSortedSet(file));
        model.addAttribute("fullContent", list);
        return "check";
    }

    @RequestMapping(value = "/createSessionAttributes", method = RequestMethod.POST)
    @ResponseBody
    public String submitLogging(@RequestParam("user") String user, @RequestParam("password") String password, Model model) throws SQLException {
        model.addAttribute("user", user);
        model.addAttribute("user", password);
        return "OK";
    }

}
