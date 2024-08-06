package frameworks.gui.view_factory.division;

import app.config.Config;
import use_cases.core_functionality.MyGroceryView;

public class MyGroceryViewSwingDivision implements ViewFactoryDivision<MyGroceryView> {
    @Override
    public MyGroceryView generate(Config config) {
        return new MyGroceryView(
                config.MyGroceryViewModel(),
                config.getAddNewGroceryListController());
    }
}
