package com.fibaro.service;

import com.fibaro.model.MissingInfo;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Component
public class MissingInfoDao {

    private static final String SELECT_FROM_INDEKSY = System.lineSeparator() +
            "Select" + System.lineSeparator() +
                "INDEKS_CZESCI," + System.lineSeparator() +
                "INDEKS," + System.lineSeparator() +
                "NAZWA_CZESCI" + System.lineSeparator() +
              "from indeksy";





    static public List<MissingInfo> loadMissingInfoFromIndeksy(Connection conn) throws SQLException {
        List<MissingInfo> list = new ArrayList<>();

        PreparedStatement preparedStatement;
        preparedStatement = conn.prepareStatement(SELECT_FROM_INDEKSY);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            MissingInfo missingInfo = new MissingInfo();
            missingInfo.setIndeks_czesci(resultSet.getString("indeks_czesci"));
            missingInfo.setIndeks(resultSet.getString("indeks"));
            missingInfo.setNazwa_czesci(resultSet.getString("nazwa_czesci"));
            list.add(missingInfo);}
            conn.close();
        return list;}
}
