package use_cases._common.gui_common.view;

import app.local.LoggedUserData;
import entity.Recipe;
import use_cases._common.gui_common.abstractions.NightModeObject;
import use_cases._common.gui_common.abstractions.ThemeColoredObject;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
//import use_cases.nutrition_stats.interface_adapter.controller.NutritionStatsController;
import use_cases._common.gui_common.abstractions.View;
import use_cases.add_new_grocery_list.AddNewGroceryListController;
import use_cases.add_to_my_recipe.AddToMyRecipeController;
import use_cases.core_functionality.CoreFunctionalityController;
import use_cases.display_recipe_detail.DisplayRecipeDetailController;
import use_cases.display_recipe_detail.DisplayRecipeDetailSearchResultView;
import use_cases.display_recipe_detail.DisplayRecipeDetailViewModel;
import use_cases.recently_viewed_recipes.RecentlyViewedRecipesController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeEvent;
import java.util.List;

public class HomeView extends View implements ThemeColoredObject, NightModeObject {

    private ViewManagerModel viewManagerModel;
    //private NutritionStatsController nutritionStatsController;

    public final String viewname;

    // Components
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JPanel recentlyViewedPanel;
    private DisplayRecipeDetailController displayRecipeDetailController;
    private AddToMyRecipeController addToMyRecipeController;
    private CoreFunctionalityController coreFunctionalityController;
    private RecentlyViewedRecipesController recentlyViewedRecipesController;
    private AddNewGroceryListController addNewGroceryListController;

    //    public HomeView(ViewManagerModel viewManagerModel, NutritionStatsController nutritionStatsController, String viewname) {
    public HomeView(ViewManagerModel viewManagerModel, DisplayRecipeDetailController displayRecipeDetailController,
                    AddToMyRecipeController addToMyRecipeController,
                    CoreFunctionalityController coreFunctionalityController,
                    RecentlyViewedRecipesController recentlyViewedRecipesController,
                    AddNewGroceryListController addNewGroceryListController ) {

        this.viewManagerModel = viewManagerModel;
        this.displayRecipeDetailController = displayRecipeDetailController;
        this.addNewGroceryListController = addNewGroceryListController;
        this.coreFunctionalityController = coreFunctionalityController;
        this.recentlyViewedRecipesController = recentlyViewedRecipesController;
        this.addToMyRecipeController = addToMyRecipeController;

        this.viewname = "Home";

        observeNight();

        // Set Layout
        this.setLayout(new BorderLayout());

        // MainPanel
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Initialize content panel
        contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Add any additional components here
        // For example, a welcome label
        JLabel welcomeLabel = new JLabel("Welcome to your BitByteBistro Home Page!");
        welcomeLabel.setFont(new Font(defaultFont, Font.BOLD, 24));
        welcomeLabel.setForeground(claudeBlack);
        contentPanel.add(welcomeLabel);

        // Initialize recently viewed panel
        recentlyViewedPanel = new JPanel();
        recentlyViewedPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        recentlyViewedPanel.setLayout(new BoxLayout(recentlyViewedPanel, BoxLayout.Y_AXIS));
        recentlyViewedPanel.setBackground(claudeWhite);
        loadRecentlyViewedRecipes();

        // Pack content panel into main panel
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(recentlyViewedPanel, BorderLayout.SOUTH);

        toggleNightMode();

        this.add(mainPanel, BorderLayout.CENTER);

        // Add component listener to reload recipes when view is shown
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                loadRecentlyViewedRecipes();
            }
        });
    }

    private void loadRecentlyViewedRecipes() {
        List<Recipe> recentlyViewedRecipes = LoggedUserData.getLoggedInUser().getRecentlyViewedRecipes();
        recentlyViewedPanel.removeAll();

        // Add title to recently viewed panel
        JLabel recentlyViewedTitle = new JLabel("Recently Viewed Recipes...");
        recentlyViewedTitle.setFont(new Font(defaultFont, Font.BOLD, 18));
        recentlyViewedTitle.setForeground(claudeBlack);
        recentlyViewedTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        recentlyViewedPanel.add(recentlyViewedTitle);

        if (recentlyViewedRecipes.isEmpty()) {
            JLabel noRecipesLabel = new JLabel("No recently viewed recipes");
            noRecipesLabel.setFont(new Font(defaultFont, Font.ITALIC, 16));
            noRecipesLabel.setForeground(claudeBlack);
            noRecipesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            recentlyViewedPanel.add(noRecipesLabel);
        }
        else {
            for (Recipe recipe : recentlyViewedRecipes) {
                JPanel recipePanel = new JPanel();
                recipePanel.setLayout(new BorderLayout());
                recipePanel.setBorder(new EmptyBorder(10, 10, 10, 10));

                JLabel recipeNameLabel = new JLabel(recipe.getName());
                recipeNameLabel.setFont(new Font(defaultFont, Font.PLAIN, 16));

                recipeNameLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        recentlyViewedRecipesController.execute(recipe);
                        DisplayRecipeDetailViewModel viewModel = new DisplayRecipeDetailViewModel(recipe.getName() + "-view-model");
                        DisplayRecipeDetailSearchResultView display = new DisplayRecipeDetailSearchResultView((JFrame) SwingUtilities.getWindowAncestor(recipeNameLabel),
                                viewModel, coreFunctionalityController, addNewGroceryListController, addToMyRecipeController);
                        displayRecipeDetailController.execute(recipe, viewModel);
                        display.setVisible(true);
                        display.enableParent();
                    }

                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        recipeNameLabel.setForeground(claudeBlackEmph); // Change color on hover
                    }

                    @Override
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        recipeNameLabel.setForeground(claudeBlack); // Change back to original color
                    }
                });

                recipePanel.add(recipeNameLabel, BorderLayout.CENTER);

                recentlyViewedPanel.add(recipePanel);
            }
        }
        recentlyViewedPanel.revalidate();
        recentlyViewedPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("nightMode")) {
            toggleNightMode();
            this.revalidate();
            this.repaint();
        }
    }

    @Override
    public String getViewName() {
        return this.viewname;
    }

    @Override
    public void setNightMode() {
        mainPanel.setBackground(Color.BLACK);
        contentPanel.setBackground(Color.BLACK);
    }

    @Override
    public void setDayMode() {
        mainPanel.setBackground(claudeWhite);
        contentPanel.setBackground(claudeWhite);
    }
}
