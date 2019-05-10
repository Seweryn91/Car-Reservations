package com.seweryn91.CarReservations.controller;

import com.seweryn91.CarReservations.dao.ReservationDAO;
import com.seweryn91.CarReservations.model.Reservation;

import java.sql.SQLException;
import java.util.Date;

public class ReservationsController {

    public Reservation createReservation(long carId, long customerId, Date startDate, Date endDate) {
        Reservation reservation = new Reservation();
        ReservationDAO reservationDAO = new ReservationDAO();
        reservation.setCarId(carId);
        reservation.setCustomerId(customerId);
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);

        try {
        reservationDAO.addReservation(reservation);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return reservation;
    }

    public void deleteReservation(Reservation reservation) {

    }
}
