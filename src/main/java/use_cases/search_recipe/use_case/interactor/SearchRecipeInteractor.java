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

public class SearchRecipeInteractor implements SearchRecipeInputBoundary, RecipeJSONHandler {
    private RecipeAPI recipeAPI;
    private SearchRecipeOutputBoundary searchRecipePresenter;

    public SearchRecipeInteractor(SearchRecipeOutputBoundary searchRecipePresenter, RecipeAPI recipeAPI) {
        this.recipeAPI = recipeAPI;
        this.searchRecipePresenter = searchRecipePresenter;
    }
    @Override
    public void execute(SearchRecipeInputData searchRecipeInputData) {

        JSONArray recipesJSONArray = recipeAPI.getRecipe(searchRecipeInputData);

        List<Recipe> recipes = new ArrayList<>();
        for (int i = 0; i < recipesJSONArray.length(); i++) {
            JSONObject recipeJSON = recipesJSONArray.getJSONObject(i).getJSONObject("recipe");
            Recipe recipe =  this.convertJSONtoRecipe(recipeJSON);
            recipes.add(recipe);
        }

        SearchRecipeOutputData searchRecipeOutputData = new SearchRecipeOutputData(recipes);
        searchRecipePresenter.prepareSuccessView(searchRecipeOutputData);
    }
}
