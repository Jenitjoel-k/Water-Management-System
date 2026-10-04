package dao;

import connection.DBConnection;
import model.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    // Make Payment
    public boolean makePayment(Payment payment) {

        String paymentSql = "INSERT INTO payment(bill_id, payment_date, amount_paid, payment_mode) VALUES(?,?,?,?)";
        String updateBillSql = "UPDATE bill SET status='PAID' WHERE bill_id=?";

        try (Connection con = DBConnection.getConnection()) {

            // Start Transaction
            con.setAutoCommit(false);

            // Insert Payment
            PreparedStatement ps1 = con.prepareStatement(paymentSql);

            ps1.setInt(1, payment.getBillId());
            ps1.setDate(2, payment.getPaymentDate());
            ps1.setDouble(3, payment.getAmountPaid());
            ps1.setString(4, payment.getPaymentMode());

            int paymentRows = ps1.executeUpdate();

            // Update Bill Status
            PreparedStatement ps2 = con.prepareStatement(updateBillSql);
            ps2.setInt(1, payment.getBillId());

            int billRows = ps2.executeUpdate();

            if (paymentRows > 0 && billRows > 0) {

                con.commit();
                return true;

            } else {

                con.rollback();
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    // View All Payments
    public List<Payment> getAllPayments() {

        List<Payment> paymentList = new ArrayList<>();

        String sql = "SELECT * FROM payment";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Payment payment = new Payment();

                payment.setPaymentId(rs.getInt("payment_id"));
                payment.setBillId(rs.getInt("bill_id"));
                payment.setPaymentDate(rs.getDate("payment_date"));
                payment.setAmountPaid(rs.getDouble("amount_paid"));
                payment.setPaymentMode(rs.getString("payment_mode"));

                paymentList.add(payment);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return paymentList;
    }

    // Search Payment By ID
    public Payment getPaymentById(int paymentId) {

        String sql = "SELECT * FROM payment WHERE payment_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, paymentId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Payment payment = new Payment();

                payment.setPaymentId(rs.getInt("payment_id"));
                payment.setBillId(rs.getInt("bill_id"));
                payment.setPaymentDate(rs.getDate("payment_date"));
                payment.setAmountPaid(rs.getDouble("amount_paid"));
                payment.setPaymentMode(rs.getString("payment_mode"));

                return payment;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    // Delete Payment
    public boolean deletePayment(int paymentId) {

        String sql = "DELETE FROM payment WHERE payment_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, paymentId);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    // Display All Payments
    public void displayPayments() {

        List<Payment> paymentList = getAllPayments();

        if (paymentList.isEmpty()) {

            System.out.println("No Payment Records Found.");
            return;
        }

        System.out.println("\n================ PAYMENT DETAILS ================");

        System.out.printf("%-10s %-10s %-15s %-12s %-15s%n",
                "Pay ID",
                "Bill ID",
                "Date",
                "Amount",
                "Mode");

        for (Payment payment : paymentList) {

            System.out.printf("%-10d %-10d %-15s %-12.2f %-15s%n",

                    payment.getPaymentId(),

                    payment.getBillId(),

                    payment.getPaymentDate(),

                    payment.getAmountPaid(),

                    payment.getPaymentMode());
        }

        System.out.println("=================================================");
    }
}