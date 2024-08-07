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
import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.List;

public class DisplayRecipeDetailSearchResultView extends DisplayRecipeDetailView implements NightModeObject {
    private AddToMyRecipeController addToMyRecipeController;
    private CoreFunctionalityController coreFunctionalityController;
    private AddNewGroceryListController addNewGroceryListController;
    private HashMap<String, ShoppingList> userGroceryLists;
    RoundButton addToRecipesButton;
    RoundButton addToGroceryButton;
    User user = LoggedUserData.getLoggedInUser();

    public DisplayRecipeDetailSearchResultView(JFrame parent, DisplayRecipeDetailViewModel viewModel,
                                               AddToMyRecipeController addToMyRecipeController,
                                               CoreFunctionalityController coreFunctionalityController,
                                               AddNewGroceryListController addNewGroceryListController) {
        super(parent, viewModel, coreFunctionalityController, addNewGroceryListController);
        this.addToMyRecipeController = addToMyRecipeController;
        this.coreFunctionalityController = coreFunctionalityController;
        this.addNewGroceryListController = addNewGroceryListController;
        this.userGroceryLists = user.getShoppingLists(); // Initialize the grocery lists
    }

    private JPopupMenu addToMenu;


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
        addToGroceryButton = new RoundButton("Add To My Grocery List(s)");

        Recipe recipe = viewModel.getRecipe();

        addToRecipesButton.addActionListener(e -> {
            addToMyRecipeController.execute(recipe, viewModel);
        });

        addToMenu = showAddToMenu(recipe);

        addToGroceryButton.addActionListener(e -> {
            addToMenu.show(addToGroceryButton, addToGroceryButton.getWidth() / 2, addToGroceryButton.getHeight() / 2);
        });

        buttonPanel.remove(closeButton);
        buttonPanel.revalidate();
        buttonPanel.repaint();
        buttonPanel.add(addToRecipesButton);
        buttonPanel.add(addToGroceryButton);
        buttonPanel.add(closeButton);

        return buttonPanel;

    };

    public JPopupMenu showAddToMenu(Recipe recipe) {
        JPopupMenu addToMenu = new JPopupMenu();

        if (userGroceryLists != null && !userGroceryLists.isEmpty()) {
            for (HashMap.Entry<String, ShoppingList> list : userGroceryLists.entrySet()) {
                String owner = list.getKey();
                ShoppingList items = list.getValue();
                JMenuItem groceryListItem = new JMenuItem("Add to " + items.getShoppingListName());
                groceryListItem.addActionListener(e -> {
                    coreFunctionalityController.execute(items, recipe, viewModel);
                });
                addToMenu.add(groceryListItem);
            }
        }

        JMenuItem createNewGroceryListItem = new JMenuItem("Create New Grocery List");
        createNewGroceryListItem.addActionListener(e -> {
            createNewGroceryListAndAdd(recipe);
        });
        addToMenu.add(createNewGroceryListItem);

        return addToMenu;
    }


    private void createNewGroceryListAndAdd(Recipe recipe) {
        String newListName = JOptionPane.showInputDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Enter name for new grocery list:");
        //need to handle when this is empty
        if (newListName != null && !newListName.trim().isEmpty()) {
            addNewGroceryListController.execute(newListName, viewModel);
        }
        ShoppingList newShoppingList = user.getShoppingList(newListName);
        coreFunctionalityController.execute(newShoppingList, recipe, viewModel);
        addToMenu = showAddToMenu(recipe);
        addToMenu.show(addToGroceryButton, addToGroceryButton.getWidth() / 2, addToGroceryButton.getHeight() / 2);
    }



    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("recipe already exists")) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                    "Recipe already exists.",
                    "",
                    JOptionPane.ERROR_MESSAGE);
        } else if (evt.getPropertyName().equals("added recipe")) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                    "Successfully added.", "",
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
