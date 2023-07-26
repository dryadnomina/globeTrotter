/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.globeTrotter.dao;

import com.sg.globeTrotter.dto.Activity;
import java.util.List;

/**
 *
 * @author marya
 */
public interface ActivityDao {

    Activity getActivityByID(int id);

    List<Activity> getAllActivities();

    Activity addActivity(Activity activity);

    void updateActivity(Activity activity);

    void deleteActivityByID(int id);
}
