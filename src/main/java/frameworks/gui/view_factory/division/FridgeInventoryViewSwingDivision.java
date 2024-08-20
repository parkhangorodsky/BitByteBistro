package frameworks.gui.view_factory.division;

import app.config.Config;
import use_cases.fridge_inventory.gui.view.FridgeInventoryView;
import use_cases.fridge_inventory.FridgeInventoryController;

public class FridgeInventoryViewSwingDivision implements ViewFactoryDivision {
    @Override
    public FridgeInventoryView generate(Config config) {
        FridgeInventoryController controller = config.getFridgeInventoryController();
        controller.refreshFridgeContents();  // Ensure the fridge contents are refreshed when accessing the view
        return new FridgeInventoryView(config.getFridgeInventoryViewModel(), controller);
    }
}


