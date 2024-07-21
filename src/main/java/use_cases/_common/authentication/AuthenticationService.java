package use_cases._common.authentication;

import entity.User;
import frameworks.data_access.DataAccessInterface;

/**
 * Service for user authentication and session management.
 */
public class AuthenticationService implements AuthenticationInterface {

    private final DataAccessInterface dataAccess;

    public AuthenticationService(DataAccessInterface dataAccess) {
        this.dataAccess = dataAccess;
    }

    @Override
    public boolean authenticate(String userEmail, String userPassword) {
        User user = dataAccess.getUserByEmail(userEmail);
        if (user != null && user.getUserPassword().equals(userPassword)) {
            dataAccess.setLoggedInUser(user); // Store the logged-in user
            return true;
        }
        return false;
    }

    @Override
    public User getLoggedInUser() {
        return dataAccess.getLoggedInUser();
    }

    @Override
    public void logout(User user) {
        if (dataAccess.getLoggedInUser() != null && dataAccess.getLoggedInUser().equals(user)) {
            dataAccess.setLoggedInUser(null); // Clear the logged-in user
        }
    }
}
