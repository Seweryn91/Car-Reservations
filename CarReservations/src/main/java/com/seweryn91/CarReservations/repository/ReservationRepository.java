package com.seweryn91.CarReservations.repository;

import com.seweryn91.CarReservations.model.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
}
