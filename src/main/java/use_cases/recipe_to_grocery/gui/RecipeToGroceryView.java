package use_cases.recipe_to_grocery.gui;

import entity.Recipe;
import entity.ShoppingList;
import entity.User;
import use_cases._common.authentication.AuthenticationService;
import use_cases._common.gui_common.abstractions.View;

import use_cases._common.gui_common.view.Sidebar;
import use_cases.recipe_to_grocery.gui.view_component.ShoppingListContainer;
import use_cases.recipe_to_grocery.gui.view_component.ShoppingListPanel;
import use_cases.recipe_to_grocery.interface_adapter.controller.RecipeToGroceryController;
import use_cases.recipe_to_grocery.interface_adapter.view_model.RecipeToGroceryViewModel;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases._common.authentication.AuthenticationService;
import use_cases.log_in.use_case.interactor.LoginInteractor;
import use_cases.recipe_to_grocery.use_case.output_data.RecipeToGroceryOutputData;
import use_cases.search_recipe.use_case.output_data.SearchRecipeOutputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionListener;

public class RecipeToGroceryView extends View implements ActionListener {
    private RecipeToGroceryViewModel RecipeToGroceryViewModel;
    private RecipeToGroceryController recipeToGroceryController;
    private AuthenticationService authenticationService; // Change here
    private LoginInteractor loginInteractor;
    private AuthenticationService authenticationService;

    public final String viewname = "recipe to grocery";

    // Components
    private JButton convertRecipesButton;


    public RecipeToGroceryView(RecipeToGroceryViewModel RecipeToGroceryViewModel,
                               RecipeToGroceryController recipeToGroceryController,
                               AuthenticationService authenticationService, // Change here
                               ViewManagerModel viewManagerModel) {

        // Add PropertyChangeListener to corresponding ViewModel
        this.RecipeToGroceryViewModel = RecipeToGroceryViewModel;
        RecipeToGroceryViewModel.addPropertyChangeListener(this);

        // Make connection to Controller
        this.recipeToGroceryController = recipeToGroceryController;
        this.authenticationService = authenticationService; // Change here
        setupUI();

        // Set Layout
        this.setLayout(new BorderLayout());

        // Sidebar
        Sidebar sidebar = new Sidebar();

        // MainPanel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Initialize Panel
        outputPanel = new JPanel();
        outputPanel.setBackground(claudeWhite);
        outputPanel.setBorder(BorderFactory.createLineBorder(claudeWhite, 20));
        outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));

        // Output Components
        shoppingListContainer = new ShoppingListContainer(outputPanel);

        // Pack input & output panel
        mainPanel.add(shoppingListContainer, BorderLayout.CENTER);

        this.add(sidebar, BorderLayout.WEST);
        this.add(mainPanel, BorderLayout.CENTER);
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
            User user = authenticationService.getLoggedInUser(); // Retrieve the logged-in user
            if (user != null) {
                // Call the convertRecipesToGroceryList method directly
                recipeToGroceryController.convertRecipesToGroceryList(user);
            } else {
                System.out.println("No user is currently logged in.");
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("no recipe")) {
            SearchRecipeOutputData response = null;
            loadEmptyResult();
        }
    }

    public void loadEmptyResult () {
        outputPanel.removeAll();
        JPanel emptyResultPanel = new JPanel();
        emptyResultPanel.setBackground(claudeWhite);
        JLabel emptyResultLabel = new JLabel("Empty shopping list...");
        emptyResultLabel.setFont(new Font(defaultFont, Font.PLAIN, 14));
        emptyResultLabel.setForeground(claudeBlackEmph);
        emptyResultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        emptyResultPanel.add(emptyResultLabel);
        outputPanel.add(emptyResultPanel);
    }


    @Override
    public String getViewName() {
        return this.viewname;
    }


}




