package frameworks.gui.view_factory.division;

import app.Config;
import use_cases.log_in.gui.view.LoginView;

public class LoginViewSwingDivision implements ViewFactoryDivision<LoginView> {

    @Override
    public LoginView generate(Config config) {
        return new LoginView(
                config.getLoginController(),
                config.getLoginViewModel(),
                config.getViewManagerModel());
    }
}
