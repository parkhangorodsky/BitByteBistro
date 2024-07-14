package use_cases.search_recipe.gui.view_component;

import entity.Recipe;
import use_cases._common.gui_common.abstractions.ImageLoader;
import use_cases._common.gui_common.abstractions.ThemeColoredObject;
import use_cases._common.gui_common.abstractions.ViewComponent;
import use_cases._common.gui_common.view_components.IngredientPanel;
import use_cases._common.gui_common.view_components.round_component.RoundPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RecipePanel extends ViewComponent implements ImageLoader, ThemeColoredObject {

    public RecipePanel(Recipe recipe) {
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
        JLabel recipeNameLabel = new JLabel(recipe.getName());
        recipeNameLabel.setFont(new Font(defaultFont, Font.PLAIN, 24));
        recipeNameLabel.setForeground(claudeBlack);
        recipeNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        JPanel imagePanel = new RoundPanel();
        ImageIcon image = this.loadRoundImage(recipe.getImage());
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(image);
        imagePanel.add(imageLabel);

        JPanel nutritionPanel = new RoundPanel();
        nutritionPanel.setBackground(claudeWhiteEmph);
        nutritionPanel.setPreferredSize(new Dimension(30, 40));

        JPanel ingredientPanel = new IngredientPanel(recipe.getIngredientList(), claudeWhiteEmph);
        ingredientPanel.setBackground(claudeWhiteEmph);

        JPanel extraInfoPanel = new RoundPanel();
        extraInfoPanel.setPreferredSize(new Dimension(100, 30));
        extraInfoPanel.setBackground(claudeOrange);

        topPanel.add(recipeNameLabel);

        leftPanel.add(imageLabel, BorderLayout.NORTH);
        leftPanel.add(nutritionPanel, BorderLayout.CENTER);

        rightPanel.add(ingredientPanel, BorderLayout.CENTER);
        rightPanel.add(extraInfoPanel, BorderLayout.SOUTH);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.CENTER);
    }
}
