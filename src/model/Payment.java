package model;

import java.sql.Date;

public class Payment {

    private int paymentId;
    private int billId;
    private Date paymentDate;
    private double amountPaid;
    private String paymentMode;

    // Default Constructor
    public Payment() {

    }

    // Parameterized Constructor
    public Payment(int paymentId, int billId, Date paymentDate,
                   double amountPaid, String paymentMode) {

        this.paymentId = paymentId;
        this.billId = billId;
        this.paymentDate = paymentDate;
        this.amountPaid = amountPaid;
        this.paymentMode = paymentMode;
    }

    // Getter and Setter for paymentId
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    // Getter and Setter for billId
    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    // Getter and Setter for paymentDate
    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    // Getter and Setter for amountPaid
    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    // Getter and Setter for paymentMode
    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", billId=" + billId +
                ", paymentDate=" + paymentDate +
                ", amountPaid=" + amountPaid +
                ", paymentMode='" + paymentMode + '\'' +
                '}';
    }
}