package service;

import dao.PaymentDAO;
import model.Payment;

import java.util.List;

public class PaymentService {

    private PaymentDAO paymentDAO = new PaymentDAO();

    public boolean makePayment(Payment payment) {
        return paymentDAO.makePayment(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentDAO.getAllPayments();
    }

    public Payment getPaymentById(int id) {
        return paymentDAO.getPaymentById(id);
    }

    public boolean deletePayment(int id) {
        return paymentDAO.deletePayment(id);
    }

    // ADD THIS METHOD
    public void displayPayments() {
        paymentDAO.displayPayments();
    }
}