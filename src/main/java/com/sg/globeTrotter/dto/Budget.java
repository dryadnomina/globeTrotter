/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.globeTrotter.dto;

import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author marya
 */
public class Budget {

    private int id;
    @NotNull(message = "Food Cost cannot be null")
    private BigDecimal foodCost;
    @NotNull(message = "Accomodation Cost cannot be null")
    private BigDecimal accomodationCost;
    @NotNull(message = "Activity Cost cannot be null")
    private BigDecimal activityCost;
    @NotNull(message = "Transportation Cost cannot be null")
    private BigDecimal transportationCost;
    private BigDecimal total;
    @NotNull(message = "Trip cannot be null")
    private int tripId;
   
    private Trip trip;
    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public BigDecimal getFoodCost() {
        return foodCost;
    }

    public void setFoodCost(BigDecimal foodCost) {
        this.foodCost = foodCost;
    }

    public BigDecimal getAccomodationCost() {
        return accomodationCost;
    }

    public void setAccomodationCost(BigDecimal accomodationCost) {
        this.accomodationCost = accomodationCost;
    }

    public BigDecimal getActivityCost() {
        return activityCost;
    }

    public void setActivityCost(BigDecimal activityCost) {
        this.activityCost = activityCost;
    }

    public BigDecimal getTransportationCost() {
        return transportationCost;
    }

    public void setTransportationCost(BigDecimal transportationCost) {
        this.transportationCost = transportationCost;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
}
