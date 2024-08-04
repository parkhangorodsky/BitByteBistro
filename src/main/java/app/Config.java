package app;

// API
import frameworks.api.NutritionAPI;
import frameworks.api.NutritionDisplayApi;
import frameworks.api.RecipeAPI;
import frameworks.api.EdamamRecipeApi;

// GUI
import frameworks.data_access.MongoDBConnection;
import frameworks.data_access.MongoUserDAO;
import frameworks.gui.GUI;
import frameworks.gui.SwingGUI;

// Interface Adapters
import use_cases._common.authentication.AuthenticationViewModel;
import use_cases.add_to_my_recipe.AddToMyRecipeController;
import use_cases.add_to_my_recipe.AddToMyRecipeInteractor;
import use_cases.add_to_my_recipe.AddToMyRecipePresenter;
import use_cases.add_to_my_recipe.MyRecipeViewModel;
import use_cases.display_recipe_detail.DisplayRecipeDetailController;
import use_cases.display_recipe_detail.DisplayRecipeDetailInteractor;
import use_cases.display_recipe_detail.DisplayRecipeDetailPresenter;
import use_cases.recently_viewed_recipes.RecentlyViewedRecipesController;
import use_cases.recently_viewed_recipes.RecentlyViewedRecipesInteractor;
import use_cases.filter_recipe.FilterRecipeController;
import use_cases.search_recipe.interface_adapter.controller.SearchRecipeController;
import use_cases.search_recipe.interface_adapter.presenter.SearchRecipePresenter;
import use_cases.search_recipe.interface_adapter.view_model.AdvancedSearchRecipeViewModel;
import use_cases.search_recipe.interface_adapter.view_model.SearchRecipeViewModel;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.search_recipe.use_case.interactor.SearchRecipeInteractor;

// Login UseCase
import use_cases.log_in.interface_adapter.controller.LoginController;
import use_cases.log_in.interface_adapter.presenter.LoginPresenter;
import use_cases.log_in.interface_adapter.view_model.LoginViewModel;
import use_cases.log_in.use_case.interactor.LoginInteractor;

// Sign Up UseCase
import use_cases.settting_preference.*;
import use_cases.sign_up.interface_adapter.controller.SignUpController;
import use_cases.sign_up.interface_adapter.presenter.SignUpPresenter;
import use_cases.sign_up.interface_adapter.view_model.SignUpViewModel;
import use_cases.sign_up.use_case.interactor.SignUpInteractor;

// Logout UseCase

import use_cases.logout.use_case.interactor.LogoutInteractor;
import use_cases.logout.interface_adapter.presenter.LogoutPresenter;
import use_cases.logout.interface_adapter.controller.LogoutController;

// Data Access
import frameworks.data_access.UserDataAccessInterface;

public class Config {

    // View Models
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();

    private final AuthenticationViewModel authenticationViewModel = new AuthenticationViewModel("AuthView", getGUI());
    private final LoginViewModel loginViewModel = new LoginViewModel("LoginView");

    private final SearchRecipeViewModel searchRecipeViewModel = new SearchRecipeViewModel("Search Recipe");
    private final AdvancedSearchRecipeViewModel advancedSearchRecipeViewModel = new AdvancedSearchRecipeViewModel("Advanced Search");
    private final MyRecipeViewModel myRecipeViewModel = new MyRecipeViewModel("My Recipe");
    private final SignUpViewModel signUpViewModel = new SignUpViewModel("SignUpView");
    // Auxiliary
    private final RecipeAPI recipeAPI = new EdamamRecipeApi();
    private final NutritionAPI nutritionAPI = new NutritionDisplayApi();

    //Database
    private final MongoDBConnection mongoDBConnection = new MongoDBConnection();
    private final UserDataAccessInterface userDAO = new MongoUserDAO(mongoDBConnection.getDatabase());

    // GUI
    private final GUI gui = new SwingGUI();

    // UseCases
    // Search Recipe
    private final SearchRecipePresenter searchRecipePresenter = new SearchRecipePresenter(viewManagerModel, searchRecipeViewModel);
    private final SearchRecipeInteractor searchRecipeInteractor = new SearchRecipeInteractor(searchRecipePresenter, recipeAPI);
    private final SearchRecipeController searchRecipeController = new SearchRecipeController(searchRecipeInteractor);

