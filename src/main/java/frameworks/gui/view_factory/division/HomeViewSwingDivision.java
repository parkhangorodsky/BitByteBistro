package frameworks.gui.view_factory.division;

import app.config.Config;
import use_cases._common.gui_common.abstractions.View;
import use_cases._common.gui_common.view.HomeView;

public class HomeViewSwingDivision implements ViewFactoryDivision {
    @Override
    public View generate(Config config) {
        return new HomeView(
                config.getViewManagerModel());

    }
}
