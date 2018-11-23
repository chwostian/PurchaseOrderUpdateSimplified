package com.fibaro.controller;


import com.fibaro.service.FileContentDao;
import com.fibaro.service.PurchaseOrdersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class PurchaseOrderController {

        private PurchaseOrdersDao purchaseOrdersDao;

        @Autowired
        public PurchaseOrderController(PurchaseOrdersDao purchaseOrdersDao) {
            this.purchaseOrdersDao = purchaseOrdersDao;
        }


        @RequestMapping("/")
        public String showHome() {
             return "home";
        }


}


