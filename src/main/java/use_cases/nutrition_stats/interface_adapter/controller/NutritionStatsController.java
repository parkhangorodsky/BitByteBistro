package use_cases.nutrition_stats.interface_adapter.controller;

import app.local.LoggedUserData;
import entity.ShoppingList;
import use_cases.nutrition_stats.use_case.input_data.NutritionStatsInputData;
import use_cases.nutrition_stats.use_case.interactor.NutritionStatsInputBoundary;

/**
 * Controller for handling nutrition stats requests.
 * It encapsulates the logic for handling nutrition stats input.
 */
public class NutritionStatsController {


    final NutritionStatsInputBoundary nutritionStatsInteractor;

    /**
     * Class constructor for NutritionStatsController.
     * @param nutritionStatsInteractor The use case interactor to which the controller will delegate actions.
     */
    public NutritionStatsController(NutritionStatsInputBoundary nutritionStatsInteractor) {
        this.nutritionStatsInteractor = nutritionStatsInteractor;
    }

    /**
     * Executes a retrieving nutritional information for a given recipe (which was retrieved from the RecipeAPI).
     * @param shoppingList the recipe for which the nutritional information will be retrieved for.
     */
    public void execute(ShoppingList shoppingList) {
        int numberOfRecipes = shoppingList.getRecipes().size();
        NutritionStatsInputData nutritionStatsInputData = new NutritionStatsInputData(
                shoppingList.getShoppingListName(), shoppingList.getListItems(), numberOfRecipes);
        nutritionStatsInteractor.execute(nutritionStatsInputData);
    }
}
