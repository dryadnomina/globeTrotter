/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.globeTrotter.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author marya
 */
public class Trip {

    private int id;
    @NotBlank
    @Size(max = 100, message = "Title must be fewer than 100 characters")
    private String title;
    @NotNull
    private String type;
    @Size(max = 255, message = "Description must be fewer than 255 characters")
    private String description;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;

    @NotNull
    private boolean completed;
    private List<Accomodation> accomodations;

    private List<Traveller> travellers;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<Traveller> getTravellers() {
        return travellers;
    }

    public void setTravellers(List<Traveller> travellers) {
        this.travellers = travellers;
    }

    public List<Accomodation> getAccomodations() {
        return accomodations;
    }

    public void setAccomodations(List<Accomodation> accomodations) {
        this.accomodations = accomodations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.id;
        hash = 17 * hash + Objects.hashCode(this.title);
        hash = 17 * hash + Objects.hashCode(this.type);
        hash = 17 * hash + Objects.hashCode(this.description);
        hash = 17 * hash + Objects.hashCode(this.startDate);
        hash = 17 * hash + Objects.hashCode(this.endDate);
        hash = 17 * hash + (this.completed ? 1 : 0);
        hash = 17 * hash + Objects.hashCode(this.accomodations);
        hash = 17 * hash + Objects.hashCode(this.travellers);
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
        final Trip other = (Trip) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.completed != other.completed) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        if (!Objects.equals(this.endDate, other.endDate)) {
            return false;
        }
        if (!Objects.equals(this.accomodations, other.accomodations)) {
            return false;
        }
        return Objects.equals(this.travellers, other.travellers);
    }

}
