/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.globeTrotter.mappers;

import com.sg.globeTrotter.dto.ActivityDetail;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author marya
 */
public class ActivityDetailMapper implements RowMapper<ActivityDetail> {

    @Override
    public ActivityDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        ActivityDetail detail = new ActivityDetail();
        detail.setId(rs.getInt("detailId"));
        detail.setDescription(rs.getString("description"));
        detail.setAddress(rs.getString("address"));
        detail.setCost(rs.getBigDecimal("cost"));
        return detail;
    }

}
