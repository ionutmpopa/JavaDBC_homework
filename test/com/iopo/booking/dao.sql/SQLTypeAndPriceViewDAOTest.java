package com.iopo.booking.dao.sql;

import com.iopo.booking.db.BookingDbException;
import com.iopo.booking.db.TestBookingDb;
import com.iopo.booking.model.Accommodation;
import com.iopo.booking.model.AccommodationFairRelation;
import com.iopo.booking.model.RoomFair;
import com.iopo.booking.model.TypeAndPriceView;
import org.junit.*;

import java.sql.SQLException;
import java.util.List;

public class SQLTypeAndPriceViewDAOTest {

    private TestBookingDb db;
    private SQLRoomFairDAO roomFairDAO;
    private SQLAccommodationDAO accommodationDAO;
    private SQLAccommodationFairRelationDAO accommodationFairRelationDAO;
    private SQLTypeAndPriceViewDAO typeAndPriceViewDAO;

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
        roomFairDAO = new SQLRoomFairDAO(db);
        accommodationDAO = new SQLAccommodationDAO(db);
        accommodationFairRelationDAO = new SQLAccommodationFairRelationDAO(db);
        typeAndPriceViewDAO = new SQLTypeAndPriceViewDAO(db);
    }

    @After
    public void tearDown() throws BookingDbException, SQLException {
        db.dropDataFromTables();
    }

    @Test
    public void whenAccommodationAndRoomFairAdded_showRoomTypeAndPrice() throws BookingDbException, SQLException {

        Accommodation accommodation1 = new Accommodation();
        accommodation1.setType("apartment");
        accommodation1.setBedType("twin beds");
        accommodation1.setMaxGuests(6);
        accommodation1.setDescription("fairly cozy");
        accommodationDAO.add(accommodation1);

        RoomFair roomFair1 = new RoomFair();
        roomFair1.setValue(85.44);
        roomFair1.setSeason("Summer");
        roomFairDAO.add(roomFair1);

        AccommodationFairRelation accommodationFairRelation1 = new AccommodationFairRelation();
        accommodationFairRelation1.setIdAccommodation(accommodation1.getId());
        accommodationFairRelation1.setIdRoomFair(roomFair1.getId());
        accommodationFairRelationDAO.add(accommodationFairRelation1);

        Accommodation accommodation2 = new Accommodation();
        accommodation2.setType("executive suite");
        accommodation2.setBedType("twin beds");
        accommodation2.setMaxGuests(4);
        accommodation2.setDescription("extremely cozy");
        accommodationDAO.add(accommodation2);

        RoomFair roomFair2 = new RoomFair();
        roomFair2.setValue(99.13);
        roomFair2.setSeason("All seasons");
        roomFairDAO.add(roomFair2);

        AccommodationFairRelation accommodationFairRelation2 = new AccommodationFairRelation();
        accommodationFairRelation2.setIdAccommodation(accommodation2.getId());
        accommodationFairRelation2.setIdRoomFair(roomFair2.getId());
        accommodationFairRelationDAO.add(accommodationFairRelation2);

        TypeAndPriceView typeAndPriceView1 = new TypeAndPriceView();
        typeAndPriceView1.setType(accommodation1.getType());
        typeAndPriceView1.setValue(roomFair1.getValue());

        TypeAndPriceView typeAndPriceView2 = new TypeAndPriceView();
        typeAndPriceView2.setType(accommodation2.getType());
        typeAndPriceView2.setValue(roomFair2.getValue());


        List<TypeAndPriceView> roomPrices = typeAndPriceViewDAO.showAccommodationPrices();

        Assert.assertArrayEquals(new TypeAndPriceView[]{typeAndPriceView1, typeAndPriceView2}, roomPrices.toArray());

        roomPrices.forEach(s -> System.out.println(s));
    }

}
