package use_cases.nutrition_display.interface_adapter.controller;

import entity.Recipe;
import use_cases.nutrition_display.use_case.interactor.NutritionDisplayInputBoundary;
import use_cases.nutrition_display.use_case.input_data.NutritionDisplayInputData;

/**
 * Controller for handling nutrition display requests.
 * It receives input from the relevant view (search recipe, nutrition stats, etc.), constructs the appropriate input
 * data, and passes it to the interactor.
 * It encapsulates the logic for handling nutrition display input.
 */
public class NutritionDisplayController {
    final NutritionDisplayInputBoundary nutritionDisplayInteractor;

    /**
     * Class constructor for NutritionDisplayController.
     * @param nutritionDisplayInteractor The use case interactor to which the controller will delegate actions.
     */
    public NutritionDisplayController(NutritionDisplayInputBoundary nutritionDisplayInteractor) {
        this.nutritionDisplayInteractor = nutritionDisplayInteractor;
    }

    /**
     * Executes a retrieving nutritional information for a given recipe (which was retrieved from the RecipeAPI).
     * @param recipe the recipe for which the nutritional information will be retrieved for.
     */
    public void execute(Recipe recipe) {
        NutritionDisplayInputData nutritionDisplayInputData = new NutritionDisplayInputData(
            recipe.getName(), recipe.getIngredientList());
        nutritionDisplayInteractor.execute(nutritionDisplayInputData);
    }
}
