package com.seweryn91.CarReservations.dao;

import com.seweryn91.CarReservations.model.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CarDAOTest {

    @Autowired
            CarDAO carDAO;

    @Test
    void getCar() {
        Assertions.assertNotNull(carDAO.getCar(1));
    }

    @Test
    void saveCar() {
        Car car = setup();
        carDAO.saveCar(car);

    }

    @Test
    void findAllCars() {

    }

    @Test
    void getCarsOfCategory() {
    }

    @Test
    void deleteCar() {
    }

    @Test
    void getCarById() {
    }

    @Test
    void updateCar() {
    }

    Car setup() {
        Car car = new Car();
        car.setBrand("Lada");
        car.setModel("2105");
        car.setYear(1980);
        car.setPrice(8.5);
        car.setCategory("Crap");
        car.setSeats(5);
        car.setDoors(4);
        car.setAutomaticAC(false);
        car.setAutomaticGearbox(false);
        return car;
    }
}