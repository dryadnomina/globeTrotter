/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.sg.globeTrotter.dao;

import com.sg.globeTrotter.dto.Budget;
import com.sg.globeTrotter.dto.Activity;
import com.sg.globeTrotter.dto.Trip;
import java.math.BigDecimal;
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
public class BudgetDaoImplTest {

    @Autowired
    ActivityDao activityDao;

    @Autowired
    BudgetDao budgetDao;

    @Autowired
    TripDao tripDao;

    public BudgetDaoImplTest() {
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
        List<Budget> budgets = budgetDao.getAllBudgets();
        budgets.forEach(traveller -> {
            budgetDao.deleteBudgetByID(traveller.getId());
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
        List<Budget> budgets = budgetDao.getAllBudgets();
        budgets.forEach(traveller -> {
            budgetDao.deleteBudgetByID(traveller.getId());
        });
        List<Trip> trips = tripDao.getAllTrips();
        trips.forEach(trip -> {
            tripDao.deleteTripByID(trip.getId());
        });
    }

    @Test
    public void testAddAndGetBudgetByID() {
        //create trip
        Trip trip = new Trip();
        trip.setTitle("Hot girl summer");
        trip.setDescription("Chillin with the girlies");
        trip.setType("beach");
        LocalDate dateStart = LocalDate.now();
        LocalDate dateEnd = LocalDate.now().plusDays(3);

        trip.setStartDate(dateStart);
        trip.setEndDate(dateEnd);

        //add trip to database
        Trip addedTrip = tripDao.addTrip(trip);
        //create new activity
        Activity activity = new Activity();
        activity.setName("Meetup at gare centrale");
        activity.setTrip(addedTrip);
        activity.setAddress("895 rue de la gauchietere o");
        activity.setDescription("bring your tickets");
        //add activity to db
        Activity addedActivity = activityDao.addActivity(activity);

        //create new activity budget
        Budget budget = new Budget();
        budget.setName("cool budget");
        budget.setAccomodationCost(new BigDecimal("10.00"));
        budget.setFoodCost(new BigDecimal("15.00"));
        budget.setTransportationCost(new BigDecimal("10.00"));
        budget.setActivityCost(new BigDecimal("15.00"));

        budget.setTrip(addedTrip);
        //add budget to db
        Budget addedBudget = budgetDao.addBudget(budget);

        Budget fromDao = budgetDao.getBudgetByID(addedBudget.getId());
        assertTrue(fromDao.getAccomodationCost().equals(fromDao.getAccomodationCost()));
    }

    @Test
    public void testGetAllBudgets() {

        Trip trip = new Trip();
        trip.setTitle("Bummer summer");
        trip.setDescription("summertime sadness");
        trip.setType("staycation");
        LocalDate dateStart = LocalDate.now();
        LocalDate dateEnd = LocalDate.now().plusDays(3);

        trip.setStartDate(dateStart);
        trip.setEndDate(dateEnd);

        Trip addedTrip = tripDao.addTrip(trip);

        Activity activity = new Activity();
        activity.setName("Netflix party");
        activity.setTrip(addedTrip);
        activity.setAddress("home");
        activity.setDescription("bring your jammies");
        Activity addedActivity = activityDao.addActivity(activity);

        Budget budget = new Budget();
        budget.setName("cool budget");
        budget.setAccomodationCost(new BigDecimal("10.00"));
        budget.setFoodCost(new BigDecimal("15.00"));
        budget.setTransportationCost(new BigDecimal("10.00"));
        budget.setActivityCost(new BigDecimal("15.00"));

        budget.setTrip(addedTrip);

        budgetDao.addBudget(budget);
        Budget budget2 = new Budget();
        budget2.setName("fire budget");
        budget2.setAccomodationCost(new BigDecimal("10.00"));
        budget2.setFoodCost(new BigDecimal("15.00"));
        budget2.setTransportationCost(new BigDecimal("10.00"));
        budget2.setActivityCost(new BigDecimal("15.00"));

        budget2.setTrip(addedTrip);
        budgetDao.addBudget(budget2);

        List<Budget> budgets = budgetDao.getAllBudgets();

        assertEquals(2, budgets.size());

    }
//

    @Test
    public void testUpdateBudget() {

        Trip trip = new Trip();
        trip.setTitle("Bummer summer");
        trip.setDescription("summertime sadness");
        trip.setType("staycation");
        LocalDate dateStart = LocalDate.now();
        LocalDate dateEnd = LocalDate.now().plusDays(3);

        trip.setStartDate(dateStart);
        trip.setEndDate(dateEnd);

        Trip addedTrip = tripDao.addTrip(trip);

        Activity activity = new Activity();
        activity.setName("Netflix party");
        activity.setTrip(addedTrip);
        activity.setAddress("home");
        activity.setDescription("bring your jammies");
        Activity addedActivity = activityDao.addActivity(activity);

        Budget budget = new Budget();
        budget.setName("cool budget");
        budget.setAccomodationCost(new BigDecimal("10.00"));
        budget.setFoodCost(new BigDecimal("15.00"));
        budget.setTransportationCost(new BigDecimal("10.00"));
        budget.setActivityCost(new BigDecimal("15.00"));

        budget.setTrip(addedTrip);
        Budget addedBudget = budgetDao.addBudget(budget);

        Budget fromDao = budgetDao.getBudgetByID(addedBudget.getId());
        assertTrue(fromDao.getAccomodationCost().equals(addedBudget.getAccomodationCost()));

        fromDao.setActivityCost(new BigDecimal("25.00"));
        budgetDao.updateBudget(fromDao);
        assertNotEquals(addedBudget, fromDao);

        Budget updated = budgetDao.getBudgetByID(fromDao.getId());
        assertTrue(fromDao.getAccomodationCost().equals(updated.getAccomodationCost()));
    }

    @Test
    public void testDeleteBudgetByID() {

        Trip trip = new Trip();
        trip.setTitle("Bummer summer");
        trip.setDescription("summertime sadness");
        trip.setType("staycation");
        LocalDate dateStart = LocalDate.now();
        LocalDate dateEnd = LocalDate.now().plusDays(3);

        trip.setStartDate(dateStart);
        trip.setEndDate(dateEnd);

        Trip addedTrip = tripDao.addTrip(trip);

        Activity activity = new Activity();
        activity.setName("Netflix party");
        activity.setTrip(addedTrip);
        activity.setAddress("home");
        activity.setDescription("bring your jammies");
        Activity addedActivity = activityDao.addActivity(activity);

        Budget budget = new Budget();
        budget.setName("cool budget");
        budget.setAccomodationCost(new BigDecimal("10.00"));
        budget.setFoodCost(new BigDecimal("15.00"));
        budget.setTransportationCost(new BigDecimal("10.00"));
        budget.setActivityCost(new BigDecimal("15.00"));

        budget.setTrip(addedTrip);
        Budget addedBudget = budgetDao.addBudget(budget);
        budgetDao.deleteBudgetByID(addedBudget.getId());

        Budget deleted = budgetDao.getBudgetByID(addedBudget.getId());
        assertNull(deleted);
    }
}
