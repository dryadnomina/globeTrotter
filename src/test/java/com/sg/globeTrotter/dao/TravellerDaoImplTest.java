/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.sg.globeTrotter.dao;

import com.sg.globeTrotter.dto.Traveller;
import com.sg.globeTrotter.dto.Trip;
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
public class TravellerDaoImplTest {

    @Autowired
    TripDao tripDao;

    @Autowired
    TravellerDao travellerDao;

    public TravellerDaoImplTest() {
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
        List<Traveller> travellers = travellerDao.getAllTravellers();
        travellers.forEach(traveller -> {
            travellerDao.deleteTravellerByID(traveller.getId());
        });
    }

    @AfterEach
    public void tearDown() {
        List<Trip> trips = tripDao.getAllTrips();
        trips.forEach(trip -> {
            tripDao.deleteTripByID(trip.getId());
        });

        List<Traveller> travellers = travellerDao.getAllTravellers();
        travellers.forEach(traveller -> {
            travellerDao.deleteTravellerByID(traveller.getId());
        });
    }

    @Test
    public void testAddAndGetTravellerByID() {

        Trip trip = new Trip();
        trip.setTitle("YOLO");
        trip.setDescription("Summer BucketList");
        trip.setType("staycation");
       

        Traveller traveller = new Traveller();
        traveller.setFirstName("Santa");
        traveller.setLastName("Claus");
        traveller.setCity("North pole");
        traveller.setPhoneNumber("1234567899");
        traveller.setPostalCode("HOHOHO");
        Traveller addedTraveller = travellerDao.addTraveller(traveller);

        Traveller added = travellerDao.getTravellerByID(addedTraveller.getId());
        assertEquals(addedTraveller, added);
    }

    @Test
    public void testGetAllTravellers() {

        Traveller traveller = new Traveller();
        traveller.setFirstName("Bart");
        traveller.setLastName("Bimpson");
        traveller.setCity("Toronto");
        traveller.setPhoneNumber("1234567899");
        traveller.setPostalCode("123h05");
        Traveller addedTraveller1 = travellerDao.addTraveller(traveller);

        Traveller traveller2 = new Traveller();
        traveller2.setFirstName("Lisa");
        traveller2.setLastName("Bimpson");
        traveller2.setCity("Toronto");
        traveller2.setPhoneNumber("1234567899");
        traveller2.setPostalCode("123h05");
        Traveller addedTraveller2 = travellerDao.addTraveller(traveller);

        List<Traveller> travellers = travellerDao.getAllTravellers();

        assertEquals(2, travellers.size());
        assertTrue(travellers.contains(addedTraveller1));
        assertTrue(travellers.contains(addedTraveller2));
    }

    @Test
    public void testUpdateTraveller() {
        Traveller traveller = new Traveller();
        traveller.setFirstName("Marge");
        traveller.setLastName("Bimpson");
        traveller.setCity("Toronto");
        traveller.setPhoneNumber("1234567899");
        traveller.setPostalCode("123h05");
        Traveller addedTraveller = travellerDao.addTraveller(traveller);

        Traveller fromDao = travellerDao.getTravellerByID(addedTraveller.getId());
        assertEquals(addedTraveller, fromDao);

        fromDao.setLastName("Sampson");
        travellerDao.updateTraveller(fromDao);
        assertNotEquals(addedTraveller, fromDao);

        Traveller updated = travellerDao.getTravellerByID(fromDao.getId());
        assertEquals(fromDao, updated);
    }

    @Test
    public void testDeleteTravellerByID() {

        Traveller traveller = new Traveller();
        traveller.setFirstName("Homer");
        traveller.setLastName("Bimpson");
        traveller.setCity("Toronto");
        traveller.setPhoneNumber("1234567899");
        traveller.setPostalCode("123h05");
        Traveller addedTraveller = travellerDao.addTraveller(traveller);

        travellerDao.deleteTravellerByID(addedTraveller.getId());

        Traveller deleted = travellerDao.getTravellerByID(addedTraveller.getId());
        assertNull(deleted);
    }

}
