package use_case.interactor;

import api.RecipeAPI;
import entity.Recipe;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.input_data.RecipeToGroceryInputBoundary;
import use_case.input_data.RecipeToGroceryInputData;
import use_case.output_data.RecipeToGroceryOutputBoundary;
import use_case.output_data.RecipeToGroceryOutputData;

import java.util.ArrayList;
import java.util.List;

public class RecipeToGroceryInteractor implements RecipeToGroceryInputBoundary, RecipeJSONHandler {
    private RecipeAPI recipeAPI;
    private RecipeToGroceryOutputBoundary recipeToGroceryPresenter;

    public RecipeToGroceryInteractor(RecipeToGroceryOutputBoundary recipeToGroceryPresenter, RecipeAPI recipeAPI) {
        this.recipeAPI = recipeAPI;
        this.recipeToGroceryPresenter = recipeToGroceryPresenter;
    }
    @Override
    public void execute(RecipeToGroceryInputData recipeToGroceryInputData) {

        String queryString = recipeToGroceryInputData.getQueryString();
        String url = recipeAPI.createURLByRecipeName(queryString);
        JSONArray recipesJSONArray = recipeAPI.getRecipe(url);


        List<Recipe> recipes = new ArrayList<>();
        for (int i = 0; i < recipesJSONArray.length(); i++) {
            JSONObject recipeJSON = recipesJSONArray.getJSONObject(i).getJSONObject("recipe");
            Recipe recipe =  this.convertJSONtoRecipe(recipeJSON);
            recipes.add(recipe);
        }

        RecipeToGroceryOutputData recipeToGroceryOutputData = new RecipeToGroceryOutputData(recipes);
        recipeToGroceryPresenter.prepareSuccessView(recipeToGroceryOutputData);
    }


}




