package app.config;

// API
import frameworks.api.NutritionAPI;
import frameworks.api.RecipeAPI;

// GUI
import frameworks.gui.GUI;

// Data Access
import frameworks.data_access.UserDataAccessInterface;

// View Models
import use_cases._common.authentication.AuthenticationViewModel;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.add_to_my_recipe.MyRecipeViewModel;
import use_cases.fridge_inventory.FridgeInventoryViewModel;
import use_cases.log_in.interface_adapter.view_model.LoginViewModel;
import use_cases.search_recipe.interface_adapter.view_model.AdvancedSearchRecipeViewModel;
import use_cases.search_recipe.interface_adapter.view_model.SearchRecipeViewModel;
import use_cases.sign_up.interface_adapter.view_model.SignUpViewModel;
import use_cases.core_functionality.MyGroceryViewModel;

// Use Case Controllers
import use_cases.add_to_my_recipe.AddToMyRecipeController;
import use_cases.display_recipe_detail.DisplayRecipeDetailController;
import use_cases.recently_viewed_recipes.RecentlyViewedRecipesController;
import use_cases.filter_recipe.FilterRecipeController;
import use_cases.search_recipe.interface_adapter.controller.SearchRecipeController;
import use_cases.log_in.interface_adapter.controller.LoginController;
import use_cases.setting_preference.SetPreferenceController;
import use_cases.sign_up.interface_adapter.controller.SignUpController;
import use_cases.logout.interface_adapter.controller.LogoutController;
import use_cases.add_new_grocery_list.AddNewGroceryListController;
import use_cases.core_functionality.CoreFunctionalityController;

public class Config {

    // ViewModel Getters
    public ViewManagerModel getViewManagerModel() { return ViewModelConfig.viewManagerModel; }
    public AuthenticationViewModel getAuthenticationViewModel() {return ViewModelConfig.authenticationViewModel;}
    public LoginViewModel getLoginViewModel() { return ViewModelConfig.loginViewModel; }
    public SignUpViewModel getSignUpViewModel() { return ViewModelConfig.signUpViewModel; }
    public SearchRecipeViewModel getSearchRecipeViewModel() { return ViewModelConfig.searchRecipeViewModel; }
    public AdvancedSearchRecipeViewModel getAdvancedSearchRecipeViewModel() { return ViewModelConfig.advancedSearchRecipeViewModel; }
    public MyRecipeViewModel getMyRecipeViewModel() { return ViewModelConfig.myRecipeViewModel; }
    public FridgeInventoryViewModel getFridgeInventoryViewModel() { return ViewModelConfig.fridgeInventoryViewModel; }
    public MyGroceryViewModel MyGroceryViewModel() { return ViewModelConfig.myGroceryViewModel; }

    // Frameworks & Drivers Getters
    public RecipeAPI getRecipeAPI() { return ApiConfig.recipeAPI; }
    public NutritionAPI getNutritionAPI() { return ApiConfig.nutritionAPI; }
    public UserDataAccessInterface getDataAccessInterface() { return DataAccessConfig.userDAO; }
    public GUI getGUI() { return GUIConfig.gui; }

    // UseCase Getters
    public SearchRecipeController getSearchRecipeController() { return SearchRecipeConfig.controller; }
    public LoginController getLoginController() { return LoginConfig.controller; }
    public SignUpController getSignUpController() { return SignUpConfig.controller; }
    public LogoutController getLogoutController() { return LogoutConfig.controller; }
    public AddToMyRecipeController getAddToMyRecipeController() { return AddToMyRecipeConfig.controller; }
    public FilterRecipeController getFilterRecipeController() { return FilterRecipeConfig.controller; }
    public SetPreferenceController getSetPreferenceController() { return SetPreferenceConfig.controller; }
    public DisplayRecipeDetailController getDisplayRecipeDetailController() { return DisplayRecipeDetailConfig.controller; }
    public RecentlyViewedRecipesController getRecentlyViewedRecipesController() { return RecentlyViewedRecipeConfig.controller; }
    public AddNewGroceryListController getAddNewGroceryListController() { return AddNewGroceryListConfig.controller;}
    public CoreFunctionalityController getCoreFunctionalityController() { return CoreFunctionalityConfig.controller; }

}
