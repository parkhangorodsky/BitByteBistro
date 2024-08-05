package app.config;

import use_cases.setting_preference.*;

import static app.config.DataAccessConfig.userDAO;

class SetPreferenceConfig {

    static final SetPreferenceOutputBoundary presenter = new SetPreferencePresenter();

    static final SetPreferenceInputBoundary interactor = new SetPreferenceInteractor(
            presenter,
            userDAO);

    static final SetPreferenceController controller = new SetPreferenceController(
            interactor);

}
