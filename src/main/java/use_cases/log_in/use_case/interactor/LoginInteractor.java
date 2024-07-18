package use_cases.log_in.use_case.interactor;

import entity.User;
import frameworks.data_access.DataAccessInterface;
import use_cases.log_in.use_case.input_data.LoginInputBoundary;
import  use_cases.log_in.use_case.input_data.LoginInputData;
import  use_cases.log_in.use_case.output_data.LoginOutputBoundary;
import  use_cases.log_in.use_case.output_data.LoginOutputData;

public class LoginInteractor implements LoginInputBoundary {
    private final LoginOutputBoundary loginOutputBoundary;
    private final DataAccessInterface DAO;
    private User loggedInUser;

    public LoginInteractor(LoginOutputBoundary loginOutputBoundary, DataAccessInterface dao) {
        this.loginOutputBoundary = loginOutputBoundary;
        this.DAO = dao;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        // Check if the user exists in the repository
        boolean userExists = DAO.existsByEmail(loginInputData.getUserEmail());

        if (userExists) {
            // Successful login
            loggedInUser = DAO.getUserByEmail(loginInputData.getUserEmail());
            if (loginOutputBoundary != null) {
                loginOutputBoundary.prepareSuccessView(new LoginOutputData(loggedInUser));
            }
        } else {
            // Failed login
            loginOutputBoundary.prepareFailView("Invalid email or password.");
        }
    }
}
