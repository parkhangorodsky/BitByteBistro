package use_cases._common.gui_common.view;

import entity.LoggedUserData;
import entity.Recipe;
import use_cases._common.gui_common.abstractions.NightModeObject;
import use_cases._common.gui_common.abstractions.ThemeColoredObject;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
//import use_cases.nutrition_stats.interface_adapter.controller.NutritionStatsController;
import use_cases._common.gui_common.abstractions.View;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

public class HomeView extends View implements ThemeColoredObject, NightModeObject {

    private ViewManagerModel viewManagerModel;
    //private NutritionStatsController nutritionStatsController;

    public final String viewname;

    // Components
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JPanel recentlyViewedPanel;

//    public HomeView(ViewManagerModel viewManagerModel, NutritionStatsController nutritionStatsController, String viewname) {
    public HomeView(ViewManagerModel viewManagerModel) {

        this.viewManagerModel = viewManagerModel;
        //this.nutritionStatsController = nutritionStatsController;
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
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        contentPanel.add(welcomeLabel);

        // Initialize recently viewed panel
        recentlyViewedPanel = new JPanel();
        recentlyViewedPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        recentlyViewedPanel.setLayout(new BoxLayout(recentlyViewedPanel, BoxLayout.Y_AXIS));
        loadRecentlyViewedRecipes();

        // Pack content panel into main panel
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(recentlyViewedPanel, BorderLayout.SOUTH);

        toggleNightMode();

        this.add(mainPanel, BorderLayout.CENTER);
    }

    private void loadRecentlyViewedRecipes() {
//        List<Recipe> recentlyViewedRecipes = LoggedUserData.getLoggedInUser().getRecentlyViewedRecipes();
        List<Recipe> recentlyViewedRecipes = new ArrayList<>();
        recentlyViewedPanel.removeAll();

        // Add title to recently viewed panel
        JLabel recentlyViewedTitle = new JLabel("Recently Viewed Recipes...");
        recentlyViewedTitle.setFont(new Font("Arial", Font.BOLD, 18));
        recentlyViewedTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        recentlyViewedPanel.add(recentlyViewedTitle);

        if (recentlyViewedRecipes.isEmpty()) {
            JLabel noRecipesLabel = new JLabel("No recently viewed recipes");
            noRecipesLabel.setFont(new Font("Arial", Font.ITALIC, 16));
            noRecipesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            recentlyViewedPanel.add(noRecipesLabel);
        }
        else {
            for (Recipe recipe : recentlyViewedRecipes) {
                JPanel recipePanel = new JPanel();
                recipePanel.setLayout(new BorderLayout());
                recipePanel.setBorder(new EmptyBorder(10, 10, 10, 10));

                JLabel recipeNameLabel = new JLabel(recipe.getName());
                recipeNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
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