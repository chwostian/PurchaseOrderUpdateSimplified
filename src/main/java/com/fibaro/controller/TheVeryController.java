package com.fibaro.controller;

import com.fibaro.model.*;
import com.fibaro.service.DBConnector;
import com.fibaro.service.FullContentDao;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

@Controller
@SessionAttributes({"user","password"})
public class TheVeryController {

    private FullContentDao fullContentDao;


    @Autowired
    public TheVeryController(FullContentDao fullContentDao) {
        this.fullContentDao = fullContentDao;
           }

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String showHome(Model model, HttpSession ses) throws SQLException {
        Connection conn = DBConnector.getConnection((String) ses.getAttribute("user"),(String) ses.getAttribute("password"));
        if (conn!=null) {
            model.addAttribute("hide_or_show", true);
            return "home";
        } else {
            model.addAttribute("hide_or_show", false);
            return "home";
        }
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String submitFileViaPost(@RequestParam("file") MultipartFile file, Model model, HttpSession ses) throws IOException, ParseException, SQLException, InvalidFormatException {
        List<FullContent> list = new ArrayList<>();

        Connection conn = DBConnector.getConnection((String) ses.getAttribute("user"),(String) ses.getAttribute("password"));
        if (conn != null) {
            list.addAll(fullContentDao.fullContentSortedSet(file, conn));
            model.addAttribute("fullContent", list);
            model.addAttribute("size", file.getSize());
            model.addAttribute("fileName", file.getOriginalFilename());
            return "check";
        } else {
            model.addAttribute("sqlException","Błąd połączenia do bazy. Błędny login lub hasło lub baza nie jest dostępna");
            model.addAttribute("hide_or_show", false);
            return "home";
        }

    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.GET)
    public String submitFileViaGet(Model model, HttpSession ses) throws IOException, ParseException, SQLException {

        Connection conn = DBConnector.getConnection((String) ses.getAttribute("user"),(String) ses.getAttribute("password"));
        if (conn != null) {
            model.addAttribute("hide_or_show", true);
            return "home";
        } else {
            model.addAttribute("sqlException","Błąd połączenia do bazy. Błędny login lub hasło lub baza nie jest dostępna");
            model.addAttribute("hide_or_show", false);
            return "home";
        }


    }

    @RequestMapping(value = "/createSessionAttributes", method = RequestMethod.POST)
    public String submitLogging(@RequestParam("user") String user, @RequestParam("password") String password, Model model) throws SQLException {
       Connection conn = DBConnector.getConnection(user,password);

       if (conn !=null) {
            model.addAttribute("user", user);
            model.addAttribute("password", password);
            model.addAttribute("hide_or_show",true);
            return "home";
        } else {
            model.addAttribute("sqlException","Błąd połączenia do bazy. Błędny login lub hasło lub baza nie jest dostępna");
            model.addAttribute("hide_or_show", false);
            return "home";
        }

    }

    @RequestMapping(value = "/createSessionAttributes", method = RequestMethod.GET)
    public String submitLoggingviaGet() {
            return "home";
    }

    @RequestMapping(value="/update", method=RequestMethod.POST)
    @ResponseBody
    public String updatePurchaseOrders(@RequestBody PurchaseOrdersFakeDTO purchaseOrdersFakeDTO) {
       List<PurchaseOrders>  purchaseOrdersList=new ArrayList<>();


            for (PurchaseOrdersFake fake: purchaseOrdersFakeDTO.getPurchaseOrdersFakeList()) {
                PurchaseOrders p = new PurchaseOrders();
                    p.setNumer_zamowienia(fake.getNumer_zamowienia());
                    p.setNumer_pozycji(Integer.parseInt(fake.getNumer_pozycji()));
                    p.setIndeks_czesci(fake.getIndeks_czesci());
                    p.setNazwa_czesci(fake.getNazwa_czesci());
                    p.setKl_termin(LocalDate.parse(fake.getKl_termin()));
                    p.setPr_termin(LocalDate.parse(fake.getPr_termin()));
                    p.setIlosc_zlecona( Integer.parseInt(fake.getIlosc_zlecona().replaceAll("[^\\d]","")));
                    p.setIlosc_do_przyjecia(Integer.parseInt(fake.getIlosc_do_przyjecia().replaceAll("[^\\d]","")));
                    p.setUwagi(fake.getUwagi());
                    p.setNumer_kontrahenta(Long.parseLong(fake.getNumer_kontrahenta().replaceAll("[^\\d]","")));
                purchaseOrdersList.add(p);

            }


        return null;
    }

}
