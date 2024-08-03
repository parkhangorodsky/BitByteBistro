package use_cases.log_in.interface_adapter.presenter;

import entity.LoggedUserData;
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

    /**
     * Constructs a new LoginPresenter with the specified view model and view manager model.
     *
     * @param loginViewModel The view model to update based on the login result.
     * @param viewManagerModel The model to manage view transitions.
     */
    public LoginPresenter(LoginViewModel loginViewModel, ViewManagerModel viewManagerModel) {
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
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

        loginViewModel.setErrorMessage("");
        LoggedUserData.setLoggedInUser(outputData.getUser());
        loginViewModel.firePropertyChange("loggedInUser", null, outputData.getUser());
        viewManagerModel.setActiveView("Home");
        viewManagerModel.firePropertyChanged();
        System.out.println("Login success: " + LoggedUserData.getLoggedInUser().getRecipes());
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
