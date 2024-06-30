package use_case.interactor;

import api.RecipeAPI;
import entity.Grocery;
import entity.Ingredient;
import entity.Nutrition;
import entity.Recipe;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.input_data.InputBoundary;
import use_case.input_data.SearchRecipeInputData;
import use_case.output_data.OutputBoundary;
import use_case.output_data.SearchRecipeOutputData;


import java.util.Collection;

import java.util.ArrayList;
import java.util.List;

public class SearchRecipeInteractor implements InputBoundary, RecipeJSONHandler {
    private RecipeAPI recipeAPI;
    private OutputBoundary searchRecipePresenter;

    public SearchRecipeInteractor(OutputBoundary searchRecipePresenter, RecipeAPI recipeAPI) {
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
