package use_cases.core_functionality.view_components;

import app.local.LocalAppSetting;
import entity.*;

import java.awt.*;
import org.jetbrains.annotations.NotNull;
import use_cases._common.gui_common.view_components.layouts.VerticalFlowLayout;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases._common.gui_common.view_components.round_component.RoundPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.List;

import static use_cases._common.gui_common.abstractions.ThemeColoredObject.*;

/**
 * The `GroceryListItem` class provides utility methods for creating and managing the graphical representation of a shopping list item.
 * This includes creating a panel with the shopping list name, a button to expand or collapse the list, and a section to display ingredients.
 */
public class GroceryListItem {

    /**
     * Creates a JPanel representing a shopping list item, including the list name, an expand/collapse button,
     * and a container for the ingredients.
     *
     * @param shoppingList The `ShoppingList` object to be displayed.
     * @return A `JPanel` containing the visual representation of the shopping list item.
     */
    public static JPanel createShoppingListItem(ShoppingList shoppingList) {
        RoundPanel shoppingListItem = new RoundPanel();
        shoppingListItem.setLayout(new BorderLayout());
        shoppingListItem.setBorder(new EmptyBorder(10, 10, 10, 10));
        shoppingListItem.setBackground(LocalAppSetting.isNightMode() ? neonPurpleEmph : claudewhiteBright);
        shoppingListItem.setBorderColor(LocalAppSetting.isNightMode() ? neonPurple : claudeWhiteEmph);

        // Shopping List Name
        JPanel shoppingListNamePanel = new JPanel(new BorderLayout());
        shoppingListNamePanel.setOpaque(false);
        JLabel shoppingListNameLabel = new JLabel(shoppingList.getShoppingListName());
        shoppingListNameLabel.setFont(new Font(defaultFont, Font.PLAIN, 20));
        shoppingListNameLabel.setForeground(LocalAppSetting.isNightMode() ? neonPinkEmph : black);
        shoppingListNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        shoppingListNameLabel.setVerticalTextPosition(SwingConstants.CENTER);
        shoppingListNamePanel.add(shoppingListNameLabel, BorderLayout.CENTER);

        // Expand/Collapse Button
        RoundButton showRecipeButton = getRoundButton(shoppingListItem);

        shoppingListNamePanel.add(showRecipeButton, BorderLayout.EAST);
        shoppingListItem.add(shoppingListNamePanel, BorderLayout.NORTH);

        // Add the items container
        JPanel itemsContainer = createIngredientsPanel(shoppingList.getListItems());
        itemsContainer.setOpaque(false);
        itemsContainer.setVisible(false);
        shoppingListItem.add(itemsContainer, BorderLayout.CENTER);

        return shoppingListItem;
    }

    /**
     * Creates a `RoundButton` for expanding or collapsing the visibility of the shopping list items.
     *
     * @param shoppingListItem The `RoundPanel` containing the shopping list item.
     * @return A `RoundButton` configured for expanding or collapsing the item.
     */
    private static @NotNull RoundButton getRoundButton(RoundPanel shoppingListItem) {
        RoundButton showRecipeButton = new RoundButton("∨");
        showRecipeButton.setHorizontalAlignment(SwingConstants.CENTER);
        showRecipeButton.setVerticalAlignment(SwingConstants.CENTER);
        showRecipeButton.setPreferredSize(new Dimension(30, 30));
        showRecipeButton.setBorderColor(LocalAppSetting.isNightMode() ? neonPinkEmph : claudeBlack);

        if (LocalAppSetting.isNightMode()) {
            showRecipeButton.setHoverColor(darkPurple, neonPinkEmph, white, white);
        } else {
            showRecipeButton.setHoverColor(claudeBlack, sunflower, claudeWhite, claudewhiteBright);
        }

        showRecipeButton.addActionListener(e -> toggleGroceryListVisibility(shoppingListItem, showRecipeButton));
        return showRecipeButton;
    }

    /**
     * Toggles the visibility of the ingredients panel and updates the button text to indicate the current state.
     *
     * @param shoppingListItem The `JPanel` containing the shopping list item and ingredients.
     * @param toggleButton The button used to expand or collapse the visibility.
     */
    private static void toggleGroceryListVisibility(JPanel shoppingListItem, JButton toggleButton) {
        Component itemsContainer = shoppingListItem.getComponent(1);
        itemsContainer.setVisible(!itemsContainer.isVisible());
        toggleButton.setText(itemsContainer.isVisible() ? "∧" : "∨");
        shoppingListItem.revalidate();
        shoppingListItem.repaint();
    }

    /**
     * Creates a `JPanel` displaying the list of ingredients in the shopping list.
     *
     * @param ingredients The list of `Ingredient` objects to be displayed.
     * @return A `JPanel` containing labels for each ingredient in the list.
     */
    private static JPanel createIngredientsPanel(List<Ingredient> ingredients) {
        JPanel ingredientsPanel = new JPanel(new VerticalFlowLayout(10));
        ingredientsPanel.setOpaque(false);
        for (Ingredient ingredient : ingredients) {
            JLabel ingredientLabel = new JLabel(ingredient.toString());
            ingredientLabel.setFont(new Font(defaultFont, Font.PLAIN, 18));
            ingredientLabel.setForeground(LocalAppSetting.isNightMode() ? neonPinkEmph : black);
            ingredientsPanel.add(ingredientLabel);
        }
        return ingredientsPanel;
    }
}
