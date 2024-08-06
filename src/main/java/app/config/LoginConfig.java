package app.config;

import use_cases.log_in.interface_adapter.controller.LoginController;
import use_cases.log_in.interface_adapter.presenter.LoginPresenter;
import use_cases.log_in.use_case.interactor.LoginInteractor;

import static app.config.ViewModelConfig.loginViewModel;
import static app.config.ViewModelConfig.viewManagerModel;
import static app.config.ViewModelConfig.authenticationViewModel;
import static app.config.DataAccessConfig.userDAO;

class LoginConfig {

    static final LoginPresenter presenter = new LoginPresenter(
            loginViewModel,
            viewManagerModel,
            authenticationViewModel);

    static final LoginInteractor interactor = new LoginInteractor(
            presenter,
            userDAO);

    static final LoginController controller = new LoginController(interactor);
}
