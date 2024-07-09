package use_case.interactor;

import api.RecipeAPI;
import entity.Recipe;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.input_data.AdvancedRecipeSearchInputBoundary;
import use_case.input_data.AdvancedRecipeSearchInputData;
import use_case.output_data.SearchRecipeOutputBoundary;
import use_case.output_data.SearchRecipeOutputData;

import java.util.ArrayList;
import java.util.List;

public class AdvancedSearchRecipeInteractor implements AdvancedRecipeSearchInputBoundary, RecipeJSONHandler {

    private RecipeAPI recipeAPI;
    private SearchRecipeOutputBoundary searchRecipePresenter;

    public AdvancedSearchRecipeInteractor(SearchRecipeOutputBoundary searchRecipePresenter, RecipeAPI recipeAPI) {
        this.recipeAPI = recipeAPI;
        this.searchRecipePresenter = searchRecipePresenter;
    }

    public void execute(AdvancedRecipeSearchInputData advancedRecipeSearchInputData) {

        String URL = recipeAPI.createURLByAdvancedSearch(advancedRecipeSearchInputData);
        System.out.println(URL);
        JSONArray recipesJSONArray = recipeAPI.getRecipe(URL);

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
