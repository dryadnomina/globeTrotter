/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.globeTrotter.service;

import com.sg.globeTrotter.dto.Accomodation;
import com.sg.globeTrotter.dto.Activity;
import com.sg.globeTrotter.dto.Budget;
import com.sg.globeTrotter.dto.Traveller;
import com.sg.globeTrotter.dto.Trip;
import java.util.List;

/**
 *
 * @author marya
 */
public interface GlobeTrotterService {

//trip  
    Trip getTripByID(int id);

    List<Trip> getAllTrips();

    Trip addTrip(Trip trip);

    void updateTrip(Trip trip);

    void deleteTripByID(int id);

    List<Trip> getAllTripsByTravellerID(int id);

//traveller
    Traveller getTravellerByID(int id);

    List<Traveller> getAllTravellers();

    Traveller addTraveller(Traveller traveller);

    void updateTraveller(Traveller traveller);

    void addTravellerToTrip(int id, int tripId);

    void deleteTravellerByID(int id);

//Activity
    Activity getActivityByID(int id);

    List<Activity> getAllActivities();

    Activity addActivity(Activity activity);

    void updateActivity(Activity activity);

    void deleteActivityByID(int id);

//Budget
    Budget getBudgetByID(int id);

    List<Budget> getAllBudgets();

    Budget addBudget(Budget detail);

    void updateBudget(Budget detail);

    void deleteBudgetByID(int id);

    //Accomodation
    Accomodation getAccomodationByID(int id);

    List<Accomodation> getAllAccomodations();

    Accomodation addAccomodation(Accomodation accomodation);

    void updateAccomodation(Accomodation accomodation);

    void deleteAccomodationByID(int id);
}
