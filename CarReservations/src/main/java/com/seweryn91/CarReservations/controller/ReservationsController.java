package com.seweryn91.CarReservations.controller;

import com.seweryn91.CarReservations.dao.ReservationDAO;
import com.seweryn91.CarReservations.model.Reservation;
import org.hibernate.SessionFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.*;

public class ReservationsController {

    private SessionFactory sessionFactory;
    private ReservationDAO reservationDAO;

    @RequestMapping (value = "/reservations", method = RequestMethod.GET)
    @ResponseBody
    private List<Reservation> getAllReservations() {
        List<Reservation> reservations = reservationDAO.getAllReservations();
        return reservations;
    }
}
