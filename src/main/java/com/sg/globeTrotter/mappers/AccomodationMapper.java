/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.globeTrotter.mappers;

import com.sg.globeTrotter.dto.Accomodation;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author marya
 */
public class AccomodationMapper implements RowMapper<Accomodation> {

    @Override
    public Accomodation mapRow(ResultSet rs, int rowNum) throws SQLException {
        Accomodation accomodation = new Accomodation();
        accomodation.setId(rs.getInt("accomodationId"));
        accomodation.setDescription(rs.getString("description"));
        accomodation.setName(rs.getString("name"));
        accomodation.setType(rs.getString("type"));
        return accomodation;

    }

}
