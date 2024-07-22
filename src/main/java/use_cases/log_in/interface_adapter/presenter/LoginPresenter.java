package use_cases.log_in.interface_adapter.presenter;

import use_cases._common.authentication.AuthenticationService;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.log_in.interface_adapter.view_model.LoginViewModel;
import use_cases.log_in.use_case.output_data.LoginOutputBoundary;
import use_cases.log_in.use_case.output_data.LoginOutputData;

/**
 * Presenter for handling the login process.
 * This class implements the LoginOutputBoundary interface and formats the login response
 * from the interactor, updating the LoginViewModel accordingly.
 */
public class LoginPresenter implements LoginOutputBoundary {
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private final AuthenticationService authenticationService; // Add reference to AuthenticationService

    /**
     * Constructs a new LoginPresenter with the specified view model and authentication service.
     *
     * @param loginViewModel The view model to update based on the login result.
     * @param viewManagerModel The model to manage view transitions.
     * @param authenticationService The service to manage authentication and session.
     */
    public LoginPresenter(LoginViewModel loginViewModel, ViewManagerModel viewManagerModel, AuthenticationService authenticationService) {
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        this.authenticationService = authenticationService; // Initialize AuthenticationService
    }

    /**
     * Prepares the success view when the login is successful.
     * This method clears any previous error messages and can perform additional actions
     * like navigation upon successful login.
     *
     * @param outputData The output data containing the user information.
     */
    @Override
    public void prepareSuccessView(LoginOutputData outputData) {
        // Clear any previous error messages
        loginViewModel.setErrorMessage("");

        // Set the logged-in user in the AuthenticationService
        authenticationService.setLoggedInUser(outputData.getUser());

        // Perform additional actions on successful login, like navigation
        viewManagerModel.setActiveView("search recipe");
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the fail view when the login fails.
     * This method updates the view model with the error message.
     *
     * @param errorMessage The error message to display.
     */
    @Override
    public void prepareFailView(String errorMessage) {
        // Update the view model with the error message
        loginViewModel.setErrorMessage(errorMessage);
    }
}
