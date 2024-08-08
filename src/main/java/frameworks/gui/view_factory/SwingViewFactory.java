package frameworks.gui.view_factory;

import app.config.Config;
import frameworks.gui.view_factory.division.*;
import use_cases._common.gui_common.abstractions.View;
import use_cases._common.gui_common.view.HomeView;
import use_cases.add_to_my_recipe.MyRecipeView;
import use_cases.core_functionality.MyGroceryView;
import use_cases.log_in.gui.view.LoginView;
import use_cases.search_recipe.gui.view.SearchRecipeView;
import use_cases.sign_up.gui.view.SignUpView;

public class SwingViewFactory extends ViewFactory {

    public SwingViewFactory(Config config) {
        super(config);
        this.addDivision("SearchRecipeView", new SearchRecipeViewSwingDivision());
        this.addDivision("LoginView", new LoginViewSwingDivision());
        this.addDivision("SignUpView", new SignUpViewSwingDivision());
        this.addDivision("HomeView", new HomeViewSwingDivision());
        this.addDivision("MyRecipeView", new MyRecipeViewSwingDivision());
        this.addDivision("MyGroceryView", new MyGroceryViewSwingDivision());
    }
}
