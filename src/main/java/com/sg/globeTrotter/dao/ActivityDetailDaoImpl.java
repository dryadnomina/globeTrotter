/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.globeTrotter.dao;

import com.sg.globeTrotter.dto.ActivityDetail;
import com.sg.globeTrotter.mappers.ActivityDetailMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author marya
 */
public class ActivityDetailDaoImpl implements ActivityDetailDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public ActivityDetail getActivityDetailByID(int id) {
        try {

            String sql = "SELECT * FROM detail WHERE detailId = ?";
            return jdbc.queryForObject(sql, new ActivityDetailMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<ActivityDetail> getAllActivityDetails() {
        String sql = "SELECT * FROM detail;";
        return jdbc.query(sql, new ActivityDetailMapper());
    }

    @Override
    public ActivityDetail addActivityDetail(ActivityDetail detail) {
        final String sql = "INSERT INTO detail (address,cost,description) "
                + "VALUES(?,?,?,?,?)";

        jdbc.update(sql,
                detail.getAddress(),
                detail.getCost(),
                detail.getDescription()
        );

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        detail.setId(newId);
        return detail;
    }

    @Override
    public void updateActivityDetail(ActivityDetail detail) {
        String sql = "UPDATE detail SET address = ?,description = ?,cost = ? WHERE detailId = ?";
        jdbc.update(sql, detail.getAddress(),
                detail.getDescription(),
                detail.getCost(),
                detail.getId());
    }

    @Override
    public void deleteActivityDetailByID(int id) {
        String sql = "DELETE FROM detail WHERE detailId = ?";
        jdbc.update(sql, id);
    }

}
