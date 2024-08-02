package use_cases.display_recipe_detail;

import entity.LoggedUserData;
import entity.Recipe;
import entity.ShoppingList;
import use_cases._common.gui_common.abstractions.ImageLoader;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;
import use_cases.add_to_my_recipe.AddToMyRecipeController;
import use_cases.recipe_to_grocery.interface_adapter.controller.RecipeToGroceryController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class DisplayRecipeDetailSearchResultView extends DisplayRecipeDetailView {
    private AddToMyRecipeController addToMyRecipeController;
    private RecipeToGroceryController recipeToGroceryController;
    private ArrayList<ShoppingList> userGroceryLists;

    public DisplayRecipeDetailSearchResultView(JFrame parent, DisplayRecipeDetailViewModel viewModel, AddToMyRecipeController addToMyRecipeController, RecipeToGroceryController recipeToGroceryController) {
        super(parent, viewModel);
        this.addToMyRecipeController = addToMyRecipeController;
        this.recipeToGroceryController = recipeToGroceryController;
        this.userGroceryLists = LoggedUserData.getLoggedInUser().getShoppingLists(); // Initialize the grocery lists
    }


    @Override
    public JPanel createControlPanel() {

        JPanel controlPanel = new JPanel(new BorderLayout());
        controlPanel.setBorder(new EmptyBorder(10, 30, 10, 30));
        controlPanel.setBackground(claudeWhite);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        RoundButton closeButton = new RoundButton("Close");
        RoundButton addToButton = new RoundButton("Add To");

        closeButton.addActionListener(e -> {
            this.dispose();
        });

        // Create a popup menu for the "Add To" button
        JPopupMenu addToMenu = new JPopupMenu();
        JMenuItem addToMyRecipeItem = new JMenuItem("Add to My Recipe");

        addToMyRecipeItem.addActionListener(e -> {
            addToMyRecipeController.execute(viewModel.getRecipe(), viewModel);
        });

        addToMenu.add(addToMyRecipeItem);
        addToMenu.addSeparator();

        // Dynamically add user's grocery lists to the menu
        if (userGroceryLists != null && !userGroceryLists.isEmpty()) {
            for (ShoppingList list : userGroceryLists) {
                JMenuItem groceryListItem = new JMenuItem("Add to " + list);
                groceryListItem.addActionListener(e -> {
                    // Logic to add to the selected grocery list
                    addToGroceryList(viewModel.getRecipe(), list);
                });
                addToMenu.add(groceryListItem);
            }
        }

        // Option to create a new grocery list
        JMenuItem createNewGroceryListItem = new JMenuItem("Create New Grocery List");
        createNewGroceryListItem.addActionListener(e -> {
            // Logic to create a new grocery list and add the recipe to it
            createNewGroceryListAndAdd(viewModel.getRecipe());
        });
        addToMenu.add(createNewGroceryListItem);


        addToButton.addActionListener(e -> {
            addToMenu.show(addToButton, addToButton.getWidth() / 2, addToButton.getHeight() / 2);
        });

        buttonPanel.add(addToButton);
        buttonPanel.add(closeButton);

        controlPanel.add(buttonPanel, BorderLayout.EAST);

        return controlPanel;
    };

    private void addToGroceryList(Recipe recipe, ShoppingList list) {
        addToMyRecipeController.execute(viewModel.getRecipe(), viewModel);
        recipeToGroceryController.convertRecipesToGroceryList(LoggedUserData.getLoggedInUser());
    }

    private void createNewGroceryListAndAdd(Recipe recipe) {
        // Implement logic to create a new grocery list and add the recipe to it
        // You can interact with the viewModel or other controllers as needed
        String newListName = JOptionPane.showInputDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Enter name for new grocery list:");
        if (newListName != null && !newListName.trim().isEmpty()) {
            System.out.println("Creating new grocery list and adding recipe to: " + newListName);
            // For example, you might have a method in a controller like:
            // groceryListController.createNewListAndAddRecipe(newListName, recipe);
        }
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
                    "Successfully added.","",
                    JOptionPane.INFORMATION_MESSAGE);
        }else {
            super.propertyChange(evt);
        }
    }


}
