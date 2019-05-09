package com.seweryn91.CarReservations.dao;

import com.seweryn91.CarReservations.database.ConnectionDriver;
import com.seweryn91.CarReservations.model.Customer;

import java.sql.*;

public class CustomerDAO {

    void addCustomer(Customer customer) throws SQLException {

        String query = "INSERT INTO customer VALUES (?, ?, ?, ?, ?, ?, ?, ?) ON CONFLICT DO NOTHING;";

        try (
                Connection connection = ConnectionDriver.getConnection();
                PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
                ) {

            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPhone());
            statement.setString(5, customer.getCountry());
            statement.setString(6, customer.getCity());
            statement.setString(7, customer.getZipcode());
            statement.setString(8, customer.getZipcode());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Adding customer to database failed, no rows affected! \n" +
                        "Prossibly user already exists or email was used before");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    customer.setCustomerId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Inserting customer failed, no ID obtained!");
                }
            }

        }
    }

    public void deleteCustomer(Customer customer) throws SQLException {

        String query = "DELETE FROM customer WHERE customer_id = ?";

        try (
                Connection connection = ConnectionDriver.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setLong(1, customer.getCustomerId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting customer failed, no rows affected!");
            }
        }
    }
}
