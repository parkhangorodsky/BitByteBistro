package use_cases.log_in.interface_adapter.presenter;

import use_cases.log_in.use_case.output_data.LoginOutputBoundary;
import use_cases.log_in.use_case.output_data.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {
    private String successMessage;
    private String errorMessage;

    @Override
    public void prepareSuccessView(LoginOutputData loginOutputData) {
        this.successMessage = "Login successful. Welcome, " + loginOutputData.getUser().getUserName() + "!";
    }

    @Override
    public void prepareFailView(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
