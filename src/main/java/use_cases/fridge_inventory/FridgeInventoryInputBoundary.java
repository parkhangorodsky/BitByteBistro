package use_cases.fridge_inventory;

import entity.Ingredient;
import java.util.List;

public interface FridgeInventoryInputBoundary {
    void addIngredient(FridgeInventoryInputData inputData);
    void removeIngredient(String ingredientID);
    void updateIngredientQuantity(String ingredientName, String unit, float delta);
    List<Ingredient> fetchFridgeContents();  // New method to fetch fridge contents
    FridgeInventoryPresenter getPresenter(); // New method to get the presenter
}
