/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.globeTrotter.dao;

import com.sg.globeTrotter.dto.Traveller;
import java.util.List;

/**
 *
 * @author marya
 */
public interface TravellerDao {

    Traveller getTravellerByID(int id);

    List<Traveller> getAllTravellers();

    Traveller addTraveller(Traveller traveller);

    Traveller checkIfTravellerJoinedTrip(int id, int tripId);

    void updateTraveller(Traveller traveller);

    void addTravellerToTrip(int id, int tripId);

    void deleteTravellerByID(int id);
}
