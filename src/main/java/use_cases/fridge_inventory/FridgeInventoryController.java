package use_cases.fridge_inventory;

import app.local.LoggedUserData;
import entity.Fridge;
import entity.Ingredient;

public class FridgeInventoryController {
    private final FridgeInventoryInputBoundary interactor;
    private final Fridge userFridge;

    public FridgeInventoryController(FridgeInventoryInputBoundary interactor) {
        this.interactor = interactor;
        this.userFridge = LoggedUserData.getLoggedInUser().getFridge(); // Ensure consistent fridge access
    }

    public void addIngredient(String ingredientName, float quantity, String unit, String category) {
        FridgeInventoryInputData inputData = new FridgeInventoryInputData(ingredientName, quantity, unit, category);
        interactor.addIngredient(inputData);

        // Debugging: Check the current state of the fridge
        System.out.println("Added to fridge: " + ingredientName + ", quantity: " + quantity + " " + unit);
        System.out.println("Current fridge contents: " + userFridge.getIngredients());
    }

    public void removeIngredient(String ingredientName, float quantity, String unit) {
        System.out.println("Controller: Request to remove " + quantity + " " + unit + " of " + ingredientName);

        // Update the quantity in the fridge
        boolean success = userFridge.updateIngredientQuantityByNameAndUnit(ingredientName, unit, -quantity);

        if (success) {
            System.out.println("Controller: Successfully removed " + quantity + " " + unit + " of " + ingredientName);
            // Call the interactor to trigger the presenter to update the view
            interactor.updateIngredientQuantity(ingredientName, unit, 0); // pass 0 as the delta to just trigger the update
        } else {
            System.out.println("Controller: Failed to remove " + quantity + " " + unit + " of " + ingredientName);
        }
        System.out.println("Current fridge contents: " + userFridge.getIngredients());
    }
}
