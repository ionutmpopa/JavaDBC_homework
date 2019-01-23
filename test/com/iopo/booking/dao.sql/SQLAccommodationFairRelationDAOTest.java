package com.iopo.booking.dao.sql;

import com.iopo.booking.db.BookingDbException;
import com.iopo.booking.db.TestBookingDb;
import com.iopo.booking.model.Accommodation;
import com.iopo.booking.model.AccommodationFairRelation;
import com.iopo.booking.model.RoomFair;
import org.junit.*;

import java.sql.SQLException;
import java.util.List;

public class SQLAccommodationFairRelationDAOTest {

    private TestBookingDb db;
    private SQLRoomFairDAO roomFairDAO;
    private SQLAccommodationDAO accommodationDAO;
    private SQLAccommodationFairRelationDAO accommodationFairRelationDAO;

    @BeforeClass
    public static void initTests() throws BookingDbException, SQLException {
        TestBookingDb.setUpTestDB();
    }

    @AfterClass
    public static void discardTests() throws BookingDbException, SQLException {
        TestBookingDb.dropTestDB();
    }

    @Before
    public void setUp() {
        db = new TestBookingDb();
        roomFairDAO = new SQLRoomFairDAO(db);
        accommodationDAO = new SQLAccommodationDAO(db);
        accommodationFairRelationDAO = new SQLAccommodationFairRelationDAO(db);
    }

    @After
    public void tearDown() throws BookingDbException, SQLException {
        db.dropDataFromTables();
    }


    @Test
    public void whenNewAccommodationFairRelationInsertedIntoDB_getReturnsThem() throws BookingDbException, SQLException {

        Accommodation accommodation1 = new Accommodation();
        accommodation1.setType("single room");
        accommodation1.setBedType("king-seize bed");
        accommodation1.setMaxGuests(2);
        accommodation1.setDescription("very cozy");

        Accommodation accommodation2 = new Accommodation();
        accommodation2.setType("triple room");
        accommodation2.setBedType("single bed");
        accommodation2.setMaxGuests(4);
        accommodation2.setDescription("not so cozy");

        accommodationDAO.add(accommodation1);
        accommodationDAO.add(accommodation2);

        RoomFair roomFair1 = new RoomFair();
        roomFair1.setValue(32.23);
        roomFair1.setSeason("Autumn");

        RoomFair roomFair2 = new RoomFair();
        roomFair2.setValue(52.55);
        roomFair2.setSeason("Summer");

        roomFairDAO.add(roomFair1);
        roomFairDAO.add(roomFair2);

        AccommodationFairRelation accommodationFairRelation1 = new AccommodationFairRelation();
        accommodationFairRelation1.setIdAccommodation(accommodation1.getId());
        accommodationFairRelation1.setIdRoomFair(roomFair1.getId());

        AccommodationFairRelation accommodationFairRelation2 = new AccommodationFairRelation();
        accommodationFairRelation2.setIdAccommodation(accommodation2.getId());
        accommodationFairRelation2.setIdRoomFair(roomFair2.getId());

        accommodationFairRelationDAO.add(accommodationFairRelation1);
        accommodationFairRelationDAO.add(accommodationFairRelation2);


        List<AccommodationFairRelation> all = accommodationFairRelationDAO.getAll();

        Assert.assertArrayEquals(new AccommodationFairRelation[]{accommodationFairRelation1, accommodationFairRelation2}, all.toArray());
    }
}
