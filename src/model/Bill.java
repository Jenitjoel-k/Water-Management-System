package model;

import java.sql.Date;

public class Bill {

    private int billId;
    private int connectionId;
    private int previousReading;
    private int currentReading;
    private int unitsConsumed;
    private double amount;
    private Date billDate;
    private Date dueDate;
    private String status;

    // Default Constructor
    public Bill() {

    }

    // Parameterized Constructor
    public Bill(int billId, int connectionId, int previousReading,
                int currentReading, int unitsConsumed,
                double amount, Date billDate,
                Date dueDate, String status) {

        this.billId = billId;
        this.connectionId = connectionId;
        this.previousReading = previousReading;
        this.currentReading = currentReading;
        this.unitsConsumed = unitsConsumed;
        this.amount = amount;
        this.billDate = billDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    // Getter and Setter for billId
    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    // Getter and Setter for connectionId
    public int getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(int connectionId) {
        this.connectionId = connectionId;
    }

    // Getter and Setter for previousReading
    public int getPreviousReading() {
        return previousReading;
    }

    public void setPreviousReading(int previousReading) {
        this.previousReading = previousReading;
    }

    // Getter and Setter for currentReading
    public int getCurrentReading() {
        return currentReading;
    }

    public void setCurrentReading(int currentReading) {
        this.currentReading = currentReading;
    }

    // Getter and Setter for unitsConsumed
    public int getUnitsConsumed() {
        return unitsConsumed;
    }

    public void setUnitsConsumed(int unitsConsumed) {
        this.unitsConsumed = unitsConsumed;
    }

    // Getter and Setter for amount
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Getter and Setter for billDate
    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    // Getter and Setter for dueDate
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    // Getter and Setter for status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}