package com.seweryn91.CarReservations.dao;

import com.seweryn91.CarReservations.database.ConnectionDriver;
import com.seweryn91.CarReservations.model.Reservation;

import java.sql.*;

public class ReservationDAO {

    void addReservation(Reservation reservation) throws SQLException {

        String query = "INSERT INTO reservation VALUES (?, ?, ?, ?)";

        try (
                Connection connection = ConnectionDriver.getConnection();
                PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
                ) {
            statement.setLong(1, reservation.getCarId());
            statement.setLong(2, reservation.getCustomerId());
            statement.setDate(3, (Date) reservation.getStartDate());
            statement.setDate(4, (Date) reservation.getEndDate());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Adding reservation to database failed, no rows affected!");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    reservation.setReservationId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Inserting reservation to database failed, no ID obtained!");
                }
            }
        }
    }
}
