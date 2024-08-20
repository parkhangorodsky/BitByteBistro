package use_cases.fridge_inventory;

import entity.Fridge;
import entity.Ingredient;
import app.local.LoggedUserData;
import frameworks.data_access.UserDataAccessInterface;
import entity.User;

import java.util.List;

public class FridgeInventoryInteractor implements FridgeInventoryInputBoundary {
    private final FridgeInventoryOutputBoundary presenter;
    private final Fridge fridge;
    private UserDataAccessInterface userDAO;

    public FridgeInventoryInteractor(FridgeInventoryOutputBoundary presenter, Fridge fridge, UserDataAccessInterface userDAO) {
        this.presenter = presenter;
        this.fridge = fridge;
        this.userDAO = userDAO;
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
        userDAO.updateFridge(LoggedUserData.getLoggedInUser(), fridge);

        // Update the view with the aggregated fridge contents
        presenter.updateView(fridge.getAggregatedFridgeContents());
    }


    @Override
    public void removeIngredient(String ingredientID) {
        fridge.removeIngredient(ingredientID);
        userDAO.updateFridge(LoggedUserData.getLoggedInUser(), LoggedUserData.getLoggedInUser().getFridge());
        presenter.updateView(fridge.getAggregatedFridgeContents()); // Ensure the view is updated
    }

    @Override
    public void updateIngredientQuantity(String ingredientName, String unit, float delta) {
        boolean updated = fridge.updateIngredientQuantityByNameAndUnit(ingredientName, unit, delta);
        List<Ingredient> currentContents = fridge.getAggregatedFridgeContents();
        if (updated) {
            userDAO.updateFridge(LoggedUserData.getLoggedInUser(), fridge);
            presenter.updateView(currentContents);
        } else {
            presenter.updateView(currentContents);
        }


    }

    public List<Ingredient> fetchFridgeContents() {
        // Fetch the latest user data from the DAO
        User currentUser = userDAO.getUserByEmail(LoggedUserData.getLoggedInUser().getUserEmail());
        return currentUser.getFridge().getIngredients();  // Return the fridge contents
    }

    public FridgeInventoryPresenter getPresenter() {
        return (FridgeInventoryPresenter) presenter;
    }


}
