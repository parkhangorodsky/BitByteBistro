package use_cases.core_functionality.view_component;

import app.local.LocalAppSetting;
import entity.Ingredient;
import entity.Recipe;
import entity.ShoppingList;
import use_cases._common.gui_common.view_components.layouts.VerticalFlowLayout;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases._common.gui_common.view_components.round_component.RoundPanel;
import use_cases.display_recipe_detail.DisplayRecipeDetailSearchResultView;
import use_cases.display_recipe_detail.DisplayRecipeDetailViewModel;

import use_cases.add_new_grocery_list.AddNewGroceryListController;
import use_cases.add_to_my_recipe.AddToMyRecipeController;
import use_cases.display_recipe_detail.DisplayRecipeDetailController;
import use_cases.core_functionality.CoreFunctionalityController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ShoppingListItem extends RoundPanel {
    private DisplayRecipeDetailController displayRecipeDetailController;
    private AddToMyRecipeController addToMyRecipeController;
    private CoreFunctionalityController coreFunctionalityController;
    private AddNewGroceryListController addNewGroceryListController;
    private JSplitPane splitPane;
    private boolean isIngredientsVisible = false;
    private boolean isRecipesVisible = false;
    private RoundButton showIngredientButton;
    private RoundButton showRecipesButton;

    public ShoppingListItem(ShoppingList shoppingList,
                            AddNewGroceryListController addNewGroceryListController,
                            AddToMyRecipeController addToMyRecipeController,
                            CoreFunctionalityController coreFunctionalityController,
                            DisplayRecipeDetailController displayRecipeDetailController) {
        this.addNewGroceryListController = addNewGroceryListController;
        this.displayRecipeDetailController = displayRecipeDetailController;
        this.addToMyRecipeController = addToMyRecipeController;
        this.coreFunctionalityController = coreFunctionalityController;

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(LocalAppSetting.isNightMode() ? Color.DARK_GRAY : Color.WHITE);
        setBorderColor(LocalAppSetting.isNightMode() ? Color.GRAY : Color.LIGHT_GRAY);

        JPanel shoppingListNamePanel = new JPanel(new BorderLayout());
        shoppingListNamePanel.setOpaque(false);
        JLabel shoppingListNameLabel = new JLabel(shoppingList.getShoppingListName());
        shoppingListNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        shoppingListNameLabel.setForeground(LocalAppSetting.isNightMode() ? Color.PINK : Color.BLACK);
        shoppingListNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        shoppingListNameLabel.setVerticalTextPosition(SwingConstants.CENTER);
        shoppingListNamePanel.add(shoppingListNameLabel, BorderLayout.CENTER);

        // Create a panel for the buttons with FlowLayout
        JPanel topButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        topButtonPanel.setOpaque(false);

        showIngredientButton = new RoundButton("∨");
        showIngredientButton.setPreferredSize(new Dimension(30, 30)); // Fixed size
        showIngredientButton.setBorderColor(LocalAppSetting.isNightMode() ? Color.PINK : Color.BLACK);
        showIngredientButton.addActionListener(e -> toggleIngredientsPanel(shoppingList));

        showRecipesButton = new RoundButton("Show Recipes");
        showRecipesButton.setPreferredSize(new Dimension(120, 30)); // Fixed size
        showRecipesButton.setBorderColor(LocalAppSetting.isNightMode() ? neonPinkEmph : claudeBlack);
        showRecipesButton.addActionListener(e -> toggleRecipesPanel(shoppingList));

        topButtonPanel.add(showIngredientButton);
        topButtonPanel.add(showRecipesButton);

        add(topButtonPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setResizeWeight(0.5);
        splitPane.setDividerLocation(0.5);
        splitPane.setVisible(false);

        contentPanel.add(splitPane, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);

        add(shoppingListNamePanel, BorderLayout.WEST);
    }

    private void toggleIngredientsPanel(ShoppingList shoppingList) {
        if (!isIngredientsVisible) {
            showIngredientButton.setText("∧");
            JPanel ingredientsPanel = createIngredientsPanel(shoppingList.getListItems());
            splitPane.setLeftComponent(ingredientsPanel);
            if (isRecipesVisible) {
                splitPane.setVisible(true);
            } else {
                splitPane.setVisible(true);
                revalidate();
            }
            isIngredientsVisible = true;
        } else {
            showIngredientButton.setText("∨");
            splitPane.setLeftComponent(null);
            if (!isRecipesVisible) {
                splitPane.setVisible(false);
            }
            isIngredientsVisible = false;
        }
        revalidate();
        repaint();
    }

    private void toggleRecipesPanel(ShoppingList shoppingList) {
        if (!isRecipesVisible) {
            showRecipesButton.setText("Hide Recipes");
            JPanel recipesPanel = createRecipesPanel(shoppingList.getRecipes());
            splitPane.setRightComponent(recipesPanel);
            if (isIngredientsVisible) {
                splitPane.setVisible(true);
            } else {
                splitPane.setVisible(true);
                revalidate();
            }
            isRecipesVisible = true;
        } else {
            showRecipesButton.setText("Show Recipes");
            splitPane.setRightComponent(null);
            if (!isIngredientsVisible) {
                splitPane.setVisible(false);
            }
            isRecipesVisible = false;
        }
        revalidate();
        repaint();
    }

    private JPanel createIngredientsPanel(List<Ingredient> ingredients) {
        JPanel ingredientsPanel = new JPanel(new VerticalFlowLayout(5));
        ingredientsPanel.setOpaque(false);
        for (Ingredient ingredient : ingredients) {
            JLabel ingredientLabel = new JLabel(ingredient.toString());
            ingredientLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            ingredientLabel.setForeground(LocalAppSetting.isNightMode() ? Color.PINK : Color.BLACK);
            ingredientsPanel.add(ingredientLabel);
        }
        return ingredientsPanel;
    }

    private JPanel createRecipesPanel(List<Recipe> recipes) {
        JPanel recipesPanel = new JPanel(new VerticalFlowLayout(5));
        recipesPanel.setOpaque(false);

        for (Recipe recipe : recipes) {
            RoundButton recipeButton = new RoundButton(recipe.getName());
            recipeButton.setFont(new Font(defaultFont, Font.PLAIN, 16));
            recipeButton.setForeground(LocalAppSetting.isNightMode() ? neonPinkEmph : black);
            recipeButton.addActionListener(e -> {
                DisplayRecipeDetailViewModel viewModel = new DisplayRecipeDetailViewModel(recipe.getName() + "-view-model");
                DisplayRecipeDetailSearchResultView display = new DisplayRecipeDetailSearchResultView((JFrame) SwingUtilities.getWindowAncestor(this),
                        viewModel, coreFunctionalityController, addNewGroceryListController, addToMyRecipeController);
                displayRecipeDetailController.execute(recipe, viewModel);
                display.setVisible(true);
                display.enableParent();
            });

            recipesPanel.add(recipeButton);
        }
        return recipesPanel;
    }
}
