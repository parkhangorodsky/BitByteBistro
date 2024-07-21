package use_cases._common.authentication;

import entity.User;
import frameworks.data_access.DataAccessInterface;

/**
 * Service for user authentication and session management.
 */
public class AuthenticationService implements AuthenticationInterface {

    private final DataAccessInterface dataAccess;
    private User loggedInUser;

    public AuthenticationService(DataAccessInterface dataAccess) {
        this.dataAccess = dataAccess;
    }

    @Override
    public boolean authenticate(String userEmail, String userPassword) {
        User user = dataAccess.getUserByEmail(userEmail);
        if (user != null && user.getUserPassword().equals(userPassword)) {
            loggedInUser = user;
            return true;
        }
        return false;
    }

    @Override
    public User getLoggedInUser() {
        return loggedInUser;
    }

    @Override
    public void logout(User user) {
        if (loggedInUser != null && loggedInUser.equals(user)) {
            loggedInUser = null;
        }
    }

    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
    }
}
