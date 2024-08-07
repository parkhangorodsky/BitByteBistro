package use_cases.fridge_inventory;

import entity.Fridge;
import entity.Ingredient;

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
        fridge.addIngredient(ingredient);
        presenter.updateView(fridge.getAggregatedFridgeContents()); // Ensure the view is updated
    }

    @Override
    public void removeIngredient(String ingredientID) {
        fridge.removeIngredient(ingredientID);
        presenter.updateView(fridge.getAggregatedFridgeContents()); // Ensure the view is updated
    }

    @Override
    public void updateIngredientQuantity(String ingredientID, float newQuantity) {
        fridge.updateIngredientQuantity(ingredientID, newQuantity);
        presenter.updateView(fridge.getAggregatedFridgeContents()); // Ensure the view is updated
    }

}
