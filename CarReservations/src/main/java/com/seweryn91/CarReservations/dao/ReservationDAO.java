package com.seweryn91.CarReservations.dao;

import com.seweryn91.CarReservations.model.Reservation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ReservationDAO {

    private SessionFactory sessionFactory;

    public Reservation getReservation(long reservationId) {
        Transaction tx = null;
        Reservation reservation = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            reservation = session.byId(Reservation.class).load(reservationId);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return reservation;
    }


    public void saveReservation(Reservation reservation) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(reservation);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void deleteReservation(long reservationId) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Reservation reservation = getReservation(reservationId);
            session.delete(reservation);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void updateReservationCar(long reservationId, long carId) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Reservation reservation = getReservation(reservationId);
            reservation.setCarId(carId);
            session.update(reservation);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void updateResrvationCustomer(long reservationId, long customerId) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Reservation reservation = getReservation(reservationId);
            reservation.setCustomerId(customerId);
            session.update(reservation);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void updateReservationStart(long reservationId, Date startDate) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Reservation reservation = getReservation(reservationId);
            reservation.setStartDate(startDate);
            session.update(reservation);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void updateReservationEnd(long reservationId, Date endDate) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Reservation reservation = getReservation(reservationId);
            reservation.setEndDate(endDate);
            session.update(reservation);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Reservation> getAllReservations() {
        Transaction tx = null;
        List<Reservation> allReservations = null;
        try (Session session = sessionFactory.openSession()) {
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