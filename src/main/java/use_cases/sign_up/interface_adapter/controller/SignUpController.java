package use_cases.sign_up.interface_adapter.controller;

import use_cases.sign_up.use_case.input_data.SignUpInputBoundary;
import use_cases.sign_up.use_case.input_data.SignUpInputData;

public class SignUpController {
    private final SignUpInputBoundary signUpInputBoundary;

    public SignUpController(SignUpInputBoundary signUpInputBoundary) {
        this.signUpInputBoundary = signUpInputBoundary;
    }

    public void signUp(String userID, String userEmail, String userPassword) {
        SignUpInputData inputData = new SignUpInputData(userID, userEmail, userPassword);
        signUpInputBoundary.execute(inputData);
    }
}
