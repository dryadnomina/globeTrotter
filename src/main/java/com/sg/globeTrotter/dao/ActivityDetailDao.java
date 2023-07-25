/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.globeTrotter.dao;

import com.sg.globeTrotter.dto.ActivityDetail;
import java.util.List;

/**
 *
 * @author marya
 */
public interface ActivityDetailDao {
    ActivityDetail getActivityDetailByID(int id);

    List<ActivityDetail> getAllActivityDetails();

    ActivityDetail addActivityDetail(ActivityDetail detail);

    void updateActivityDetail(ActivityDetail detail);

    void deleteActivityDetailByID(int id);
}
