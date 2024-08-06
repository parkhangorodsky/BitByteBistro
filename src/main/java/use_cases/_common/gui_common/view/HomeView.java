package use_cases._common.gui_common.view;

import app.local.LoggedUserData;
import entity.Nutrition;
import entity.Recipe;
import entity.ShoppingList;
import entity.User;
import use_cases._common.gui_common.abstractions.NightModeObject;
import use_cases._common.gui_common.abstractions.ThemeColoredObject;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.nutrition_stats.interface_adapter.controller.NutritionStatsController;
import use_cases._common.gui_common.abstractions.View;
import use_cases.nutrition_stats.use_case.output_data.NutritionStatsOutputData;

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
    private NutritionStatsController nutritionStatsController;

    public final String viewname;

    // Components
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JPanel nutritionStatsPanel;
    private JPanel recentlyViewedPanel;
    private List<ShoppingList> userGroceryLists;

    // user variable
    private User user = LoggedUserData.getLoggedInUser();

    public HomeView(ViewManagerModel viewManagerModel, NutritionStatsController nutritionStatsController) {
        this.viewManagerModel = viewManagerModel;
        this.viewname = "Home";
        this.nutritionStatsController = nutritionStatsController;

        observeNight();

        initializeComponents();
        configureMainPanel();
        toggleNightMode();
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        initializeContentPanel();
        initializeNutritionStatsPanel();
        initializeRecentlyViewedPanel();
    }

    private void configureMainPanel() {
        mainPanel.add(contentPanel, BorderLayout.NORTH);
        mainPanel.add(nutritionStatsPanel);
        mainPanel.add(recentlyViewedPanel, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.CENTER);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                displayNutritionStats();
                loadRecentlyViewedRecipes();
            }
        });
    }

    private void initializeContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel welcomeLabel = new JLabel("Welcome to your BitByteBistro Home Page!");
        welcomeLabel.setFont(new Font(defaultFont, Font.BOLD, 24));
        welcomeLabel.setForeground(claudeBlack);
        contentPanel.add(welcomeLabel);
    }

    private void initializeNutritionStatsPanel() {
        nutritionStatsPanel = new JPanel();
        nutritionStatsPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        nutritionStatsPanel.setLayout(new BoxLayout(nutritionStatsPanel, BoxLayout.Y_AXIS));
        nutritionStatsPanel.setBackground(claudeWhite);
        displayNutritionStats();
    }

    private void initializeRecentlyViewedPanel() {
        recentlyViewedPanel = new JPanel();
        recentlyViewedPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        recentlyViewedPanel.setLayout(new BoxLayout(recentlyViewedPanel, BoxLayout.Y_AXIS));
        recentlyViewedPanel.setBackground(claudeWhite);
        loadRecentlyViewedRecipes();
    }

    private void displayNutritionStats() {
        List<ShoppingList> userGroceryLists = user.getShoppingLists();

        nutritionStatsPanel.removeAll();

        JLabel nutritionStatsTitle = new JLabel("Nutrition Stats of Your Grocery Lists...");
        nutritionStatsTitle.setFont(new Font(defaultFont, Font.BOLD, 18));
        nutritionStatsTitle.setForeground(claudeBlack);
        nutritionStatsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        nutritionStatsPanel.add(nutritionStatsTitle);

        JButton selectGroceryListButton = new JButton("Select Grocery List...");
        selectGroceryListButton.addActionListener(e -> {
            if (userGroceryLists.isEmpty()) {
                JOptionPane.showMessageDialog(this, "You don't have any grocery lists", "No Grocery Lists", JOptionPane.INFORMATION_MESSAGE);
            } else {
                showGroceryListDropdown(selectGroceryListButton, userGroceryLists);
            }
        });
        nutritionStatsPanel.add(selectGroceryListButton);
        nutritionStatsPanel.revalidate();
        nutritionStatsPanel.repaint();
    }

    private void showGroceryListDropdown(JButton selectGroceryListButton, List<ShoppingList> userGroceryLists) {
        JPopupMenu selectGroceryList = new JPopupMenu();

        for (ShoppingList list : userGroceryLists) {
                JMenuItem groceryListItem = new JMenuItem(list.getShoppingListName());
                groceryListItem.addActionListener(e -> selectGroceryList(list));
                selectGroceryList.add(groceryListItem);
        }

        // Show popup menu below the button
        selectGroceryList.show(selectGroceryListButton, 0, selectGroceryListButton.getHeight());
    }

    private void selectGroceryList(ShoppingList list) {
        if (list.getListItems().isEmpty()) {
            JOptionPane.showMessageDialog(this, "This grocery list is empty! Add items to use this feature.", "Empty Grocery List", JOptionPane.INFORMATION_MESSAGE);
        } else {
            nutritionStatsController.execute(list);
        }
    }

    private void loadNutritionStats(NutritionStatsOutputData outputData) {
        System.out.println("Loading Nutrition Stats");
        for (Nutrition nutrition : outputData.getNutrition()) {
            // Create a panel to hold the label and progress bar
            JPanel nutritionPanel = new JPanel();
            nutritionPanel.setLayout(new BorderLayout());
            nutritionPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
            nutritionPanel.setBackground(claudeWhite);

            JLabel nutritionLabel = new JLabel(nutrition.getLabel() + ": " + nutrition.getQuantity() + " " + nutrition.getUnit());
            nutritionLabel.setFont(new Font(defaultFont, Font.PLAIN, 14));
            nutritionPanel.add(nutritionLabel, BorderLayout.WEST);

            JProgressBar nutritionBar = new JProgressBar();
            nutritionBar.setMinimum(0);
            nutritionBar.setMaximum(Math.round(nutrition.getQuantity() + 10));
            nutritionBar.setValue(Math.round(nutrition.getQuantity()));
            nutritionBar.setStringPainted(true);
            nutritionPanel.add(nutritionBar, BorderLayout.CENTER);

            nutritionStatsPanel.add(nutritionPanel);
        }
    }

    private void loadRecentlyViewedRecipes() {
        List<Recipe> recentlyViewedRecipes = user.getRecentlyViewedRecipes();
        recentlyViewedPanel.removeAll();

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
        } else {
            for (Recipe recipe : recentlyViewedRecipes) {
                JPanel recipePanel = new JPanel();
                recipePanel.setLayout(new BorderLayout());
                recipePanel.setBorder(new EmptyBorder(10, 10, 10, 10));

                JLabel recipeNameLabel = new JLabel(recipe.getName());
                recipeNameLabel.setFont(new Font(defaultFont, Font.PLAIN, 16));
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
        if (evt.getPropertyName().equals("nutrition info")) {
            System.out.println("Property Name " + evt.getPropertyName());
            NutritionStatsOutputData nutritionStats = (NutritionStatsOutputData) evt.getNewValue();
            loadNutritionStats(nutritionStats);
        } else if (evt.getPropertyName().equals("nightMode")) {
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