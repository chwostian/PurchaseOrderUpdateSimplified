package com.fibaro.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class PurchaseOrdersUpdater {


    private static final String UPDATE =
            "UPDATE BAZA_BPSC.POZYCJE_ZAMOWIEN_ZAKUPU pzz\n" +
                    "Set \n" +
                    "    pzz.ILOSC_ZLECONA = ?,\n" +
                    "    pzz.KL_TERMIN = TO_DATE(?,'yyyy-mm-dd'),\n" +
                    "    pzz.PR_TERMIN = TO_DATE(?,'yyyy-mm-dd'),\n" +
                    "    pzz.UWAGI = ? \n" +
                    "    \n" +
                    "\n" +
                    "WHERE pzz.NUMER_ZAMOWIENIA = ? and pzz.NUMER_POZYCJI = ?;";



    public void update(Connection connection, PurchaseOrders purchaseOrder) throws SQLException {

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setInt(1, purchaseOrder.getIlosc_zlecona());
            preparedStatement.setDate(2, Date.valueOf(purchaseOrder.getKl_termin()));
            preparedStatement.setDate(3, Date.valueOf(purchaseOrder.getPr_termin()));
            preparedStatement.setString(4, purchaseOrder.getUwagi());
            preparedStatement.setString(5, purchaseOrder.getNumer_zamowienia());
            preparedStatement.setLong(6, purchaseOrder.getNumer_pozycji());


            preparedStatement.executeUpdate();
        }
    }

