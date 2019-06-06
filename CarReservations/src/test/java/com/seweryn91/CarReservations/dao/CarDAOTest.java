package com.seweryn91.CarReservations.dao;

import com.seweryn91.CarReservations.model.Car;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
class CarDAOTest {

    @Autowired
            CarDAO carDAO;
    SessionFactory sessionFactory;

    void setup() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Car.class)
            .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
            .setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
            .setProperty("username", "postgres").setProperty("password", "postgres")
            .setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/carreservations?currentSchema=public&user=postgres&password=postgres")
            .setProperty("hibernate.hbm2ddl.auto", "none");

        sessionFactory = configuration.buildSessionFactory();
        sessionFactory.openSession();
        }

    @DisplayName("Test get car from DB")
    @Test
    void testGetCar() {
        Car car = carDAO.getCar(1);
        Assertions.assertNotNull(car);
        Assertions.assertEquals("Opel", car.getBrand());
        Assertions.assertEquals("Adam", car.getModel());
    }

    @DisplayName("Test save car in DB")
    @Test
    void testSaveCar() {
        setup();
        Car car = createCar();
        int originalNumberOfCars = carDAO.findAllCars().size();
        carDAO.saveCar(car);
        int newNumberOfCars = carDAO.findAllCars().size();
        Assertions.assertEquals(originalNumberOfCars + 1, newNumberOfCars + 1);
        List<Car> testCarList = carDAO.getCarsOfCategory("Crap");
        Car carFromList = testCarList.get(0);
        Assertions.assertEquals(car.getBrand(), carFromList.getBrand());
        Assertions.assertEquals(car.getModel(), carFromList.getModel());
        Assertions.assertEquals(car.getCategory(), carFromList.getCategory());
        Assertions.assertEquals(car.getPrice(), carFromList.getPrice());
        Assertions.assertEquals(car.getDoors(), carFromList.getDoors());
        Assertions.assertEquals(car.getYear(), carFromList.getYear());
    }

    @Test
    void testFindAllCars() {
        List<Car> carsRetrieved;

    }

    @Test
    void testGetCarsOfCategory() {
    }

    @Test
    void testDeleteCar() {
        List<Car> carsList;
        Car carToDelete = carDAO.getCarsOfCategory("Crap").get(0);
        carsList = carDAO.findAllCars();
        int carsListLength = carsList.size();
        long carToDeleteId = carToDelete.getCarId();
        carDAO.deleteCar(carToDeleteId);
        int newCarsListLength = carDAO.findAllCars().size();
        Assertions.assertEquals(carsListLength-1, newCarsListLength);
    }

    @Test
    void testGetCarById() {
    }

    @Test
    void testUpdateCarPrice() {
        long carToUpdateId = carDAO.getCarsOfCategory("Crap").get(0).getCarId();
        double newPrice = 7.5;
        carDAO.updateCarPrice(carToUpdateId, newPrice);
        Car carAfterUpdate = carDAO.getCarById(carToUpdateId);
        Assertions.assertNotNull(carAfterUpdate);
        double price = carAfterUpdate.getPrice();
        Assertions.assertEquals(7.5, price);

    }

    Car createCar() {
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