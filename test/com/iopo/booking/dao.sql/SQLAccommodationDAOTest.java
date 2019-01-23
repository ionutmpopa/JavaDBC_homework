package com.iopo.booking.dao.sql;

import com.iopo.booking.db.BookingDbException;
import com.iopo.booking.db.TestBookingDb;
import com.iopo.booking.model.Accommodation;
import org.junit.*;

import java.sql.SQLException;
import java.util.List;

public class SQLAccommodationDAOTest {

    private TestBookingDb db;
    private SQLAccommodationDAO accommodationDAO;

    @BeforeClass
    public static void initTests() throws BookingDbException, SQLException {
        TestBookingDb.setUpTestDB();
    }

    @AfterClass
    public static void discardTests() throws BookingDbException, SQLException {
        TestBookingDb.dropTestDB();
    }

    @Before
    public void setUp(){
        db = new TestBookingDb();
        accommodationDAO = new SQLAccommodationDAO(db);
    }

    @After
    public void tearDown() throws BookingDbException, SQLException {
        db.dropDataFromTables();
    }


    @Test
    public void whenNewAccommodationInsertedIntoDB_getReturnsThem() throws BookingDbException, SQLException {
        Accommodation accommodation1 = new Accommodation();
        accommodation1.setType("single room");
        accommodation1.setBedType("king-seize bed");
        accommodation1.setMaxGuests(2);
        accommodation1.setDescription("very cozy");

        Accommodation accommodation2 = new Accommodation();
        accommodation2.setType("double room");
        accommodation2.setBedType("single bed");
        accommodation2.setMaxGuests(4);
        accommodation2.setDescription("not so cozy");

        accommodationDAO.add(accommodation1);
        accommodationDAO.add(accommodation2);

        List<Accommodation> all = accommodationDAO.getAll();

        Assert.assertArrayEquals(new Accommodation[]{accommodation1, accommodation2}, all.toArray());
    }
}
