package use_cases.recipe_to_grocery.gui;

import entity.Recipe;
import entity.User;
import use_cases._common.gui_common.abstractions.View;

import use_cases.recipe_to_grocery.interface_adapter.controller.RecipeToGroceryController;
import use_cases.recipe_to_grocery.interface_adapter.view_model.RecipeToGroceryViewModel;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.log_in.use_case.interactor.LoginInteractor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionListener;

public class RecipeToGroceryView extends View implements ActionListener {
    private RecipeToGroceryViewModel RecipeToGroceryViewModel;
    private RecipeToGroceryController recipeToGroceryController;
    private LoginInteractor loginInteractor;

    public final String viewname = "recipe to grocery";

    // Components
    private JButton convertRecipesButton;


    public RecipeToGroceryView(RecipeToGroceryViewModel RecipeToGroceryViewModel,
                               RecipeToGroceryController recipeToGroceryController,
                               ViewManagerModel viewManagerModel) {

        // Add PropertyChangeListener to corresponding ViewModel
        this.RecipeToGroceryViewModel = RecipeToGroceryViewModel;
        RecipeToGroceryViewModel.addPropertyChangeListener(this);

        // Make connection to Controller
        this.recipeToGroceryController = recipeToGroceryController;
        setupUI();
    }

    private void setupUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // Convert Recipes Button
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        convertRecipesButton = new JButton("Convert Recipes to Grocery List");
        convertRecipesButton.addActionListener(this);
        add(convertRecipesButton, gbc);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convertRecipesButton) {
            User user = loginInteractor.getLoggedInUser();
            recipeToGroceryController.handleAuthenticationAndConversion(user.getUserEmail(), user.getUserPassword());
        }
}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }


    @Override
    public String getViewName() {
        return this.viewname;
    }


}




