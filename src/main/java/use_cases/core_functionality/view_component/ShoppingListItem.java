package use_cases.core_functionality.view_component;

import app.local.LocalAppSetting;
import entity.Ingredient;
import entity.ShoppingList;
import use_cases._common.gui_common.view_components.round_component.RoundButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;


public class ShoppingListItem extends JPanel {
    public ShoppingListItem(ShoppingList shoppingList) {
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

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setOpaque(false);
        RoundButton showRecipeButton = new RoundButton("∨");
        showRecipeButton.setHorizontalAlignment(SwingConstants.CENTER);
        showRecipeButton.setVerticalAlignment(SwingConstants.CENTER);
        showRecipeButton.setPreferredSize(new Dimension(30, 30));
        showRecipeButton.setBorderColor(LocalAppSetting.isNightMode() ? Color.PINK : Color.BLACK);

        if (LocalAppSetting.isNightMode()) {
            showRecipeButton.setHoverColor(Color.DARK_GRAY, Color.PINK, Color.WHITE, Color.WHITE);
        } else {
            showRecipeButton.setHoverColor(Color.BLACK, Color.YELLOW, Color.WHITE, Color.LIGHT_GRAY);
        }

        showRecipeButton.addActionListener(e -> toggleIngredientsPanel(showRecipeButton, shoppingList));
        buttonPanel.add(showRecipeButton, BorderLayout.SOUTH);

        add(buttonPanel, BorderLayout.EAST);
        add(shoppingListNamePanel, BorderLayout.WEST);
    }

    private void toggleIngredientsPanel(RoundButton showRecipeButton, ShoppingList shoppingList) {
        if (showRecipeButton.getText().equals("∨")) {
            showRecipeButton.setText("∧");
            JPanel ingredientsPanel = createIngredientsPanel(shoppingList.getListItems());
            add(ingredientsPanel, BorderLayout.SOUTH);
        } else {
            showRecipeButton.setText("∨");
            remove(2); // Remove ingredients panel
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
}
