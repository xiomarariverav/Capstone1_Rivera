package org.example;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


public class UserInterface{
    static Scanner scanner = new Scanner(System.in);
    static List<Transaction> transactions;

    public static void showSplashScreen() {
        System.out.println(Colors.CYAN + "=====================================");
        System.out.println(Colors.CYAN + "         BOUTIQUE BOOKS");
        System.out.println(Colors.CYAN + "      Your Fashion Store Ledger");
        System.out.println(Colors.CYAN + "=====================================");
        System.out.println("\nWelcome back! Press ENTER to continue..." + Colors.RESET);
        scanner.nextLine();
    }

    public static void homeScreen() {

        while (true) {

            System.out.println(Colors.CYAN + "\n--- BOUTIQUE BOOKS | MAIN MENU ---");
            System.out.println(Colors.PINK + "D) " + "Record a Sale");
            System.out.println(Colors.PINK+ "P) " + "Record a Purchase");
            System.out.println(Colors.PINK + "L) " + "Ledger");
            System.out.println(Colors.PINK+ "X) " + "Exit"+ Colors.RESET);

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
                    System.out.println(Colors.CYAN + "Thank you for using Boutique Books. Goodbye!" + Colors.RESET);
                    return;

                default:
                    System.out.println(Colors.RED + "Invalid option. Please try again." + Colors.RESET);
            }
        }
    }

    // Prompts user for deposit details
    private static void addDeposit() {
        double amount = readDouble("Sale Amount: $");

        System.out.print("Item Description: ");
        String desc = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();

        // this method creates the Transaction and writes it to the csv
        AccountService.addDeposit(amount, desc.trim(), vendor.trim());
        System.out.println("Sale recorded successfully!"); // was "Deposit Added."
    }


    private static void makePayment(){
        double amount = readDouble("Purchase Amount: $");

        System.out.print("Item Description: ");
        String description = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();

        AccountService.makePayment(amount, description, vendor);
        System.out.println("Purchase recorded successfully!");// was "Payment added."
    }

    private static void ledgerScreen() {
        while (true) {

            transactions = FileManager.getTransaction();

            System.out.println(Colors.CYAN + "\n--- BOUTIQUE BOOKS | LEDGER ---" );
            System.out.println(Colors.PURPLE + "A) " + "All");
            System.out.println(Colors.PURPLE + "D) " + "Sales");
            System.out.println(Colors.PURPLE + "P) " + "Purchases");
            System.out.println(Colors.PURPLE + "R) " +  "Reports");
            System.out.println(Colors.PURPLE + "H) " + "Home"+ Colors.RESET);

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

            System.out.println(Colors.CYAN + "\n--- BOUTIQUE BOOKS | REPORTS ---" );
            System.out.println(Colors.GREEN + "1) " + "Month To Date");
            System.out.println(Colors.GREEN + "2) " + "Previous Month");
            System.out.println(Colors.GREEN + "3) " + "Year To Date");
            System.out.println(Colors.GREEN + "4) " + "Previous Year");
            System.out.println(Colors.GREEN + "5) " +  "Search by Vendor");
            System.out.println(Colors.GREEN + "6) " + "Custom Search");
            System.out.println(Colors.GREEN + "0) " + "Back" + Colors.RESET);
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