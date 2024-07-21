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

        // Get recipe search result based on input data in the form of JSONArray.
        JSONArray recipesJSONArray = recipeAPI.getRecipe(searchRecipeInputData);

        // Create a new recipe container.
        List<Recipe> recipeContainer = new ArrayList<>();

        // For each of the recipe JSON object in the recipesJSONArray,
        // convert it into a Recipe object, and add it to the recipe container
        for (int i = 0; i < recipesJSONArray.length(); i++) {
            JSONObject recipeJSON = recipesJSONArray.getJSONObject(i).getJSONObject("recipe");
            Recipe recipe =  this.convertJSONtoRecipe(recipeJSON);
            recipeContainer.add(recipe);
        }

        // Create a new SearchRecipeOutputData.
        SearchRecipeOutputData searchRecipeOutputData = new SearchRecipeOutputData(recipeContainer);

        // Prepare the success view.
        searchRecipePresenter.prepareSuccessView(searchRecipeOutputData);
    }
}
