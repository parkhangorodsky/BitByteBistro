package app.config;

import use_cases.sign_up.interface_adapter.controller.SignUpController;
import use_cases.sign_up.interface_adapter.presenter.SignUpPresenter;
import use_cases.sign_up.use_case.interactor.SignUpInteractor;

import static app.config.DataAccessConfig.userDAO;
import static app.config.ViewModelConfig.signUpViewModel;
import static app.config.ViewModelConfig.viewManagerModel;

class SignUpConfig {

    static final SignUpPresenter presenter = new SignUpPresenter(
            signUpViewModel,
            viewManagerModel);
    static final SignUpInteractor interactor = new SignUpInteractor(
            presenter,
            userDAO);
    static final SignUpController controller = new SignUpController(
            interactor);
}
