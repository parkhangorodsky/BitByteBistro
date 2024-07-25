package use_cases.log_in.use_case.interactor;

import entity.User;
import frameworks.data_access.DataAccessInterface;
import use_cases.log_in.use_case.input_data.LoginInputBoundary;
import use_cases.log_in.use_case.input_data.LoginInputData;
import use_cases.log_in.use_case.output_data.LoginOutputBoundary;
import use_cases.log_in.use_case.output_data.LoginOutputData;

/**
 * Interactor for handling the login process.
 * This class implements the LoginInputBoundary interface and performs the
 * login operation by interacting with a data access object (DAO) and
 * providing output to the LoginOutputBoundary.
 */
public class LoginInteractor implements LoginInputBoundary {
    private final LoginOutputBoundary loginOutputBoundary;
    private final DataAccessInterface DAO;
    private User loggedInUser;

    /**
     * Constructs a new LoginInteractor with the specified output boundary and DAO.
     *
     * @param loginOutputBoundary The boundary to handle the output of the login process.
     * @param dao The data access object to interact with the data source.
     */
    public LoginInteractor(LoginOutputBoundary loginOutputBoundary, DataAccessInterface dao) {
        this.loginOutputBoundary = loginOutputBoundary;
        this.DAO = dao;
    }

    /**
     * Returns the currently logged-in user.
     *
     * @return The logged-in user, or null if no user is logged in.
     */
    public User getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * Executes the login process with the specified input data.
     * This method checks if the user exists in the data source and if the
     * provided password matches the stored password. It sets the
     * loggedInUser field if the login is successful and calls the
     * appropriate method on the loginOutputBoundary to handle the result.
     *
     * @param loginInputData The input data for the login process.
     */
    @Override
    public void execute(LoginInputData loginInputData) {
        // Check if the user exists and authenticate
        if (DAO.authenticate(loginInputData.getUserEmail(), loginInputData.getUserPassword())) {
            // Successful login
            loggedInUser = DAO.getLoggedInUser(); // Get the logged-in user from DAO
            if (loginOutputBoundary != null) {
                loginOutputBoundary.prepareSuccessView(new LoginOutputData(loggedInUser));
            }
        } else {
            // Failed login
            loginOutputBoundary.prepareFailView("Invalid email or password.");
        }
    }
}
