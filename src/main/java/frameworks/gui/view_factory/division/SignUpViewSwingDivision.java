package frameworks.gui.view_factory.division;

import app.config.Config;
import use_cases._common.gui_common.abstractions.View;
import use_cases.sign_up.gui.view.SignUpView;

public class SignUpViewSwingDivision implements ViewFactoryDivision {

    @Override
    public View generate(Config config) {
        return new SignUpView(
                config.getSignUpController(),
                config.getSignUpViewModel(),
                config.getViewManagerModel());
    }
}
