package use_cases.logout.interface_adapter.presenter;

import entity.LoggedUserData;
import use_cases._common.authentication.AuthenticationViewModel;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.logout.use_case.output.LogoutOutputBoundary;

/**
 * The LogoutPresenter class implements the LogoutOutputBoundary interface and is responsible
 * for handling the presentation logic after a logout operation.
 * <p>
 * It updates the authentication view model and manages the view based on the success or failure
 * of the logout process.
 * </p>
 */
public class LogoutPresenter implements LogoutOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final AuthenticationViewModel authenticationViewModel;

    /**
     * Constructs a LogoutPresenter with the specified authentication and view manager models.
     *
     * @param authenticationViewModel The view model responsible for managing authentication state.
     * @param viewManagerModel The view manager model responsible for managing the user interface.
     */
    public LogoutPresenter(AuthenticationViewModel authenticationViewModel, ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        this.authenticationViewModel = authenticationViewModel;
    }

    /**
     * Handles a successful logout operation.
     * <p>
     * This method updates the authentication view model to reflect that the user has logged out
     * and triggers any associated property change listeners.
     * </p>
     */
    @Override
    public void logoutSuccess() {
        System.out.println("Logged out");
        authenticationViewModel.firePropertyChange("logoutSuccess", LoggedUserData.getLoggedInUser(), null);
    }

    /**
     * Handles a failed logout operation.
     * <p>
     * This method prints the error message to the console.
     * </p>
     *
     * @param errorMessage The error message describing why the logout failed.
     */
    @Override
    public void logoutFailure(String errorMessage) {
        System.out.println(errorMessage); // Handle logout failure
    }
}

