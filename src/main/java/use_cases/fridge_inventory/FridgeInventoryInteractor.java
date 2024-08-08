package use_cases.fridge_inventory;

import entity.Fridge;
import entity.Ingredient;
import app.local.LoggedUserData;

public class FridgeInventoryInteractor implements FridgeInventoryInputBoundary {
    private final FridgeInventoryOutputBoundary presenter;
    private final Fridge fridge;

    public FridgeInventoryInteractor(FridgeInventoryOutputBoundary presenter, Fridge fridge) {
        this.presenter = presenter;
        this.fridge = fridge;
    }

    @Override
    public void addIngredient(FridgeInventoryInputData inputData) {
        Ingredient ingredient = new Ingredient(
                java.util.UUID.randomUUID().toString(),
                inputData.getIngredientName(),
                inputData.getUnit(),
                inputData.getCategory(),
                inputData.getQuantity()
        );

        // Add ingredient to the fridge
        fridge.addIngredient(ingredient);

        // Update the view with the aggregated fridge contents
        presenter.updateView(fridge.getAggregatedFridgeContents());

        // Debugging: Check the updated fridge contents
        System.out.println("FridgeInventoryInteractor: Added ingredient to fridge: " + ingredient);
        System.out.println("FridgeInventoryInteractor: Current fridge contents: " + fridge.getIngredients());
    }


    @Override
    public void removeIngredient(String ingredientID) {
        fridge.removeIngredient(ingredientID);
        presenter.updateView(fridge.getAggregatedFridgeContents()); // Ensure the view is updated
    }

    @Override
    public void updateIngredientQuantity(String ingredientName, String unit, float delta) {
        boolean updated = fridge.updateIngredientQuantityByNameAndUnit(ingredientName, unit, delta);
        if (updated) {
            presenter.updateView(fridge.getAggregatedFridgeContents());
        }
    }


}
