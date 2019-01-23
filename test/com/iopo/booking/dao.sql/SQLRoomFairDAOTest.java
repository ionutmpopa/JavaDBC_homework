package com.iopo.booking.dao.sql;

import com.iopo.booking.db.BookingDbException;
import com.iopo.booking.db.TestBookingDb;
import com.iopo.booking.model.RoomFair;
import org.junit.*;

import java.sql.SQLException;
import java.util.List;

public class SQLRoomFairDAOTest {

    private TestBookingDb db;
    private SQLRoomFairDAO roomFairDAO;

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
    }

    @After
    public void tearDown() throws BookingDbException, SQLException {
        db.dropDataFromTables();
    }


    @Test
    public void whenNewRoomFairInsertedIntoDB_getReturnsThem() throws BookingDbException, SQLException {

        RoomFair roomFair1 = new RoomFair();
        roomFair1.setValue(32.23);
        roomFair1.setSeason("Autumn");

        RoomFair roomFair2 = new RoomFair();
        roomFair2.setValue(52.55);
        roomFair2.setSeason("Summer");

        RoomFair roomFair3 = new RoomFair();
        roomFair3.setValue(41.15);
        roomFair3.setSeason("Winter");

        roomFairDAO.add(roomFair1);
        roomFairDAO.add(roomFair2);
        roomFairDAO.add(roomFair3);


        List<RoomFair> all = roomFairDAO.getAll();

        Assert.assertArrayEquals(new RoomFair[]{roomFair1, roomFair2, roomFair3}, all.toArray());
    }
}
