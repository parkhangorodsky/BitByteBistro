package frameworks.gui.view_factory.division;

import app.config.Config;
import use_cases._common.gui_common.abstractions.View;
import use_cases.core_functionality.MyGroceryView;

public class MyGroceryViewSwingDivision implements ViewFactoryDivision {
    @Override
    public View generate(Config config) {
        return new MyGroceryView(
                config.MyGroceryViewModel(),
                config.getAddNewGroceryListController());
    }
}
