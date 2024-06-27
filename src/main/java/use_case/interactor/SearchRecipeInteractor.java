package use_case.interactor;

import api.RecipeAPI;
import entity.Recipe;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.input_data.InputBoundary;
import use_case.input_data.SearchRecipeInputData;
import use_case.output_data.OutputBoundary;

import java.util.ArrayList;
import java.util.List;

public class SearchRecipeInteractor implements InputBoundary {
    private RecipeAPI recipeAPI;
    private OutputBoundary searchRecipePresenter;

    public SearchRecipeInteractor(RecipeAPI recipeAPI) {
        this.recipeAPI = recipeAPI;
    }
    @Override
    public void execute(SearchRecipeInputData searchRecipeInputData) {
        String queryString = searchRecipeInputData.getQueryString();
        JSONArray recipesJSONArray = recipeAPI.getRecipe(queryString);

        List<Recipe> recipes = new ArrayList<>();
        for (int i = 0; i < recipesJSONArray.length(); i++) {
            JSONObject recipeJSON = recipesJSONArray.getJSONObject(i).getJSONObject("recipe");
            Recipe recipe = Recipe.convertJSONtoRecipe(recipeJSON);
            recipes.add(recipe);
        }
        for (Recipe recipe : recipes) {
            System.out.println(recipe.getName());
        }



    }
}
