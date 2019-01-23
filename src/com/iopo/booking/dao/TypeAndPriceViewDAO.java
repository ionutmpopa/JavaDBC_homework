package com.iopo.booking.dao;

import com.iopo.booking.db.BookingDbException;
import com.iopo.booking.model.TypeAndPriceView;

import java.sql.SQLException;
import java.util.List;

public interface TypeAndPriceViewDAO {

    List<TypeAndPriceView> showAccommodationPrices() throws BookingDbException, SQLException;
}
