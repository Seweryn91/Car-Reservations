package com.seweryn91.CarReservations.dao;

import com.seweryn91.CarReservations.model.Car;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CarDAOTest {

    @Autowired
    private CarDAO carDAO;
    private SessionFactory sessionFactory;
    private long testCarId;

    @BeforeAll
    void before() {
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
        carDAO.saveCar(car);
        testCarId = car.getCarId();
    }

    @AfterAll
    void after() {
        carDAO.deleteCar(testCarId);
    }


    @Test
    @DisplayName("Test get car from DB")
    void testGetCar() {
        Car car = carDAO.getCarById(1L);
        Assertions.assertNotNull(car);
        Assertions.assertEquals("Opel", car.getBrand());
        Assertions.assertEquals("Adam", car.getModel());
    }

    @Test
    @DisplayName("Test save car in DB")
    void testSaveCar() {
        List<Car> cars = new ArrayList<>();
        carDAO.findAllCars().forEach(cars::add);
        int prevSize = cars.size();
        Car car = new Car();
        car.setModel("Model");
        car.setBrand("Brand");
        car.setPrice(3.0);
        carDAO.saveCar(car);
        long testCarId2 = car.getCarId();
        List<Car> carsAfterInsert = new ArrayList<>();
        carDAO.findAllCars().forEach(carsAfterInsert::add);
        int newSize = carsAfterInsert.size();
        Assertions.assertEquals(prevSize + 1, newSize);
        carDAO.deleteCar(testCarId2);
    }

    @Test
    @DisplayName("Test find all cars")
    void testFindAllCars() {
        List<Car> carsRetrieved = new ArrayList<>();
        carDAO.findAllCars().forEach(carsRetrieved::add);
        int expectedLength = 22;
        int actualLength = carsRetrieved.size();
        Assertions.assertEquals(expectedLength, actualLength);
    }

    @Test
    @DisplayName("Test find cars of category Economic")
    void testGetCarsOfCategoryEconomic() {
        List<Car> economicCars = new ArrayList<>();
        carDAO.getCarsOfCategory("Economic").forEach(economicCars::add);
        int expectedLength = 7;
        int actualLength = economicCars.size();
        Assertions.assertEquals(expectedLength, actualLength);
    }

    @Test
    @DisplayName("Test find cars of category Compact")
    void testGetCarsOfCategoryCompact() {
        List<Car> compactCars = new ArrayList<>();
        carDAO.getCarsOfCategory("Compact").forEach(compactCars::add);
        int expectedLength = 4;
        int actualLength = compactCars.size();
        Assertions.assertEquals(expectedLength, actualLength);
    }

    @Test
    @DisplayName("Test delete car")
    void testDeleteCar() {
        Car car = new Car();
        car.setBrand("Fake");
        car.setModel("0000");
        car.setCategory("Dummy");
        carDAO.saveCar(car);
        List<Car> carsList = new ArrayList<>();
        carDAO.findAllCars().forEach(carsList::add);
        Long carToDeleteId = car.getCarId();
        carDAO.deleteCar(carToDeleteId);
        int carsListLength = carsList.size();
        List<Car> carsAfterDelete = new ArrayList<>();
        carDAO.findAllCars().forEach(carsAfterDelete::add);
        int newCarsListLength = carsAfterDelete.size();
        Assertions.assertEquals(carsListLength-1, newCarsListLength);
    }

    @Test
    @DisplayName("Test update price")
    void testUpdateCarPrice() {
        long carToUpdateId = testCarId;
        double newPrice = 7.5;
        carDAO.updateCarPrice(carToUpdateId, newPrice);
        Car carAfterUpdate = carDAO.getCarById(testCarId);
        Assertions.assertEquals(newPrice, carAfterUpdate.getPrice());
    }

    @Test
    @DisplayName("Test update brand")
    void testUpdateCarBrand() {
        long carToUpdateId = testCarId;
        String brand = "TestBrand";
        carDAO.updateCarBrand(carToUpdateId, brand);
        Car carAfterUpdate = carDAO.getCarById(carToUpdateId);
        Assertions.assertEquals(brand, carAfterUpdate.getBrand());
    }

    @Test
    @DisplayName("Test update year")
    void testUpdateCarYear() {
        long carToUpdateId = testCarId;
        int year = 1410;
        carDAO.updateCarYear(carToUpdateId, year);
        Car carAfterUpdate = carDAO.getCarById(carToUpdateId);
        Assertions.assertEquals(year, carAfterUpdate.getYear());
    }

    @Test
    @DisplayName("Test update category")
    void testUpdateCarCategory() {
        long carToUpdateId = testCarId;
        String category = "Heckin awesome";
        carDAO.updateCarCategory(carToUpdateId, category);
        Car carAfterUpdate = carDAO.getCarById(carToUpdateId);
        Assertions.assertEquals(category, carAfterUpdate.getCategory());
    }

    @Test
    @DisplayName("Test update car model")
    void testUpdateCarModel() {
        long carToUpdateId = testCarId;
        String model = "2137";
        carDAO.updateCarModel(carToUpdateId, model);
        Car carAfterUpdate = carDAO.getCarById(carToUpdateId);
        Assertions.assertEquals(model, carAfterUpdate.getModel());
    }

    @Test
    @DisplayName("Test update seats")
    void testUpdateCarSeats() {
        long carToUpdateId = testCarId;
        int seats = 999;
        carDAO.updateCarSeats(carToUpdateId, seats);
        Car carAfterUpdate = carDAO.getCarById(carToUpdateId);
        Assertions.assertEquals(seats, carAfterUpdate.getSeats());
    }

    @Test
    @DisplayName("Test update doors")
    void testUpdateCarDoors() {
        long carToUpdateId = testCarId;
        int doors = 15;
        carDAO.updateCarDoors(carToUpdateId, 15);
        Car carAfterUpdate = carDAO.getCarById(carToUpdateId);
        Assertions.assertEquals(doors, carAfterUpdate.getDoors());

    }

    @Test
    @DisplayName("Test update gearbox")
    void testUpdateCarGearbox() {
        long carToUpdateId = testCarId;
        boolean isAutomaticGearbox = true;
        carDAO.updateCarGearbox(carToUpdateId, isAutomaticGearbox);
        Car carAfterUpdate = carDAO.getCarById(carToUpdateId);
        Assertions.assertTrue(carAfterUpdate.isAutomaticGearbox());
    }


    @Test
    @DisplayName("Test update AC")
    void testUpdateCarAC() {
        long carToUpdateId = testCarId;
        boolean isAutomaticAC = true;
        carDAO.updateCarAutomaticAC(carToUpdateId, isAutomaticAC);
        Car carAfterUpdate = carDAO.getCarById(carToUpdateId);
        Assertions.assertTrue(carAfterUpdate.isAutomaticAC());
    }

    @Test
    @DisplayName("Test update image")
    void testUpdateImage() {
        long carToUpdateId = testCarId;
        String imageSource = "http://clipart-library.com/data_images/269418.png";
        carDAO.updateCarImageSource(carToUpdateId, imageSource);
        Car carAfterUpdate = carDAO.getCarById(carToUpdateId);
        Assertions.assertEquals(imageSource, carAfterUpdate.getImageSource());
    }


}