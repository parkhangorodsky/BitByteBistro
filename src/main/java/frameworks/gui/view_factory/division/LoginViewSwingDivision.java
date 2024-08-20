package frameworks.gui.view_factory.division;

import app.config.Config;
import use_cases._common.gui_common.abstractions.View;
import use_cases.log_in.gui.view.LoginView;

public class LoginViewSwingDivision implements ViewFactoryDivision {

    @Override
    public View generate(Config config) {
        return new LoginView(
                config.getLoginController(),
                config.getLoginViewModel(),
                config.getViewManagerModel());
    }
}
