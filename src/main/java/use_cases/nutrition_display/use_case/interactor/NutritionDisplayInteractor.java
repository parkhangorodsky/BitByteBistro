package use_cases.nutrition_display.use_case.interactor;

import entity.Recipe;
import frameworks.api.RecipeAPI;
import org.json.JSONArray;
import org.json.JSONObject;
import use_cases.nutrition_display.interface_adapter.presenter.NutritionDisplayOutputBoundary;
import use_cases.search_recipe.interface_adapter.presenter.SearchRecipeOutputBoundary;
import use_cases.search_recipe.use_case.output_data.SearchRecipeOutputData;

import java.util.ArrayList;
import java.util.List;

public class NutritionDisplayInteractor implements NutritionDisplayInputBoundary{
    private NutritionAPI nutritionAPI;
    private NutritionDisplayOutputBoundary nutritionDisplayPresenter;

    public NutritionDisplayInteractor(NutritionDisplayOutputBoundary nutritionDisplayPresenter, NutritionAPI nutritionAPI) {
        this.nutritionAPI = nutritionAPI;
        this.nutritionDisplayPresenter = nutritionDisplayPresenter;
    }
    @Override
    public void execute(Recipe recipe) {

        JSONArray nutritionJSONArray

        JSONArray recipesJSONArray = recipeAPI.getRecipe(recipe);

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
