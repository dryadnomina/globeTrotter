/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.sg.globeTrotter.dao;

import com.sg.globeTrotter.dto.Traveller;
import com.sg.globeTrotter.dto.Trip;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author marya
 */
@SpringBootTest
public class TripDaoImplTest {

    @Autowired
    TripDao tripDao;

    @Autowired
    TravellerDao travellerDao;

    public TripDaoImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        List<Trip> trips = tripDao.getAllTrips();
        trips.forEach(trip -> {
            tripDao.deleteTripByID(trip.getId());
        });
    }

    @AfterEach
    public void tearDown() {

        List<Trip> trips = tripDao.getAllTrips();
        trips.forEach(trip -> {
            tripDao.deleteTripByID(trip.getId());
        });
    }

    @Test
    public void testAddAndGetTripByID() {

        Trip trip = new Trip();
        trip.setTitle("YOLO");
        trip.setDescription("Summer BucketList");
        trip.setType("staycation");
        LocalDate dateStart = LocalDate.now();
        LocalDate dateEnd = LocalDate.now().plusDays(2);

        trip.setStartDate(dateStart);
        trip.setEndDate(dateEnd);
        

        Trip addedTrip = tripDao.addTrip(trip);

        Trip added = tripDao.getTripByID(addedTrip.getId());
        assertEquals(addedTrip, added);
    }

    @Test
    public void testGetAllTrips() {

        Trip trip = new Trip();
        trip.setTitle("Hot girl summer");
        trip.setDescription("Chillin with the girlies");
        trip.setType("beach");
        LocalDate dateStart = LocalDate.now();
        LocalDate dateEnd = LocalDate.now().plusDays(2);

        trip.setStartDate(dateStart);
        trip.setEndDate(dateEnd);
        
        Trip addedTrip1 = tripDao.addTrip(trip);

        Trip trip2 = new Trip();
        trip2.setTitle("Sweet Summer");
        trip2.setDescription("Foodies united");
        trip2.setType("culinary");
        LocalDate dateStart2 = LocalDate.now();
        LocalDate dateEnd2 = LocalDate.now().plusDays(3);

        trip2.setStartDate(dateStart2);
        trip2.setEndDate(dateEnd2);
       
        Trip addedTrip2 = tripDao.addTrip(trip2);

        List<Trip> trips = tripDao.getAllTrips();

        assertEquals(2, trips.size());
        assertTrue(trips.contains(addedTrip1));
        assertTrue(trips.contains(addedTrip2));
    }

    @Test
    public void testUpdateTrip() {
        Trip trip = new Trip();
        trip.setTitle("Japan tour 202X");
        trip.setDescription("Discovering the ancient cultural wonders!");
        LocalDate dateStart = LocalDate.now();
        LocalDate dateEnd = LocalDate.now().plusDays(2);

        trip.setStartDate(dateStart);
        trip.setEndDate(dateEnd);
        trip.setType("cultural");
        
        Trip addedTrip = tripDao.addTrip(trip);

        Trip fromDao = tripDao.getTripByID(addedTrip.getId());
        assertEquals(addedTrip, fromDao);

        fromDao.setDescription("Discovering the recent cultural wonders!");
        tripDao.updateTrip(fromDao);
        assertNotEquals(addedTrip, fromDao);

        Trip updated = tripDao.getTripByID(trip.getId());
        assertEquals(fromDao, updated);
    }

    @Test
    public void testDeleteTripByID() {

        Trip trip = new Trip();
        trip.setTitle("Honeymoon on the moon");
        trip.setDescription("Space the final frontier");
        trip.setType("adventure");
        

        LocalDate dateStart = LocalDate.now();
        LocalDate dateEnd = LocalDate.now().plusDays(2);

        trip.setStartDate(dateStart);
        trip.setEndDate(dateEnd);
        Trip addedTrip = tripDao.addTrip(trip);

        tripDao.deleteTripByID(addedTrip.getId());

        Trip deleted = tripDao.getTripByID(addedTrip.getId());
        assertNull(deleted);
    }
}
