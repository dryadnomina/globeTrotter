/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.globeTrotter.service;

import com.sg.globeTrotter.dao.AccomodationDao;
import com.sg.globeTrotter.dao.ActivityDao;
import com.sg.globeTrotter.dao.ActivityDetailDao;
import com.sg.globeTrotter.dao.TravellerDao;
import com.sg.globeTrotter.dao.TripDao;
import com.sg.globeTrotter.dto.Accomodation;
import com.sg.globeTrotter.dto.Activity;
import com.sg.globeTrotter.dto.ActivityDetail;
import com.sg.globeTrotter.dto.Traveller;
import com.sg.globeTrotter.dto.Trip;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marya
 */
@Service
public class GlobeTrotterServiceImpl implements GlobeTrotterService {

    @Autowired
    TripDao tripDao;

    @Autowired
    AccomodationDao accomodationDao;

    @Autowired
    ActivityDao activityDao;

    @Autowired
    ActivityDetailDao detailDao;

    @Autowired
    TravellerDao travellerDao;

    //Trip
    @Override
    public Trip getTripByID(int id) {

        return tripDao.getTripByID(id);
    }

    @Override
    public List<Trip> getAllTrips() {
        return tripDao.getAllTrips();
    }

    @Override
    public Trip addTrip(Trip trip) {
        return tripDao.addTrip(trip);
    }

    @Override
    public void updateTrip(Trip trip) {
        tripDao.updateTrip(trip);
    }

    @Override
    public void deleteTripByID(int id) {
        tripDao.deleteTripByID(id);
    }

//Traveller
    @Override
    public Traveller getTravellerByID(int id) {
        return travellerDao.getTravellerByID(id);
    }

    @Override
    public List<Traveller> getAllTravellers() {
       return travellerDao.getAllTravellers();
    }

    @Override
    public Traveller addTraveller(Traveller traveller) {
        return travellerDao.addTraveller(traveller);
    }

    @Override
    public void updateTraveller(Traveller traveller) {
        travellerDao.updateTraveller(traveller);
    }

    @Override
    public void addTravellerToTrip(int id, int tripId) {
        travellerDao.addTravellerToTrip(id, tripId);
    }

    @Override
    public void deleteTravellerByID(int id) {
        travellerDao.deleteTravellerByID(id);
    }

    //Activity
    @Override
    public Activity getActivityByID(int id) {
        return activityDao.getActivityByID(id);
    }

    @Override
    public List<Activity> getAllActivities() {
        return activityDao.getAllActivities();
    }

    @Override
    public Activity addActivity(Activity activity) {
        return activityDao.addActivity(activity);
    }

    @Override
    public void updateActivity(Activity activity) {
        activityDao.updateActivity(activity);
    }

    @Override
    public void deleteActivityByID(int id) {
        activityDao.deleteActivityByID(id);
    }

    //Activity Detail
    @Override
    public ActivityDetail getActivityDetailByID(int id) {
        return detailDao.getActivityDetailByID(id);
    }

    @Override
    public List<ActivityDetail> getAllActivityDetails() {
        return detailDao.getAllActivityDetails();
    }

    @Override
    public ActivityDetail addActivityDetail(ActivityDetail detail) {
        return detailDao.addActivityDetail(detail);
    }

    @Override
    public void updateActivityDetail(ActivityDetail detail) {
        detailDao.updateActivityDetail(detail);
    }

    @Override
    public void deleteActivityDetailByID(int id) {
        detailDao.deleteActivityDetailByID(id);
    }

    //Accomodations
    @Override
    public Accomodation getAccomodationByID(int id) {
        return accomodationDao.getAccomodationByID(id);
    }

    @Override
    public List<Accomodation> getAllAccomodations() {
        return accomodationDao.getAllAccomodations();
    }

    @Override
    public Accomodation addAccomodation(Accomodation accomodation) {
        return accomodationDao.addAccomodation(accomodation);
    }

    @Override
    public void updateAccomodation(Accomodation accomodation) {
        accomodationDao.updateAccomodation(accomodation);
    }

    @Override
    public void deleteAccomodationByID(int id) {
        accomodationDao.deleteAccomodationByID(id);
    }

}
