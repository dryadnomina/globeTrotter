/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.globeTrotter.mappers;

import com.sg.globeTrotter.dto.Budget;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author marya
 */
public class BudgetMapper implements RowMapper<Budget> {

    
//     private BigDecimal foodCost;
//    private BigDecimal accomodationCost;
//    private BigDecimal activityCost;
//    private BigDecimal transportationCost;
//    private BigDecimal total;
    @Override
    public Budget mapRow(ResultSet rs, int rowNum) throws SQLException {
        Budget budget = new Budget();
        budget.setId(rs.getInt("budgetId"));
        budget.setAccomodationCost(rs.getBigDecimal("accomodationCost"));
        budget.setFoodCost(rs.getBigDecimal("foodCost"));
        budget.setActivityCost(rs.getBigDecimal("activityCost"));
        budget.setTransportationCost(rs.getBigDecimal("transportationCost"));
        budget.setTotal(rs.getBigDecimal("total"));
    
    
        return budget;
    }

}
