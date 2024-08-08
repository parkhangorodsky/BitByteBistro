package app.config;

import use_cases.fridge_inventory.FridgeInventoryController;
import use_cases.fridge_inventory.FridgeInventoryInteractor;
import use_cases.fridge_inventory.FridgeInventoryPresenter;
import use_cases.fridge_inventory.FridgeInventoryViewModel;
import app.local.LoggedUserData;

import static app.config.ViewModelConfig.fridgeInventoryViewModel;

class FridgeInventoryConfig {

    static final FridgeInventoryPresenter presenter = new FridgeInventoryPresenter(
            fridgeInventoryViewModel);

    // Get the fridge from the logged-in user's data
    static final FridgeInventoryInteractor interactor = new FridgeInventoryInteractor(
            presenter,
            LoggedUserData.getLoggedInUser().getFridge());

    static final FridgeInventoryController controller = new FridgeInventoryController(
            interactor);

}
