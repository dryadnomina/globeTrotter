/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.globeTrotter.dao;

import com.sg.globeTrotter.dto.Accomodation;
import com.sg.globeTrotter.dto.Trip;
import com.sg.globeTrotter.mappers.AccomodationMapper;
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
public class AccomodationDaoImpl implements AccomodationDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Accomodation getAccomodationByID(int id) {
        try {

            String sql = "SELECT * FROM accomodation WHERE accomodationId = ?";
            Accomodation accomodation = jdbc.queryForObject(sql, new AccomodationMapper(), id);
            Trip trip = setTripForAccomodation(accomodation);
            accomodation.setTrip(trip);
            return accomodation;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    private Trip setTripForAccomodation(Accomodation accomodation) {
        try {
            String sql = "SELECT * FROM trip JOIN accomodation ON accomodation.tripId = trip.tripId WHERE accomodation.accomodationId = ?";
            Trip trip = jdbc.queryForObject(sql, new TripMapper(), accomodation.getId());
            return trip;
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Accomodation> getAllAccomodations() {
        String sql = "SELECT * FROM accomodation";
        List<Accomodation> accomodations = jdbc.query(sql, new AccomodationMapper());

        accomodations.forEach(accomodation -> {
            Trip trip = setTripForAccomodation(accomodation);
            accomodation.setTrip(trip);

        });
        return accomodations;
    }

    @Override
    public Accomodation addAccomodation(Accomodation accomodation) {
        final String sql = "INSERT INTO accomodation (name, type,description,tripId) "
                + "VALUES(?,?,?,?)";

        Trip trip = accomodation.getTrip();

        jdbc.update(sql,
                accomodation.getName(),
                accomodation.getType(),
                accomodation.getDescription(),
                trip.getId()
        );

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        accomodation.setId(newId);
        return accomodation;
    }

    @Override
    public void updateAccomodation(Accomodation accomodation) {
        String sql = "UPDATE accomodation SET name = ?, type = ?, description = ? ,tripId = ? WHERE accomodationId = ?";
        jdbc.update(sql,
                accomodation.getName(),
                accomodation.getType(),
                accomodation.getDescription(),
                accomodation.getTripId(),
//                accomodation.getTrip().getId(),
                accomodation.getId());
    }

    @Override
    public void deleteAccomodationByID(int id) {
        String sql = "DELETE FROM accomodation WHERE accomodationId = ?";
        jdbc.update(sql, id);
    }

}
