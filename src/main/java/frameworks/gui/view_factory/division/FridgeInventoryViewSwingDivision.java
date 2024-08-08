package frameworks.gui.view_factory.division;

import app.config.Config;
import use_cases.fridge_inventory.gui.view.FridgeInventoryView;

public class FridgeInventoryViewSwingDivision implements ViewFactoryDivision {
    @Override
    public FridgeInventoryView generate(Config config) {
        return new FridgeInventoryView(
                config.getFridgeInventoryViewModel(),
                config.getFridgeInventoryController() // Pass the controller here
        );
    }
}
