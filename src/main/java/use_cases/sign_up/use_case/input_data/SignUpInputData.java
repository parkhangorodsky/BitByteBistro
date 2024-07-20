package use_cases.sign_up.use_case.input_data;

/**
 * Class representing the input data for the sign-up use case.
 * This class holds the user's sign-up information, including the user ID, email, and password.
 */
public class SignUpInputData {
    private String userID;
    private String userEmail;
    private String userPassword;

    /**
     * Constructs a new SignUpInputData instance with the specified user ID, email, and password.
     *
     * @param userID       The user's ID.
     * @param userEmail    The user's email address.
     * @param userPassword The user's password.
     */
    public SignUpInputData(String userID, String userEmail, String userPassword) {
        this.userID = userID;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    /**
     * Returns the user's ID.
     *
     * @return The user's ID.
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Returns the user's email address.
     *
     * @return The user's email address.
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Returns the user's password.
     *
     * @return The user's password.
     */
    public String getUserPassword() {
        return userPassword;
    }
}
