package use_cases.log_in.interface_adapter.controller;

import use_cases.log_in.use_case.input_data.LoginInputBoundary;
import use_cases.log_in.use_case.input_data.LoginInputData;

public class LoginController {
    private final LoginInputBoundary loginInputBoundary;

    public LoginController(LoginInputBoundary loginInputBoundary) {
        this.loginInputBoundary = loginInputBoundary;
    }

    public void login(String userEmail, String userPassword) {
        LoginInputData inputData = new LoginInputData(userEmail, userPassword);
        loginInputBoundary.execute(inputData);
    }
}
