package com.yearup.dealership.db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventoryDao {

    private final DataSource dataSource;

    public InventoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // ADD VEHICLE TO INVENTORY
    public void addVehicleToInventory(int dealershipId, String vin) {
        String sql = "INSERT INTO inventory (dealership_id, VIN) VALUES (?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, dealershipId);
            stmt.setString(2, vin);

            stmt.executeUpdate();
            System.out.println("Vehicle added to inventory.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // REMOVE VEHICLE FROM INVENTORY
    public void removeVehicleFromInventory(String vin) {
        String sql = "DELETE FROM inventory WHERE VIN = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vin);

            stmt.executeUpdate();
            System.out.println("Vehicle removed from inventory.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
