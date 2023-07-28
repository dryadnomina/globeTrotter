/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.sg.globeTrotter.dao;

import com.sg.globeTrotter.dto.Activity;
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
public class ActivityDaoImplTest {

    public ActivityDaoImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @Autowired
    ActivityDao activityDao;

    @Autowired
    TripDao tripDao;

    @BeforeEach
    public void setUp() {
        List<Activity> activities = activityDao.getAllActivities();
        activities.forEach(activity -> {
            activityDao.deleteActivityByID(activity.getId());
        });

        List<Trip> trips = tripDao.getAllTrips();
        trips.forEach(trip -> {
            tripDao.deleteTripByID(trip.getId());
        });
    }

    @AfterEach
    public void tearDown() {
        List<Activity> activities = activityDao.getAllActivities();
        activities.forEach(activity -> {
            activityDao.deleteActivityByID(activity.getId());
        });

        List<Trip> trips = tripDao.getAllTrips();
        trips.forEach(trip -> {
            tripDao.deleteTripByID(trip.getId());
        });
    }

    @Test
    public void testAddAndGetActivityByID() {
        //create trip
        Trip trip = new Trip();
        trip.setTitle("Hot girl summer");
        trip.setDescription("Chillin with the girlies");
        trip.setType("beach");
        LocalDate dateStart = LocalDate.now();
        LocalDate dateEnd = LocalDate.now().plusDays(3);

        trip.setStartDate(dateStart);
        trip.setEndDate(dateEnd);
        trip.setCompleted(false);

        //add trip to db
        Trip addedTrip = tripDao.addTrip(trip);

        //create new activity and add trip to activity
        Activity activity = new Activity();
        activity.setName("Meetup at gare centrale");
        activity.setTripId(addedTrip.getId());

        //add activity to db
        Activity added = activityDao.addActivity(activity);
        //get activity from dao
        Activity fromDao = activityDao.getActivityByID(added.getId());
        assertEquals(added, fromDao);
    }

    @Test
    public void testGetAllActivities() {

        Trip trip = new Trip();
        trip.setTitle("Hot girl summer");
        trip.setDescription("Chillin with the girlies");
        trip.setType("beach");
        LocalDate dateStart = LocalDate.now();
        LocalDate dateEnd = LocalDate.now().plusDays(3);

        trip.setStartDate(dateStart);
        trip.setEndDate(dateEnd);
        trip.setCompleted(false);
        Trip addedTrip = tripDao.addTrip(trip);

        Activity activity = new Activity();
        activity.setName("Get ice cream at the Pier");
        activity.setTripId(addedTrip.getId());
        Activity addedActivity1 = activityDao.addActivity(activity);

        Activity activity2 = new Activity();
        activity2.setName("Surf on the beach");
        activity2.setTripId(addedTrip.getId());
        Activity addedActivity2 = activityDao.addActivity(activity2);

        List<Activity> activities = activityDao.getAllActivities();

        assertEquals(2, activities.size());
        assertTrue(activities.contains(addedActivity1));
        assertTrue(activities.contains(addedActivity2));
    }

    @Test
    public void testUpdateActivity() {

        Trip trip = new Trip();
        trip.setTitle("Hot girl summer");
        trip.setDescription("Chillin with the girlies");
        trip.setType("beach");
        LocalDate dateStart = LocalDate.now();
        LocalDate dateEnd = LocalDate.now().plusDays(3);

        trip.setStartDate(dateStart);
        trip.setEndDate(dateEnd);
        trip.setCompleted(false);
        Trip addedTrip = tripDao.addTrip(trip);

        Activity activity = new Activity();
        activity.setName("Brunch and mimosa party on queen Street");
        activity.setTripId(addedTrip.getId());
        Activity addedActivity = activityDao.addActivity(activity);

        Activity fromDao = activityDao.getActivityByID(addedActivity.getId());
        assertEquals(addedActivity, fromDao);

        fromDao.setName("Dinner on elm street");
        activityDao.updateActivity(fromDao);
        assertNotEquals(addedActivity, fromDao);

        Activity updated = activityDao.getActivityByID(fromDao.getId());
        assertEquals(fromDao, updated);
    }

    @Test
    public void testDeleteActivityByID() {

        Trip trip = new Trip();
        trip.setTitle("Bummer summer");
        trip.setDescription("summertime sadness");
        trip.setType("staycation");
        LocalDate dateStart = LocalDate.now();
        LocalDate dateEnd = LocalDate.now().plusDays(3);

        trip.setStartDate(dateStart);
        trip.setEndDate(dateEnd);
        trip.setCompleted(true);
        Trip addedTrip = tripDao.addTrip(trip);

        Activity activity = new Activity();
        activity.setName("Netflix party");
        activity.setTripId(addedTrip.getId());
        Activity addedActivity = activityDao.addActivity(activity);

        activityDao.deleteActivityByID(addedActivity.getId());

        Activity deleted = activityDao.getActivityByID(addedActivity.getId());
        assertNull(deleted);
    }
}
