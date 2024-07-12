package app.gui;

import app.Config;
import interface_adapter.controller.SearchRecipeController;
import view.SearchRecipeView;
import view.View;

public interface GUI {
    public void initialize(Config config);
    public void addView(View view);
    public void setActiveView(View view);
    public SearchRecipeView createUseCaseIntegratedSearchRecipeView(SearchRecipeController searchRecipeController);
}
