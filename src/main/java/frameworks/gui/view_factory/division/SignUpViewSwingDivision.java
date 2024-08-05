package frameworks.gui.view_factory.division;

import app.Config;
import use_cases.sign_up.gui.view.SignUpView;

public class SignUpViewSwingDivision implements ViewFactoryDivision<SignUpView>{

    @Override
    public SignUpView generate(Config config) {
        return new SignUpView(config.getSignUpController()
        ,config.getSignUpViewModel()
        ,config.getViewManagerModel());
    }
}
