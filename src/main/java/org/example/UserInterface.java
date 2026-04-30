package org.example;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


public class UserInterface{
    static Scanner scanner = new Scanner(System.in);
    static List<Transaction> transactions;

    public static void homeScreen() {

        while (true) {

            System.out.println("\n--- HOME ---");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");

            String choice = scanner.nextLine().toUpperCase().trim();
            switch (choice) {

                case "D":
                    addDeposit();
                    break;

                case "P":
                    makePayment();
                    break;

                case "L":
                    ledgerScreen();
                    break;

                case "X":
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // Prompts user for deposit details
    private static void addDeposit() {
        double amount = readDouble("Amount: ");

        System.out.print("Description: ");
        String desc = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();

        // this method creates the Transaction and writes it to the csv
        AccountService.addDeposit(amount, desc.trim(), vendor.trim());
    }


    private static void makePayment(){
        double amount = readDouble("Amount: ");

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();

        AccountService.makePayment(amount, description, vendor);
    }

    private static void ledgerScreen() {
        while (true) {

            transactions = FileManager.getTransaction();

            System.out.println("\n--- LEDGER ---");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");

            String choice = scanner.nextLine().toUpperCase().trim();

            switch (choice) {

                case "A":
                    Ledger.showAll(transactions);
                    break;

                case "D":
                    Ledger.showDeposits(transactions);
                    break;

                case "P":
                    Ledger.showPayments(transactions);
                    break;

                case "R":
                    reportsScreen();
                    break;

                case "H":
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void reportsScreen() {

        while (true) {

            System.out.println("\n--- REPORTS ---");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("6) Custom Search");
            System.out.println("0) Back");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    Reports.monthToDate(transactions);
                    break;

                case "2":
                    Reports.previousMonth(transactions);
                    break;

                case "3":
                    Reports.yearToDate(transactions);
                    break;

                case "4":
                    Reports.previousYear(transactions);
                    break;

                case "5":
                    System.out.print("Enter vendor: ");
                    String vendor = scanner.nextLine();
                    Reports.searchByVendor(transactions, vendor);
                    break;

                case "6":
                    customSearchPrompt();
                    break;

                case "0":
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void customSearchPrompt() {

        // Start Date
        System.out.print("Start Date (yyyy-mm-dd) or press ENTER to skip): ");
        String startInput = scanner.nextLine().trim();
        LocalDate startDate;

        if (startInput.isEmpty()) {  startDate = null;
        } else {
            startDate = LocalDate.parse(startInput);
        }

        // End Date
        System.out.print("End Date (yyyy-mm-dd) or press ENTER to skip: ");
        String endInput = scanner.nextLine().trim();
        LocalDate endDate;

        if (endInput.isEmpty()) { endDate = null;
        } else {
            endDate = LocalDate.parse(endInput);
        }

        // Description
        System.out.print("Description or press ENTER to skip): ");
        String description = scanner.nextLine().trim();
        if (description.isEmpty()) { description = null;
        }

        // Vendor
        System.out.print("Vendor or press ENTER to skip): ");
        String vendor = scanner.nextLine().trim();
        if (vendor.isEmpty()) {
            vendor = null; }

        // Amount
        System.out.print("Amount or press ENTER to skip): ");
        String amountInput = scanner.nextLine().trim();
        Double amount;

        if (amountInput.isEmpty()) { amount = null;
        }
        else {
            amount = Double.parseDouble(amountInput);
        }

        // Run search
        Reports.customSearch(transactions, startDate, endDate, description, vendor, amount);
    }

    private static double readDouble(String amount) {
        while (true) {
            try {
                System.out.print(amount);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }
}