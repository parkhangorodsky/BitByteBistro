package frameworks.gui;

import app.Config;
import use_cases.search_recipe.interface_adapter.controller.SearchRecipeController;
import use_cases.search_recipe.gui.view.SearchRecipeView;
import use_cases._common.gui_common.abstractions.View;

import java.beans.PropertyChangeListener;

public interface GUI extends PropertyChangeListener {
    public void initialize(Config config);
    public void addView(View view);
    public void setActiveView(View view);

}
