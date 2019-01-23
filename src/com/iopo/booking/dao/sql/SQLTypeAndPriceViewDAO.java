package com.iopo.booking.dao.sql;

import com.iopo.booking.dao.TypeAndPriceViewDAO;
import com.iopo.booking.db.BookingDb;
import com.iopo.booking.db.BookingDbException;
import com.iopo.booking.model.TypeAndPriceView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLTypeAndPriceViewDAO implements TypeAndPriceViewDAO {

    private BookingDb db;

    public SQLTypeAndPriceViewDAO(BookingDb db) {
        this.db = db;
    }


    @Override
    public List<TypeAndPriceView> showAccommodationPrices() throws BookingDbException, SQLException {
        try (Connection connection = db.connect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT acc.type, rf.value FROM accommodation acc INNER JOIN accommodation_fair_relation afr ON (acc.id = afr.id_accommodation)INNER JOIN room_fair rf ON (afr.id_room_fair = rf.id);");
            List<TypeAndPriceView> result = new ArrayList<>();
            while (resultSet.next()) {
                TypeAndPriceView myJoin = mapResultFromAccommodationRoomFairJoin(resultSet);
                result.add(myJoin);
            }
            return result;
        }
    }

    private TypeAndPriceView mapResultFromAccommodationRoomFairJoin (ResultSet resultSet) throws SQLException {
        TypeAndPriceView typeAndPrice = new TypeAndPriceView();
        typeAndPrice.setType(resultSet.getString("type"));
        typeAndPrice.setValue(resultSet.getDouble("value"));
        return typeAndPrice;
    }

}
