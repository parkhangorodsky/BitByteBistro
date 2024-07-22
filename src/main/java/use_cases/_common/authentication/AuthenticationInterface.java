package use_cases._common.authentication;

import entity.User;

/**
 * Interface for authentication service.
 * Defines methods for user authentication and session management.
 */
public interface AuthenticationInterface {

    boolean authenticate(String userEmail, String userPassword);

    User getLoggedInUser();

    void logout(User user);

    void setLoggedInUser(User user); // Ensure this method is in the interface
}
