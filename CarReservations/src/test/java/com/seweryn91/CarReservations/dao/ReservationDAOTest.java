package com.seweryn91.CarReservations.dao;

import com.seweryn91.CarReservations.model.Reservation;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Transactional
class ReservationDAOTest {

    @Autowired
    ReservationDAO reservationDAO;
    SessionFactory sessionFactory;

    @Test
    void testGetReservation() {
        Reservation reservation = reservationDAO.getReservation(1);
        Assertions.assertEquals(2, reservation.getCustomerId());
        Assertions.assertEquals(9, reservation.getCarId());
    }

    @Test
    void testSaveReservation() {
        List<Reservation> reservations = reservationDAO.getAllReservations();
        int prevSize = reservations.size();
        Reservation reservation = createReservation();
        reservationDAO.saveReservation(reservation);
        List<Reservation> reservationsAfterInsert = reservationDAO.getAllReservations();
        int newSize = reservationsAfterInsert.size();
        Assertions.assertEquals(prevSize + 1, newSize);
        reservationDAO.deleteReservation(reservation.getReservationId());
    }

    @Test
    void testDeleteReservation() {
        Reservation reservation = createReservation();
        reservationDAO.saveReservation(reservation);
        List<Reservation> reservations = reservationDAO.getAllReservations();
        int prevSize = reservations.size();
        reservationDAO.deleteReservation(reservation.getReservationId());
        List<Reservation> reservationsAfterDelete = reservationDAO.getAllReservations();
        int newSize = reservationsAfterDelete.size();
        Assertions.assertEquals(prevSize - 1, newSize);
    }

    @Test
    void testUpdateReservationCar() {
        Reservation reservation = createReservation();
        reservationDAO.saveReservation(reservation);
        long carId = 1;
        reservationDAO.updateReservationCar(reservation.getReservationId(), carId);
        Reservation afterUpdate = reservationDAO.getReservation(reservation.getReservationId());
        long newId = afterUpdate.getCarId();
        Assertions.assertEquals(carId, newId);
        reservationDAO.deleteReservation(reservation.getReservationId());
    }

    @Test
    void testUpdateReservationCustomer() {
        Reservation reservation = createReservation();
        reservationDAO.saveReservation(reservation);
        long customerId = 1;
        reservationDAO.updateReservationCustomer(reservation.getReservationId(), customerId);
        Reservation afterUpdate = reservationDAO.getReservation(reservation.getReservationId());
        long newId = afterUpdate.getCustomerId();
        Assertions.assertEquals(customerId, newId);
        reservationDAO.deleteReservation(reservation.getReservationId());
    }

    @Test
    void testUpdateReservationStart() {
        Reservation reservation = createReservation();
        reservationDAO.saveReservation(reservation);
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date startDate = null;
        try {
            startDate = simpleDateFormat.parse("2030-02-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        reservationDAO.updateReservationStart(reservation.getReservationId(), startDate);
        Reservation afterUpdate = reservationDAO.getReservation(reservation.getReservationId());
        String newDate = afterUpdate.getStartDate().toString();
        Assertions.assertEquals("2030-02-01 00:00:00.0", newDate);
        reservationDAO.deleteReservation(reservation.getReservationId());
    }

    @Test
    void testUpdateReservationEnd() {
        Reservation reservation = createReservation();
        reservationDAO.saveReservation(reservation);
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date endDate = null;
        try {
            endDate = simpleDateFormat.parse("2030-05-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        reservationDAO.updateReservationStart(reservation.getReservationId(), endDate);
        Reservation afterUpdate = reservationDAO.getReservation(reservation.getReservationId());
        String newDate = afterUpdate.getStartDate().toString();
        Assertions.assertEquals("2030-05-01 00:00:00.0", newDate);
        reservationDAO.deleteReservation(reservation.getReservationId());
    }

    @Test
    void testGetAllReservations() {
        List<Reservation> reservations = reservationDAO.getAllReservations();
        Assertions.assertEquals(3, reservations.size());
        Reservation reservation1 = reservations.get(0);
        Assertions.assertEquals(9, reservation1.getCarId());
        Assertions.assertEquals(2, reservation1.getCustomerId());
    }

    Reservation createReservation() {
        Reservation reservation = new Reservation();
        reservation.setCustomerId(3);
        reservation.setCarId(3);
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = simpleDateFormat.parse("2030-03-03");
            endDate = simpleDateFormat.parse("2030-03-15");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);

        return reservation;
    }
}