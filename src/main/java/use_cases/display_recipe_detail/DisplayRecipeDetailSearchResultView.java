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
    RoundButton addToRecipesButton;


    public DisplayRecipeDetailSearchResultView(JFrame parent, DisplayRecipeDetailViewModel viewModel,
                                               CoreFunctionalityController coreFunctionalityController,
                                               AddNewGroceryListController addNewGroceryListController,  AddToMyRecipeController addToMyRecipeController) {
        super(parent, viewModel, coreFunctionalityController, addNewGroceryListController );
        this.addToMyRecipeController = addToMyRecipeController;
    }


    protected void initialize() {
        super.initialize();
        observeNight();
    }


    @Override
    public JPanel createButtonPanel() {
        // Call the parent method to get the initialized controlPanel
        JPanel buttonPanel = super.createButtonPanel();

        // Initialize and add the additional button
        addToRecipesButton = new RoundButton("Add To My Recipes");
        Recipe recipe = viewModel.getRecipe();

        addToRecipesButton.addActionListener(e -> {
            addToMyRecipeController.execute(recipe, viewModel);
        });

       buttonPanel.remove(closeButton);
       buttonPanel.revalidate();
       buttonPanel.repaint();
       buttonPanel.add(addToRecipesButton);
       buttonPanel.add(closeButton);

        return buttonPanel;

    }


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
        addToRecipesButton.setHoverColor(neonPink, darkPurple, white, white);
        addToRecipesButton.setBorderColor(neonPurple);

        super.setNightMode();
    }

    @Override
    public void setDayMode() {

        addToRecipesButton.setHoverColor(claudeWhite, claudeWhiteEmph, claudeBlackEmph, claudeWhite);
        addToRecipesButton.setBorderColor(claudeWhite);

        super.setDayMode();
    }


}
