package use_cases.fridge_inventory;

import entity.Ingredient;

import java.util.List;

public class FridgeInventoryPresenter implements FridgeInventoryOutputBoundary {
    private final FridgeInventoryViewModel viewModel;

    public FridgeInventoryPresenter(FridgeInventoryViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void updateView(List<Ingredient> ingredients) {
        viewModel.setIngredients(ingredients);
        viewModel.firePropertyChange("update", null, null);
    }
}
