package org.example;

import java.util.Comparator;
import java.util.List;

public class Ledger {

    //this helper method compares transaction date, if dates are equal, then compare by time
    //reverse bc data is oldest to newest (least to greatest)
    private static void sortNewestFirst(List<Transaction> list) {
        list.sort(Comparator.comparing(Transaction::getDate)
                .thenComparing(Transaction::getTime)
                .reversed());
    }

    public static void showAll(List<Transaction> list) {
        sortNewestFirst(list);
        for (Transaction t : list) {
            printTransaction(t);
        }
}

    // Sorts transactions from newest to oldest and prints only the ones with positive amounts/deposits (> 0)
    public static void showDeposits(List<Transaction> list) {
        sortNewestFirst(list);

        boolean found = false;

        for (Transaction t : list) {
            if (t.getAmount() > 0) {
                printTransaction(t);
                found = true;
            }
        }
    }
    // Sorts transactions from newest to oldest and prints only payment transactions/negative amounts (<0)
    public static void showPayments(List<Transaction> list) {
        sortNewestFirst(list);

        boolean found = false;

        for (Transaction t : list) {
            if (t.getAmount() < 0) {
                printTransaction(t);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No payments found.");
        }
    }
     private static void printTransaction (Transaction t){
            System.out.println(t.getDate() + " " + t.getTime() + " | " + t.getDescription() + " | "
                    + t.getVendor() + " | $" + String.format("%.2f", t.getAmount()));
        }
    }
