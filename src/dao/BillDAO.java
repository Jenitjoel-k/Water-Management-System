package dao;

import connection.DBConnection;
import model.Bill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {

    // Rate per unit
    private static final double RATE_PER_UNIT = 12.0;

    // Calculate Bill Amount
    private double calculateAmount(int unitsConsumed) {
        return unitsConsumed * RATE_PER_UNIT;
    }

    // ==========================
    // Generate Bill
    // ==========================
    public boolean generateBill(Bill bill) {

        String sql = "INSERT INTO bill(connection_id, previous_reading, current_reading,"
                + " units_consumed, amount, bill_date, due_date, status)"
                + " VALUES(?,?,?,?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            int units = bill.getCurrentReading() - bill.getPreviousReading();

            if (units < 0) {
                System.out.println("Current Reading cannot be less than Previous Reading.");
                return false;
            }

            double amount = calculateAmount(units);

            bill.setUnitsConsumed(units);
            bill.setAmount(amount);

            ps.setInt(1, bill.getConnectionId());
            ps.setInt(2, bill.getPreviousReading());
            ps.setInt(3, bill.getCurrentReading());
            ps.setInt(4, bill.getUnitsConsumed());
            ps.setDouble(5, bill.getAmount());
            ps.setDate(6, bill.getBillDate());
            ps.setDate(7, bill.getDueDate());
            ps.setString(8, bill.getStatus());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ==========================
    // View All Bills
    // ==========================
    public List<Bill> getAllBills() {

        List<Bill> billList = new ArrayList<>();

        String sql = "SELECT * FROM bill";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Bill bill = new Bill();

                bill.setBillId(rs.getInt("bill_id"));
                bill.setConnectionId(rs.getInt("connection_id"));
                bill.setPreviousReading(rs.getInt("previous_reading"));
                bill.setCurrentReading(rs.getInt("current_reading"));
                bill.setUnitsConsumed(rs.getInt("units_consumed"));
                bill.setAmount(rs.getDouble("amount"));
                bill.setBillDate(rs.getDate("bill_date"));
                bill.setDueDate(rs.getDate("due_date"));
                bill.setStatus(rs.getString("status"));

                billList.add(bill);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return billList;
    }

    // ==========================
    // Search Bill By ID
    // ==========================
    public Bill getBillById(int billId) {

        String sql = "SELECT * FROM bill WHERE bill_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, billId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Bill bill = new Bill();

                bill.setBillId(rs.getInt("bill_id"));
                bill.setConnectionId(rs.getInt("connection_id"));
                bill.setPreviousReading(rs.getInt("previous_reading"));
                bill.setCurrentReading(rs.getInt("current_reading"));
                bill.setUnitsConsumed(rs.getInt("units_consumed"));
                bill.setAmount(rs.getDouble("amount"));
                bill.setBillDate(rs.getDate("bill_date"));
                bill.setDueDate(rs.getDate("due_date"));
                bill.setStatus(rs.getString("status"));

                return bill;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // ==========================
    // Update Bill Status
    // ==========================
    public boolean updateBillStatus(int billId, String status) {

        String sql = "UPDATE bill SET status=? WHERE bill_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, billId);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ==========================
    // Update Entire Bill
    // ==========================
    public boolean updateBill(Bill bill) {

        String sql = "UPDATE bill SET previous_reading=?, current_reading=?, "
                + "units_consumed=?, amount=?, bill_date=?, due_date=?, status=? "
                + "WHERE bill_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            int units = bill.getCurrentReading() - bill.getPreviousReading();

            if (units < 0) {
                System.out.println("Invalid Meter Reading.");
                return false;
            }

            double amount = calculateAmount(units);

            ps.setInt(1, bill.getPreviousReading());
            ps.setInt(2, bill.getCurrentReading());
            ps.setInt(3, units);
            ps.setDouble(4, amount);
            ps.setDate(5, bill.getBillDate());
            ps.setDate(6, bill.getDueDate());
            ps.setString(7, bill.getStatus());
            ps.setInt(8, bill.getBillId());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ==========================
    // Delete Bill
    // ==========================
    public boolean deleteBill(int billId) {

        String sql = "DELETE FROM bill WHERE bill_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, billId);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ==========================
    // Display Bills
    // ==========================
    public void displayBills() {

        List<Bill> bills = getAllBills();

        if (bills.isEmpty()) {
            System.out.println("\nNo Bills Found.");
            return;
        }

        System.out.println("\n================ BILL DETAILS ================");
        System.out.printf("%-6s %-8s %-8s %-8s %-8s %-10s %-10s%n",
                "ID",
                "ConnID",
                "Prev",
                "Curr",
                "Units",
                "Amount",
                "Status");

        for (Bill bill : bills) {

            System.out.printf("%-6d %-8d %-8d %-8d %-8d %-10.2f %-10s%n",
                    bill.getBillId(),
                    bill.getConnectionId(),
                    bill.getPreviousReading(),
                    bill.getCurrentReading(),
                    bill.getUnitsConsumed(),
                    bill.getAmount(),
                    bill.getStatus());
        }

        System.out.println("==============================================");
    }
}