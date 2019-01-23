package com.iopo.booking.dao.sql;

import com.iopo.booking.dao.AccommodationFairRelationDAO;
import com.iopo.booking.db.BookingDb;
import com.iopo.booking.db.BookingDbException;
import com.iopo.booking.model.AccommodationFairRelation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLAccommodationFairRelationDAO implements AccommodationFairRelationDAO {

    private BookingDb db;

    public SQLAccommodationFairRelationDAO(BookingDb db) {
        this.db = db;
    }

    @Override
    public List<AccommodationFairRelation> getAll() throws BookingDbException, SQLException {
        try (Connection conn = db.connect()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from accommodation_fair_relation;");
            ArrayList<AccommodationFairRelation> accommodationFairRelations = new ArrayList<>();
            while (resultSet.next()) {
                AccommodationFairRelation accommodationFairRelation = mapResultSetToAccommodationFairRelation(resultSet);
                accommodationFairRelations.add(accommodationFairRelation);
            }
            return accommodationFairRelations;
        }
    }

    @Override
    public void add(AccommodationFairRelation accommodationFairRelation) throws BookingDbException, SQLException {
        try (Connection connection = db.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO accommodation_fair_relation(id_accommodation, id_room_fair) values('" + accommodationFairRelation.getIdAccommodation() + "', '" + accommodationFairRelation.getIdRoomFair() + "');");
            preparedStatement.executeUpdate();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT CURRVAL('accommodation_fair_id')");
            resultSet.next();
            accommodationFairRelation.setId(resultSet.getInt(1));
        }
    }

    private AccommodationFairRelation mapResultSetToAccommodationFairRelation(ResultSet resultSet) throws SQLException {
        AccommodationFairRelation accommodationFairRelation = new AccommodationFairRelation();
        accommodationFairRelation.setId(resultSet.getInt("id"));
        accommodationFairRelation.setIdAccommodation(resultSet.getInt("id_accommodation"));
        accommodationFairRelation.setIdRoomFair(resultSet.getInt("id_room_fair"));
        return accommodationFairRelation;
    }
}
