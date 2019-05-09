package com.seweryn91.CarReservations.dao;

import com.seweryn91.CarReservations.database.ConnectionDriver;
import com.seweryn91.CarReservations.model.Car;

import java.sql.*;

public class CarDAO {

    private Connection connectionDriver = ConnectionDriver.getConnection();

    public void addCar(Car car) throws SQLException {
        try (
                Connection connectionDriver = ConnectionDriver.getConnection();
                PreparedStatement statement = connectionDriver.prepareStatement("INSERT INTO " +
                "car(brand, model, year, category, seats, automaticGearbox, automaticAC)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)
                ) {

            statement.setString(1, car.getBrand());
            statement.setString(2, car.getModel());
            statement.setInt(3, car.getYear());
            statement.setString(4, car.getCategory());
            statement.setInt(5, car.getSeats());
            statement.setBoolean(6, car.isAutomaticGearbox());
            statement.setBoolean(7, car.isAutomaticAC());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0)
                throw new SQLException("Adding car to database failed, no rows affected!");

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    car.setCarId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Inserting car failed, no ID obtained!");
                }
            }
        }
    }
}
