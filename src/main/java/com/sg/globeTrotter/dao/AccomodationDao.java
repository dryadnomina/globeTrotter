/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.globeTrotter.dao;

import com.sg.globeTrotter.dto.Accomodation;
import java.util.List;

/**
 *
 * @author marya
 */
public interface AccomodationDao {

    Accomodation getAccomodationByID(int id);

    List<Accomodation> getAllAccomodations();

    Accomodation addAccomodation(Accomodation accomodation);

    void updateAccomodation(Accomodation accomodation);

    void deleteAccomodationByID(int id);

}
