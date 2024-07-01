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
import interface_adapter.controller.SearchRecipeController;
import interface_adapter.view_model.SearchRecipeViewModel;
import interface_adapter.view_model.ViewManagerModel;

public class Config {

    // View Models
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final SearchRecipeViewModel searchRecipeViewModel = new SearchRecipeViewModel("search recipe");


    // Auxiliary
    private final RecipeAPI recipeAPI = new EdamamRecipeApi();
    private final GUI gui = new SwingGUI(this);


    // UseCases
    private final SearchRecipeController searchRecipeController = SearchRecipeUseCaseFactory.create(viewManagerModel, searchRecipeViewModel, recipeAPI);



    // ViewModel Getters
    public ViewManagerModel getViewManagerModel() {return viewManagerModel;}
    public SearchRecipeViewModel getSearchRecipeViewModel() {return searchRecipeViewModel;}


    // Auxiliary Getters
    public RecipeAPI getRecipeAPI() {return recipeAPI;}
    public GUI getGUI() {return gui;}

    // UseCase Getters
    public SearchRecipeController getSearchRecipeController() {return searchRecipeController;}
}
