package use_cases._common.authentication;

import entity.User;

/**
 * Interface for authentication service.
 * Defines methods for user authentication and session management.
 */
public interface AuthenticationInterface {

    /**
     * Authenticates a user with the provided credentials.
     *
     * @param userEmail The user's email address.
     * @param userPassword The user's password.
     * @return true if authentication succeeds, false otherwise.
     */
    boolean authenticate(String userEmail, String userPassword);

    /**
     * Retrieves the currently logged-in user.
     *
     * @return The logged-in User object, or null if no user is logged in.
     */
    User getLoggedInUser();

    /**
     * Logs out the specified user.
     *
     * @param user The user to log out.
     */
    void logout(User user);

    // Add other authentication-related methods as needed
}

