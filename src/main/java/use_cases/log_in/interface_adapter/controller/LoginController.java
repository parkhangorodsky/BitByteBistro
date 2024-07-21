package use_cases.log_in.interface_adapter.controller;

import use_cases.log_in.use_case.input_data.LoginInputBoundary;
import use_cases.log_in.use_case.input_data.LoginInputData;

/**
 * Controller for handling user input related to the login process.
 * This class takes the user's email and password, wraps them in a LoginInputData object,
 * and passes this data to the LoginInputBoundary for further processing.
 */
public class LoginController {
    private final LoginInputBoundary loginInputBoundary;

    /**
     * Constructs a new LoginController with the specified input boundary.
     *
     * @param loginInputBoundary The boundary to handle the login input data.
     */
    public LoginController(LoginInputBoundary loginInputBoundary) {
        this.loginInputBoundary = loginInputBoundary;
    }

    /**
     * Handles the login process with the specified user email and password.
     * This method creates a LoginInputData object with the provided email and password,
     * and calls the execute method on the loginInputBoundary.
     *
     * @param userEmail The user's email address.
     * @param userPassword The user's password.
     */
    public void login(String userEmail, String userPassword) {
        LoginInputData inputData = new LoginInputData(userEmail, userPassword);
        loginInputBoundary.execute(inputData);
    }
}