    // Login UseCase
    LoginPresenter loginPresenter = new LoginPresenter(loginViewModel, viewManagerModel, authenticationViewModel);
    private final LoginInteractor loginInteractor = new LoginInteractor(loginPresenter, userDAO);
    private final LoginController loginController = new LoginController(loginInteractor);

    // Sign Up UseCase
    private final SignUpPresenter signUpPresenter = new SignUpPresenter(signUpViewModel, viewManagerModel);
    private final SignUpInteractor signUpInteractor = new SignUpInteractor(signUpPresenter, userDAO);
    private final SignUpController signUpController = new SignUpController(signUpInteractor);

    // Logout UseCase

    private final LogoutPresenter logoutPresenter = new LogoutPresenter(authenticationViewModel, viewManagerModel);
    private final LogoutInteractor logoutInteractor = new LogoutInteractor(logoutPresenter);
    private final LogoutController logoutController = new LogoutController(logoutInteractor);

    // Recipe To Grocery UseCase

    // Display Recipe Detail UseCase
    private final DisplayRecipeDetailPresenter displayRecipeDetailPresenter = new DisplayRecipeDetailPresenter();
    private final DisplayRecipeDetailInteractor displayRecipeDetailInteractor = new DisplayRecipeDetailInteractor(displayRecipeDetailPresenter);
    private final DisplayRecipeDetailController displayRecipeDetailController = new DisplayRecipeDetailController(displayRecipeDetailInteractor);

    // Add to my recipe UseCase
    private final AddToMyRecipePresenter addToMyRecipePresenter = new AddToMyRecipePresenter(myRecipeViewModel);
    private final AddToMyRecipeInteractor addToMyRecipeInteractor = new AddToMyRecipeInteractor(addToMyRecipePresenter, userDAO);
    private final AddToMyRecipeController addToMyRecipeController = new AddToMyRecipeController(addToMyRecipeInteractor);

    // Filter My Recipe Usecase
    private  final FilterRecipeController filterRecipeController = new FilterRecipeController(myRecipeViewModel);
    // Add to my recently viewed recipes UseCase
    private final RecentlyViewedRecipesInteractor recentlyViewedRecipesInteractor = new RecentlyViewedRecipesInteractor(userDAO);
    private final RecentlyViewedRecipesController recentlyViewedRecipesController = new RecentlyViewedRecipesController(recentlyViewedRecipesInteractor);

    // Set Preference Use Case
    private final SetPreferenceOutputBoundary SetPreferencePresenter = new SetPreferencePresenter();
    private final SetPreferenceInputBoundary setPreferenceInteractor = new SetPreferenceInteractor(SetPreferencePresenter, userDAO);
    private final SetPreferenceController setPreferenceController = new SetPreferenceController(setPreferenceInteractor);

    // ViewModel Getters
    public ViewManagerModel getViewManagerModel() { return viewManagerModel; }
    public SearchRecipeViewModel getSearchRecipeViewModel() { return searchRecipeViewModel; }
    public AdvancedSearchRecipeViewModel getAdvancedSearchRecipeViewModel() { return advancedSearchRecipeViewModel; }
    public MyRecipeViewModel getMyRecipeViewModel() { return myRecipeViewModel; }
    public LoginViewModel getLoginViewModel() { return loginViewModel; }
    public SignUpViewModel getSignUpViewModel() { return signUpViewModel; }

    // Auxiliary Getters
    public RecipeAPI getRecipeAPI() { return recipeAPI; }
    public NutritionAPI getNutritionAPI() { return nutritionAPI; }
    public UserDataAccessInterface getDataAccessInterface() { return userDAO; }
    public GUI getGUI() { return gui; }

    // UseCase Getters
    public SearchRecipeController getSearchRecipeController() { return searchRecipeController; }
    public LoginController getLoginController() { return loginController; }
    public SignUpController getSignUpController() { return signUpController; }
    public LogoutController getLogoutController() { return logoutController; }
    public AddToMyRecipeController getAddToMyRecipeController() { return addToMyRecipeController; }
    public FilterRecipeController getFilterRecipeController() { return filterRecipeController; }
    public SetPreferenceController getSetPreferenceController() { return setPreferenceController; }
    public DisplayRecipeDetailController getDisplayRecipeDetailController() { return displayRecipeDetailController; }
    public RecentlyViewedRecipesController getRecentlyViewedRecipesController() { return recentlyViewedRecipesController; }

    public AuthenticationViewModel getAuthenticationViewModel() {return authenticationViewModel;}
}
