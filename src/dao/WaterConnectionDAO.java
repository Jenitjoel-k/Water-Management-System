package dao;

import connection.DBConnection;
import model.WaterConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WaterConnectionDAO {

    // Add Water Connection
    public boolean addConnection(WaterConnection wc) {

        String sql = "INSERT INTO water_connection(customer_id, connection_type, meter_number, installation_date, status) VALUES(?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, wc.getCustomerId());
            ps.setString(2, wc.getConnectionType());
            ps.setString(3, wc.getMeterNumber());
            ps.setDate(4, wc.getInstallationDate());
            ps.setString(5, wc.getStatus());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // View All Connections
    public List<WaterConnection> getAllConnections() {

        List<WaterConnection> list = new ArrayList<>();

        String sql = "SELECT * FROM water_connection";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                WaterConnection wc = new WaterConnection();

                wc.setConnectionId(rs.getInt("connection_id"));
                wc.setCustomerId(rs.getInt("customer_id"));
                wc.setConnectionType(rs.getString("connection_type"));
                wc.setMeterNumber(rs.getString("meter_number"));
                wc.setInstallationDate(rs.getDate("installation_date"));
                wc.setStatus(rs.getString("status"));

                list.add(wc);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Search Connection by ID
    public WaterConnection getConnectionById(int connectionId) {

        String sql = "SELECT * FROM water_connection WHERE connection_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, connectionId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                WaterConnection wc = new WaterConnection();

                wc.setConnectionId(rs.getInt("connection_id"));
                wc.setCustomerId(rs.getInt("customer_id"));
                wc.setConnectionType(rs.getString("connection_type"));
                wc.setMeterNumber(rs.getString("meter_number"));
                wc.setInstallationDate(rs.getDate("installation_date"));
                wc.setStatus(rs.getString("status"));

                return wc;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Update Connection
    public boolean updateConnection(WaterConnection wc) {

        String sql = "UPDATE water_connection SET connection_type=?, meter_number=?, installation_date=?, status=? WHERE connection_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, wc.getConnectionType());
            ps.setString(2, wc.getMeterNumber());
            ps.setDate(3, wc.getInstallationDate());
            ps.setString(4, wc.getStatus());
            ps.setInt(5, wc.getConnectionId());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Delete Connection
    public boolean deleteConnection(int connectionId) {

        String sql = "DELETE FROM water_connection WHERE connection_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, connectionId);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
