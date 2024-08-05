package frameworks.gui.view_factory;

import app.config.Config;
import frameworks.gui.view_factory.division.ViewFactoryDivision;
import use_cases._common.gui_common.view.HomeView;
import use_cases.add_to_my_recipe.MyRecipeView;
import use_cases.core_functionality.MyGroceryView;
import use_cases.log_in.gui.view.LoginView;
import use_cases.search_recipe.gui.view.SearchRecipeView;
import use_cases.sign_up.gui.view.SignUpView;

public abstract class ViewFactory {
    protected Config config;
    protected ViewFactoryDivision<SearchRecipeView> searchRecipeViewDivision;
    protected ViewFactoryDivision<LoginView> loginViewDivision;
    protected ViewFactoryDivision<SignUpView> signUpViewDivision;
    protected ViewFactoryDivision<HomeView> homeViewDivision;
    protected ViewFactoryDivision<MyRecipeView> myRecipeViewDivision;
    protected ViewFactoryDivision<MyGroceryView> myGroceryViewDivision;

    public ViewFactory(Config config) {
        this.config = config;
    }

    public abstract SearchRecipeView generateSearchRecipeView();
    public abstract LoginView generateLoginView();
    public abstract SignUpView generateSignUpView();
    public abstract HomeView generateHomeView();
    public abstract MyRecipeView generateMyRecipeView();
    public abstract MyGroceryView generateMyGroceryView();

}
