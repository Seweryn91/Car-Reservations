package com.seweryn91.CarReservations.controller;

import com.seweryn91.CarReservations.dao.CarDAO;
import com.seweryn91.CarReservations.model.Car;
import com.seweryn91.CarReservations.utils.JSONFormatter;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CarController {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private CarDAO carDAO;

    @Autowired
    private JSONFormatter jsonFormatter;

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public String getAllCars() {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append(jsonFormatter.serializeCollectionCars(carDAO.findAllCars()));
        } catch (Exception e) {
            return e.toString();
        }
        return sb.toString();
    }

    @RequestMapping(value = "cars/compact", method = RequestMethod.GET)
    public String getAllCompact() {
        StringBuilder sb = new StringBuilder();
        try {
            List<Car> cars = carDAO.getCarsOfCategory("Compact");
            sb.append(jsonFormatter.serializeCollectionCars(cars));
        } catch (Exception e) {
            return e.toString();
        }
        return sb.toString();
    }

    @RequestMapping(value = "cars/economic", method = RequestMethod.GET)
    public String getAllEconomic() {
        StringBuilder sb = new StringBuilder();
        try {
            List<Car> cars = carDAO.getCarsOfCategory("Economic");
            sb.append(jsonFormatter.serializeCollectionCars(cars));
        } catch (Exception e) {
            return e.toString();
        }
        return sb.toString();
    }

    @RequestMapping(value = "cars/minivan", method = RequestMethod.GET)
    public String getAllMinivan() {
        StringBuilder sb = new StringBuilder();
        try {
            List<Car> cars = carDAO.getCarsOfCategory("Minivan");
            sb.append(jsonFormatter.serializeCollectionCars(cars));
        } catch (Exception e) {
            return e.toString();
        }
        return sb.toString();
    }

    @RequestMapping(value = "cars/suv", method = RequestMethod.GET)
    public String getAllSUV() {
        StringBuilder sb = new StringBuilder();
        try {
            List<Car> cars = carDAO.getCarsOfCategory("SUV");
            sb.append(jsonFormatter.serializeCollectionCars(cars));
        } catch (Exception e) {
            return e.toString();
        }
        return sb.toString();
    }

    @RequestMapping(value = "cars/family", method = RequestMethod.GET)
    public String getAllFamily() {
        StringBuilder sb = new StringBuilder();
        try {
            List<Car> cars = carDAO.getCarsOfCategory("Family");
            sb.append(jsonFormatter.serializeCollectionCars(cars));
        } catch (Exception e) {
            return e.toString();
        }
        return sb.toString();
    }

    @RequestMapping(value = "/airconditioning", method = RequestMethod.GET)
    public String getAllAirConditioning() {
        StringBuilder sb = new StringBuilder();
        try {
        List<Car> cars = carDAO.findAllCars();
        List<Car> carsWithAC = cars.stream()
               .filter( car -> car.isAutomaticAC()).collect(Collectors.toList());
            sb.append(jsonFormatter.serializeCollectionCars(carsWithAC));
        } catch (Exception e) {
            return e.toString();
        }
        return sb.toString();
    }

    @RequestMapping(value = "/today")
    public String getCarsAvailableToday() {
        StringBuilder sb = new StringBuilder();
        try {
            List<Car> carsAvailable = carDAO.getAllCarsAvailableToday();
            sb.append(jsonFormatter.serializeCollectionCars(carsAvailable));
        } catch (Exception e) {
            return e.toString();
        }
        return sb.toString();
    }

    @RequestMapping(value = "/cars/{carId}", method = RequestMethod.GET)
    public String getCar(@PathVariable(value = "carId") Long carId) {
        //TODO: Implement fool-proofing
        String carString = "";
        try {
        Car car = carDAO.getCarById(carId);
        if (car == null) throw new IllegalArgumentException("Car not found");
        carString = jsonFormatter.serialize(car);
        } catch (Exception e) {
            return e.toString();
        }
        return carString;
    }

}