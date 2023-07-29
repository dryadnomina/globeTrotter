/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.globeTrotter.dao;

import com.sg.globeTrotter.dto.Budget;
import java.util.List;

/**
 *
 * @author marya
 */
public interface BudgetDao {
    Budget getBudgetByID(int id);

    List<Budget> getAllBudgets();

    Budget addBudget(Budget detail);

    void updateBudget(Budget detail);

    void deleteBudgetByID(int id);
}
