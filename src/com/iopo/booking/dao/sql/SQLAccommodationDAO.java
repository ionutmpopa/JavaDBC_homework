package com.iopo.booking.dao.sql;

import com.iopo.booking.dao.AccommodationDAO;
import com.iopo.booking.db.BookingDb;
import com.iopo.booking.db.BookingDbException;
import com.iopo.booking.model.Accommodation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLAccommodationDAO implements AccommodationDAO {

    private BookingDb db;

    public SQLAccommodationDAO(BookingDb db) {
        this.db = db;
    }

    @Override
    public List<Accommodation> getAll() throws BookingDbException, SQLException {
        try (Connection conn = db.connect()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from accommodation;");
            ArrayList<Accommodation> accommodations = new ArrayList<>();
            while (resultSet.next()) {
                Accommodation accommodation = mapResultSetToAccommodation(resultSet);
                accommodations.add(accommodation);
            }
            return accommodations;
        }
    }

    @Override
    public void add(Accommodation accommodation) throws BookingDbException, SQLException {
        try (Connection connection = db.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO accommodation(type, bed_type, max_guests, description) values('" + accommodation.getType() + "', '" + accommodation.getBedType() + "', '" + accommodation.getMaxGuests() + "', '" + accommodation.getDescription() + "');");
            preparedStatement.executeUpdate();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT CURRVAL('accommodation_id')");
            resultSet.next();
            accommodation.setId(resultSet.getInt(1));
        }
    }

    private Accommodation mapResultSetToAccommodation(ResultSet resultSet) throws SQLException {
        Accommodation accommodation = new Accommodation();
        accommodation.setId(resultSet.getInt("id"));
        accommodation.setType(resultSet.getString("type"));
        accommodation.setBedType(resultSet.getString("bed_type"));
        accommodation.setMaxGuests(resultSet.getInt("max_guests"));
        accommodation.setDescription(resultSet.getString("description"));
        return accommodation;
    }

}
