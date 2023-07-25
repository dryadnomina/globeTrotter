/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.globeTrotter.dao;

import com.sg.globeTrotter.dto.Trip;
import java.util.List;

/**
 *
 * @author marya
 */
public interface TripDao {
    Trip getTripByID(int id);

    List<Trip> getAllTrips();

    Trip addTrip(Trip trip);

    void updateTrip(Trip trip);

    void deleteTripByID(int id);
}
