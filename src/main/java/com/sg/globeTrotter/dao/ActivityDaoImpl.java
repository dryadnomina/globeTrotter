/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.globeTrotter.dao;

import com.sg.globeTrotter.dto.Activity;
import com.sg.globeTrotter.mappers.ActivityDetailMapper;
import com.sg.globeTrotter.mappers.ActivityMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author marya
 */
public class ActivityDaoImpl implements ActivityDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Activity getActivityByID(int id) {
        try {

            String sql = "SELECT * FROM activity WHERE activityId = ?";
            return jdbc.queryForObject(sql, new ActivityMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Activity> getAllActivities() {
        String sql = "SELECT * FROM activity;";
        return jdbc.query(sql, new ActivityMapper());
    }

    @Override
    public Activity addActivity(Activity activity) {
        final String sql = "INSERT INTO activity (activityName, activityId) "
                + "VALUES(?,?)";

        jdbc.update(sql,
                activity.getName(),
                activity.getId()
        );

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
        String sql = "DELETE FROM activity WHERE activityId = ?";
        jdbc.update(sql, id);
    }
}
