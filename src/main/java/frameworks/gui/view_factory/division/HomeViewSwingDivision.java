package frameworks.gui.view_factory.division;

import app.Config;
import use_cases._common.gui_common.view.HomeView;

public class HomeViewSwingDivision implements ViewFactoryDivision<HomeView> {
    @Override
    public HomeView generate(Config config) {
        return new HomeView(config.getViewManagerModel());
    }
}
