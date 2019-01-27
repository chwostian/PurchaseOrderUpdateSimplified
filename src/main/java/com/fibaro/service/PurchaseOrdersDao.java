package com.fibaro.service;

import com.fibaro.model.PurchaseOrders;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDate;
import java.util.SortedSet;
import java.util.TreeSet;

@Component
public class PurchaseOrdersDao {

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
            "where zz.numer_kontrahenta = ? and pz.status_zamowienia = 'O' and pz.STATUS_POZYCJI_USER IN('CD','O') and pz.id_firmy = '1'" + System.lineSeparator() +
            "order by zz.numer_kontrahenta, i.indeks, pr_termin";





    static public SortedSet<PurchaseOrders> loadAllOrders(Long numer_kontrahenta, Connection conn) throws SQLException {
        SortedSet<PurchaseOrders> ordersSet = new TreeSet<>(PurchaseOrders::compareTo);

        PreparedStatement preparedStatement;
        preparedStatement = conn.prepareStatement(SELECT_ALL_ORDERS);
        preparedStatement.setLong(1,numer_kontrahenta);
        ResultSet resultSet = preparedStatement.executeQuery();
        LocalDate kl_termin;
        LocalDate pr_termin;

        while (resultSet.next()) {
            PurchaseOrders purchaseOrders = new PurchaseOrders();
            purchaseOrders.setNumer_kontrahenta(resultSet.getLong("numer_kontrahenta"));
            purchaseOrders.setNumer_zamowienia(resultSet.getString("numer_zamowienia"));
            purchaseOrders.setNumer_pozycji(resultSet.getInt("numer_pozycji"));
            purchaseOrders.setIndeks_czesci(resultSet.getString("indeks_czesci"));
            purchaseOrders.setIndeks(resultSet.getString("indeks"));

            kl_termin = resultSet.getDate("kl_termin") == null ? null: resultSet.getDate("kl_termin").toLocalDate();
            pr_termin = resultSet.getDate("pr_termin") == null ? null : resultSet.getDate("pr_termin").toLocalDate();

            if (kl_termin == null) {
                purchaseOrders.setKl_termin(null);
            }
            purchaseOrders.setKl_termin(kl_termin);
            purchaseOrders.setPr_termin(pr_termin);
            purchaseOrders.setIlosc_zlecona(resultSet.getInt("ilosc_zlecona"));
            purchaseOrders.setIlosc_do_przyjecia(resultSet.getInt("ilosc_do_przyjecia"));
            purchaseOrders.setUwagi(resultSet.getString("uwagi"));

            ordersSet.add(purchaseOrders);}

        return ordersSet;}
}
