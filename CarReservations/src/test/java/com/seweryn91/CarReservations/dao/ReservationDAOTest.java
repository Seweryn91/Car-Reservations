package com.seweryn91.CarReservations.dao;

import com.seweryn91.CarReservations.model.Reservation;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ReservationDAOTest {

    @Autowired
    ReservationDAO reservationDAO;
    SessionFactory sessionFactory;
    private long testReservationId;

    @BeforeAll
    void before() {
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

        reservationDAO.saveReservation(reservation);
        testReservationId = reservation.getReservationId();
    }

    @AfterAll
    void after() {
        reservationDAO.deleteReservation(testReservationId);
    }

    @Test
    @DisplayName("Test get reservation from DB")
    void testGetReservation() {
        Reservation reservation = reservationDAO.getReservation(1);
        Assertions.assertEquals(2, reservation.getCustomerId());
        Assertions.assertEquals(9, reservation.getCarId());
    }

    @Test
    @DisplayName("Test save reservation in DB")
    void testSaveReservation() {
        List<Reservation> reservations = reservationDAO.getAllReservations();
        int prevSize = reservations.size();
        Reservation reservation = new Reservation();
        reservation.setCustomerId(1);
        reservation.setCarId(1);
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = simpleDateFormat.parse("1991-01-01");
            endDate = simpleDateFormat.parse("1991-02-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservationDAO.saveReservation(reservation);
        long testReservationId2 = reservation.getReservationId();
        List<Reservation> reservationsAfterInsert = reservationDAO.getAllReservations();
        int newSize = reservationsAfterInsert.size();
        Assertions.assertEquals(prevSize + 1, newSize);
        reservationDAO.deleteReservation(testReservationId2);
    }

    @Test
    @DisplayName("Test delete reservation from DB")
    void testDeleteReservation() {
        Reservation reservation = new Reservation();
        reservation.setCustomerId(1);
        reservation.setCarId(1);
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = simpleDateFormat.parse("1991-01-01");
            endDate = simpleDateFormat.parse("1991-02-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservationDAO.saveReservation(reservation);
        List<Reservation> reservations = reservationDAO.getAllReservations();
        int prevSize = reservations.size();
        reservationDAO.deleteReservation(reservation.getReservationId());
        List<Reservation> reservationsAfterDelete = reservationDAO.getAllReservations();
        int newSize = reservationsAfterDelete.size();
        Assertions.assertEquals(prevSize - 1, newSize);
    }

    @Test
    @DisplayName("Test update car")
    void testUpdateReservationCar() {
        long carId = 21;
        reservationDAO.updateReservationCar(testReservationId, carId);
        Reservation afterUpdate = reservationDAO.getReservation(testReservationId);
        long newId = afterUpdate.getCarId();
        Assertions.assertEquals(carId, newId);
    }

    @Test
    @DisplayName("Test update customer")
    void testUpdateReservationCustomer() {
        long customerId = 3;
        reservationDAO.updateReservationCustomer(testReservationId, customerId);
        Reservation afterUpdate = reservationDAO.getReservation(testReservationId);
        long newId = afterUpdate.getCustomerId();
        Assertions.assertEquals(customerId, newId);
    }

    @Test
    @DisplayName("Test update start date")
    void testUpdateReservationStart() {
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date startDate = null;
        try {
            startDate = simpleDateFormat.parse("2030-02-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        reservationDAO.updateReservationStart(testReservationId, startDate);
        Reservation afterUpdate = reservationDAO.getReservation(testReservationId);
        String newDate = afterUpdate.getStartDate().toString();
        Assertions.assertEquals("2030-02-01 00:00:00.0", newDate);
    }

    @Test
    @DisplayName("Test update end date")
    void testUpdateReservationEnd() {
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date endDate = null;
        try {
            endDate = simpleDateFormat.parse("2030-05-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        reservationDAO.updateReservationEnd(testReservationId, endDate);
        Reservation afterUpdate = reservationDAO.getReservation(testReservationId);
        String newDate = afterUpdate.getEndDate().toString();
        Assertions.assertEquals("2030-05-01 00:00:00.0", newDate);
    }

    @Test
    @DisplayName("Test get all reservations")
    void testGetAllReservations() {
        List<Reservation> reservations = reservationDAO.getAllReservations();
        Assertions.assertEquals(4, reservations.size());
    }

}