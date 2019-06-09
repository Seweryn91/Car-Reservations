package com.seweryn91.CarReservations.controller;

import com.seweryn91.CarReservations.dao.CarDAO;
import com.seweryn91.CarReservations.model.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CarController {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private CarDAO carDAO;

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    @ResponseBody
    public String getAllCars() {
        StringBuilder sb = new StringBuilder();
        try {
            List<Car> cars = carDAO.findAllCars();
            for (Car car : cars)
                sb.append(car.getBrand()).append(" ").append(car.getModel()).append("<br>");
        } catch (Exception e) {
            return e.toString();
        }
        return sb.toString();
    }

    @RequestMapping(value = "/compact", method = RequestMethod.GET)
    @ResponseBody
    public String getAllCompact() {
        StringBuilder sb = new StringBuilder();
        try {
            List<Car> cars = carDAO.getCarsOfCategory("Compact");
            for (Car car : cars)
                sb.append(car.getBrand()).append(" ").append(car.getModel()).append("<br>");
        } catch (Exception e) {
                return e.toString();
        }
        return sb.toString();
    }
}
