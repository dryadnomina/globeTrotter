/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.globeTrotter.dao;

import com.sg.globeTrotter.dto.Activity;
import com.sg.globeTrotter.dto.Trip;
import com.sg.globeTrotter.mappers.ActivityDetailMapper;
import com.sg.globeTrotter.mappers.ActivityMapper;
import com.sg.globeTrotter.mappers.TripMapper;
import java.util.List;
import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author marya
 */
@Repository
public class ActivityDaoImpl implements ActivityDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Activity getActivityByID(int id) {
        try {

            String sql = "SELECT * FROM activity WHERE activityId = ?";
            Activity activity = jdbc.queryForObject(sql, new ActivityMapper(), id);

           

            return activity;

        } catch (DataAccessException ex) {
            return null;
        }
    }

//    private Trip setTripForActivity(Activity activity) {
//        try {
//            String sql = "SELECT * FROM trip JOIN activity ON activity.tripId = trip.tripId WHERE activity.activityId = ?";
//            Trip trip = jdbc.queryForObject(sql, new TripMapper(), activity.getId());
//            return trip;
//        } catch (DataAccessException e) {
//            return null;
//        }
//    }

    @Override
    public List<Activity> getAllActivities() {
        String sql = "SELECT * FROM activity";
        List<Activity> activities = jdbc.query(sql, new ActivityMapper());

       
        return activities;
    }

    @Override
    public Activity addActivity(Activity activity) {
        final String sql = "INSERT INTO activity (activityName, tripId) VALUES(?,?)";

        
        jdbc.update(sql,
                activity.getName(),
                activity.getTripId());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        activity.setId(newId);
        return activity;
    }

    @Override
    public void updateActivity(Activity activity) {
        String sql = "UPDATE activity SET activityName = ? WHERE activityId = ?";
        jdbc.update(sql, activity.getName(),
                activity.getId());
    }

    @Override
    public void deleteActivityByID(int id) {
        String sql1 = "DELETE FROM detail WHERE activityId = ?";
        String sql2 = "DELETE FROM activity WHERE activityId = ?";

        jdbc.update(sql1, id);

        jdbc.update(sql2, id);
    }
}
