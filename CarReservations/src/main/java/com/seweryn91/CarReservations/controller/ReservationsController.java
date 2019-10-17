package com.seweryn91.CarReservations.controller;

import com.seweryn91.CarReservations.dao.ReservationDAO;
import com.seweryn91.CarReservations.model.Reservation;
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


    @RequestMapping (value = "/reservations", method = RequestMethod.GET)
    private List<Reservation> getAllReservations() {
        List<Reservation> reservations = reservationDAO.getAllReservations();
        return reservations;
    }

    public Double getPrice(long reservationId) {
        return reservationDAO.getPrice(reservationId);
    }
}
