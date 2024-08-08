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
        boolean success = userFridge.updateIngredientQuantityByNameAndUnit(ingredientName, unit, -quantity);
        if (success) {
            interactor.updateIngredientQuantity(ingredientName, unit, -quantity);
        }

        // Ensure the view is updated after removing the ingredient
        System.out.println("Removed from fridge: " + ingredientName + ", quantity: " + quantity + " " + unit);
        System.out.println("Current fridge contents: " + userFridge.getIngredients());
    }


}
