package com.seweryn91.CarReservations.controller;

import com.seweryn91.CarReservations.dao.ReservationDAO;
import com.seweryn91.CarReservations.model.Reservation;
import com.seweryn91.CarReservations.utils.JSONFormatter;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservationsController {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ReservationDAO reservationDAO;

    @Autowired
    private JSONFormatter jsonFormatter;

    @RequestMapping (value = "/reservations", method = RequestMethod.GET)
    private String getAllReservations() {
        StringBuilder sb = new StringBuilder();
        try {
            List<Reservation> reservations = reservationDAO.getAllReservations();
            sb.append(jsonFormatter.serializeCollectionReservations(reservations));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
