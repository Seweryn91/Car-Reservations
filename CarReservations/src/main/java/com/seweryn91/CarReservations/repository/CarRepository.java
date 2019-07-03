package com.seweryn91.CarReservations.repository;

import com.seweryn91.CarReservations.model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository <Car, Long> {
}
