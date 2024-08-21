package use_cases.fridge_inventory;

import entity.Fridge;
import entity.Ingredient;
import app.local.LoggedUserData;
import frameworks.data_access.UserDataAccessInterface;
import entity.User;

import java.util.List;

/**
 * Interactor for managing fridge inventory operations.
 * This class handles adding, removing, and updating ingredients in the fridge,
 * and interacts with the data access layer to persist changes.
 */
public class FridgeInventoryInteractor implements FridgeInventoryInputBoundary {
    private final FridgeInventoryOutputBoundary presenter;
    private final Fridge fridge;
    private UserDataAccessInterface userDAO;

    /**
     * Constructs a FridgeInventoryInteractor with the specified presenter, fridge, and userDAO.
     *
     * @param presenter The output boundary to update the view.
     * @param fridge The fridge entity associated with the user.
     * @param userDAO The data access object for user data operations.
     */
    public FridgeInventoryInteractor(FridgeInventoryOutputBoundary presenter, Fridge fridge, UserDataAccessInterface userDAO) {
        this.presenter = presenter;
        this.fridge = fridge;
        this.userDAO = userDAO;
    }

    /**
     * Adds an ingredient to the fridge and updates the view.
     *
     * @param inputData The data for the ingredient to be added.
     */
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

    /**
     * Removes an ingredient from the fridge by its ID and updates the view.
     *
     * @param ingredientID The ID of the ingredient to remove.
     */
    @Override
    public void removeIngredient(String ingredientID) {
        fridge.removeIngredient(ingredientID);
        userDAO.updateFridge(LoggedUserData.getLoggedInUser(), LoggedUserData.getLoggedInUser().getFridge());
        presenter.updateView(fridge.getAggregatedFridgeContents()); // Ensure the view is updated
    }

    /**
     * Updates the quantity of an ingredient in the fridge and updates the view.
     *
     * @param ingredientName The name of the ingredient.
     * @param unit The unit of measurement for the ingredient.
     * @param delta The amount by which to adjust the ingredient's quantity.
     */
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

    /**
     * Fetches the latest contents of the fridge from the data access object.
     *
     * @return A list of ingredients currently in the fridge.
     */
    public List<Ingredient> fetchFridgeContents() {
        // Fetch the latest user data from the DAO
        User currentUser = userDAO.getUserByEmail(LoggedUserData.getLoggedInUser().getUserEmail());
        return currentUser.getFridge().getIngredients();  // Return the fridge contents
    }

    /**
     * Returns the presenter associated with this interactor.
     *
     * @return The FridgeInventoryPresenter used to update the view.
     */
    public FridgeInventoryPresenter getPresenter() {
        return (FridgeInventoryPresenter) presenter;
    }
}
