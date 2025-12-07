package com.yearup.dealership.db;

import com.yearup.dealership.models.SalesContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesDao {

    private final DataSource dataSource;

    public SalesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // SAVE SALES CONTRACT INTO DATABASE
    public void saveSalesContract(SalesContract contract) {
        String sql = "INSERT INTO sales_contracts (VIN, sale_date, price) VALUES (?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, contract.getVin());
            stmt.setDate(2, java.sql.Date.valueOf(contract.getSaleDate()));
            stmt.setDouble(3, contract.getPrice());

            stmt.executeUpdate();
            System.out.println("Sales contract saved successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
