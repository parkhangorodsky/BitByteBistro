package use_cases.log_in.interface_adapter.controller;

import entity.User;
import use_cases._common.authentication.AuthenticationService;
import use_cases.log_in.use_case.input_data.LoginInputBoundary;
import use_cases.log_in.use_case.input_data.LoginInputData;

/**
 * Controller for handling user input related to the login process.
 * This class takes the user's email and password, wraps them in a LoginInputData object,
 * and passes this data to the LoginInputBoundary for further processing.
 */
public class LoginController {
    private final LoginInputBoundary loginInputBoundary;
    private final AuthenticationService authService;

    /**
     * Constructs a new LoginController with the specified input boundary.
     *
     * @param loginInputBoundary The boundary to handle the login input data.
     * @param authService The authentication service for user authentication and session management.
     */
    public LoginController(LoginInputBoundary loginInputBoundary, AuthenticationService authService) {
        this.loginInputBoundary = loginInputBoundary;
        this.authService = authService;
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
        // Perform authentication
        boolean isAuthenticated = authService.authenticate(userEmail, userPassword);

        if (isAuthenticated) {
            // If authenticated, retrieve logged-in user
            User loggedInUser = authService.getLoggedInUser();
            // Proceed with further actions (e.g., load user profile, redirect to dashboard)
            loginInputBoundary.execute(new LoginInputData(loggedInUser.getUserEmail(), loggedInUser.getUserPassword()));
        } else {
            // Handle authentication failure (e.g., show error message to user)
        }
    }
}