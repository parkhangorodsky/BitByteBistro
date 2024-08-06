package use_cases.display_recipe_detail;

import use_cases._common.gui_common.abstractions.NightModeObject;
import use_cases.add_new_grocery_list.AddNewGroceryListController;
import use_cases.core_functionality.CoreFunctionalityController;

import javax.swing.*;

public class DisplayRecipeDetailMyRecipeView extends DisplayRecipeDetailView implements NightModeObject {
    public DisplayRecipeDetailMyRecipeView(JFrame parent, DisplayRecipeDetailViewModel viewModel, CoreFunctionalityController coreFunctionalityController,  AddNewGroceryListController addNewGroceryListController) {
        super(parent, viewModel, coreFunctionalityController, addNewGroceryListController);
    }
}
