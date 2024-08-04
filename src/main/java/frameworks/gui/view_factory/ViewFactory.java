package frameworks.gui.view_factory;

import app.Config;
import frameworks.gui.view_factory.division.ViewFactoryDivision;
import use_cases._common.gui_common.view.HomeView;
import use_cases.add_to_my_recipe.MyRecipeView;
import use_cases.log_in.gui.view.LoginView;
import use_cases.recipe_to_grocery.gui.RecipeToGroceryView;
import use_cases.search_recipe.gui.view.SearchRecipeView;
import use_cases.sign_up.gui.view.SignUpView;

public abstract class ViewFactory {
    protected Config config;
    protected ViewFactoryDivision<SearchRecipeView> searchRecipeViewDivision;
    protected ViewFactoryDivision<RecipeToGroceryView> recipeToGroceryViewDivision;
    protected ViewFactoryDivision<LoginView> loginViewDivision;
    protected ViewFactoryDivision<SignUpView> signUpViewDivision;
    protected ViewFactoryDivision<HomeView> homeViewDivision;
    protected ViewFactoryDivision<MyRecipeView> myRecipeViewDivision;

    public ViewFactory(Config config) {
        this.config = config;
    }

    public abstract SearchRecipeView generateSearchRecipeView();
    public abstract RecipeToGroceryView generateRecipeToGroceryView();
    public abstract LoginView generateLoginView();
    public abstract SignUpView generateSignUpView();
    public abstract HomeView generateHomeView();
    public abstract MyRecipeView generateMyRecipeView();
}
