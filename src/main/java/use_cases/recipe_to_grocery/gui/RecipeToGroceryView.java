package use_cases.recipe_to_grocery.gui;

import entity.LoggedUserData;
import entity.Recipe;
import entity.ShoppingList;
import entity.User;
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

/**
 * RecipeToGroceryView is responsible for displaying the recipe to grocery conversion view.
 * It extends View and implements ActionListener to handle user actions and PropertyChangeListener to react to ViewModel changes.
 */
public class RecipeToGroceryView extends View implements ActionListener {
    private RecipeToGroceryViewModel recipeToGroceryViewModel;
    private RecipeToGroceryController recipeToGroceryController;
    private AuthenticationService authenticationService;

    public final String viewName = "recipe to grocery";

    // Components
    private JPanel outputPanel;
    private JScrollPane shoppingListContainer;

    /**
     * Constructs a RecipeToGroceryView with the specified ViewModel, Controller, authentication service, and view manager model.
     *
     * @param recipeToGroceryViewModel The ViewModel for recipe to grocery conversion.
     * @param recipeToGroceryController The Controller for recipe to grocery conversion.
     * @param authenticationService The AuthenticationService for managing user authentication.
     * @param viewManagerModel The ViewManagerModel for managing view state.
     */
    public RecipeToGroceryView(RecipeToGroceryViewModel recipeToGroceryViewModel,
                               RecipeToGroceryController recipeToGroceryController,
                               AuthenticationService authenticationService,
                               ViewManagerModel viewManagerModel) {

        // Add PropertyChangeListener to corresponding ViewModel
        this.recipeToGroceryViewModel = recipeToGroceryViewModel;
        recipeToGroceryViewModel.addPropertyChangeListener(this);

        // Make connection to Controller
        this.recipeToGroceryController = recipeToGroceryController;
        this.authenticationService = authenticationService;

        // Set Layout
        this.setLayout(new BorderLayout());

        // Sidebar
//        Sidebar sidebar = new Sidebar();

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

//        this.add(sidebar, BorderLayout.WEST);
        this.add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Loads shopping lists into the view based on the output data from recipe to grocery conversion.
     *
     * @param response The output data containing shopping lists.
     */
    public void loadShoppingList(RecipeToGroceryOutputData response) {
        outputPanel.removeAll();
        for (ShoppingList shoppingList : response) {
            JPanel shoppingListPanel = new ShoppingListPanel(shoppingList);
            outputPanel.add(shoppingListPanel);
        }
        SwingUtilities.invokeLater(() -> shoppingListContainer.getVerticalScrollBar().setValue(0));
    }

    /**
     * Handles user action events, specifically triggering recipe to grocery conversion.
     *
     * @param e The action event triggered.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        User user = LoggedUserData.getLoggedInUser(); // Retrieve the logged-in user
        System.out.println("found currently logged in user");
        if (user != null) {
            System.out.println("calling convert recipes to grocery list");
            recipeToGroceryController.convertRecipesToGroceryList(user);
        } else {
            System.out.println("No user is currently logged in.");
        }
    }

    /**
     * Listens to property change events from the ViewModel.
     *
     * @param evt The property change event fired.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("no recipe")) {
            // Handle empty recipe result
            System.out.println("no recipe found - view");
            RecipeToGroceryOutputData response = (RecipeToGroceryOutputData) evt.getNewValue();
            loadEmptyResult();
        }
    }

    /**
     * Loads an empty result message into the view when there are no recipes found.
     */
    public void loadEmptyResult() {
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

    /**
     * Returns the name of the view.
     *
     * @return The name of the view.
     */
    @Override
    public String getViewName() {
        return this.viewName;
    }
}
