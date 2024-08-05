package frameworks.gui.view_factory;

import app.config.Config;
import frameworks.gui.view_factory.division.*;
import use_cases._common.gui_common.view.HomeView;
import use_cases.add_to_my_recipe.MyRecipeView;
import use_cases.core_functionality.MyGroceryView;
import use_cases.log_in.gui.view.LoginView;
import use_cases.search_recipe.gui.view.SearchRecipeView;
import use_cases.sign_up.gui.view.SignUpView;

public class SwingViewFactory extends ViewFactory {

    public SwingViewFactory(Config config) {
        super(config);
        this.searchRecipeViewDivision = new SearchRecipeViewSwingDivision();
        this.loginViewDivision = new LoginViewSwingDivision();
        this.signUpViewDivision = new SignUpViewSwingDivision();
        this.homeViewDivision = new HomeViewSwingDivision();
        this.myRecipeViewDivision = new MyRecipeViewSwingDivision();
        this.myGroceryViewDivision = new MyGroceryViewSwingDivision();
    }

    @Override
    public SearchRecipeView generateSearchRecipeView() {
        return searchRecipeViewDivision.generate(config);
    }

    @Override
    public LoginView generateLoginView() {
        return loginViewDivision.generate(config);
    }

    @Override
    public SignUpView generateSignUpView() {
        return signUpViewDivision.generate(config);
    }

    @Override
    public HomeView generateHomeView() {
        return homeViewDivision.generate(config);
    }

    @Override
    public MyRecipeView generateMyRecipeView() {
        return myRecipeViewDivision.generate(config);
    }

    @Override
    public MyGroceryView generateMyGroceryView() {
        return myGroceryViewDivision.generate(config);
    }


}
