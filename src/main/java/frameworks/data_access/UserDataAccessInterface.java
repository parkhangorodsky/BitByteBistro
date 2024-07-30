package frameworks.data_access;

import entity.User;

/**
 * Interface for data access operations related to user data.
 * This interface defines methods for checking the existence of a user by email,
 * adding a new user, retrieving a user by email, and managing the logged-in user.
 */
public interface UserDataAccessInterface {


    /**
     * Adds a new user to the data source.
     *
     * @param user The user to add.
     */
    void addUser(User user);

    void updateUser(User user);
    void deleteUser(User user);


    /**
     * Checks if a user exists by their email address.
     *
     * @param email The email address to check.
     * @return True if the user exists, false otherwise.
     */
    boolean existsByEmail(String email);

    /**
     * Retrieves a user by their email address.
     *
     * @param email The email address to look up.
     * @return The user associated with the email address, or null if not found.
     */
    User getUserByEmail(String email);
}
