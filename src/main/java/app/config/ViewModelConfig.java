package app.config;

import use_cases._common.authentication.AuthenticationViewModel;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.add_to_my_recipe.MyRecipeViewModel;
import use_cases.core_functionality.MyGroceryViewModel;
import use_cases.log_in.interface_adapter.view_model.LoginViewModel;
import use_cases.search_recipe.interface_adapter.view_model.AdvancedSearchRecipeViewModel;
import use_cases.search_recipe.interface_adapter.view_model.SearchRecipeViewModel;
import use_cases.sign_up.interface_adapter.view_model.SignUpViewModel;

class ViewModelConfig {

    static final ViewManagerModel viewManagerModel = new ViewManagerModel();
    static final AuthenticationViewModel authenticationViewModel = new AuthenticationViewModel("AuthView", GUIConfig.gui );
    static final LoginViewModel loginViewModel = new LoginViewModel("LoginView");
    static final SignUpViewModel signUpViewModel = new SignUpViewModel("SignUpView");
    static final SearchRecipeViewModel searchRecipeViewModel = new SearchRecipeViewModel("Search Recipe");
    static final AdvancedSearchRecipeViewModel advancedSearchRecipeViewModel = new AdvancedSearchRecipeViewModel("Advanced Search");
    static final MyRecipeViewModel myRecipeViewModel = new MyRecipeViewModel("My Recipe");
    static final MyGroceryViewModel myGroceryViewModel = new MyGroceryViewModel("My Grocery");


}
