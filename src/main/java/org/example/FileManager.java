package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public static List<Transaction> getTransaction() {
        try{
            FileReader fileReader = new FileReader("src/main/resources/transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String input;

            List<Transaction> transactionsList = new ArrayList<>();

            //(date|time|description|vendor|amount
            while((input = bufferedReader.readLine()) !=null) {
                String[] csvRow = input.split("\\|");
                LocalDate date = LocalDate.parse(csvRow[0]);
                LocalTime time = LocalTime.parse(csvRow[1]);
                String description = csvRow[2];
                String vendor = csvRow[3];
                double amount = Double.parseDouble(csvRow[4]);

                Transaction newTransaction = new Transaction(date, time, description, vendor, amount);

                transactionsList.add(newTransaction);
            }
            bufferedReader.close();
            return transactionsList;
        }
        catch (IOException ex){
            System.out.println("There was a problem with the file.");
        }
        return new ArrayList<>();

    }
}
