package use_cases.recipe_to_grocery.gui;

import entity.Recipe;
import entity.User;
import use_cases._common.gui_common.abstractions.View;
import use_cases._common.gui_common.view.Sidebar;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases._common.gui_common.view_components.round_component.RoundPanel;

import use_cases._common.gui_common.view_components.round_component.RoundTextField;
import use_cases.recipe_to_grocery.interface_adapter.controller.RecipeToGroceryController;
import use_cases.recipe_to_grocery.interface_adapter.presenter.RecipeToGroceryPresenter;
import use_cases.recipe_to_grocery.interface_adapter.view_model.RecipeListViewModel;
import use_cases.recipe_to_grocery.interface_adapter.view_model.ShoppingListViewModel;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.recipe_to_grocery.use_case.output_data.RecipeToGroceryOutputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RecipeToGroceryView extends View implements ActionListener {
    private RecipeListViewModel recipeListViewModel;
    private RecipeToGroceryController recipeToGroceryController;

    public final String viewname = "recipe to grocery";

    // Components
    private JButton convertRecipesButton;
    private JPanel recipeListPanel;
    private JPanel shoppingListPanel;
    private JScrollPane shoppingListScrollPane;
    private JScrollPane recipeListScrollPane;


    public RecipeToGroceryView(RecipeListViewModel recipeListViewModel,
                               RecipeToGroceryController recipeToGroceryController,
                               ViewManagerModel viewManagerModel) {
        // Add PropertyChangeListener to corresponding ViewModel
        this.recipeListViewModel = recipeListViewModel;
        recipeListViewModel.addPropertyChangeListener(this);

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

//        // Set Layout
//        this.setLayout(new BorderLayout());
//
//        // Sidebar
//        Sidebar sidebar = new Sidebar();
//
//        // MainPanel
//        JPanel mainPanel = new JPanel(new BorderLayout());
//
//        // Initialize Panels
//        recipeListPanel = new JPanel();
//        recipeListPanel.setLayout(new BoxLayout(recipeListPanel, BoxLayout.Y_AXIS));
//
//        shoppingListPanel = new JPanel();
//        shoppingListPanel.setLayout(new BoxLayout(shoppingListPanel, BoxLayout.Y_AXIS));
//
//        // Scroll Panes
//        recipeListScrollPane = new JScrollPane(recipeListPanel);
//        shoppingListScrollPane = new JScrollPane(shoppingListPanel);
//
//        // Fetch User's Recipes Button
//        RoundButton fetchRecipesButton = new RoundButton("Fetch Recipes");
//        fetchRecipesButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Call controller to fetch recipes
//                recipeToGroceryController.convertRecipesToGroceryList();
//            }
//        });
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convertRecipesButton) {
            User loggedInUser = recipeToGroceryController.handleAuthenticationAndConversion(email, password);
        }
}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("recipe to grocery".equals(evt.getPropertyName())) {}
        outputPanel.revalidate();
        outputPanel.repaint();
    }


    @Override
    public String getViewName() {
        return this.viewname;
    }


}




