/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.globeTrotter.controller;

import com.sg.globeTrotter.dao.AccomodationDao;
import com.sg.globeTrotter.dto.Accomodation;
import com.sg.globeTrotter.dto.Trip;
import com.sg.globeTrotter.service.GlobeTrotterService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class AccomodationController {

    @Autowired
    GlobeTrotterService service;
    Set<ConstraintViolation<Accomodation>> violations = new HashSet<>();

    @GetMapping("accomodations")
    public String displayTrips(Model model) {
        List<Trip> trips = service.getAllTrips();
        List<Accomodation> accomodations = service.getAllAccomodations();
        model.addAttribute("trips", trips);
        model.addAttribute("accomodations", accomodations);
        model.addAttribute("errors", violations);

        return "accomodations";
    }

    @PostMapping("addAccomodation")
    public String addActivity(Accomodation accomodation, HttpServletRequest request) {
        
        int tripId = Integer.parseInt(request.getParameter("tripId"));
        Trip trip = service.getTripByID(tripId);
        accomodation.setTrip(trip);
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        System.out.println("name " + accomodation.getName());
        
        violations = validate.validate(accomodation);
        
        if (violations.isEmpty()) {
            service.addAccomodation(accomodation);

        }
        return "redirect:/accomodations";
    }
    
//     @GetMapping("accomodations")
//    public String displayActivityByID(Integer id, Model model) {
//       Accomodation accomodation = service.getAccomodationByID(id);
//       model.addAttribute("accomodation", accomodation);
//        return "accomodations";
//    }

    @GetMapping("deleteAccomodation")
    public String deleteAccomodation(Integer id) {
        service.deleteAccomodationByID(id);
        return "redirect:/accomodations";
    }

    @GetMapping("editAccomodation")
    public String editAccomodation(Integer id, Model model) {
        Accomodation accomodation = service.getAccomodationByID(id);
        model.addAttribute("accomodation", accomodation);
        return "editAccomodation";
    }

    @PostMapping("editAccomodation")
    public String performEditAccomodation(@Valid Accomodation accomodation, BindingResult result, HttpServletRequest request, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("accomodation", accomodation);
            return "editAccomodation";
        } else {

            service.updateAccomodation(accomodation);
        }

        return "redirect:/accomodations";
    }
}
