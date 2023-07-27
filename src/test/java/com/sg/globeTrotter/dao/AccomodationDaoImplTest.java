/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.sg.globeTrotter.dao;

import com.sg.globeTrotter.dto.Accomodation;
import com.sg.globeTrotter.dto.Trip;
import java.time.LocalDate;
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
public class AccomodationDaoImplTest {

    @Autowired
    TripDao tripDao;

    @Autowired
    AccomodationDao accomodationDao;

    public AccomodationDaoImplTest() {
    }

    @BeforeEach
    public void setUp() {
        List<Trip> trips = tripDao.getAllTrips();
        trips.forEach(trip -> {
            tripDao.deleteTripByID(trip.getId());
        });

        List<Accomodation> accomodations = accomodationDao.getAllAccomodations();
        accomodations.forEach(accomodation -> {
            accomodationDao.deleteAccomodationByID(accomodation.getId());
        });
    }

    @AfterEach
    public void tearDown() {

        List<Trip> trips = tripDao.getAllTrips();
        trips.forEach(trip -> {
            tripDao.deleteTripByID(trip.getId());
        });

        List<Accomodation> accomodations = accomodationDao.getAllAccomodations();
        accomodations.forEach(accomodation -> {
            accomodationDao.deleteAccomodationByID(accomodation.getId());
        });
    }

    @Test
    public void testAddAndGetAccomodationByID() {

        Trip trip = new Trip();
        trip.setTitle("YOLO");
        trip.setDescription("Summer BucketList");
        trip.setType("staycation");
        LocalDate dateStart = LocalDate.now();
        LocalDate dateEnd = LocalDate.now().plusDays(3);

        trip.setStartDate(dateStart);
        trip.setEndDate(dateEnd);
        trip.setCompleted(false);

        Trip addedTrip = tripDao.addTrip(trip);

        Accomodation accomodation = new Accomodation();
        accomodation.setTrip(trip);
        accomodation.setType("airbnb");
        accomodation.setName("little house on the prairie");
        accomodation.setDescription("summer house vacay");
        Accomodation added = accomodationDao.addAccomodation(accomodation);
        Accomodation fromDao = accomodationDao.getAccomodationByID(added.getId());
        assertEquals(fromDao.getDescription(), added.getDescription());
    }

    @Test
    public void testGetAllAccomodations() {

        Trip trip = new Trip();
        trip.setTitle("Sweet Summer");
        trip.setDescription("Foodies united");
        trip.setType("culinary");
        LocalDate dateStart2 = LocalDate.now();
        LocalDate dateEnd2 = LocalDate.now().plusDays(3);

        trip.setStartDate(dateStart2);
        trip.setEndDate(dateEnd2);
        trip.setCompleted(false);
        Trip addedTrip = tripDao.addTrip(trip);

        Accomodation accomodation = new Accomodation();
        accomodation.setTrip(addedTrip);
        accomodation.setType("airbnb");
        accomodation.setName("little house on the prairie");
        accomodation.setDescription("summer house vacay");
        Accomodation addedAccomodation = accomodationDao.addAccomodation(accomodation);

        Accomodation accomodation2 = new Accomodation();
        accomodation2.setTrip(addedTrip);
        accomodation2.setType("hotel");
        accomodation2.setName("Big house on the prairie");
        accomodation2.setDescription("Fall house vacay");
        Accomodation addedAccomodation2 = accomodationDao.addAccomodation(accomodation2);

        List<Accomodation> accomodations = accomodationDao.getAllAccomodations();

        assertEquals(2, accomodations.size());

    }

    @Test
    public void testUpdateTrip() {
        Trip trip = new Trip();
        trip.setTitle("Japan tour 202X");
        trip.setDescription("Discovering the ancient cultural wonders!");
        trip.setType("cultural");
        LocalDate dateStart = LocalDate.now();
        LocalDate dateEnd = LocalDate.now().plusDays(3);

        trip.setStartDate(dateStart);
        trip.setEndDate(dateEnd);
        trip.setCompleted(false);
        Trip addedTrip = tripDao.addTrip(trip);

        Accomodation accomodation = new Accomodation();
        accomodation.setTrip(addedTrip);
        accomodation.setType("airbnb");
        accomodation.setName("Kyoto hotel");
        accomodation.setDescription("checking in early");
        Accomodation addedAccomodation = accomodationDao.addAccomodation(accomodation);

        Accomodation fromDao = accomodationDao.getAccomodationByID(addedAccomodation.getId());
        fromDao.setDescription("checking in late");
        accomodationDao.updateAccomodation(fromDao);
        assertNotEquals(addedTrip, fromDao);

        Accomodation updated = accomodationDao.getAccomodationByID(fromDao.getId());
        assertEquals(fromDao, updated);
    }

    @Test
    public void testDeleteTripByID() {

        Trip trip = new Trip();
        trip.setTitle("Honeymoon on the moon");
        trip.setDescription("Space the final frontier");
        trip.setType("adventure");
        LocalDate dateStart = LocalDate.now();
        LocalDate dateEnd = LocalDate.now().plusDays(3);

        trip.setStartDate(dateStart);
        trip.setEndDate(dateEnd);
        trip.setCompleted(true);
        Trip addedTrip = tripDao.addTrip(trip);

        Accomodation accomodation = new Accomodation();
        accomodation.setTrip(addedTrip);
        accomodation.setType("hotel");
        accomodation.setName("ISS");
        accomodation.setDescription("heading to mars the next day");
        Accomodation addedAccomodation = accomodationDao.addAccomodation(accomodation);
        accomodationDao.deleteAccomodationByID(addedAccomodation.getId());

        Accomodation deleted = accomodationDao.getAccomodationByID(addedAccomodation.getId());
        assertNull(deleted);
    }

}
