package use_cases.fridge_inventory;

import app.local.LoggedUserData;
import entity.Fridge;
import entity.Ingredient;

public class FridgeInventoryController {
    private final FridgeInventoryInputBoundary interactor;
    private final Fridge userFridge;

    public FridgeInventoryController(FridgeInventoryInputBoundary interactor) {
        this.interactor = interactor;
        this.userFridge = LoggedUserData.getLoggedInUser().getFridge(); // Retrieve the user's fridge
    }

    public void addIngredient(String ingredientName, float quantity, String unit, String category) {
        FridgeInventoryInputData inputData = new FridgeInventoryInputData(ingredientName, quantity, unit, category);
        interactor.addIngredient(inputData);

        // Update the User's fridge directly
        Ingredient newIngredient = new Ingredient(java.util.UUID.randomUUID().toString(), ingredientName, unit, category, quantity);
        userFridge.addIngredient(newIngredient);

        // Optionally print out fridge contents for debugging
        System.out.println("Added to fridge: " + newIngredient);
        System.out.println("Current fridge contents: " + userFridge.getIngredients());
    }

    public void removeIngredient(String ingredientID) {
        interactor.removeIngredient(ingredientID);
        userFridge.removeIngredient(ingredientID);

        // Optionally print out fridge contents for debugging
        System.out.println("Removed ingredient with ID: " + ingredientID);
        System.out.println("Current fridge contents: " + userFridge.getIngredients());
    }

    public void updateIngredientQuantity(String ingredientID, float newQuantity) {
        interactor.updateIngredientQuantity(ingredientID, newQuantity);
        userFridge.updateIngredientQuantity(ingredientID, newQuantity);

        // Optionally print out fridge contents for debugging
        System.out.println("Updated ingredient with ID: " + ingredientID + " to new quantity: " + newQuantity);
        System.out.println("Current fridge contents: " + userFridge.getIngredients());
    }
}
