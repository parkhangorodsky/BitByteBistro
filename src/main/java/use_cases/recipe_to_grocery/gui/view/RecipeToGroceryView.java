package use_cases.recipe_to_grocery.gui.view;

import entity.Recipe;
import use_cases._common.gui_common.abstractions.View;
import use_cases._common.gui_common.view.Sidebar;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases._common.gui_common.view_components.round_component.RoundPanel;

import use_cases._common.gui_common.view_components.round_component.RoundTextField;
import use_cases.recipe_to_grocery.interface_adapter.controller.RecipeToGroceryController;
import use_cases.recipe_to_grocery.interface_adapter.presenter.RecipeToGroceryPresenter;
import use_cases.recipe_to_grocery.interface_adapter.view_model.RecipeListViewModel;
import use_cases.recipe_to_grocery.interface_adapter.view_model.ShoppingListViewModel;
import use_cases.recipe_to_grocery.use_case.output_data.RecipeToGroceryOutputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionListener;
import java.util.List;



public class RecipeToGroceryView extends View {

    private RecipeListViewModel recipeListViewModel;
    private RecipeToGroceryController controller;
    private RecipeToGroceryPresenter recipeToGroceryPresenter;

    public final String viewname = "recipe to grocery";

    // Components
    private JPanel recipeListPanel;
    private JPanel shoppingListPanel;
    private JScrollPane recipeScrollPane;
    private JScrollPane shoppingListScrollPane;

    
    public RecipeToGroceryView(RecipeListViewModel recipeListViewModel,
                            RecipeToGroceryController controller) {

        // Add PropertyChangeListener to corresponding ViewModel
        this.recipeListViewModel = recipeListViewModel;
        recipeListViewModel.addPropertyChangeListener(this);

        // Make connection to Controller
        this.controller = controller;

        // Set Layout
        this.setLayout(new BorderLayout());

        // Sidebar
        Sidebar sidebar = new Sidebar();

        // MainPanel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Initialize Panels
        recipeListPanel = new JPanel();
        recipeListPanel.setLayout(new BoxLayout(recipeListPanel, BoxLayout.Y_AXIS));

        shoppingListPanel = new JPanel();
        shoppingListPanel.setLayout(new BoxLayout(shoppingListPanel, BoxLayout.Y_AXIS));

        // Scroll Panes
        recipeScrollPane = new JScrollPane(recipeListPanel);
        shoppingListScrollPane = new JScrollPane(shoppingListPanel);

        // Fetch Recipes Button
        RoundButton fetchRecipesButton = new RoundButton("Fetch Recipes");
        fetchRecipesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call controller to fetch recipes
                controller.fetchRecipes();
            }
        });
        

        // Input Components

        // Output Components


        // Pack input & output panel
//        inputPanel.add(title);
        inputPanel.add(recipeName);
        inputPanel.add(searchButton);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(recipeContainer, BorderLayout.CENTER);

        this.add(sidebar, BorderLayout.WEST);
        this.add(mainPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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




