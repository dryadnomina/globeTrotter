/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.sg.globeTrotter.dao;

import com.sg.globeTrotter.dto.ActivityDetail;
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
public class ActivityDetailDaoImplTest {

    @Autowired
    ActivityDao activityDao;

    @Autowired
    ActivityDetailDao detailDao;

    @Autowired
    TripDao tripDao;

    public ActivityDetailDaoImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        List<Activity> activities = activityDao.getAllActivities();
        activities.forEach(activity -> {
            activityDao.deleteActivityByID(activity.getId());
        });
        List<ActivityDetail> details = detailDao.getAllActivityDetails();
        details.forEach(traveller -> {
            detailDao.deleteActivityDetailByID(traveller.getId());
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
        List<ActivityDetail> details = detailDao.getAllActivityDetails();
        details.forEach(traveller -> {
            detailDao.deleteActivityDetailByID(traveller.getId());
        });
        List<Trip> trips = tripDao.getAllTrips();
        trips.forEach(trip -> {
            tripDao.deleteTripByID(trip.getId());
        });
    }

    @Test
    public void testAddAndGetActivityDetailByID() {
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
        //add trip to database
        Trip addedTrip = tripDao.addTrip(trip);
        //create new activity
        Activity activity = new Activity();
        activity.setName("Meetup at gare centrale");
        activity.setTripId(addedTrip.getId());
        //add activity to db
        Activity addedActivity = activityDao.addActivity(activity);

        //create new activity detail
        ActivityDetail detail = new ActivityDetail();
        detail.setDescription("meet up with the girls");
        detail.setAddress("895 Rue De la Gauchetière O Montréal, QC H3B 4G1");
        //set activity object in detail
        detail.setActivityId(addedActivity.getId());
        //add detail to db
        ActivityDetail addedActivityDetail = detailDao.addActivityDetail(detail);

        ActivityDetail fromDao = detailDao.getActivityDetailByID(addedActivityDetail.getId());
        assertEquals(addedActivityDetail, fromDao);
    }

    @Test
    public void testGetAllActivityDetails() {

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

        ActivityDetail detail = new ActivityDetail();
        detail.setDescription("Supercool location");
        detail.setAddress("100 super cool street");
        detail.setActivityId(addedActivity.getId());
        ActivityDetail addedDetail1 = detailDao.addActivityDetail(detail);

        ActivityDetail detail2 = new ActivityDetail();
        detail2.setDescription("Superlame location");
        detail2.setAddress("100 super lame street");
        detail2.setActivityId(addedActivity.getId());
        ActivityDetail addedDetail2 = detailDao.addActivityDetail(detail2);

        List<ActivityDetail> details = detailDao.getAllActivityDetails();

        assertEquals(2, details.size());

    }
//

    @Test
    public void testUpdateActivityDetail() {

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

        ActivityDetail detail = new ActivityDetail();
        detail.setDescription("Supercool location");
        detail.setAddress("100 super cool street");
        detail.setActivityId(addedActivity.getId());
        ActivityDetail addedDetail = detailDao.addActivityDetail(detail);

        ActivityDetail fromDao = detailDao.getActivityDetailByID(addedDetail.getId());
        assertEquals(addedDetail.getActivityId(), fromDao.getActivityId());

        fromDao.setAddress("super lame address");
        detailDao.updateActivityDetail(fromDao);
        assertNotEquals(addedDetail.getAddress(), fromDao.getAddress());

        ActivityDetail updated = detailDao.getActivityDetailByID(fromDao.getId());
        assertEquals(fromDao.getDescription(), updated.getDescription());
    }

    @Test
    public void testDeleteActivityDetailByID() {

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

        ActivityDetail detail = new ActivityDetail();
        detail.setDescription("Supercool location");
        detail.setAddress("100 super cool street");
        detail.setActivityId(addedActivity.getId());
        ActivityDetail addedDetail = detailDao.addActivityDetail(detail);

        detailDao.deleteActivityDetailByID(addedDetail.getId());

        ActivityDetail deleted = detailDao.getActivityDetailByID(addedDetail.getId());
        assertNull(deleted);
    }
}
