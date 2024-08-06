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
import java.util.HashMap;
import java.util.List;

public class DisplayRecipeDetailSearchResultView extends DisplayRecipeDetailView implements NightModeObject {
    private AddToMyRecipeController addToMyRecipeController;
    private CoreFunctionalityController coreFunctionalityController;
    private AddNewGroceryListController addNewGroceryListController;
    private HashMap<String, ShoppingList> userGroceryLists;
    User user = LoggedUserData.getLoggedInUser();

    public DisplayRecipeDetailSearchResultView(JFrame parent, DisplayRecipeDetailViewModel viewModel,
                                               AddToMyRecipeController addToMyRecipeController,
                                               CoreFunctionalityController coreFunctionalityController,
                                               AddNewGroceryListController addNewGroceryListController) {
        super(parent, viewModel);
        this.addToMyRecipeController = addToMyRecipeController;
        this.coreFunctionalityController = coreFunctionalityController;
        this.addNewGroceryListController = addNewGroceryListController;
        this.userGroceryLists = user.getShoppingLists(); // Initialize the grocery lists
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

        controlPanel = new JPanel(new BorderLayout());
        controlPanel.setBorder(new EmptyBorder(10, 30, 10, 30));

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        closeButton = new RoundButton("Close");
        addToRecipesButton = new RoundButton("Add To My Recipes");
        addToGroceryButton = new RoundButton("Add To My Grocery List(s)");


        closeButton.addActionListener(e -> {
            this.dispose();
        });

        Recipe recipe = viewModel.getRecipe();

        addToRecipesButton.addActionListener(e -> {
            addToMyRecipeController.execute(recipe, viewModel);
        });


        JPopupMenu addToMenu = new JPopupMenu();
        JMenuItem addToGroceryButton = new JMenuItem("Add To My Grocery List(s)");


        if (userGroceryLists != null && !userGroceryLists.isEmpty()) {
            for (HashMap.Entry<String, ShoppingList> list : userGroceryLists.entrySet()) {
                String owner = list.getKey();
                ShoppingList items = list.getValue();
                JMenuItem groceryListItem = new JMenuItem("Add to " + items.getShoppingListName());
                groceryListItem.addActionListener(e -> {
                    addToGroceryList(recipe, items);
                });
                addToMenu.add(groceryListItem);
                }
        }

        // Option to create a new grocery list
        JMenuItem createNewGroceryListItem = new JMenuItem("Create New Grocery List");
        createNewGroceryListItem.addActionListener(e -> {
            createNewGroceryListAndAdd(recipe);
        });
        addToMenu.add(createNewGroceryListItem);


        addToGroceryButton.addActionListener(e -> {
            addToMenu.show(addToGroceryButton, addToGroceryButton.getWidth() / 2, addToGroceryButton.getHeight() / 2);
        });


        buttonPanel.add(addToRecipesButton);
        buttonPanel.add(addToGroceryButton);
        buttonPanel.add(closeButton);

        controlPanel.add(buttonPanel, BorderLayout.EAST);

        toggleNightMode();

        return controlPanel;
    };

    private void addToGroceryList(Recipe recipe, ShoppingList shoppingList) {
        coreFunctionalityController.execute(shoppingList, recipe, viewModel);
    }


    private void createNewGroceryListAndAdd(Recipe recipe) {
        String newListName = JOptionPane.showInputDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Enter name for new grocery list:");
        if (newListName != null && !newListName.trim().isEmpty()) {
            addNewGroceryListController.execute(newListName, viewModel);
        }
        ShoppingList newShoppingList = user.getShoppingList(newListName);
        coreFunctionalityController.execute(newShoppingList, recipe, viewModel);
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
