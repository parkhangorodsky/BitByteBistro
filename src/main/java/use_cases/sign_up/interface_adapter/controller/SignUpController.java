package use_cases.sign_up.interface_adapter.controller;

import use_cases.sign_up.use_case.input_data.SignUpInputBoundary;
import use_cases.sign_up.use_case.input_data.SignUpInputData;

/**
 * Controller for handling user input related to the sign-up process.
 * This class takes user ID, email, and password, wraps them in a SignUpInputData object,
 * and passes this data to the SignUpInputBoundary for further processing.
 */
public class SignUpController {
    private final SignUpInputBoundary signUpInputBoundary;

    /**
     * Constructs a new SignUpController with the specified input boundary.
     *
     * @param signUpInputBoundary The boundary to handle the sign-up input data.
     */
    public SignUpController(SignUpInputBoundary signUpInputBoundary) {
        this.signUpInputBoundary = signUpInputBoundary;
    }

    /**
     * Handles the sign-up process with the specified user ID, email, and password.
     * This method creates a SignUpInputData object with the provided user ID, email, and password,
     * and calls the execute method on the signUpInputBoundary.
     *
     * @param userID The user's unique identifier.
     * @param userEmail The user's email address.
     * @param userPassword The user's password.
     */
    public void signUp(String userID, String userEmail, String userPassword) {
        SignUpInputData inputData = new SignUpInputData(userID, userEmail, userPassword);
        signUpInputBoundary.execute(inputData);
    }
}
