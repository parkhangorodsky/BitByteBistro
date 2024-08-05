package app.config;

import use_cases.logout.interface_adapter.controller.LogoutController;
import use_cases.logout.interface_adapter.presenter.LogoutPresenter;
import use_cases.logout.use_case.interactor.LogoutInteractor;

import static app.config.ViewModelConfig.authenticationViewModel;
import static app.config.ViewModelConfig.viewManagerModel;

class LogoutConfig {

    static final LogoutPresenter presenter = new LogoutPresenter(
            authenticationViewModel,
            viewManagerModel);

    static final LogoutInteractor interactor = new LogoutInteractor(
            presenter);

    static final LogoutController controller = new LogoutController(
            interactor);

}
