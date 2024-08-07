package use_cases.log_in.use_case.interactor;

import app.local.LocalAppSetting;
import app.local.LoggedUserData;
import entity.User;
import frameworks.data_access.UserDataAccessInterface;
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
    private final UserDataAccessInterface DAO;

    /**
     * Constructs a new LoginInteractor with the specified output boundary and DAO.
     *
     * @param loginOutputBoundary The boundary to handle the output of the login process.
     * @param dao The data access object to interact with the data source.
     */
    public LoginInteractor(LoginOutputBoundary loginOutputBoundary, UserDataAccessInterface dao) {
        this.loginOutputBoundary = loginOutputBoundary;
        this.DAO = dao;
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
        // Check if the user exists in the repository
        User user = DAO.getUserByEmail(loginInputData.getUserEmail());

        if (user != null && user.getUserPassword().equals(loginInputData.getUserPassword())) {
            // Successful login
            LoggedUserData.setLoggedInUser(user);
            LocalAppSetting.setNightMode((boolean) user.getPreference().get("nightMode"));
            LocalAppSetting.firePropertyChange("nightMode");
            // Set the logged-in user in LoggedUserData

            if (loginOutputBoundary != null) {
                loginOutputBoundary.prepareSuccessView(new LoginOutputData(user));
            }
        } else {
            // Failed login
            loginOutputBoundary.prepareFailView("Invalid email or password.");
        }
    }
}
