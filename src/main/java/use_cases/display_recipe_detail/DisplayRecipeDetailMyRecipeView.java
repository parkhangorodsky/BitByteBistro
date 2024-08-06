package use_cases.display_recipe_detail;

import use_cases._common.gui_common.abstractions.NightModeObject;

import javax.swing.*;

public class DisplayRecipeDetailMyRecipeView extends DisplayRecipeDetailView implements NightModeObject {
    public DisplayRecipeDetailMyRecipeView(JFrame parent, DisplayRecipeDetailViewModel viewModel) {
        super(parent, viewModel);
    }
}
