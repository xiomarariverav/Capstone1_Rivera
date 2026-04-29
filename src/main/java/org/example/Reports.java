package org.example;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class Reports {

    // helper method to sort newest first(also in ledger)
    private static void sortNewestFirst(List<Transaction> list) {
        list.sort(Comparator.comparing(Transaction::getDate)
                .thenComparing(Transaction::getTime)
                .reversed());
    }

    //this method shows all transactions that happened this month so far,
    // starting from the 1st up to today(newest ones first)
    public static void monthToDate(List<Transaction> list) {

        sortNewestFirst(list);

        LocalDate start = LocalDate.now().withDayOfMonth(1);

        for (Transaction t : list) {
            if (!t.getDate().isBefore(start)) {
                //If the transaction is on or after the first day of the month,print
                // same thing as if (t.getDate().isAfter(start) || t.getDate().isEqual(start))
                System.out.println(t);
            }
        }
    }

    //shows all transactions from last month (from the 1st to the last day) newest ones first
    public static void previousMonth(List<Transaction> list) {

        sortNewestFirst(list);

        LocalDate start = LocalDate.now().minusMonths(1).withDayOfMonth(1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        for (Transaction t : list) {
            if (!t.getDate().isBefore(start) && !t.getDate().isAfter(end)) {

                // if (t.getDate().isAfter(start) && t.getDate().isBefore(end)) { this would not work bc it misses transactions on the first (isAfter) and last day
                System.out.println(t);
            }
        }
    }

    //shows all transactions from January 1st of the current year up to today(newest ones first)
    public static void yearToDate(List<Transaction> list) {

        sortNewestFirst(list);

        LocalDate start = LocalDate.now().withDayOfYear(1);

        for (Transaction t : list) {
            if (!t.getDate().isBefore(start)) {
                System.out.println(t);
            }
        }
    }

    // Displays all transactions that occurred last year(newest ones first)
    public static void previousYear(List<Transaction> list) {

        sortNewestFirst(list);

        int lastYear = LocalDate.now().minusYears(1).getYear();

        //Checks if that year matches lastYear
        for (Transaction t : list) {
            if (t.getDate().getYear() == lastYear) {
                System.out.println(t);
            }
        }
    }

    public static void searchByVendor(List<Transaction> list, String vendor) {
        sortNewestFirst(list);

// using toLowerCase() bc it checks for a partial match, equalsIgnoreCase only checks for exact matches
        String search = vendor.toLowerCase();

        for (Transaction t : list) {
            if (t.getVendor().toLowerCase().contains(search)) {
                System.out.println(t);
            }
        }
    }
}


