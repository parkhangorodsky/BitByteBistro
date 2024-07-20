package use_cases.recipe_to_grocery.use_case.interactor;

import entity.Ingredient;
import frameworks.api.RecipeAPI;
import entity.Recipe;
import org.json.JSONArray;
import org.json.JSONObject;
import use_cases.recipe_to_grocery.use_case.input_data.RecipeToGroceryInputData;
import use_cases.recipe_to_grocery.interface_adapter.presenter.RecipeToGroceryOutputBoundary;
import use_cases.recipe_to_grocery.use_case.output_data.RecipeToGroceryOutputData;

import use_cases.search_recipe.use_case.output_data.SearchRecipeOutputData;

import use_cases._common.xtra.json_processor.RecipeJSONHandler;

import java.util.ArrayList;
import java.util.List;

public class RecipeToGroceryInteractor implements RecipeToGroceryInputBoundary, RecipeJSONHandler {
    private final RecipeRepository recipeRepository; // Example repository interface for managing recipes
    private RecipeToGroceryOutputBoundary recipeToGroceryPresenter;

    public RecipeToGroceryInteractor(RecipeToGroceryOutputBoundary recipeToGroceryPresenter, RecipeAPI recipeAPI) {
        this.recipeRepository = recipeRepository;
        this.recipeToGroceryPresenter = recipeToGroceryPresenter;
    }

    @Override
    public List<Recipe> fetchRecipes() {
        // Example fetch method for fetching recipes from the repository
        SearchRecipeOutputData searchRecipeOutputData = recipeRepository.fetchRecipes();
        return searchRecipeOutputData.getRecipes();
    }

    @Override
    public void execute(RecipeToGroceryInputData recipeToGroceryInputData) {

        List<Recipe> recipes = fetchRecipes();

        // Implement logic to convert recipes to grocery list
        List<Ingredient> shoppingList = getGroceryList(recipes);

        // Notify presenter or other components with outputData
        RecipeToGroceryOutputData recipeToGroceryOutputData = new RecipeToGroceryOutputData(shoppingList);
        recipeToGroceryPresenter.prepareSuccessView(recipeToGroceryOutputData);
    }

    private List<Ingredient> getGroceryList(List<Recipe> recipeList) {
        List<Ingredient> groceries = new ArrayList<>();
        for (Recipe recipe : recipeList) {
            for (Ingredient grocery : recipe.getIngredientList()) {
                if (groceries.contains(grocery)) {
                    Ingredient item = groceries.get(groceries.indexOf(grocery));
                    float new_quant = item.getIngredientQuantity() + grocery.getIngredientQuantity();
                    item.setIngredientQuantity(new_quant);
                } else {
                    groceries.add(grocery);
                }
            }
        }
        return groceries;
    }
}





