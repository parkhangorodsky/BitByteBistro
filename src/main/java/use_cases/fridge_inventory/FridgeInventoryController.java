package use_cases.fridge_inventory;

import app.local.LoggedUserData;
import entity.Fridge;
import entity.Ingredient;
import java.util.List;

public class FridgeInventoryController {
    private final FridgeInventoryInputBoundary interactor;
    private final Fridge userFridge;

    /**
     * Constructs a FridgeInventoryController with the specified interactor.
     * Initializes the fridge for the currently logged-in user.
     *
     * @param interactor The interactor to handle the fridge inventory operations.
     */
    public FridgeInventoryController(FridgeInventoryInputBoundary interactor) {
        this.interactor = interactor;
        this.userFridge = LoggedUserData.getLoggedInUser().getFridge(); // Ensure consistent fridge access
    }

    /**
     * Adds an ingredient to the fridge.
     *
     * @param ingredientName The name of the ingredient.
     * @param quantity The quantity of the ingredient.
     * @param unit The unit of measurement for the ingredient.
     * @param category The category of the ingredient.
     */
    public void addIngredient(String ingredientName, float quantity, String unit, String category) {
        FridgeInventoryInputData inputData = new FridgeInventoryInputData(ingredientName, quantity, unit, category);
        interactor.addIngredient(inputData);
    }

    /**
     * Removes a specified quantity of an ingredient from the fridge.
     *
     * @param ingredientName The name of the ingredient to remove.
     * @param quantity The quantity to remove.
     * @param unit The unit of measurement for the ingredient.
     */
    public void removeIngredient(String ingredientName, float quantity, String unit) {
        // Update the quantity in the fridge
        boolean success = userFridge.updateIngredientQuantityByNameAndUnit(ingredientName, unit, -quantity);

        if (success) {
            // Call the interactor to trigger the presenter to update the view
            interactor.updateIngredientQuantity(ingredientName, unit, 0); // pass 0 as the delta to just trigger the update
        }
    }

    /**
     * Refreshes the fridge contents by fetching the latest data
     * and updating the view.
     */
    public void refreshFridgeContents() {
        // Fetch the latest fridge contents from the interactor
        List<Ingredient> latestContents = interactor.fetchFridgeContents();

        // Pass the contents to the presenter to update the view
        interactor.getPresenter().updateView(latestContents);
    }
}