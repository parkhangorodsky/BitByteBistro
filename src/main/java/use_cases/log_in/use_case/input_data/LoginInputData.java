package use_cases.log_in.use_case.input_data;

/**
 * LoginInputData is a data transfer object (DTO) that holds the user's email and password.
 * This class is used to transfer the login credentials from the view to the interactor.
 */
public class LoginInputData {
    private String userEmail;
    private String userPassword;

    /**
     * Constructs a new LoginInputData object with the specified email and password.
     *
     * @param userEmail The user's email address.
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
