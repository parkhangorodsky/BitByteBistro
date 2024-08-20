package use_cases.core_functionality;

import app.local.LocalAppSetting;
import app.local.LoggedUserData;
import entity.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import org.jetbrains.annotations.NotNull;
import use_cases._common.gui_common.abstractions.NightModeObject;
import use_cases._common.gui_common.abstractions.ThemeColoredObject;
import use_cases._common.gui_common.abstractions.View;
import use_cases._common.gui_common.view_components.layouts.VerticalFlowLayout;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases._common.gui_common.view_components.round_component.RoundPanel;
import use_cases.add_new_grocery_list.AddNewGroceryListController;
import use_cases.core_functionality.CoreFunctionalityInteractor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;

import static use_cases._common.gui_common.abstractions.ThemeColoredObject.*;


public class GroceryListItem {

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

    private static void toggleGroceryListVisibility(JPanel shoppingListItem, JButton toggleButton) {
        Component itemsContainer = shoppingListItem.getComponent(1);
        itemsContainer.setVisible(!itemsContainer.isVisible());
        toggleButton.setText(itemsContainer.isVisible() ? "∧" : "∨");
        shoppingListItem.revalidate();
        shoppingListItem.repaint();
    }

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

