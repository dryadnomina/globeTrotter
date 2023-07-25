/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.globeTrotter.controller;


import com.sg.globeTrotter.dao.AccomodationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author marya
 */
public class AccomodationController {
    @Autowired
    AccomodationDao accomodations;
    
     @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accomodations", accomodations.getAllAccomodations());
        return "index";
    }
}
