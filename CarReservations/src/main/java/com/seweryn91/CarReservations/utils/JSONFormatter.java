package com.seweryn91.CarReservations.utils;

import com.google.gson.Gson;
import com.seweryn91.CarReservations.model.Car;
import com.seweryn91.CarReservations.model.Customer;
import com.seweryn91.CarReservations.model.Reservation;
import org.springframework.stereotype.Component;

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


}
