package use_cases.fridge_inventory;

import app.local.LoggedUserData;
import entity.Fridge;
import entity.Ingredient;
import java.util.List;

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
    }

    public void removeIngredient(String ingredientName, float quantity, String unit) {
        // Update the quantity in the fridge
        boolean success = userFridge.updateIngredientQuantityByNameAndUnit(ingredientName, unit, -quantity);

        if (success) {
            // Call the interactor to trigger the presenter to update the view
            interactor.updateIngredientQuantity(ingredientName, unit, 0); // pass 0 as the delta to just trigger the update
        }
    }

    public void refreshFridgeContents() {
        // Fetch the latest fridge contents from the interactor
        List<Ingredient> latestContents = interactor.fetchFridgeContents();

        // Pass the contents to the presenter to update the view
        interactor.getPresenter().updateView(latestContents);
    }


}
