package use_cases.search_recipe.gui.view_component;

import app.LocalAppSetting;
import use_cases._common.gui_common.abstractions.StringCaseEditor;
import use_cases._common.gui_common.abstractions.ThemeColoredObject;
import use_cases.search_recipe.gui.view.AdvancedSearchView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.awt.Component.*;

public class AdvancedSearchInputSummarizer implements ThemeColoredObject, StringCaseEditor {

    private JPanel displayPanel;

    private String recipeName;
    private Map<String, Integer> ingredientsQuantity;
    private List<String> excludedIngredients;
    private List<String> dietSelection;
    private List<String> healthSelection;
    private List<String> cuisineTypeSelection;
    private List<String> dishTypeSelection;
    private List<String> mealTypeSelection;

    Font dfS = new Font(defaultFont, Font.PLAIN, 12);
    Font dfM = new Font(defaultFont, Font.PLAIN, 16);
    Font dfL = new Font(defaultFont, Font.PLAIN, 20);
    Font dfXL = new Font(defaultFont, Font.PLAIN, 26);

    public AdvancedSearchInputSummarizer(AdvancedSearchView view, JPanel displayPanel) {
        this.displayPanel = displayPanel;
        this.recipeName = view.getRecipeName();
        this.ingredientsQuantity = view.getIngredientsQuantity();
        this.excludedIngredients = view.getExcludedIngredients();
        this.dietSelection = view.getDietSelection();
        this.healthSelection = view.getHealthSelection();
        this.cuisineTypeSelection = view.getCuisineTypeSelection();
        this.dishTypeSelection = view.getDishTypeSelection();
        this.mealTypeSelection = view.getMealTypeSelection();
    }
    public void summarize(){

        boolean recipeNameFilled = recipeName != null && !recipeName.isEmpty();
        List<String> ingredients = new ArrayList<>(ingredientsQuantity.keySet()).reversed();
        boolean ingredientFilled = !ingredients.isEmpty();
        boolean excludedFilled = !excludedIngredients.isEmpty();
        boolean dietFilled = !dietSelection.isEmpty();
        boolean healthFilled = !healthSelection.isEmpty();
        boolean cuisineTypeFilled = !cuisineTypeSelection.isEmpty();
        boolean dishTypeFilled = !dishTypeSelection.isEmpty();
        boolean mealTypeFilled = !mealTypeSelection.isEmpty();

        if (recipeNameFilled
                || ingredientFilled
                || excludedFilled
                || dietFilled
                || healthFilled
                || cuisineTypeFilled
                || dishTypeFilled
                || mealTypeFilled) {

            addToSummary(dfM, "Looking");
            addToSummary(dfS, "for");

            if (recipeNameFilled) {
                for (String word : recipeName.trim().split(" ")) {
                    addToSummary(dfXL, capitalizeWords(word));
                }

            }

            addToSummary(dfM, "recipe");
            addToSummary(dfS, "that");

            int counter = 0;

            if (excludedFilled) {
                addToSummary(dfS, "does");
                addToSummary(dfM, "Not");
                addToSummary(dfM, "Contain");

                for (String excluded : excludedIngredients) {
                    for (String word : excluded.trim().split(" ")) {
                        addToSummary(dfL, capitalizeWords(word));
                    }
                    counter += 1;
                    if (counter < excludedIngredients.size()) {
                        addToSummary(dfM,",");
                    } else {
                        counter = 0;
                    }
                }
                addToSummary(dfS, "that");
            }

            if (dietFilled) {
                addToSummary(dfS, "is");
                for (String selection : dietSelection) {
                    for (String word : selection.trim().split("%20")) {
                        addToSummary(dfL, capitalizeWords(word));
                    }
                    counter += 1;
                    if (counter < dietSelection.size()) {
                        addToSummary(dfM,",");
                    } else {
                        counter = 0;
                    }
                }
            }

            if (healthFilled) {
                addToSummary(dfS, "and");
                for (String selection : healthSelection) {
                    for (String word : selection.trim().split("%20")) {
                        addToSummary(dfM, capitalizeWords(word));
                    }
                    counter += 1;
                    if (counter < healthSelection.size()) {
                        addToSummary(dfM,",");
                    } else {
                        counter = 0;
                    }
                }
            }

            if (cuisineTypeFilled) {
                addToSummary(dfS, "which");
                addToSummary(dfS, "is");
                for (String selection : cuisineTypeSelection) {
                    for (String word : selection.trim().split("%20")) {
                        addToSummary(dfXL, capitalizeWords(word));
                    }
                    counter += 1;
                    if (counter < cuisineTypeSelection.size()) {
                        addToSummary(dfM,",");
                        addToSummary(dfS,"or");
                    } else {
                        counter = 0;
                    }
                }
                addToSummary(dfS,"cuisine");
            }

            if (dishTypeFilled) {

                for (String selection : dishTypeSelection) {
                    for (String word : selection.trim().split("%20")) {
                        addToSummary(dfL, capitalizeWords(word));
                    }
                    counter += 1;
                    if (counter < dishTypeSelection.size()) {
                        addToSummary(dfL,",");
                        addToSummary(dfS,"or");
                    } else {
                        counter = 0;
                    }
                }
            }

            if (mealTypeFilled) {
                addToSummary(dfS, "for");

                for (String selection : mealTypeSelection) {

                    for (String word : selection.trim().split("%20")) {
                        addToSummary(dfL, capitalizeWords(word));
                    }

                    counter += 1;
                    if (counter < mealTypeSelection.size()) {
                        addToSummary(dfM,",");
                        addToSummary(dfS,"or");
                    } else {
                        counter = 0;
                    }
                }

            }

            addToSummary(dfM, "...");
        }
    }

    private void addToSummary(Font font, String text) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(LocalAppSetting.isNightMode() ? mint : claudeBlack);
        label.setAlignmentX(RIGHT_ALIGNMENT);
        label.setAlignmentY(BOTTOM_ALIGNMENT);
        displayPanel.add(label);
    }
}
