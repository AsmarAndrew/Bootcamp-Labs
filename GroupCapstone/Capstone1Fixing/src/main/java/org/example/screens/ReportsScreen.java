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
            System.out.println("ʀᴇᴘᴏʀᴛꜱ ꜱᴄʀᴇᴇɴ");
            System.out.println("↳ 1) ᴍᴏɴᴛʜ ᴛᴏ ᴅᴀᴛᴇ");
            System.out.println("↳ 2) ᴘʀᴇᴠɪᴏᴜꜱ ᴍᴏɴᴛʜ");
            System.out.println("↳ 3) ʏᴇᴀʀ ᴛᴏ ᴅᴀᴛᴇ");
            System.out.println("↳ 4) ᴘʀᴇᴠɪᴏᴜꜱ ʏᴇᴀʀ");
            System.out.println("↳ 5) ꜱᴇᴀʀᴄʜ ʙʏ ᴠᴇɴᴅᴏʀ");
            System.out.println("↳ 6) ᴄᴜꜱᴛᴏᴍ ꜱᴇᴀʀᴄʜ");
            System.out.println("↳ x) ɢᴏ ʙᴀᴄᴋ");
            System.out.print("ᴇɴᴛᴇʀ ʏᴏᴜʀ ᴄʜᴏɪᴄᴇ: ");
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
                    System.out.println("ɪɴᴠᴀʟɪᴅ ᴄʜᴏɪᴄᴇ, ᴘʟᴇᴀꜱᴇ ᴛʀʏ ᴀɢᴀɪɴ");
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
            System.out.println("ᴀɴ ᴇʀʀᴏʀ ᴏᴄᴄᴜʀʀᴇᴅ ᴡʜɪʟᴇ ꜰᴇᴛᴄʜɪɴɢ ᴛʜᴇ ʀᴇᴘᴏʀᴛ");
            e.printStackTrace();
        }
    }

    private void searchByVendor(User user, Scanner scanner) {
        System.out.print("ᴇɴᴛᴇʀ ᴠᴇɴᴅᴏʀ ɴᴀᴍᴇ: ");
        String vendor = scanner.nextLine();

        try {
            ResultSet rs = reportHandler.searchByVendor(user.getUserId(), vendor);
            displayResultSet(rs);
        } catch (SQLException e) {
            System.out.println("ᴀɴ ᴇʀʀᴏʀ ᴏᴄᴄᴜʀʀᴇᴅ ᴡʜɪʟᴇ ꜱᴇᴀʀᴄʜɪɴɢ ʙʏ ᴠᴇɴᴅᴏʀ.");
            e.printStackTrace();
        }
    }

    private void customSearch(User user, Scanner scanner) {
        System.out.print("ᴇɴᴛᴇʀ ꜱᴛᴀʀᴛ ᴅᴀᴛᴇ (ʏʏʏʏ-ᴍᴍ-ᴅᴅ): ");
        String startDate = scanner.nextLine();
        System.out.print("ᴇɴᴛᴇʀ ᴇɴᴅ ᴅᴀᴛᴇ (ʏʏʏʏ-ᴍᴍ-ᴅᴅ): ");
        String endDate = scanner.nextLine();
        System.out.print("ᴇɴᴛᴇʀ ᴠᴇɴᴅᴏʀ (ʟᴇᴀᴠᴇ ʙʟᴀɴᴋ ꜰᴏʀ ᴀɴʏ): ");
        String vendor = scanner.nextLine();

        try {
            ResultSet rs = reportHandler.customSearch(user.getUserId(), Date.valueOf(startDate), Date.valueOf(endDate), vendor);
            displayResultSet(rs);
        } catch (SQLException e) {
            System.out.println("ᴀɴ ᴇʀʀᴏʀ ᴏᴄᴄᴜʀʀᴇᴅ ᴡʜɪʟᴇ ᴘᴇʀꜰᴏʀᴍɪɴɢ ᴛʜᴇ ᴄᴜꜱᴛᴏᴍ ꜱᴇᴀʀᴄʜ.");
            e.printStackTrace();
        }
    }

    private void displayResultSet(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println("ᴛʀᴀɴꜱᴀᴄᴛɪᴏɴ ɪᴅ: " + rs.getInt("transaction_id"));
            System.out.println("ᴅᴀᴛᴇ: " + rs.getTimestamp("date"));
            System.out.println("ᴅᴇꜱᴄʀɪᴘᴛɪᴏɴ: " + rs.getString("description"));
            System.out.println("ᴠᴇɴᴅᴏʀ: " + rs.getString("vendor"));
            System.out.println("ᴀᴍᴏᴜɴᴛ: " + rs.getDouble("amount"));
            System.out.println("-----------------------------------");
        }
    }
}