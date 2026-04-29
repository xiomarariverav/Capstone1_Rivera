package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String filePath = ("src/main/resources/transactions.csv");

    public static List<Transaction> getTransaction() {

        List<Transaction> transactionsList = new ArrayList<>();

        try{
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String input;


            while((input = bufferedReader.readLine()) !=null) {

                //If this line is empty, skip it
                if (input.trim().isEmpty()) {
                    continue;
                }

                String[] csvRow = input.split("\\|");

                //if row doesn’t have exactly 5 values, report it and ignore it
                if (csvRow.length != 5) {
                    System.out.println("Invalid row format, expected 5 columns: " + input);
                    continue;
                }
                //(date|time|description|vendor|amount)
                LocalDate date = LocalDate.parse(csvRow[0]);
                LocalTime time = LocalTime.parse(csvRow[1]);
                String description = csvRow[2];
                String vendor = csvRow[3];
                double amount = Double.parseDouble(csvRow[4]);

                Transaction newTransaction = new Transaction(date, time, description, vendor, amount);
                transactionsList.add(newTransaction);
            }

            bufferedReader.close();

        } catch (IOException ex){
            System.out.println("There was a problem with the file.");
        }
        return transactionsList;

    }

    public static void writeTransaction(Transaction Transaction) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            //(date|time|description|vendor|amount)
            String line = Transaction.getDate() + "|" +
                    Transaction.getTime() + "|" +
                    Transaction.getDescription() + "|" +
                    Transaction.getVendor() + "|" + Transaction.getAmount();

            writer.write(line);
            writer.newLine();

            writer.close();

        } catch (IOException ex) {
            System.out.println("There was a problem writing to the file.");
        }
    }
}
