/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.globeTrotter.controller;

import com.sg.globeTrotter.dto.Budget;
import com.sg.globeTrotter.dto.Trip;
import com.sg.globeTrotter.service.GlobeTrotterService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author marya
 */
@Controller
public class BudgetController {
       @Autowired
    GlobeTrotterService service;
    Set<ConstraintViolation<Budget>> violations = new HashSet<>();

    @GetMapping("budgets")
    public String displaybudgets(Model model) {
        List<Budget> budgets = service.getAllBudgets();
        List<Trip> trips = service.getAllTrips();
        model.addAttribute("budgets", budgets);
        model.addAttribute("trips", trips);
        model.addAttribute("errors", violations);

        return "budgets";
    }

    @PostMapping("addBudget")
    public String addBudget(Budget budget, HttpServletRequest request) {

        int tripId = Integer.parseInt(request.getParameter("tripId"));
        Trip trip = service.getTripByID(tripId);
        budget.setTrip(trip);
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();

        if (violations.isEmpty()) {
            service.addBudget(budget);

        }
        return "redirect:/budgets";
    }

    @GetMapping("deleteBudget")
    public String deleteBudget(Integer id) {
        service.deleteBudgetByID(id);
        return "redirect:/budgets";
    }


    @GetMapping("editBudget")
    public String editBudget(Integer id, Model model) {
        Budget budget = service.getBudgetByID(id);
        List<Trip> trips = service.getAllTrips();
        model.addAttribute("budget", budget);
        model.addAttribute("trips", trips);
        return "editBudget";
    }

    @PostMapping("editBudget")
    public String performEditBudget(@Valid Budget budget, BindingResult result, HttpServletRequest request, Model model) {
         List<Trip> trips = service.getAllTrips();
    
        if (result.hasErrors()) {
            model.addAttribute("budget", budget);
            model.addAttribute("trips", trips);
            
            System.out.println("errors" + result.getFieldErrors());
            return "editBudget";
        }

            service.updateBudget(budget);

        
        return "redirect:/budgets";
    }

}
