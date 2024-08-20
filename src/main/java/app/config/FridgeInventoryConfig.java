package app.config;

import use_cases.fridge_inventory.FridgeInventoryController;
import use_cases.fridge_inventory.FridgeInventoryInteractor;
import use_cases.fridge_inventory.FridgeInventoryPresenter;
import entity.Fridge;
import app.local.LoggedUserData;

import static app.config.ViewModelConfig.fridgeInventoryViewModel;

class FridgeInventoryConfig {

    static FridgeInventoryPresenter presenter = new FridgeInventoryPresenter(
            fridgeInventoryViewModel);

    // Get the fridge from the logged-in user's data
    static FridgeInventoryInteractor interactor = new FridgeInventoryInteractor(
            presenter,
            LoggedUserData.getLoggedInUser().getFridge(),
            DataAccessConfig.userDAO);

    static FridgeInventoryController controller = new FridgeInventoryController(
            interactor);

    public static void resetFridgeInteractor(Fridge newFridge) {
        interactor = new FridgeInventoryInteractor(presenter, newFridge, DataAccessConfig.userDAO);
        controller = new FridgeInventoryController(interactor);
    }
}
