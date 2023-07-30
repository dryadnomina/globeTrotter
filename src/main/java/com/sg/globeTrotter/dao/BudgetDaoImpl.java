/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.globeTrotter.dao;

import com.sg.globeTrotter.dto.Budget;
import com.sg.globeTrotter.dto.Trip;
import com.sg.globeTrotter.mappers.BudgetMapper;
import com.sg.globeTrotter.mappers.TripMapper;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

public class BudgetDaoImpl implements BudgetDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Budget getBudgetByID(int id) {
        try {

            String sql = "SELECT * FROM budget WHERE budgetId = ?";
            Budget budget = jdbc.queryForObject(sql, new BudgetMapper(), id);
            Trip trip = setTripForBudget(budget);
            budget.setTrip(trip);
            return budget;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    private Trip setTripForBudget(Budget budget) {
        try {
            String sql = "SELECT * FROM trip JOIN budget ON budget.tripId = trip.tripId WHERE budget.budgetId = ?";
            Trip trip = jdbc.queryForObject(sql, new TripMapper(), budget.getId());
            return trip;
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Budget> getAllBudgets() {
        String sql = "SELECT * FROM budget";

        List<Budget> budgets = jdbc.query(sql, new BudgetMapper());

        budgets.forEach(budget -> {
            Trip trip = setTripForBudget(budget);
            budget.setTrip(trip);

        });

        return budgets;
    }

    @Override
    public Budget addBudget(Budget budget) {
        final String sql = "INSERT INTO budget (accomodationCost,foodCost,transportationCost, activityCost,tripId,total) "
                + "VALUES(?,?,?,?,?,?)";

        calculateBudgetTotal(budget);

        jdbc.update(sql,
                budget.getAccomodationCost().setScale(2, RoundingMode.HALF_UP),
                budget.getFoodCost().setScale(2, RoundingMode.HALF_UP),
                budget.getTransportationCost().setScale(2, RoundingMode.HALF_UP),
                budget.getActivityCost().setScale(2, RoundingMode.HALF_UP),
                budget.getTrip().getId(),
                budget.getTotal().setScale(2, RoundingMode.HALF_UP));

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        budget.setId(newId);
        return budget;
    }

    private void calculateBudgetTotal(Budget budget) {
        BigDecimal accomodationCost = budget.getAccomodationCost();
        BigDecimal foodCost = budget.getFoodCost();
        BigDecimal activityCost = budget.getActivityCost();
        BigDecimal transportationCost = budget.getTransportationCost();

        BigDecimal total = accomodationCost.add(foodCost).add(activityCost).add(transportationCost);

        budget.setTotal(total);
    }

    @Override
    public void updateBudget(Budget budget) {
        String sql = "UPDATE budget SET accomodationCost = ?,foodCost = ?,transportationCost = ?,activityCost = ?,tripId = ?, total = ? WHERE budgetId = ?";
        calculateBudgetTotal(budget);
        jdbc.update(sql,
                budget.getAccomodationCost().setScale(2, RoundingMode.HALF_UP),
                budget.getFoodCost().setScale(2, RoundingMode.HALF_UP),
                budget.getTransportationCost().setScale(2, RoundingMode.HALF_UP),
                budget.getActivityCost().setScale(2, RoundingMode.HALF_UP),
                budget.getTripId(),
                budget.getTotal().setScale(2, RoundingMode.HALF_UP),
                budget.getId());

    }

    @Override
    public void deleteBudgetByID(int id) {
        String sql = "DELETE FROM budget WHERE budgetId = ?";
        jdbc.update(sql, id);
    }

}
