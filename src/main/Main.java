package main;

import model.*;
import service.*;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        CustomerService customerService = new CustomerService();
        WaterConnectionService connectionService = new WaterConnectionService();
        BillService billService = new BillService();
        PaymentService paymentService = new PaymentService();

        int choice;

        do {

            System.out.println("\n==============================================");
            System.out.println("   WATER CONNECTION MANAGEMENT SYSTEM");
            System.out.println("==============================================");
            System.out.println("1. Customer Management");
            System.out.println("2. Water Connection Management");
            System.out.println("3. Bill Management");
            System.out.println("4. Payment Management");
            System.out.println("5. Exit");
            System.out.print("Enter your choice : ");

            choice = sc.nextInt();

            switch (choice) {

                // CUSTOMER MODULE
                case 1:

                    int customerChoice;

                    do {

                        System.out.println("\n----- Customer Menu -----");
                        System.out.println("1. Add Customer");
                        System.out.println("2. View Customers");
                        System.out.println("3. Search Customer");
                        System.out.println("4. Delete Customer");
                        System.out.println("5. Back");
                        System.out.print("Enter Choice : ");

                        customerChoice = sc.nextInt();

                        switch (customerChoice) {

                            case 1:

                                Customer customer = new Customer();

                                sc.nextLine();

                                System.out.print("Customer Name : ");
                                customer.setCustomerName(sc.nextLine());

                                System.out.print("Address : ");
                                customer.setAddress(sc.nextLine());

                                System.out.print("Phone Number : ");
                                customer.setPhoneNumber(sc.nextLine());

                                System.out.print("Email : ");
                                customer.setEmail(sc.nextLine());

                                if(customerService.addCustomer(customer))
                                    System.out.println("Customer Added Successfully.");
                                else
                                    System.out.println("Failed.");

                                break;

                            case 2:

                                List<Customer> customers =
                                        customerService.getAllCustomers();

                                for(Customer c : customers){

                                    System.out.println("--------------------------------");
                                    System.out.println("Customer ID : " + c.getCustomerId());
                                    System.out.println("Name : " + c.getCustomerName());
                                    System.out.println("Address : " + c.getAddress());
                                    System.out.println("Phone : " + c.getPhoneNumber());
                                    System.out.println("Email : " + c.getEmail());
                                }

                                break;

                            case 3:

                                System.out.print("Enter Customer ID : ");
                                int cid = sc.nextInt();

                                Customer c =
                                        customerService.getCustomerById(cid);

                                if(c != null){

                                    System.out.println("Customer Found");

                                    System.out.println(c.getCustomerName());
                                    System.out.println(c.getAddress());
                                    System.out.println(c.getPhoneNumber());
                                    System.out.println(c.getEmail());

                                }else{

                                    System.out.println("Customer Not Found");
                                }

                                break;

                            case 4:

                                System.out.print("Enter Customer ID : ");
                                int deleteId = sc.nextInt();

                                if(customerService.deleteCustomer(deleteId))
                                    System.out.println("Deleted Successfully");
                                else
                                    System.out.println("Delete Failed");

                                break;

                        }

                    }while(customerChoice!=5);

                    break;

                // WATER CONNECTION MODULE
                case 2:

                    WaterConnection connection =
                            new WaterConnection();

                    System.out.print("Customer ID : ");
                    connection.setCustomerId(sc.nextInt());

                    sc.nextLine();

                    System.out.print("Connection Type : ");
                    connection.setConnectionType(sc.nextLine());

                    System.out.print("Meter Number : ");
                    connection.setMeterNumber(sc.nextLine());

                    connection.setInstallationDate(
                            new Date(System.currentTimeMillis()));

                    connection.setStatus("ACTIVE");

                    if(connectionService.addConnection(connection))
                        System.out.println("Connection Added.");
                    else
                        System.out.println("Failed.");

                    break;

                // BILL MODULE
                case 3:

                    Bill bill = new Bill();

                    System.out.print("Connection ID : ");
                    bill.setConnectionId(sc.nextInt());

                    System.out.print("Previous Reading : ");
                    bill.setPreviousReading(sc.nextInt());

                    System.out.print("Current Reading : ");
                    bill.setCurrentReading(sc.nextInt());

                    bill.setBillDate(
                            new Date(System.currentTimeMillis()));

                    long due =
                            System.currentTimeMillis()+
                                    (15L*24*60*60*1000);

                    bill.setDueDate(new Date(due));

                    bill.setStatus("UNPAID");

                    if(billService.generateBill(bill))
                        System.out.println("Bill Generated.");
                    else
                        System.out.println("Failed.");

                    billService.displayBills();

                    break;

                // PAYMENT MODULE
                case 4:

                    Payment payment = new Payment();

                    System.out.print("Bill ID : ");
                    payment.setBillId(sc.nextInt());

                    System.out.print("Amount : ");
                    payment.setAmountPaid(sc.nextDouble());

                    sc.nextLine();

                    System.out.print("Payment Mode : ");
                    payment.setPaymentMode(sc.nextLine());

                    payment.setPaymentDate(
                            new Date(System.currentTimeMillis()));

                    if(paymentService.makePayment(payment))
                        System.out.println("Payment Successful");
                    else
                        System.out.println("Payment Failed");

                    paymentService.displayPayments();

                    break;

                case 5:

                    System.out.println("Thank You...");
                    break;

                default:

                    System.out.println("Invalid Choice");

            }

        }while(choice!=5);

        sc.close();
    }
}