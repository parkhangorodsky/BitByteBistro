package use_cases.sign_up.interface_adapter.presenter;

import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.sign_up.interface_adapter.view_model.SignUpViewModel;
import use_cases.sign_up.use_case.output_data.SignUpOutputBoundary;
import use_cases.sign_up.use_case.output_data.SignUpOutputData;

/**
 * Presenter for handling the sign-up process.
 * This class implements the SignUpOutputBoundary interface and formats the sign-up response
 * from the interactor, updating the SignUpViewModel accordingly.
 */
public class SignUpPresenter implements SignUpOutputBoundary {
    private final SignUpViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs a new SignUpPresenter with the specified view model and view manager model.
     *
     * @param viewModel The view model to update based on the sign-up result.
     * @param viewManagerModel The view manager model to handle view changes.
     */
    public SignUpPresenter(SignUpViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares the view to display a successful sign-up result.
     * Updates the view model with the user's email and ID, and sets a success message.
     * Switches the active view to the login view.
     *
     * @param outputData The output data containing the user information and sign-up result.
     */
    @Override
    public void prepareSuccessView(SignUpOutputData outputData) {
        // Convert the output data to the view model format
        viewModel.setUserEmail(outputData.getUser().getUserEmail());
        viewModel.setUserID(outputData.getUser().getUserName());
        viewModel.setSuccessMessage("User signed up successfully!");

        // Switch to login view
        viewManagerModel.setActiveView("LoginView");
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the view to display an error message.
     * Updates the view model with the provided error message.
     *
     * @param error The error message to be displayed.
     */
    @Override
    public void prepareErrorView(String error) {
        viewModel.setErrorMessage(error);
    }
}
