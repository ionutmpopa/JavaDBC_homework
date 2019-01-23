package com.iopo.booking.dao;

import com.iopo.booking.db.BookingDbException;
import com.iopo.booking.model.RoomFair;

import java.sql.SQLException;
import java.util.List;

public interface RoomFairDAO {


    List<RoomFair> getAll() throws Exception, BookingDbException;

    void add(RoomFair roomFair) throws BookingDbException, SQLException;

}
