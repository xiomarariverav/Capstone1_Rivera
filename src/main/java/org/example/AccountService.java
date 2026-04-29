package org.example;

import java.time.LocalDate;
import java.time.LocalTime;

public class AccountService {

    public static void addDeposit(double amount, String description, String vendor){

        Transaction t = new Transaction(LocalDate.now(), LocalTime.now(), description,vendor,amount);

        FileManager.writeTransaction(t);
        System.out.println("Deposit Added.");
    }

    public static void makePayment(double amount, String description, String vendor){

        Transaction t = new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, -amount);

        FileManager.writeTransaction(t);
        System.out.println("Payment added.");
    }
}
