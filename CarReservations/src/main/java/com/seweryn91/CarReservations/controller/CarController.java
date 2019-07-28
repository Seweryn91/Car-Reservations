package com.seweryn91.CarReservations.controller;

import com.seweryn91.CarReservations.dao.CarDAO;
import com.seweryn91.CarReservations.model.Car;
import com.seweryn91.CarReservations.service.CarService;
import com.seweryn91.CarReservations.utils.JSONFormatter;
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
    private JSONFormatter jsonFormatter;

    @Autowired
    private CarDAO carDAO;

    @Autowired
    private CarService carService;

    private List<Car> cars = new ArrayList<>();

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public String getAllCars() {
        StringBuilder sb = new StringBuilder();
        if (cars.isEmpty()) {
            carDAO.findAllCars().forEach(cars::add);
        }
        sb.append(jsonFormatter.serializeCollectionCars(cars));
        return sb.toString();
    }

    @RequestMapping(value = "/cars/compact", method = RequestMethod.GET)
    public String getAllCompact() {
        StringBuilder sb = new StringBuilder();
        List<Car> compacts = new ArrayList<>();
        if (cars.isEmpty()) {
            carDAO.findAllCars().forEach(cars::add);
        }
        cars.stream().filter(c -> c.getCategory().equals("Compact")).forEach(compacts::add);
        sb.append(jsonFormatter.serializeCollectionCars(compacts));
        return sb.toString();
    }

    @RequestMapping(value = "/cars/economic", method = RequestMethod.GET)
    public String getAllEconomic() {
        StringBuilder sb = new StringBuilder();
        List<Car> economics = new ArrayList<>();
        if (cars.isEmpty()) {
            carDAO.findAllCars().forEach(cars::add);
        }
        cars.stream().filter(c -> c.getCategory().equals("Economic")).forEach(economics::add);
        sb.append(jsonFormatter.serializeCollectionCars(economics));
        return sb.toString();
    }

    @RequestMapping(value = "/cars/minivan", method = RequestMethod.GET)
    public String getAllMinivan() {
        StringBuilder sb = new StringBuilder();
        List<Car> minivans = new ArrayList<>();
        if (cars.isEmpty()) {
            carDAO.findAllCars().forEach(cars::add);
        }
        cars.stream().filter(c -> c.getCategory().equals("Minivan")).forEach(minivans::add);
        sb.append(jsonFormatter.serializeCollectionCars(minivans));
        return sb.toString();
    }

    @RequestMapping(value = "/cars/suv", method = RequestMethod.GET)
    public String getAllSUV() {
        StringBuilder sb = new StringBuilder();
        List<Car> suvs = new ArrayList<>();
        if (cars.isEmpty()) {
            carDAO.findAllCars().forEach(cars::add);
        }
        cars.stream().filter(c -> c.getCategory().equals("SUV")).forEach(suvs::add);
        sb.append(jsonFormatter.serializeCollectionCars(suvs));
        return sb.toString();
    }

    @RequestMapping(value = "/cars/family", method = RequestMethod.GET)
    public String getAllFamily() {
        StringBuilder sb = new StringBuilder();
        List<Car> families = new ArrayList<>();
        if (cars.isEmpty()) {
            carDAO.findAllCars().forEach(cars::add);
        }
        cars.stream().filter(c -> c.getCategory().equals("Family")).forEach(families::add);
        sb.append(jsonFormatter.serializeCollectionCars(families));
        return sb.toString();
    }

    @RequestMapping(value = "/cars/airconditioning", method = RequestMethod.GET)
    public String getAllAirConditioning() {
        StringBuilder sb = new StringBuilder();
        if (cars.isEmpty()) {
            carDAO.findAllCars().forEach(cars::add);
        }
        List<Car> carsWithAC = new ArrayList<>();
        cars.stream().filter(Car::isAutomaticAC).forEach(carsWithAC::add);
        sb.append(jsonFormatter.serializeCollectionCars(carsWithAC));
        return sb.toString();
    }

    @RequestMapping(value = "/cars/today")
    public String getCarsAvailableToday() {
        StringBuilder sb = new StringBuilder();
        List<Car> carsAvailable = carService.getAllCarsAvailableToday();
        sb.append(jsonFormatter.serializeCollectionCars(carsAvailable));
        return sb.toString();
    }

    @RequestMapping(value = "/cars/{carId}", method = RequestMethod.GET)
    public String getCar(@PathVariable(value = "carId") Long carId) {
        String carString = "";
        Car car = carDAO.getCarById(carId);
        if (car == null) throw new EntityNotFoundException("Provided incorrect ID");
        carString = jsonFormatter.serialize(car);
        return carString;
    }

}