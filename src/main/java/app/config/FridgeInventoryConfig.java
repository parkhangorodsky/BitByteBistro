package app.config;

import use_cases.fridge_inventory.FridgeInventoryController;
import use_cases.fridge_inventory.FridgeInventoryInteractor;
import use_cases.fridge_inventory.FridgeInventoryPresenter;
import use_cases.fridge_inventory.FridgeInventoryViewModel;
import entity.Fridge;

import static app.config.ViewModelConfig.fridgeInventoryViewModel;

class FridgeInventoryConfig {

    static final FridgeInventoryPresenter presenter = new FridgeInventoryPresenter(
            fridgeInventoryViewModel);

    static final Fridge fridge = new Fridge(); // Ideally, tie this to the logged-in user's data

    static final FridgeInventoryInteractor interactor = new FridgeInventoryInteractor(
            presenter,
            fridge);

    static final FridgeInventoryController controller = new FridgeInventoryController(
            interactor);

}

