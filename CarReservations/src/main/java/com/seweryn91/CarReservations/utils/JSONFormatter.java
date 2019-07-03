package com.seweryn91.CarReservations.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.seweryn91.CarReservations.model.Car;
import com.seweryn91.CarReservations.model.Customer;
import com.seweryn91.CarReservations.model.Reservation;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Component
public class JSONFormatter {

    public String serialize(Car car) {
        Gson carGson = new Gson();
        return carGson.toJson(car);
    }

    public String serialize(Optional<Car> car) {
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

    private Object fromJson(String json, Type type) {
        return new Gson().fromJson(json, type);
    }

    @SuppressWarnings("unchecked")
    public List<Car> deserializeCollectionCars(String carsJson) {
        return (List<Car>) fromJson(carsJson, new TypeToken<List<Car>>(){}.getType());
    }

    @SuppressWarnings("unchecked")
    public List<Customer> deserializeCollectionCustomers(String customersJson) {
        return (List<Customer>) fromJson(customersJson, new TypeToken<List<Customer>>(){}.getType());
    }

    @SuppressWarnings("unchecked")
    public List<Reservation> deserializeCollectionReservation(String reservationsJson) {
        return (List<Reservation>) fromJson(reservationsJson, new TypeToken<List<Reservation>>(){}.getType());
    }
}
