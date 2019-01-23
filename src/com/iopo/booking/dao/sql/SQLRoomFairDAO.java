package com.iopo.booking.dao.sql;

import com.iopo.booking.dao.RoomFairDAO;
import com.iopo.booking.db.BookingDb;
import com.iopo.booking.db.BookingDbException;
import com.iopo.booking.model.RoomFair;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLRoomFairDAO implements RoomFairDAO {

    private BookingDb db;

    public SQLRoomFairDAO(BookingDb db) {
        this.db = db;
    }

    @Override
    public List<RoomFair> getAll() throws BookingDbException, SQLException {
        try (Connection conn = db.connect()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from room_fair;");
            ArrayList<RoomFair> roomFairs = new ArrayList<>();
            while (resultSet.next()) {
                RoomFair roomFair = mapResultSetToRoomFair(resultSet);
                roomFairs.add(roomFair);
            }
            return roomFairs;
        }
    }

    @Override
    public void add(RoomFair roomFair) throws BookingDbException, SQLException {
        try (Connection connection = db.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO room_fair(value, season) values('" + roomFair.getValue() + "', '" + roomFair.getSeason() + "');");
            preparedStatement.executeUpdate();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT CURRVAL('room_fair_id')");
            resultSet.next();
            roomFair.setId(resultSet.getInt(1));
        }
    }

    private RoomFair mapResultSetToRoomFair(ResultSet resultSet) throws SQLException {
        RoomFair roomFair = new RoomFair();
        roomFair.setId(resultSet.getInt("id"));
        roomFair.setValue(resultSet.getDouble("value"));
        roomFair.setSeason(resultSet.getString("season"));
        return roomFair;
    }
}
