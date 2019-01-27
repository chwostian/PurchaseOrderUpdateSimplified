package com.fibaro.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class PurchaseOrdersUpdater {


    private static final String UPDATE =
            "UPDATE BAZA_BPSC.POZYCJE_ZAMOWIEN_ZAKUPU pzz" + System.lineSeparator() +
                    "Set " + System.lineSeparator() +
                    "    pzz.ILOSC_ZLECONA = ?," + System.lineSeparator() +
                    "    pzz.KL_TERMIN = ?," + System.lineSeparator() +
                    "    pzz.PR_TERMIN = ?," + System.lineSeparator() +
                    "    pzz.UWAGI = ? " + System.lineSeparator() +
                    "WHERE pzz.NUMER_ZAMOWIENIA = ? and pzz.NUMER_POZYCJI = ? and pzz.STATUS_ZAMOWIENIA ='O' and pzz.STATUS_POZYCJI_USER IN('CD','O')";



    public static int update(Connection connection, PurchaseOrders purchaseOrder) throws SQLException {
        int i = 0;
            if (purchaseOrder.getIlosc_zlecona() != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
                preparedStatement.setInt(1, purchaseOrder.getIlosc_zlecona());
                preparedStatement.setDate(2, purchaseOrder.getKl_termin() == null ? null : Date.valueOf(purchaseOrder.getKl_termin()));
                preparedStatement.setDate(3, purchaseOrder.getPr_termin() == null ? null : Date.valueOf(purchaseOrder.getPr_termin()));
                preparedStatement.setString(4, purchaseOrder.getUwagi());
                preparedStatement.setString(5, purchaseOrder.getNumer_zamowienia());
                preparedStatement.setLong(6, purchaseOrder.getNumer_pozycji());

            i = preparedStatement.executeUpdate();

            }
        return i;
    }}

