package frameworks.gui.view_factory.division;

import app.config.Config;
import use_cases._common.gui_common.abstractions.View;

public interface ViewFactoryDivision {
    public abstract View generate(Config config);
}
