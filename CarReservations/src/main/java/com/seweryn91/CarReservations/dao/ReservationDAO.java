package com.seweryn91.CarReservations.dao;

import com.seweryn91.CarReservations.database.HibernateUtil;
import com.seweryn91.CarReservations.model.Reservation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class ReservationDAO {

    private SessionFactory sessionFactory;

    public Reservation getReservation(long reservationId) {
        Session session = sessionFactory.getCurrentSession();
        Reservation reservation = session.get(Reservation.class, reservationId);
        return reservation;
    }


    public void saveReservation(Reservation reservation) {
        Session session = sessionFactory.getCurrentSession();
        Reservation reservationFromDB = session.get(Reservation.class, reservation.getReservationId());
        if (reservationFromDB == null) {
            session.save(reservation);
        } else {
            reservation = reservationFromDB;
        }
    }


    public void deleteReservation(long reservationId) {

        Session session = sessionFactory.getCurrentSession();
        Reservation reservationInDB = session.load(Reservation.class, reservationId);
        if (reservationInDB != null) {
            session.delete(reservationId);
        }
    }
}