/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.globeTrotter.dto;

import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author marya
 */
public class Budget {

    private int id;
    private BigDecimal foodCost;
    private BigDecimal accomodationCost;
    private BigDecimal activityCost;
    private BigDecimal transportationCost;
    private BigDecimal total;

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

    @NotNull
    private Trip trip;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.id;
        hash = 11 * hash + Objects.hashCode(this.foodCost);
        hash = 11 * hash + Objects.hashCode(this.accomodationCost);
        hash = 11 * hash + Objects.hashCode(this.activityCost);
        hash = 11 * hash + Objects.hashCode(this.transportationCost);
        hash = 11 * hash + Objects.hashCode(this.total);
        hash = 11 * hash + Objects.hashCode(this.trip);
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
