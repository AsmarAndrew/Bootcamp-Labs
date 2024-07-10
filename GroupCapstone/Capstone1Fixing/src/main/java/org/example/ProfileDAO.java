package org.example;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileDAO {

    public Profile getProfileByUserId(int userId) throws SQLException {
        Profile profile = null;
        String sql = "{CALL ViewProfile(?)}";

        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                profile = new Profile();
                profile.setUserId(rs.getInt("user_id"));
                profile.setFirstName(rs.getString("first_name"));
                profile.setLastName(rs.getString("last_name"));
                profile.setPhone(rs.getString("phone"));
                profile.setEmail(rs.getString("email"));
                profile.setAddress(rs.getString("address"));
                profile.setCity(rs.getString("city"));
                profile.setState(rs.getString("state"));
                profile.setZip(rs.getString("zip"));
            }
        }

        return profile;
    }

    public void updateProfile(Profile profile) throws SQLException {
        String sql = "{CALL UpdateProfile(?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, profile.getUserId());
            stmt.setString(2, profile.getFirstName());
            stmt.setString(3, profile.getLastName());
            stmt.setString(4, profile.getPhone());
            stmt.setString(5, profile.getEmail());
            stmt.setString(6, profile.getAddress());
            stmt.setString(7, profile.getCity());
            stmt.setString(8, profile.getState());
            stmt.setString(9, profile.getZip());
            stmt.executeUpdate();
        }
    }
}
