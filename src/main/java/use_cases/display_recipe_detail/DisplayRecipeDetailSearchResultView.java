package use_cases.display_recipe_detail;

import app.local.LoggedUserData;
import entity.Recipe;
import entity.ShoppingList;
import entity.User;
import use_cases._common.gui_common.abstractions.NightModeObject;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases.add_to_my_recipe.AddToMyRecipeController;
import use_cases.core_functionality.CoreFunctionalityController;
import use_cases.add_new_grocery_list.AddNewGroceryListController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.util.List;

public class DisplayRecipeDetailSearchResultView extends DisplayRecipeDetailView implements NightModeObject {
    private AddToMyRecipeController addToMyRecipeController;

    public DisplayRecipeDetailSearchResultView(JFrame parent, DisplayRecipeDetailViewModel viewModel,
                                               CoreFunctionalityController coreFunctionalityController,
                                               AddNewGroceryListController addNewGroceryListController,  AddToMyRecipeController addToMyRecipeController) {
        super(parent, viewModel, coreFunctionalityController, addNewGroceryListController );
        this.addToMyRecipeController = addToMyRecipeController;
    }


    JPanel controlPanel;

    JPanel buttonPanel;
    RoundButton closeButton;
    RoundButton addToRecipesButton;
    RoundButton addToGroceryButton;


    protected void initialize() {
        super.initialize();
        observeNight();
    }


    @Override
    public JPanel createControlPanel() {
        JPanel controlPanel = super.createControlPanel();
        // Add the additional buttons
        addToRecipesButton = new RoundButton("Add To My Recipes");

        Recipe recipe = viewModel.getRecipe();

        addToRecipesButton.addActionListener(e -> {
            addToMyRecipeController.execute(recipe, viewModel);
        });

        // Add the new buttons to the button panel
        buttonPanel.add(addToRecipesButton);

        return controlPanel;
    };


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("recipe already exists")) {
            JOptionPane.showMessageDialog((JFrame) SwingUtilities.getWindowAncestor(this),
                    "Recipe already exists.",
                    "",
                    JOptionPane.ERROR_MESSAGE);
        } else if (evt.getPropertyName().equals("added recipe")) {
            JOptionPane.showMessageDialog((JFrame) SwingUtilities.getWindowAncestor(this),
                    "Succesfully added.", "",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (evt.getPropertyName().equals("nightMode")) {
            toggleNightMode();
            this.revalidate();
            this.repaint();
        } else {
            super.propertyChange(evt);
        }
    }

    @Override
    public void setNightMode() {
        controlPanel.setBackground(black);
        buttonPanel.setBackground(black);

        closeButton.setHoverColor(neonPink, darkPurple, white, white);
        closeButton.setBorderColor(neonPurple);
        addToRecipesButton.setHoverColor(neonPink, darkPurple, white, white);
        addToRecipesButton.setBorderColor(neonPurple);

        super.setNightMode();
    }

    @Override
    public void setDayMode() {
        controlPanel.setBackground(claudeWhite);
        buttonPanel.setBackground(claudeWhite);

        closeButton.setHoverColor(claudeWhite, claudeWhiteEmph, claudeBlackEmph, claudeWhite);
        closeButton.setBorderColor(claudeWhite);
        addToRecipesButton.setHoverColor(claudeWhite, claudeWhiteEmph, claudeBlackEmph, claudeWhite);
        addToRecipesButton.setBorderColor(claudeWhite);

        super.setDayMode();
    }


}
