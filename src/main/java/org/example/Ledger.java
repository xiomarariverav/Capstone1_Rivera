package org.example;

import java.util.Comparator;
import java.util.List;

public class Ledger {
    private static void sortNewestFirst(List<Transaction> list) {
        list.sort(Comparator.comparing(Transaction::getDate)
                .thenComparing(Transaction::getTime)
                .reversed());
    }

}
