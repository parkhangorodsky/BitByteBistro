package use_cases.fridge_inventory;

public class FridgeInventoryController {
    private final FridgeInventoryInputBoundary interactor;

    public FridgeInventoryController(FridgeInventoryInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void addIngredient(String ingredientName, float quantity, String unit, String category) {
        FridgeInventoryInputData inputData = new FridgeInventoryInputData(ingredientName, quantity, unit, category);
        interactor.addIngredient(inputData);
    }

    public void removeIngredient(String ingredientID) {
        interactor.removeIngredient(ingredientID);
    }

    public void updateIngredientQuantity(String ingredientID, float newQuantity) {
        interactor.updateIngredientQuantity(ingredientID, newQuantity);
    }
}
