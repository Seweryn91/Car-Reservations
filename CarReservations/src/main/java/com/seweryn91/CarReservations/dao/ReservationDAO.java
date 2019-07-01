package com.seweryn91.CarReservations.dao;

import com.seweryn91.CarReservations.model.Reservation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ReservationDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Reservation getReservation(long reservationId) {
        Transaction tx = null;
        Reservation reservation = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            reservation = session.byId(Reservation.class).load(reservationId);
            tx.commit();
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
            tx.commit();
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
            tx.commit();
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
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void updateReservationCustomer(long reservationId, long customerId) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Reservation reservation = getReservation(reservationId);
            reservation.setCustomerId(customerId);
            session.update(reservation);
            tx.commit();
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

            if (startDate.compareTo(reservation.getEndDate()) >= 0) {
                throw new IllegalArgumentException("Start date must be before end date!");
            }

            reservation.setStartDate(startDate);
            session.update(reservation);
            tx.commit();
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

            if (endDate.compareTo(reservation.getStartDate()) <= 0) {
                throw new IllegalArgumentException("End date must be later than start date!");
            }

            reservation.setEndDate(endDate);
            session.update(reservation);
            tx.commit();
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

    @SuppressWarnings("unchecked")
    public Double getPrice(long reservationId) {
        Transaction tx = null;
        Double price = 0.00;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            price = (Double) session.createQuery("select (r.endDate-r.startDate) * (c.price) from Reservation r" +
                    " inner join Car c on c.id = r.carId where r.id = :id")
                    .setParameter("id", reservationId).list().get(0);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return price;
    }
}