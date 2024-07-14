package use_cases.sign_up.interface_adapter.presenter;

import use_cases.sign_up.interface_adapter.view_model.SignUpViewModel;
import use_cases.sign_up.use_case.output_data.SignUpOutputBoundary;
import use_cases.sign_up.use_case.output_data.SignUpOutputData;

public class SignUpPresenter implements SignUpOutputBoundary {
    private SignUpViewModel viewModel;

    public SignUpPresenter(SignUpViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(SignUpOutputData signUpOutputData) {
        // Convert the output data to the view model format
        viewModel.setUserID(signUpOutputData.getUser().getUserID());
        viewModel.setUserEmail(signUpOutputData.getUser().getUserEmail());
        viewModel.setSuccessMessage("User signed up successfully!");
        // Optionally, you can add more logic for error handling or other messages
    }
}
