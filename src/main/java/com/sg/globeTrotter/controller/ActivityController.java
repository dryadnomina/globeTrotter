/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.globeTrotter.controller;

import com.sg.globeTrotter.dto.Activity;
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
public class ActivityController {

    @Autowired
    GlobeTrotterService service;
    Set<ConstraintViolation<Activity>> violations = new HashSet<>();

    @GetMapping("activities")
    public String displayActivitiesAndTrips(Model model) {
        List<Activity> activities = service.getAllActivities();
        List<Trip> trips = service.getAllTrips();
        model.addAttribute("activities", activities);
        model.addAttribute("trips", trips);
        model.addAttribute("errors", violations);
//        model.addAttribute("activity", new Activity());
        return "activities";
    }

    @PostMapping("addActivity")
    public String addActivity( Activity activity, HttpServletRequest request) {
//        Activity activity =new Activity();
//        activity.setName(request.getParameter("activityName"));
//        activity.setTrip(request.getParameter("trip"));
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(activity);
      
    
        if (violations.isEmpty()) {
            service.addActivity(activity);

        }
        return "redirect:/activities";
    }

    @GetMapping("deleteActivity")
    public String deleteActivity(Integer id) {
        service.deleteActivityByID(id);
        return "redirect:/activities";
    }

    @GetMapping("editActivity")
    public String editActivity(Integer id, Model model) {
        Activity activity = service.getActivityByID(id);
        model.addAttribute("activity", activity);
        return "editActivity";
    }

    @PostMapping("editActivity")
    public String performEditActivity(@Valid Activity activity, BindingResult result, HttpServletRequest request, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("activity", activity);
            return "editActivity";
        }

        service.updateActivity(activity);

        return "redirect:/activities";
    }
}
