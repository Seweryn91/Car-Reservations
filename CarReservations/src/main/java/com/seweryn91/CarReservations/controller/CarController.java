package com.seweryn91.CarReservations.controller;

import com.seweryn91.CarReservations.dao.CarDAO;
import com.seweryn91.CarReservations.model.Car;
import com.seweryn91.CarReservations.utils.JSONFormatter;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/compact", method = RequestMethod.GET)
    public String getAllCompact() {
        StringBuilder sb = new StringBuilder();
        try {
            List<Car> cars = carDAO.getCarsOfCategory("Compact");
            for (Car car : cars)
                sb.append(jsonFormatter.serializeCollectionCars(cars));
        } catch (Exception e) {
            return e.toString();
        }
        return sb.toString();
    }

    @RequestMapping(value = "/economic", method = RequestMethod.GET)
    public String getAllEconomic() {
        StringBuilder sb = new StringBuilder();
        try {
            List<Car> cars = carDAO.getCarsOfCategory("Economic");
            for (Car car : cars)
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
            for (Car car : cars)
                sb.append(jsonFormatter.serializeCollectionCars(cars));
        } catch (Exception e) {
            return e.toString();
        }
        return sb.toString();
    }

    @RequestMapping(value = "/suv", method = RequestMethod.GET)
    public String getAllSUV() {
        StringBuilder sb = new StringBuilder();
        try {
            List<Car> cars = carDAO.getCarsOfCategory("SUV");
            for (Car car : cars)
                sb.append(jsonFormatter.serializeCollectionCars(cars));
        } catch (Exception e) {
            return e.toString();
        }
        return sb.toString();
    }

    @RequestMapping(value = "/family", method = RequestMethod.GET)
    public String getAllFamily() {
        StringBuilder sb = new StringBuilder();
        try {
            List<Car> cars = carDAO.getCarsOfCategory("Family");
            for (Car car : cars)
                sb.append(jsonFormatter.serializeCollectionCars(cars));
        } catch (Exception e) {
            return e.toString();
        }
        return sb.toString();
    }

    @RequestMapping(value = "/airconditioning", method = RequestMethod.GET)
    public String getAllAirConditioning() {
       List<Car> cars = carDAO.findAllCars();
       return cars.stream()
               .filter( car -> car.isAutomaticAC()).collect(Collectors.toList()).toString();
    }

    @RequestMapping(value = "/today")
    public String getCarsAvailableToday() {
        List<Car> carsAvailable = carDAO.getAllCarsAvailableToday();
        return jsonFormatter.serializeCollectionCars(carsAvailable);
    }

}