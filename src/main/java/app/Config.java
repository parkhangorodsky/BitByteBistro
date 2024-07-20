package app;


// API
import frameworks.api.NutritionAPI;
import frameworks.api.NutritionDisplayApi;
import frameworks.api.RecipeAPI;
import frameworks.api.EdamamRecipeApi;

// GUI
import frameworks.gui.GUI;
import frameworks.gui.SwingGUI;

// UseCaseFactory

// Interface Adapters
import use_cases.nutrition_display.interface_adapter.controller.NutritionDisplayController;
import use_cases.nutrition_display.interface_adapter.presenter.NutritionDisplayPresenter;
import use_cases.nutrition_display.use_case.input_data.NutritionDisplayInputData;
import use_cases.nutrition_display.use_case.interactor.NutritionDisplayInteractor;
import use_cases.search_recipe.interface_adapter.controller.SearchRecipeController;
import use_cases.search_recipe.interface_adapter.presenter.SearchRecipePresenter;
import use_cases.search_recipe.interface_adapter.view_model.AdvancedSearchRecipeViewModel;
import use_cases.search_recipe.interface_adapter.view_model.SearchRecipeViewModel;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.search_recipe.use_case.interactor.SearchRecipeInteractor;

public class Config {

    // View Models
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final SearchRecipeViewModel searchRecipeViewModel = new SearchRecipeViewModel("search recipe");
    private final AdvancedSearchRecipeViewModel advancedSearchRecipeViewModel = new AdvancedSearchRecipeViewModel();


    // Auxiliary
    private final RecipeAPI recipeAPI = new EdamamRecipeApi();
    private final NutritionAPI nutritionAPI = new NutritionDisplayApi();
    private final GUI gui = new SwingGUI(this);


    // UseCases
    // Search Recipe
    private final SearchRecipePresenter searchRecipePresenter = new SearchRecipePresenter(viewManagerModel, searchRecipeViewModel);
    private final SearchRecipeInteractor searchRecipeInteractor = new SearchRecipeInteractor(searchRecipePresenter, recipeAPI);
    private final SearchRecipeController searchRecipeController = new SearchRecipeController(searchRecipeInteractor);

    private final NutritionDisplayPresenter nutritionDisplayPresenter = new NutritionDisplayPresenter(viewManagerModel, searchRecipeViewModel);
    private final NutritionDisplayInteractor nutritionDisplayInteractor = new NutritionDisplayInteractor(nutritionDisplayPresenter, nutritionAPI);
    private final NutritionDisplayController nutritionDisplayController = new NutritionDisplayController(nutritionDisplayInteractor);

    // ViewModel Getters
    public ViewManagerModel getViewManagerModel() {return viewManagerModel;}
    public SearchRecipeViewModel getSearchRecipeViewModel() {return searchRecipeViewModel;}
    public AdvancedSearchRecipeViewModel getAdvancedSearchRecipeViewModel() {return advancedSearchRecipeViewModel;}

    // Auxiliary Getters
    public RecipeAPI getRecipeAPI() {return recipeAPI;}
    public GUI getGUI() {return gui;}

    // UseCase Getters
    public SearchRecipeController getSearchRecipeController() {return searchRecipeController;}
    public NutritionDisplayController getNutritionDisplayController() {return nutritionDisplayController;}
}
