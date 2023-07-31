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
   @NotBlank
     @Size(max = 100, message = "Budget Name must be fewer than 100 characters")
   private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
   
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.foodCost);
        hash = 97 * hash + Objects.hashCode(this.accomodationCost);
        hash = 97 * hash + Objects.hashCode(this.activityCost);
        hash = 97 * hash + Objects.hashCode(this.transportationCost);
        hash = 97 * hash + Objects.hashCode(this.total);
        hash = 97 * hash + this.tripId;
        hash = 97 * hash + Objects.hashCode(this.trip);
        hash = 97 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Budget other = (Budget) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.tripId != other.tripId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.foodCost, other.foodCost)) {
            return false;
        }
        if (!Objects.equals(this.accomodationCost, other.accomodationCost)) {
            return false;
        }
        if (!Objects.equals(this.activityCost, other.activityCost)) {
            return false;
        }
        if (!Objects.equals(this.transportationCost, other.transportationCost)) {
            return false;
        }
        if (!Objects.equals(this.total, other.total)) {
            return false;
        }
        return Objects.equals(this.trip, other.trip);
    }

    
    
    
}
