package com.iopo.booking.dao;

import com.iopo.booking.db.BookingDbException;
import com.iopo.booking.model.AccommodationFairRelation;

import java.sql.SQLException;
import java.util.List;

public interface AccommodationFairRelationDAO {

    List<AccommodationFairRelation> getAll() throws Exception, BookingDbException;

    void add(AccommodationFairRelation accommodationFairRelation) throws BookingDbException, SQLException;
}
