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

    //@BeforeAll
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
        Assertions.assertNotNull(carDAO.getCar(1));
    }

    @DisplayName("Test save car in DB")
    @Test
    void testSaveCar() {
        setup();
        Car car = createCar();
        carDAO.saveCar(car);
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

    }

    @Test
    void testGetCarsOfCategory() {
    }

    @Test
    void testDeleteCar() {
    }

    @Test
    void testGetCarById() {
    }

    @Test
    void testUpdateCar() {
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