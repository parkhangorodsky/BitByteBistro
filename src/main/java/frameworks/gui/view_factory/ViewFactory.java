package frameworks.gui.view_factory;

import app.config.Config;
import frameworks.gui.view_factory.division.ViewFactoryDivision;
import use_cases._common.gui_common.abstractions.View;

import java.util.HashMap;
import java.util.Map;

public abstract class ViewFactory{

    protected Config config;
    protected Map<String, ViewFactoryDivision> divisions = new HashMap<>();

    public ViewFactory(Config config) {
        this.config = config;
    }

    protected void addDivision(String name, ViewFactoryDivision division) {
        divisions.put(name, division);
    }

    public View generate(String viewName) {
        return divisions.get(viewName).generate(config);
    }

}
