package com.seweryn91.CarReservations.dao;

import com.seweryn91.CarReservations.model.Car;
import org.hibernate.SessionFactory;
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


    @DisplayName("Test get car from DB")
    @Test
    void testGetCar() {
        Car car = carDAO.getCarById(1);
        Assertions.assertNotNull(car);
        Assertions.assertEquals("Opel", car.getBrand());
        Assertions.assertEquals("Adam", car.getModel());
        carDAO.deleteCar(26);
    }

    @DisplayName("Test save car in DB")
    @Test
    void testSaveCar() {
        Car car = createCar();
        int originalNumberOfCars = carDAO.findAllCars().size();
        carDAO.saveCar(car);
        int newNumberOfCars = carDAO.findAllCars().size();
        Assertions.assertEquals(originalNumberOfCars + 1, newNumberOfCars);
        List<Car> testCarList = carDAO.getCarsOfCategory("Test");
        Car carFromList = testCarList.get(0);
        Assertions.assertEquals(car.getBrand(), carFromList.getBrand());
        Assertions.assertEquals(car.getModel(), carFromList.getModel());
        Assertions.assertEquals(car.getCategory(), carFromList.getCategory());
        Assertions.assertEquals(car.getPrice(), carFromList.getPrice());
        Assertions.assertEquals(car.getDoors(), carFromList.getDoors());
        Assertions.assertEquals(car.getYear(), carFromList.getYear());
        carDAO.deleteCar(carFromList.getCarId());
    }

    @Test
    void testFindAllCars() {
        List<Car> carsRetrieved = carDAO.findAllCars();
        int expectedLength = 21;
        int actualSize = carsRetrieved.size();
        Assertions.assertEquals(expectedLength, actualSize);
    }

    @Test
    void testGetCarsOfCategory() {
        List<Car> economicCars = carDAO.getCarsOfCategory("Economic");
        int expectedLength = 7;
        int actualLength = economicCars.size();
        Assertions.assertEquals(expectedLength, actualLength);

        List<Car> compactCars = carDAO.getCarsOfCategory("Compact");
        expectedLength = 4;
        actualLength = compactCars.size();
        Assertions.assertEquals(expectedLength, actualLength);
    }

    @Test
    void testDeleteCar() {
        carDAO.saveCar(createCar());
        List<Car> carsList = carDAO.findAllCars();
        Car carToDelete = carDAO.getCarsOfCategory("Test").get(0);
        int carsListLength = carsList.size();
        carDAO.deleteCar(carToDelete.getCarId());
        int newCarsListLength = carDAO.findAllCars().size();
        Assertions.assertEquals(carsListLength-1, newCarsListLength);
    }

    @Test
    void testUpdateCarPrice() {
        carDAO.saveCar(createCar());
        long carToUpdateId = carDAO.getCarsOfCategory("Test").get(0).getCarId();
        double newPrice = 7.5;
        carDAO.updateCarPrice(carToUpdateId, newPrice);
        Car carAfterUpdate = carDAO.getCarById(carToUpdateId);
        Assertions.assertNotNull(carAfterUpdate);
        double price = carAfterUpdate.getPrice();
        Assertions.assertEquals(7.5, price);
        carDAO.deleteCar(carAfterUpdate.getCarId());

    }

    @Test
    void testUpdateCarBrand() {
        carDAO.saveCar(createCar());
        long cartoUpdateId = carDAO.getCarsOfCategory("Test").get(0).getCarId();
        String brand = "TestBrand";
        carDAO.updateCarBrand(cartoUpdateId, brand);
        Car carAfterUpdate = carDAO.getCarById(cartoUpdateId);
        Assertions.assertEquals(brand, carAfterUpdate.getBrand());
        carDAO.deleteCar(carAfterUpdate.getCarId());
    }

    @Test
    void testUpdateCarYear() {
        carDAO.saveCar(createCar());
        long cartoUpdateId = carDAO.getCarsOfCategory("Test").get(0).getCarId();
        int year = 1410;
        carDAO.updateCarYear(cartoUpdateId, year);
        Car carAfterUpdate = carDAO.getCarById(cartoUpdateId);
        Assertions.assertEquals(year, carAfterUpdate.getYear());
        carDAO.deleteCar(carAfterUpdate.getCarId());
    }

    @Test
    void testUpdateCarCategory() {
        carDAO.saveCar(createCar());
        long cartoUpdateId = carDAO.getCarsOfCategory("Test").get(0).getCarId();
        String category = "Heckin awesome";
        carDAO.updateCarCategory(cartoUpdateId, category);
        Car carAfterUpdate = carDAO.getCarById(cartoUpdateId);
        Assertions.assertEquals(category, carAfterUpdate.getCategory());
        carDAO.deleteCar(carAfterUpdate.getCarId());
    }

    @Test
    void testUpdateCarSeats() {
        carDAO.saveCar(createCar());
        long cartoUpdateId = carDAO.getCarsOfCategory("Test").get(0).getCarId();
        int seats = 999;
        carDAO.updateCarSeats(cartoUpdateId, seats);
        Car carAfterUpdate = carDAO.getCarById(cartoUpdateId);
        Assertions.assertEquals(seats, carAfterUpdate.getSeats());
        carDAO.deleteCar(carAfterUpdate.getCarId());
    }

    @Test
    void testUpdateCarDoors() {
        carDAO.saveCar(createCar());
        long cartoUpdateId = carDAO.getCarsOfCategory("Test").get(0).getCarId();
        int doors = 0;
        carDAO.updateCarDoors(cartoUpdateId, 0);
        Car carAfterUpdate = carDAO.getCarById(cartoUpdateId);
        Assertions.assertEquals(0, carAfterUpdate.getCategory());
        carDAO.deleteCar(carAfterUpdate.getCarId());
    }

    Car createCar() {
        Car car = new Car();
        car.setBrand("Lada");
        car.setModel("2105");
        car.setYear(1980);
        car.setPrice(8.5);
        car.setCategory("Test");
        car.setSeats(5);
        car.setDoors(4);
        car.setAutomaticAC(false);
        car.setAutomaticGearbox(false);
        return car;
    }
}