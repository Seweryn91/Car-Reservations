package com.seweryn91.CarReservations.controller;

import com.seweryn91.CarReservations.dao.CarDAO;
import com.seweryn91.CarReservations.model.Car;
import com.seweryn91.CarReservations.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CarController {

    @Autowired
    private CarDAO carDAO;

    @Autowired
    private CarService carService;

    private List<Car> cars = new ArrayList<>();

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public List<Car> getAllCars() {
        if (cars.isEmpty()) {
            carDAO.findAllCars().forEach(cars::add);
        }
        return cars;
    }

    @RequestMapping(value = "/cars/compact", method = RequestMethod.GET)
    public List<Car> getAllCompact() {
        List<Car> compacts = new ArrayList<>();
        if (cars.isEmpty()) {
            carDAO.findAllCars().forEach(cars::add);
        }
        cars.stream().filter(c -> c.getCategory().equals("Compact")).forEach(compacts::add);
        return compacts;
    }

    @RequestMapping(value = "/cars/economic", method = RequestMethod.GET)
    public List<Car> getAllEconomic() {
        List<Car> economics = new ArrayList<>();
        if (cars.isEmpty()) {
            carDAO.findAllCars().forEach(cars::add);
        }
        cars.stream().filter(c -> c.getCategory().equals("Economic")).forEach(economics::add);
        return economics;
    }

    @RequestMapping(value = "/cars/minivan", method = RequestMethod.GET)
    public List<Car> getAllMinivan() {
        List<Car> minivans = new ArrayList<>();
        if (cars.isEmpty()) {
            carDAO.findAllCars().forEach(cars::add);
        }
        cars.stream().filter(c -> c.getCategory().equals("Minivan")).forEach(minivans::add);
       return minivans;
    }

    @RequestMapping(value = "/cars/suv", method = RequestMethod.GET)
    public List<Car> getAllSUV() {
        List<Car> suvs = new ArrayList<>();
        if (cars.isEmpty()) {
            carDAO.findAllCars().forEach(cars::add);
        }
        cars.stream().filter(c -> c.getCategory().equals("SUV")).forEach(suvs::add);
        return suvs;
    }

    @RequestMapping(value = "/cars/family", method = RequestMethod.GET)
    public List<Car> getAllFamily() {
        List<Car> families = new ArrayList<>();
        if (cars.isEmpty()) {
            carDAO.findAllCars().forEach(cars::add);
        }
        cars.stream().filter(c -> c.getCategory().equals("Family")).forEach(families::add);
        return families;
    }

    @RequestMapping(value = "/cars/airconditioning", method = RequestMethod.GET)
    public List<Car> getAllAirConditioning() {
        if (cars.isEmpty()) {
            carDAO.findAllCars().forEach(cars::add);
        }
        List<Car> carsWithAC = new ArrayList<>();
        cars.stream().filter(Car::isAutomaticAC).forEach(carsWithAC::add);
        return carsWithAC;
    }

    @RequestMapping(value = "/cars/today")
    public List<Car> getCarsAvailableToday() {
        List<Car> carsAvailable = carService.getAllCarsAvailableToday();
        return carsAvailable;
    }

    @RequestMapping(value = "/cars/{carId}", method = RequestMethod.GET)
    public Car getCar(@PathVariable(value = "carId") Long carId) {
        Car car = carDAO.getCarById(carId);
        if (car == null) throw new EntityNotFoundException("Provided incorrect ID");
        return car;
    }

}