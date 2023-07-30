/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.globeTrotter.dao;

import com.sg.globeTrotter.dto.Trip;
import com.sg.globeTrotter.mappers.TripMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author marya
 */
@Repository
public class TripDaoImpl implements TripDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Trip getTripByID(int id) {
        try {

            String sql = "SELECT * FROM trip WHERE tripId = ?";
            return jdbc.queryForObject(sql, new TripMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Trip> getAllTrips() {
        String sql = "SELECT * FROM trip";
        return jdbc.query(sql, new TripMapper());
    }

    @Override
    public Trip addTrip(Trip trip) {

        final String sql = "INSERT INTO trip(title,type,description,startDate,endDate,completed) VALUES (?,?,?,?,?,?)";

        jdbc.update(sql,
                trip.getTitle(),
                trip.getType(),
                trip.getDescription(),
                trip.getStartDate(),
                trip.getEndDate(),
                trip.isCompleted());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        trip.setId(newId);
        return trip;

    }

    @Override
    public void updateTrip(Trip trip) {
        String sql = "UPDATE trip SET title = ?,type = ?,description = ?, completed = ?, startDate = ?,endDate = ?  WHERE tripId = ?";
        jdbc.update(sql, trip.getTitle(),
                trip.getType(),
                trip.getDescription(),
                trip.isCompleted(),
                trip.getStartDate(),
                trip.getEndDate(),
                trip.getId());
    }

    @Override
    public void deleteTripByID(int id) {
        final String sql1 = "DELETE FROM activity WHERE tripId = ?";
        final String sql2 = "DELETE FROM accomodation WHERE tripId = ?";
        final String sql3 = "DELETE FROM budget WHERE tripId = ?";
        final String sql4 = "DELETE FROM trip WHERE tripId = ?";
        jdbc.update(sql1, id);
        jdbc.update(sql2, id);
        jdbc.update(sql3, id);
         jdbc.update(sql4, id);
    }

}
