package app;

// API
import frameworks.api.RecipeAPI;
import frameworks.api.EdamamRecipeApi;

// GUI
import frameworks.gui.GUI;
import frameworks.gui.SwingGUI;

// Interface Adapters
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
import use_cases.sign_up.interface_adapter.controller.SignUpController;
import use_cases.sign_up.interface_adapter.presenter.SignUpPresenter;
import use_cases.sign_up.interface_adapter.view_model.SignUpViewModel;
import use_cases.sign_up.use_case.interactor.SignUpInteractor;

import frameworks.data_access.DataAccessInterface;
import frameworks.data_access.CSVDataAccessObject;

public class Config {

    // View Models
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final SearchRecipeViewModel searchRecipeViewModel = new SearchRecipeViewModel("search recipe");
    private final AdvancedSearchRecipeViewModel advancedSearchRecipeViewModel = new AdvancedSearchRecipeViewModel();
    private final LoginViewModel loginViewModel = new LoginViewModel("LoginView");
    private final SignUpViewModel signUpViewModel = new SignUpViewModel("SignUpView");

    // Auxiliary
    private final RecipeAPI recipeAPI = new EdamamRecipeApi();
    private final DataAccessInterface dataAccessInterface = new CSVDataAccessObject("path/to/users.csv"); // Update path accordingly
    private final GUI gui = new SwingGUI(this);

    // UseCases
    // Search Recipe
    private final SearchRecipePresenter searchRecipePresenter = new SearchRecipePresenter(viewManagerModel, searchRecipeViewModel);
    private final SearchRecipeInteractor searchRecipeInteractor = new SearchRecipeInteractor(searchRecipePresenter, recipeAPI);
    private final SearchRecipeController searchRecipeController = new SearchRecipeController(searchRecipeInteractor);

    // Login UseCase
    private final LoginPresenter loginPresenter = new LoginPresenter(loginViewModel, viewManagerModel);
    private final LoginInteractor loginInteractor = new LoginInteractor(loginPresenter, dataAccessInterface);
    private final LoginController loginController = new LoginController(loginInteractor);

    // Sign Up UseCase
    private final SignUpPresenter signUpPresenter = new SignUpPresenter(signUpViewModel, viewManagerModel);
    private final SignUpInteractor signUpInteractor = new SignUpInteractor(signUpPresenter, dataAccessInterface);
    private final SignUpController signUpController = new SignUpController(signUpInteractor);

    // ViewModel Getters
    public ViewManagerModel getViewManagerModel() { return viewManagerModel; }
    public SearchRecipeViewModel getSearchRecipeViewModel() { return searchRecipeViewModel; }
    public AdvancedSearchRecipeViewModel getAdvancedSearchRecipeViewModel() { return advancedSearchRecipeViewModel; }
    public LoginViewModel getLoginViewModel() { return loginViewModel; }
    public SignUpViewModel getSignUpViewModel() { return signUpViewModel; }

    // Auxiliary Getters
    public RecipeAPI getRecipeAPI() { return recipeAPI; }
    public DataAccessInterface getDataAccessInterface() { return dataAccessInterface; }
    public GUI getGUI() { return gui; }

    // UseCase Getters
    public SearchRecipeController getSearchRecipeController() { return searchRecipeController; }
    public LoginController getLoginController() { return loginController; }
    public SignUpController getSignUpController() { return signUpController; }
}
