package use_cases.recipe_to_grocery.gui.view_component;

import entity.Recipe;
import entity.ShoppingList;
import use_cases._common.gui_common.abstractions.ThemeColoredObject;
import use_cases._common.gui_common.abstractions.ViewComponent;
import use_cases._common.gui_common.view_components.IngredientPanel;
import use_cases._common.gui_common.view_components.round_component.RoundPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ShoppingListPanel extends ViewComponent implements ThemeColoredObject {
    public ShoppingListPanel(ShoppingList shoppingList) {
        this.setBackground(claudeWhite);
        this.setLayout(new BorderLayout(2, 3));
        this.setBorder(new EmptyBorder(30, 20, 20, 20));

        // Layout Panel
        JPanel topPanel = new RoundPanel();
        topPanel.setPreferredSize(new Dimension(100, 40));
        topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 3, 5));
        topPanel.setBorder(new EmptyBorder(5, 15, 5, 15));
        topPanel.setBackground(claudeWhite);

        JPanel leftPanel = new RoundPanel();
        leftPanel.setLayout(new BorderLayout(3, 4));

        JPanel rightPanel = new RoundPanel();
        rightPanel.setLayout(new BorderLayout(3, 4));

        // Components
        JLabel shoppingListNameLabel = new JLabel(shoppingList.getShoppingListName());
        shoppingListNameLabel.setFont(new Font(defaultFont, Font.PLAIN, 24));
        shoppingListNameLabel.setForeground(claudeBlack);
        shoppingListNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        JPanel ingredientPanel = new IngredientPanel(shoppingList.getListItems(), claudeWhiteEmph);
        ingredientPanel.setBackground(claudeWhiteEmph);

        topPanel.add(shoppingListNameLabel);
        rightPanel.add(ingredientPanel, BorderLayout.CENTER);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.CENTER);

    }
}
