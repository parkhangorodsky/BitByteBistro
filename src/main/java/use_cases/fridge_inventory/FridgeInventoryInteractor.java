package use_cases.fridge_inventory;

import entity.Fridge;
import entity.Ingredient;
import app.local.LoggedUserData;
import frameworks.data_access.UserDataAccessInterface;

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

        // Debugging: Check the updated fridge contents
        System.out.println("FridgeInventoryInteractor: Added ingredient to fridge: " + ingredient);
        System.out.println("FridgeInventoryInteractor: Current fridge contents: " + fridge.getIngredients());
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
            System.out.println("Interactor: Updated ingredient quantity. Current fridge contents: " + currentContents);
            userDAO.updateFridge(LoggedUserData.getLoggedInUser(), fridge);
            presenter.updateView(currentContents);
        } else {
            System.out.println("Interactor: No update performed. Ingredient not found or quantity unchanged.");
            presenter.updateView(currentContents);
        }


    }

}
