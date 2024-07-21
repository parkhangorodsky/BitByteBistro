package use_cases.search_recipe.gui.view;

import entity.Recipe;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases._common.gui_common.view_components.round_component.RoundTextField;
import use_cases.nutrition_display.interface_adapter.controller.NutritionDisplayController;
import use_cases.search_recipe.interface_adapter.controller.SearchRecipeController;
import use_cases.search_recipe.interface_adapter.view_model.SearchRecipeViewModel;
import use_cases.search_recipe.use_case.output_data.SearchRecipeOutputData;

import java.awt.event.ActionListener;
import java.util.List;

public class ActionListenerUtil {

    public static void addSearchActionListener(RoundButton button,
                                               SearchRecipeController searchRecipeController,
                                               SearchRecipeViewModel searchRecipeViewModel,
                                               NutritionDisplayController nutritionDisplayController) {

        button.addActionListener(e -> {
            if (e.getSource().equals(button)) {
                String queryString = button.getText();
                if (queryString != null && !queryString.isEmpty()) {
                    searchRecipeController.execute(queryString);
                    SearchRecipeOutputData recipes = searchRecipeViewModel.getRecipeSearchResult();
                    for (Recipe recipe : recipes) {
                        nutritionDisplayController.execute(recipe);
                    }
                }
            }
        });

    }
    public static void addAdvancedSearchActionListener(AdvancedSearchView advancedSearchView,
                                                       RoundButton button,
                                                       SearchRecipeController searchRecipeController,
                                                       String recipeName,
                                                       List<String> excludedIngredients,
                                                       List<String> dietSelection,
                                                       List<String> healthSelection,
                                                       List<String> cuisineTypeSelection,
                                                       List<String> dishTypeSelection,
                                                       List<String> mealTypeSelection) {
        button.addActionListener(e -> {
            if (e.getSource() == button) {
                searchRecipeController.execute(recipeName,
                        excludedIngredients,
                        dietSelection,
                        healthSelection,
                        cuisineTypeSelection,
                        dishTypeSelection,
                        mealTypeSelection);
                advancedSearchView.dispose();
            }

        });
    }
    public static void closeAdvancedSearchActionListener(AdvancedSearchView advancedSearchView,
                                                         RoundButton closeButton) {
        closeButton.addActionListener(e -> {
            if (e.getSource() == closeButton) {
                advancedSearchView.dispose();
            }
        });
    }
}
