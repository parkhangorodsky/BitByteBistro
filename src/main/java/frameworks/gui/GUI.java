package frameworks.gui;

import app.Config;
import use_cases.search_recipe.interface_adapter.controller.SearchRecipeController;
import use_cases.search_recipe.gui.view.SearchRecipeView;
import use_cases._common.gui_common.abstractions.View;

public interface GUI {
    public void initialize(Config config);
    public void addView(View view);
    public void setActiveView(View view);
    public SearchRecipeView createUseCaseIntegratedSearchRecipeView(SearchRecipeController searchRecipeController);
}
