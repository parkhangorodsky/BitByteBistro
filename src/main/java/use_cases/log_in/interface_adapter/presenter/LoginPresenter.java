package use_cases.log_in.interface_adapter.presenter;

import entity.LoggedUserData;
import entity.User;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.log_in.interface_adapter.view_model.LoginViewModel;
import use_cases.log_in.use_case.output_data.LoginOutputBoundary;
import use_cases.log_in.use_case.output_data.LoginOutputData;
import java.beans.PropertyChangeSupport;

/**
 * Presenter for handling the login process.
 * This class implements the LoginOutputBoundary interface and formats the login response
 * from the interactor, updating the LoginViewModel accordingly.
 */
public class LoginPresenter implements LoginOutputBoundary {
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private final PropertyChangeSupport support;

    /**
     * Constructs a new LoginPresenter with the specified view model, view manager model, and PropertyChangeSupport.
     *
     * @param loginViewModel The view model to update based on the login result.
     * @param viewManagerModel The model to manage view transitions.
     * @param support The PropertyChangeSupport for notifying listeners of property changes.
     */
    public LoginPresenter(LoginViewModel loginViewModel, ViewManagerModel viewManagerModel, PropertyChangeSupport support) {
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        this.support = support;
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

        // Set the logged-in user in LoggedUserData
        User oldLoggedInUser = LoggedUserData.getLoggedInUser();
        LoggedUserData.setLoggedInUser(outputData.getUser());

        // Notify listeners that the loggedInUser property has changed
        support.firePropertyChange("loggedInUser", oldLoggedInUser, outputData.getUser());

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
