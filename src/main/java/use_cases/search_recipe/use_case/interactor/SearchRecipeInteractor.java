package use_cases.search_recipe.use_case.interactor;

import frameworks.api.RecipeAPI;
import entity.Recipe;
import org.json.JSONArray;
import org.json.JSONObject;
import use_cases.search_recipe.use_case.input_data.SearchRecipeInputData;
import use_cases.search_recipe.interface_adapter.presenter.SearchRecipeOutputBoundary;
import use_cases.search_recipe.use_case.output_data.SearchRecipeOutputData;
import use_cases._common.xtra.json_processor.RecipeJSONHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Interactor for searching recipes.
 * It handles the search request sent from the user through controller,
 * processes the results by calling API,
 * and communicates the results to the presenter.
 */
public class SearchRecipeInteractor implements SearchRecipeInputBoundary, RecipeJSONHandler {
    private RecipeAPI recipeAPI;
    private SearchRecipeOutputBoundary searchRecipePresenter;

    /**
     * Constructs a SearchRecipeInteractor with the specified presenter and recipe API.
     *
     * @param searchRecipePresenter the presenter to handle the output data.
     * @param recipeAPI the API to search for recipes
     */
    public SearchRecipeInteractor(SearchRecipeOutputBoundary searchRecipePresenter, RecipeAPI recipeAPI) {

        this.recipeAPI = recipeAPI;

        // Note that the SearchRecipeInteractor depends on an Output Boundary.
        this.searchRecipePresenter = searchRecipePresenter;
    }

    /**
     * Executes the search for recipes based on the input data generated from the controller and,
     * processes the JSON response from the API,
     * and calls method in the presenter to prepare the update on the view model.
     *
     * @param searchRecipeInputData the input data containing the search criteria
     */
    @Override
    public void execute(SearchRecipeInputData searchRecipeInputData) {

        // Get recipe search result based on input data in the form of List<Recipe>.
        List<Recipe> recipeList = recipeAPI.getRecipe(searchRecipeInputData);

        // Create a new SearchRecipeOutputData.
        SearchRecipeOutputData searchRecipeOutputData = new SearchRecipeOutputData(recipeList);

        // Prepare the success view.
        if (recipeList == null) {
            searchRecipePresenter.prepareFailView("api fail");
        } else {
            searchRecipePresenter.prepareSuccessView(searchRecipeOutputData);
        }

    }
}
