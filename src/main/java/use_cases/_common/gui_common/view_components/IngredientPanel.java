package use_cases._common.gui_common.view_components;

import entity.Ingredient;
import use_cases._common.gui_common.abstractions.ThemeColoredObject;
import use_cases._common.gui_common.view_components.layouts.VerticalFlowLayout;
import use_cases._common.gui_common.view_components.round_component.RoundPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class IngredientPanel extends RoundPanel implements ThemeColoredObject {

    public IngredientPanel(List<Ingredient> ingredients, Color color) {
        super();

        setLayout(new VerticalFlowLayout(5));
        setBorder(new EmptyBorder(30, 15, 15, 15));
        setBackground(claudeWhiteEmph);
        setAlignmentX(LEFT_ALIGNMENT);
        setAlignmentY(TOP_ALIGNMENT);

        JLabel label = new JLabel("Ingredients");
        label.setFont(new Font(secondaryFont, Font.PLAIN, 15));
        label.setHorizontalAlignment(JLabel.RIGHT);
        label.setBorder(new EmptyBorder(0, 0, 14, 10));
        add(label);

        for (Ingredient ingredient : ingredients) {
            JPanel singleIngredientPanel = new JPanel();
            singleIngredientPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));
            singleIngredientPanel.setBackground(color);
            singleIngredientPanel.setOpaque(true);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;

            // Capitalize name
            String ingredientName = ingredient.getIngredientName();
            ingredientName = ingredientName.substring(0, 1).toUpperCase() + ingredientName.substring(1);

            JLabel name = new JLabel(ingredientName);
            name.setFont(new Font(defaultFont, Font.PLAIN, 12));
            JLabel quantity = new JLabel(String.valueOf(ingredient.getQuantity()));
            name.setFont(new Font(defaultFont, Font.PLAIN, 12));
            JLabel unit = new JLabel(String.valueOf(ingredient.getQuantityUnit()));
            unit.setFont(new Font(defaultFont, Font.PLAIN, 12));

            singleIngredientPanel.add(name);
            singleIngredientPanel.add(quantity);
            singleIngredientPanel.add(unit);

            this.add(singleIngredientPanel);
        }
    }
}
