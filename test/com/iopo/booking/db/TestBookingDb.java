package com.iopo.booking.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestBookingDb extends BookingDb {

     //Creates a connection to the PostgreSQL without selecting a database
    private Connection connectToPostgreSQL() throws SQLException, BookingDbException {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            DriverManager.setLoginTimeout(60);
            String url = new StringBuilder()
                    .append("jdbc:")
                    .append("postgresql")
                    .append("://")
                    .append("localhost")
                    .append(":")
                    .append(5432)
                    .append("/")
                    .append("?user=")
                    .append("postgres")
                    .append("&password=")
                    .append("Caragiale6").toString();
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new BookingDbException("Could not load DB driver.", e);
        }
    }

    @Override
    public Connection connect() throws BookingDbException, SQLException {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            DriverManager.setLoginTimeout(60);
            String url = new StringBuilder()
                    .append("jdbc:")
                    .append("postgresql")
                    .append("://")
                    .append("localhost")
                    .append(":")
                    .append(5432)
                    .append("/")
                    .append("booking_test")
                    .append("?user=")
                    .append("postgres")
                    .append("&password=")
                    .append("Caragiale6").toString();
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new BookingDbException("Could not load DB driver.", e);
        }
    }

    public static void setUpTestDB() throws BookingDbException, SQLException {
        TestBookingDb tdb = new TestBookingDb();
        try(Connection connection = tdb.connectToPostgreSQL()) {
            Statement statement = connection.createStatement();
            statement.execute("CREATE DATABASE booking_test;");
        }

        // connect to newly created tests database and create tables.
        try(Connection connection = tdb.connect()) {
            StringBuilder builder = new StringBuilder();
            builder.append("CREATE SEQUENCE accommodation_id;");
            builder.append("CREATE TABLE accommodation(id INT PRIMARY KEY DEFAULT NEXTVAL('accommodation_id'), type VARCHAR(32) NOT NULL, bed_type VARCHAR(32) NOT NULL, max_guests INT NOT NULL, description VARCHAR(512));");
            builder.append("CREATE SEQUENCE room_fair_id;");
            builder.append("CREATE TABLE room_fair(id INT PRIMARY KEY DEFAULT NEXTVAL('room_fair_id'), value NUMERIC(10,2), season VARCHAR(32));");
            builder.append("CREATE SEQUENCE accommodation_fair_id;");
            builder.append("CREATE TABLE accommodation_fair_relation(id INT PRIMARY KEY DEFAULT NEXTVAL('accommodation_fair_id'), id_accommodation INT REFERENCES accommodation(id), id_room_fair INT REFERENCES room_fair(id));");

            Statement statement = connection.createStatement();
            statement.execute(builder.toString());
        }
    }

    public static void dropTestDB() throws BookingDbException, SQLException {
        TestBookingDb tdb = new TestBookingDb();
        try(Connection connection = tdb.connectToPostgreSQL()) {
            Statement statement = connection.createStatement();
            statement.execute("DROP DATABASE booking_test;");
        }
    }

    public void dropDataFromTables() throws BookingDbException, SQLException {
        try(Connection connection = connect()){
            StringBuilder builder = new StringBuilder();
            builder.append("DELETE FROM accommodation_fair_relation;");
            builder.append("DELETE FROM accommodation;");
            builder.append("DELETE FROM room_fair;");

            Statement statement = connection.createStatement();
            statement.execute(builder.toString());
        }
    }
}
