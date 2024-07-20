package use_cases.log_in.use_case.input_data;

/**
 * Class representing the input data for the login use case.
 * This class holds the user's login credentials, including the email and password.
 */
public class LoginInputData {
    private String userEmail;
    private String userPassword;

    /**
     * Constructs a new LoginInputData instance with the specified email and password.
     *
     * @param userEmail    The user's email address.
     * @param userPassword The user's password.
     */
    public LoginInputData(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
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
