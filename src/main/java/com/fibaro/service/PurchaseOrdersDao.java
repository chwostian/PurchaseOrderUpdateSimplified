package com.fibaro.service;

import com.fibaro.model.PurchaseOrders;
import org.springframework.stereotype.Component;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashSet;
import java.util.Set;
@Component
public class PurchaseOrdersDao {
    private static Set<PurchaseOrders> ordersSet = new HashSet<>();
    private static final String SELECT_ALL_ORDERS = System.lineSeparator() +
            "Select" + System.lineSeparator() +
                "pz.numer_zamowienia," + System.lineSeparator() +
                "pz.numer_pozycji," + System.lineSeparator() +
                "pz.indeks_czesci," + System.lineSeparator() +
                "i.indeks," + System.lineSeparator() +
                "i.nazwa_czesci," + System.lineSeparator() +
                "pz.kl_termin," + System.lineSeparator() +
                "pz.pr_termin," + System.lineSeparator() +
                "pz.ilosc_zlecona," + System.lineSeparator() +
                "pz.ilosc_do_przyjecia," + System.lineSeparator() +
                "pz.uwagi," + System.lineSeparator() +
                "zz.numer_kontrahenta," + System.lineSeparator() +
                "vk.nazwa_pelna" + System.lineSeparator() +
            "from pozycje_zamowien_zakupu pz" + System.lineSeparator() +
                "join zamowienia_zakupu zz on pz.numer_zamowienia = zz.numer_zamowienia and pz.id_firmy = zz.id_firmy" + System.lineSeparator() +
                "left join v_kontrahenci vk on zz.numer_kontrahenta = vk.numer_kontrahenta and zz.id_firmy = vk.id_firmy" + System.lineSeparator() +
                "join indeksy i on pz.indeks_czesci = i.indeks_czesci" + System.lineSeparator() +
            "where zz.numer_kontrahenta = ? and pz.status_zamowienia = 'O' and pz.id_firmy = '1'";





    static public Set<PurchaseOrders> loadAllOrders(Long numer_kontrahenta, Connection conn) throws SQLException {
        PreparedStatement preparedStatement;
        preparedStatement = conn.prepareStatement(SELECT_ALL_ORDERS);
        preparedStatement.setLong(1,numer_kontrahenta);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            PurchaseOrders purchaseOrders = new PurchaseOrders();
            purchaseOrders.setNumer_kontrahenta(resultSet.getLong("numer_kontrahenta"));
            purchaseOrders.setNazwa_pelna(resultSet.getString("nazwa_pelna"));
            purchaseOrders.setNumer_zamowienia(resultSet.getString("numer_zamowienia"));
            purchaseOrders.setNumer_pozycji(resultSet.getString("numer_pozycji"));
            purchaseOrders.setIndeks_czesci(resultSet.getString("indeks_czesci"));
            purchaseOrders.setIndeks(resultSet.getString("indeks"));
            purchaseOrders.setNazwa_czesci(resultSet.getString("nazwa_czesci"));
            purchaseOrders.setKl_termin(resultSet.getDate("kl_termin"));
            purchaseOrders.setPr_termin(resultSet.getDate("pr_termin"));
            purchaseOrders.setIlosc_zlecona(resultSet.getInt("ilosc_zlecona"));
            purchaseOrders.setIlosc_do_przyjecia(resultSet.getInt("ilosc_do_przyjecia"));
            purchaseOrders.setUwagi(resultSet.getString("uwagi"));
            ordersSet.add(purchaseOrders);}

        return ordersSet;}
}
