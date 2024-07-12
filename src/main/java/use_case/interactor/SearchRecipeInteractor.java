package use_case.interactor;

import api.RecipeAPI;
import entity.Recipe;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.input_data.SearchRecipeInputBoundary;
import use_case.input_data.SearchRecipeInputData;
import use_case.output_data.SearchRecipeOutputBoundary;
import use_case.output_data.SearchRecipeOutputData;

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

        String queryString = searchRecipeInputData.getQueryString();
        String url = recipeAPI.createURLByRecipeName(queryString);
        JSONArray recipesJSONArray = recipeAPI.getRecipe(url);


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
