package use_cases.fridge_inventory;

public interface FridgeInventoryInputBoundary {
    void addIngredient(FridgeInventoryInputData inputData);
    void removeIngredient(String ingredientID);
    void updateIngredientQuantity(String ingredientID, String unit, float delta);
}
