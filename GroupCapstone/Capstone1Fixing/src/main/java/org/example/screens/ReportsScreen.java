package org.example.screens;

import org.example.handlers.ReportHandler;
import org.example.models.User;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ReportsScreen {

    private final ReportHandler reportHandler = new ReportHandler();

    public void showReportsScreen(User user) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Reports Screen");
            System.out.println("1) Month to Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year to Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("6) Custom Search");
            System.out.println("X) Go Back");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "1":
                    displayReport(user, "month");
                    break;
                case "2":
                    displayReport(user, "prevMonth");
                    break;
                case "3":
                    displayReport(user, "year");
                    break;
                case "4":
                    displayReport(user, "prevYear");
                    break;
                case "5":
                    searchByVendor(user, scanner);
                    break;
                case "6":
                    customSearch(user, scanner);
                    break;
                case "X":
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void displayReport(User user, String type) {
        try {
            ResultSet rs;
            switch (type) {
                case "month":
                    rs = reportHandler.showReportsByMonthToDate(user.getUserId());
                    break;
                case "prevMonth":
                    rs = reportHandler.showReportsByPreviousMonth(user.getUserId());
                    break;
                case "year":
                    rs = reportHandler.showReportsByYearToDate(user.getUserId());
                    break;
                case "prevYear":
                    rs = reportHandler.showReportsByPreviousYear(user.getUserId());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid report type: " + type);
            }
            displayResultSet(rs);
        } catch (SQLException e) {
            System.out.println("An error occurred while fetching the report.");
            e.printStackTrace();
        }
    }

    private void searchByVendor(User user, Scanner scanner) {
        System.out.print("Enter vendor name: ");
        String vendor = scanner.nextLine();

        try {
            ResultSet rs = reportHandler.searchByVendor(user.getUserId(), vendor);
            displayResultSet(rs);
        } catch (SQLException e) {
            System.out.println("An error occurred while searching by vendor.");
            e.printStackTrace();
        }
    }

    private void customSearch(User user, Scanner scanner) {
        System.out.print("Enter start date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();
        System.out.print("Enter end date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();
        System.out.print("Enter vendor (leave blank for any): ");
        String vendor = scanner.nextLine();

        try {
            ResultSet rs = reportHandler.customSearch(user.getUserId(), Date.valueOf(startDate), Date.valueOf(endDate), vendor);
            displayResultSet(rs);
        } catch (SQLException e) {
            System.out.println("An error occurred while performing the custom search.");
            e.printStackTrace();
        }
    }

    private void displayResultSet(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println("Transaction ID: " + rs.getInt("transaction_id"));
            System.out.println("Date: " + rs.getTimestamp("date"));
            System.out.println("Description: " + rs.getString("description"));
            System.out.println("Vendor: " + rs.getString("vendor"));
            System.out.println("Amount: " + rs.getDouble("amount"));
            System.out.println("-----------------------------------");
        }
    }
}
