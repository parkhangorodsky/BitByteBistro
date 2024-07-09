package app;


// API
import api.RecipeAPI;
import api.EdamamRecipeApi;

// GUI
import app.gui.GUI;
import app.gui.SwingGUI;

// UseCaseFactory
import app.use_case_factory.SearchRecipeUseCaseFactory;

// Interface Adapters
import interface_adapter.controller.AdvancedSearchRecipeController;
import interface_adapter.controller.SearchRecipeController;
import interface_adapter.presenter.SearchRecipePresenter;
import interface_adapter.view_model.AdvancedSearchRecipeViewModel;
import interface_adapter.view_model.SearchRecipeViewModel;
import interface_adapter.view_model.ViewManagerModel;
import use_case.interactor.AdvancedSearchRecipeInteractor;
import use_case.interactor.SearchRecipeInteractor;

public class Config {

    // View Models
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final SearchRecipeViewModel searchRecipeViewModel = new SearchRecipeViewModel("search recipe");
    private final AdvancedSearchRecipeViewModel advancedSearchRecipeViewModel = new AdvancedSearchRecipeViewModel();


    // Auxiliary
    private final RecipeAPI recipeAPI = new EdamamRecipeApi();
    private final GUI gui = new SwingGUI(this);


    // UseCases
    // Search Recipe
    private final SearchRecipePresenter searchRecipePresenter = new SearchRecipePresenter(viewManagerModel, searchRecipeViewModel);
    private final SearchRecipeInteractor searchRecipeInteractor = new SearchRecipeInteractor(searchRecipePresenter, recipeAPI);
    private final SearchRecipeController searchRecipeController = new SearchRecipeController(searchRecipeInteractor);
    // Advanced Search
    private final AdvancedSearchRecipeInteractor advancedSearchRecipeInteractor = new AdvancedSearchRecipeInteractor(searchRecipePresenter, recipeAPI);
    private final AdvancedSearchRecipeController advancedSearchRecipeController = new AdvancedSearchRecipeController(advancedSearchRecipeInteractor);


    // ViewModel Getters
    public ViewManagerModel getViewManagerModel() {return viewManagerModel;}
    public SearchRecipeViewModel getSearchRecipeViewModel() {return searchRecipeViewModel;}
    public AdvancedSearchRecipeViewModel getAdvancedSearchRecipeViewModel() {return advancedSearchRecipeViewModel;}
    public AdvancedSearchRecipeController getAdvancedSearchRecipeController() {return advancedSearchRecipeController;}

    // Auxiliary Getters
    public RecipeAPI getRecipeAPI() {return recipeAPI;}
    public GUI getGUI() {return gui;}

    // UseCase Getters
    public SearchRecipeController getSearchRecipeController() {return searchRecipeController;}
}
