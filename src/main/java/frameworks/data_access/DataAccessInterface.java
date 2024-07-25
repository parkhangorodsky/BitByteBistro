package frameworks.data_access;

import entity.User;

/**
 * Interface for data access operations related to user data.
 * This interface defines methods for checking the existence of a user by email,
 * adding a new user, retrieving a user by email, and managing the logged-in user.
 */
public interface DataAccessInterface {

    /**
     * Checks if a user exists by their email address.
     *
     * @param email The email address to check.
     * @return True if the user exists, false otherwise.
     */
    boolean existsByEmail(String email);

    /**
     * Adds a new user to the data source.
     *
     * @param user The user to add.
     */
    void addUser(User user);

    /**
     * Retrieves a user by their email address.
     *
     * @param email The email address to look up.
     * @return The user associated with the email address, or null if not found.
     */
    User getUserByEmail(String email);

    /**
     * Authenticates a user with the provided credentials.
     *
     * @param userEmail The user's email address.
     * @param userPassword The user's password.
     * @return True if authentication succeeds, false otherwise.
     */
    boolean authenticate(String userEmail, String userPassword);

    /**
     * Retrieves the currently logged-in user.
     *
     * @return The currently logged-in user, or null if no user is logged in.
     */
    User getLoggedInUser();

    /**
     * Sets the currently logged-in user.
     *
     * @param user The user to set as logged in.
     */
    void setLoggedInUser(User user);

    /**
     * Logs out the specified user.
     *
     * @param user The user to log out.
     */
    void logout(User user);
}
