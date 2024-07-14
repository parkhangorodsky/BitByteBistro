package use_cases.log_in.use_case.interactor;

import entity.User;
import frameworks.data_access.UserRepository;
import use_cases.log_in.use_case.input_data.LoginInputBoundary;
import  use_cases.log_in.use_case.input_data.LoginInputData;
import  use_cases.log_in.use_case.output_data.LoginOutputBoundary;
import  use_cases.log_in.use_case.output_data.LoginOutputData;

public class LoginInteractor implements LoginInputBoundary {
    private final LoginOutputBoundary loginOutputBoundary;
    private final UserRepository userRepository;
    private User loggedInUser;

    public LoginInteractor(LoginOutputBoundary loginOutputBoundary, UserRepository userRepository) {
        this.loginOutputBoundary = loginOutputBoundary;
        this.userRepository = userRepository;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        // Check if the user exists in the repository
        User user = userRepository.getUserByEmail(loginInputData.getUserEmail());

        if (user != null && user.getUserPassword().equals(loginInputData.getUserPassword())) {
            // Successful login
            loggedInUser = user;
            if (loginOutputBoundary != null) {
                loginOutputBoundary.prepareSuccessView(new LoginOutputData(user));
            }
        } else {
            // Failed login
            loggedInUser = null;
            if (loginOutputBoundary != null) {
                loginOutputBoundary.prepareFailView("Invalid email or password.");
            }
        }
    }
}
