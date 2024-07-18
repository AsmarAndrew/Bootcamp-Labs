package org.example.handlers;

import org.example.dao.ProfileDAO;
import org.example.models.Profile;

import java.sql.SQLException;

public class ProfileHandler {

    private final ProfileDAO profileDAO = new ProfileDAO();

    public Profile viewProfile(int userId) throws SQLException {
        return profileDAO.getProfileByUserId(userId);
    }

    public void updateProfile(Profile profile) throws SQLException {
        profileDAO.updateProfile(profile);
    }
}
