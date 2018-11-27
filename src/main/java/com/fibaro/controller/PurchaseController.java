//package com.fibaro.controller;
//
//import com.fibaro.model.FileContent;
//import com.fibaro.model.PurchaseOrders;
//import com.fibaro.service.DBConnector;
//import com.fibaro.service.PurchaseOrdersDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.text.ParseException;
//import java.util.SortedSet;
//
//@Controller
//public class PurchaseController {
//    private PurchaseOrdersDao purchaseOrdersDao;
//
//    @Autowired
//    public PurchaseController(PurchaseOrdersDao purchaseOrdersDao) {
//        this.purchaseOrdersDao = purchaseOrdersDao;
//    }
//
//    @RequestMapping(value = "/purchase", method = RequestMethod.GET)
//    public String submit(Model model) throws IOException, ParseException, SQLException {
//        SortedSet<PurchaseOrders> purchaseOrdersSortedSet = purchaseOrdersDao.loadAllOrders(new Long(5557));
//        model.addAttribute("purchaseContent", purchaseOrdersSortedSet);
//
//        return "check";
//    }
//}
