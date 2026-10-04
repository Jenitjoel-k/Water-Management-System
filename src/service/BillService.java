package service;

import dao.BillDAO;
import model.Bill;

import java.util.List;

public class BillService {

    private BillDAO billDAO = new BillDAO();

    public boolean generateBill(Bill bill) {
        return billDAO.generateBill(bill);
    }

    public List<Bill> getAllBills() {
        return billDAO.getAllBills();
    }

    public Bill getBillById(int id) {
        return billDAO.getBillById(id);
    }

    public boolean updateBillStatus(int id, String status) {
        return billDAO.updateBillStatus(id, status);
    }

    public boolean deleteBill(int id) {
        return billDAO.deleteBill(id);
    }

    // ADD THIS METHOD
    public void displayBills() {
        billDAO.displayBills();
    }
}