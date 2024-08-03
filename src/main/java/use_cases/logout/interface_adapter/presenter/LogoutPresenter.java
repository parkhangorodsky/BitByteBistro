package use_cases.logout.interface_adapter.presenter;

import use_cases._common.authentication.AuthenticationViewModel;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.logout.use_case.output.LogoutOutputBoundary;

public class LogoutPresenter implements LogoutOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final AuthenticationViewModel authenticationViewModel;

    public LogoutPresenter(AuthenticationViewModel authenticationViewModel, ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        this.authenticationViewModel = authenticationViewModel;
    }

    @Override
    public void logoutSuccess() {
        viewManagerModel.setActiveView("LoginView");
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void logoutFailure(String errorMessage) {
        System.out.println(errorMessage); // Handle logout failure
    }
}
