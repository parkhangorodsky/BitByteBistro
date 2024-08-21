package use_cases.fridge_inventory;

import entity.Ingredient;

import java.util.List;

/**
 * Presenter for the fridge inventory use case.
 * This class updates the view model with the current state of the fridge ingredients.
 */
public class FridgeInventoryPresenter implements FridgeInventoryOutputBoundary {
    private final FridgeInventoryViewModel viewModel;

    /**
     * Constructs a FridgeInventoryPresenter with the specified view model.
     *
     * @param viewModel The view model to be updated with fridge data.
     */
    public FridgeInventoryPresenter(FridgeInventoryViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Updates the view model with the given list of ingredients.
     *
     * @param ingredients The list of ingredients to display in the fridge view.
     */
    @Override
    public void updateView(List<Ingredient> ingredients) {
        viewModel.setIngredients(ingredients);
        viewModel.firePropertyChange("ingredients", null, ingredients);
    }
}
