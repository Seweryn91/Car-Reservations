package com.seweryn91.CarReservations.dao;

import com.seweryn91.CarReservations.model.Reservation;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

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
    }

    @Test
    void testDeleteReservation() {
    }

    @Test
    void testUpdateReservationCar() {
    }

    @Test
    void testUpdateReservationCustomer() {
    }

    @Test
    void testUpdateReservationStart() {
    }

    @Test
    void testUpdateReservationEnd() {
    }

    @Test
    void testGetAllReservations() {
    }
}