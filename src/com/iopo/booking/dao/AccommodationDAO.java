package com.iopo.booking.dao;

import com.iopo.booking.db.BookingDbException;
import com.iopo.booking.model.Accommodation;

import java.sql.SQLException;
import java.util.List;

public interface AccommodationDAO {

    List<Accommodation> getAll() throws Exception, BookingDbException;

    void add(Accommodation accommodation) throws BookingDbException, SQLException;
}
