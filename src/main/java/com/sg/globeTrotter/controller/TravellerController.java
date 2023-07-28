/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.globeTrotter.controller;

import com.sg.globeTrotter.dto.Traveller;
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
public class TravellerController {

    @Autowired
    GlobeTrotterService service;
    Set<ConstraintViolation<Traveller>> violations = new HashSet<>();

    @GetMapping("travellers")
    public String displayTravellers(Model model) {
        List<Traveller> travellers = service.getAllTravellers();
        model.addAttribute("travellers", travellers);
        model.addAttribute("errors", violations);

        return "travellers";
    }

    @PostMapping("addTraveller")
    public String addTraveller(Traveller traveller, HttpServletRequest request) {

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(traveller);
        if (violations.isEmpty()) {
            service.addTraveller(traveller);

        }
        return "redirect:/travellers";
    }

    @GetMapping("deleteTraveller")
    public String deleteTraveller(Integer id) {
        service.deleteTravellerByID(id);
        return "redirect:/travellers";
    }

    @GetMapping("editTraveller")
    public String editTraveller(Integer id, Model model) {
        Traveller traveller = service.getTravellerByID(id);
        model.addAttribute("traveller", traveller);
        return "editTraveller";
    }

    @PostMapping("editTraveller")
    public String performEditTraveller(@Valid Traveller traveller, BindingResult result, HttpServletRequest request, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("traveller", traveller);
            return "editTraveller";
        }

            service.updateTraveller(traveller);
        

        return "redirect:/travellers";
    }
}
