/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.globeTrotter.mappers;

import com.sg.globeTrotter.dto.Trip;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author marya
 */
public class TripMapper implements RowMapper<Trip> {

        @Override
        public Trip mapRow(ResultSet rs, int rowNum) throws SQLException {
            Trip trip = new Trip();
            trip.setId(rs.getInt("tripId"));
            trip.setTitle(rs.getString("title"));
            trip.setDescription(rs.getString("description"));
            trip.setCompleted(rs.getBoolean("completed"));
            trip.setType(rs.getString("type"));
            return trip;
        }
}
