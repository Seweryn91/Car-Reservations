package com.seweryn91.CarReservations.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDriver {
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/carreservations",
                    "postgres", "postgres");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            System.out.println("Opened database successfully");
        }
        return con;
    }
}
