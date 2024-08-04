package frameworks.gui;

import app.Config;
import frameworks.gui.view_factory.ViewFactory;
import use_cases.search_recipe.interface_adapter.controller.SearchRecipeController;
import use_cases.search_recipe.gui.view.SearchRecipeView;
import use_cases._common.gui_common.abstractions.View;

import java.beans.PropertyChangeListener;

public abstract class GUI implements PropertyChangeListener {
    ViewFactory viewFactory;
    public abstract void initialize(Config config);
}
