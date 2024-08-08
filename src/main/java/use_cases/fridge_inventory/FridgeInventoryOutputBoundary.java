package use_cases.fridge_inventory;

import entity.Ingredient;

import java.util.List;

public interface FridgeInventoryOutputBoundary {
    void updateView(List<Ingredient> ingredients);
}
