package use_cases.sign_up.use_case.input_data;

/**
 * SignUpInputData is a data transfer object (DTO) that holds the input data for the sign-up process.
 * This class encapsulates the user's ID, email, and password.
 */
public class SignUpInputData {
    private String userID;
    private String userEmail;
    private String userPassword;

    /**
     * Constructs a new SignUpInputData object with the specified user ID, email, and password.
     *
     * @param userID       The user's unique identifier.
     * @param userEmail    The user's email address.
     * @param userPassword The user's password.
     */
    public SignUpInputData(String userID, String userEmail, String userPassword) {
        this.userID = userID;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    /**
     * Returns the user ID.
     *
     * @return The user ID.
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Returns the user email.
     *
     * @return The user email.
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Returns the user password.
     *
     * @return The user password.
     */
    public String getUserPassword() {
        return userPassword;
    }
}
