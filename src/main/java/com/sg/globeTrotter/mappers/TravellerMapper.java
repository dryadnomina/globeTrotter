/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.globeTrotter.mappers;

import com.sg.globeTrotter.dto.Traveller;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author marya
 */
public class TravellerMapper implements RowMapper<Traveller> {

    @Override
    public Traveller mapRow(ResultSet rs, int rowNum) throws SQLException {
        Traveller traveller = new Traveller();
        traveller.setId(rs.getInt("travellerId"));
        traveller.setFirstName(rs.getString("firstName"));
        traveller.setLastName(rs.getString("lastName"));
        traveller.setPhoneNumber(rs.getString("phoneNumber"));
        traveller.setPostalCode(rs.getString("postalCode"));
        traveller.setCity(rs.getString("city"));
        return traveller;

    }

}
