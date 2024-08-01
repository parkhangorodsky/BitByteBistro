package use_cases.search_recipe.gui.view_component;

import entity.Recipe;
import use_cases._common.gui_common.abstractions.NightModeObject;
import use_cases._common.gui_common.abstractions.ThemeColoredObject;
import use_cases._common.gui_common.abstractions.ViewComponent;
import use_cases._common.gui_common.view_components.IngredientPanel;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases._common.gui_common.view_components.round_component.RoundPanel;
import use_cases.add_to_my_recipe.AddToMyRecipeController;
import use_cases.display_recipe_detail.DisplayRecipeDetailController;
import use_cases.display_recipe_detail.DisplayRecipeDetailSearchResultView;
import use_cases.display_recipe_detail.DisplayRecipeDetailViewModel;
import use_cases.search_recipe.interface_adapter.view_model.RecipeModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;

public class RecipePanel extends ViewComponent implements ThemeColoredObject, NightModeObject {

    private RecipeModel recipeModel;

    JPanel leftPanel;
    JPanel rightPanel;

    RoundPanel topPanel;
    RoundPanel nutritionPanel;
    RoundPanel extraInfoPanel;
    RoundPanel ingredientPanel;
    JLabel recipeNameLabel;

    RoundButton detailButton;

    public RecipePanel(Recipe recipe, DisplayRecipeDetailController displayRecipeDetailController, AddToMyRecipeController addToMyRecipeController) {

        this.setLayout(new BorderLayout(2, 3));
        this.setBorder(new EmptyBorder(30, 20, 20, 20));

        // Layout Panel
        topPanel = new RoundPanel();
        topPanel.setPreferredSize(new Dimension(100, 40));
        topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 3, 5));
        topPanel.setBorder(new EmptyBorder(5, 15, 5, 15));

        leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout(3, 4));
        leftPanel.setOpaque(false);

        rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout(3, 4));
        rightPanel.setOpaque(false);

        // Components
        recipeNameLabel = new JLabel(recipe.getName());
        recipeNameLabel.setFont(new Font(defaultFont, Font.PLAIN, 24));
        recipeNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        RoundPanel imagePanel = new RoundPanel();
        BufferedImage image = recipe.getImage();
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon(image));
        imagePanel.add(imageLabel);

        nutritionPanel = new RoundPanel();
        nutritionPanel.setPreferredSize(new Dimension(30, 40));

        ingredientPanel = new IngredientPanel(recipe.getIngredientList());

        extraInfoPanel = new RoundPanel();
        extraInfoPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

        detailButton = new RoundButton("Detail");
        detailButton.addActionListener(e -> {
            DisplayRecipeDetailViewModel viewModel = new DisplayRecipeDetailViewModel(recipe.getName() + "-view-model");
            DisplayRecipeDetailSearchResultView display = new DisplayRecipeDetailSearchResultView((JFrame) SwingUtilities.getWindowAncestor(this), viewModel, addToMyRecipeController);
            displayRecipeDetailController.execute(recipe, viewModel);
            display.setVisible(true);
            display.enableParent();
        });

        extraInfoPanel.add(detailButton);

        topPanel.add(recipeNameLabel);

        leftPanel.add(imageLabel, BorderLayout.NORTH);
        leftPanel.add(nutritionPanel, BorderLayout.CENTER);

        rightPanel.add(ingredientPanel, BorderLayout.CENTER);
        rightPanel.add(extraInfoPanel, BorderLayout.SOUTH);

        toggleNightMode();

        this.add(topPanel, BorderLayout.NORTH);
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.CENTER);
    }

    @Override
    public void setNightMode() {
        this.setBackground(black);

        topPanel.setBackground(black);
        topPanel.setBorderColor(black);
        recipeNameLabel.setForeground(neonPurple);

        nutritionPanel.setBackground(black);
        nutritionPanel.setBorderColor(neonPink);

        ingredientPanel.setBackground(black);
        ingredientPanel.setBorderColor(neonPink);

        extraInfoPanel.setBackground(black);
        extraInfoPanel.setBorderColor(lightPurple);

        detailButton.setHoverColor(black, darkPurple, white, white);
        detailButton.setPressedColor(lightPurple);
        detailButton.setBorderColor(neonPurple);
    }

    @Override
    public void setDayMode() {
        this.setBackground(claudeWhite);

        topPanel.setBackground(claudeWhite);
        topPanel.setBorderColor(claudeWhite);
        recipeNameLabel.setForeground(claudeBlack);

        nutritionPanel.setBackground(claudeWhiteEmph);
        nutritionPanel.setBorderColor(claudeWhite);

        ingredientPanel.setBackground(claudeWhiteEmph);
        ingredientPanel.setBorderColor(claudeWhite);

        extraInfoPanel.setBackground(claudeOrange);
        extraInfoPanel.setBorderColor(claudeWhite);

        detailButton.setHoverColor(claudeOrange, claudeWhite, claudeWhite, claudeOrange);
        detailButton.setPressedColor(claudeWhite);
        detailButton.setBorderColor(claudeWhite);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("nightMode")) {
            toggleNightMode();
            this.revalidate();
            this.repaint();
        }
    }
}
