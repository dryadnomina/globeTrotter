/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.globeTrotter.dao;

import com.sg.globeTrotter.dto.Traveller;
import com.sg.globeTrotter.mappers.TravellerMapper;
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
public class TravellerDaoImpl implements TravellerDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Traveller getTravellerByID(int id) {
        try {

            String sql = "SELECT * FROM traveller WHERE travellerId = ?";
            return jdbc.queryForObject(sql, new TravellerMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Traveller> getAllTravellers() {
        String sql = "SELECT * FROM traveller;";
        return jdbc.query(sql, new TravellerMapper());
    }

    @Override
    public Traveller addTraveller(Traveller traveller) {

        final String sql = "INSERT INTO traveller (firstName,lastName,phoneNumber,city,postalCode) "
                + "VALUES(?,?,?,?,?)";

        jdbc.update(sql,
                traveller.getFirstName(),
                traveller.getLastName(),
                traveller.getPhoneNumber(),
                traveller.getCity(),
                traveller.getPostalCode());
        
        

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        traveller.setId(newId);
        return traveller;
    }
    
    public void addTravellerToTrip(int id, int tripId){
      String sql = "INSERT INTO tripTraveller (travellerId,tripId) VALUES (?,?)";
        jdbc.update(sql, id,tripId);
    
    }

    @Override
    public void updateTraveller(Traveller traveller) {
        String sql = "UPDATE traveller SET firstName = ?,lastName = ?,phoneNumber = ?,city = ?, postalCode = ? WHERE travellerId = ?";
        jdbc.update(sql, traveller.getFirstName(),
                traveller.getLastName(),
                traveller.getPhoneNumber(),
                traveller.getCity(),
                traveller.getPostalCode(),
                traveller.getId());
    }

    @Override
    public void deleteTravellerByID(int id) {
        String sql = "DELETE FROM traveller WHERE travellerId = ?";
        jdbc.update(sql, id);
    }
}
