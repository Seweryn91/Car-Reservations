package com.seweryn91.CarReservations.dao;

import com.seweryn91.CarReservations.model.Reservation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservationDAO {

    private SessionFactory sessionFactory;

    public Reservation getReservation(long reservationId) {
        Transaction tx = null;
        Reservation reservation = null;
        try {
            Session session = sessionFactory.openSession();
            tx = session.beginTransaction();
            reservation = session.get(Reservation.class, reservationId);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return reservation;
    }


    public void saveReservation(Reservation reservation) {
        Transaction tx = null;
        try {
            Session session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Reservation reservationFromDB = session.get(Reservation.class, reservation.getReservationId());
            if (reservationFromDB == null) {
                session.save(reservation);
            } else {
                reservation = reservationFromDB;
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }


    public void deleteReservation(long reservationId) {
        Transaction tx = null;
        try {
            Session session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Reservation reservationInDB = session.load(Reservation.class, reservationId);
            if (reservationInDB != null) {
                session.delete(reservationId);
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void updateReservation(Reservation reservation) {
        long reservationId = reservation.getReservationId();
        Transaction tx = null;
        try {
            Session session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Reservation reservationInDB = session.get(Reservation.class, reservationId);
            if (reservationInDB != null) {
                session.update(reservation);
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Reservation> getAllReservations(Reservation reservation) {
        Transaction tx = null;
        List<Reservation> allReservations = null;
        try {
            Session session = sessionFactory.openSession();
            tx = session.beginTransaction();
            allReservations = (List<Reservation>) session.createQuery("from Reservation").list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return allReservations;
    }
}