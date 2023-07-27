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
import org.springframework.stereotype.Repository;

/**
 *
 * @author marya
 */
@Repository
public class ActivityDetailDaoImpl implements ActivityDetailDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public ActivityDetail getActivityDetailByID(int id) {
        try {

            String sql = "SELECT * FROM detail WHERE detailId = ?";
            ActivityDetail detail = jdbc.queryForObject(sql, new ActivityDetailMapper(), id);
            return detail;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<ActivityDetail> getAllActivityDetails() {
        String sql = "SELECT * FROM detail";

        List<ActivityDetail> details = jdbc.query(sql, new ActivityDetailMapper());

        return details;
    }

    @Override
    public ActivityDetail addActivityDetail(ActivityDetail detail) {
        final String sql = "INSERT INTO detail (description,address,activityId) "
                + "VALUES(?,?,?)";

        jdbc.update(sql,
                detail.getDescription(),
                detail.getAddress(),
                detail.getActivityId()
        );

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        detail.setId(newId);
        return detail;
    }

    @Override
    public void updateActivityDetail(ActivityDetail detail) {
        String sql = "UPDATE detail SET address = ?,description = ?,activityId = ? WHERE detailId = ?";
        jdbc.update(sql, detail.getAddress(),
                detail.getDescription(),
                detail.getActivityId(),
                detail.getId());
    }

    @Override
    public void deleteActivityDetailByID(int id) {
        String sql = "DELETE FROM detail WHERE detailId = ?";
        jdbc.update(sql, id);
    }

}
