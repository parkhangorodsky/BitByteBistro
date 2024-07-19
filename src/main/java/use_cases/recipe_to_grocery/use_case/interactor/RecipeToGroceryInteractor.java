package use_cases.recipe_to_grocery.use_case.interactor;

import frameworks.api.RecipeAPI;
import entity.Recipe;
import org.json.JSONArray;
import org.json.JSONObject;
import use_cases.recipe_to_grocery.use_case.input_data.RecipeToGroceryInputData;
import use_cases.recipe_to_grocery.interface_adapter.presenter.RecipeToGroceryOutputBoundary;
import use_cases.recipe_to_grocery.use_case.output_data.RecipeToGroceryOutputData;
import use_cases._common.xtra.json_processor.RecipeJSONHandler;

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

        List<Recipe> recipes = new ArrayList<>();

        RecipeToGroceryOutputData recipeToGroceryOutputData = new RecipeToGroceryOutputData(recipes);
        recipeToGroceryPresenter.prepareSuccessView(recipeToGroceryOutputData);
    }


}




