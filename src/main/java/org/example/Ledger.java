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
}
