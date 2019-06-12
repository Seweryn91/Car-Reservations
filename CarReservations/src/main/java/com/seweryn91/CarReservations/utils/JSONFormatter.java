package com.seweryn91.CarReservations.utils;

import com.google.gson.Gson;
import com.seweryn91.CarReservations.model.Car;
import com.seweryn91.CarReservations.model.Customer;
import com.seweryn91.CarReservations.model.Reservation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JSONFormatter {

    public String serialize(Car car) {
        Gson carGson = new Gson();
        return carGson.toJson(car);
    }

    public String serialize(Customer customer) {
        Gson customerGson = new Gson();
        return customerGson.toJson(customer);
    }

    public String serialize(Reservation reservation) {
       Gson reservationGson = new Gson();
       return reservationGson.toJson(reservation);
    }

    public Car deserializeCar(String carJson) {
        return new Gson().fromJson(carJson, Car.class);
    }

    public Customer deserializeCustomer(String customerJson) {
        return new Gson().fromJson(customerJson, Customer.class);
    }

    public Reservation deserializeReservation(String reservationJson) {
        return new Gson().fromJson(reservationJson, Reservation.class);
    }

    public String serializeCollectionCars(List<Car> cars) {
        Gson carsGson = new Gson();
        return carsGson.toJson(cars);
    }

    public String serializeCollectionCustomers(List<Customer> customers) {
        Gson customersGson = new Gson();
        return customersGson.toJson(customers);
    }

    public String serializeCollectionReservations(List<Reservation> reservations) {
        Gson reservationsGson = new Gson();
        return reservationsGson.toJson(reservations);
    }
}
