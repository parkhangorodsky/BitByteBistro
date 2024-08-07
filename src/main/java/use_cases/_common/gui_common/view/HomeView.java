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
import use_cases.nutrition_stats.interface_adapter.view_model.NutritionStatsViewModel;
import use_cases.nutrition_stats.use_case.output_data.NutritionStatsOutputData;

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
import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeView extends View implements ThemeColoredObject, NightModeObject {

    private ViewManagerModel viewManagerModel;
    private NutritionStatsController nutritionStatsController;

    public final String viewname;

    // Components
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JPanel nutritionStatsPanel;
    private JPanel nutritionPanel;
    private JPanel recentlyViewedPanel;
    private DisplayRecipeDetailController displayRecipeDetailController;
    private AddToMyRecipeController addToMyRecipeController;
    private CoreFunctionalityController coreFunctionalityController;
    private RecentlyViewedRecipesController recentlyViewedRecipesController;
    private AddNewGroceryListController addNewGroceryListController;
    private List<ShoppingList> userGroceryLists;
    NutritionStatsViewModel nutritionStatsViewModel;

    // user variable
    private User user = LoggedUserData.getLoggedInUser();

    public HomeView(ViewManagerModel viewManagerModel,
                    NutritionStatsController nutritionStatsController,
                    NutritionStatsViewModel nutritionStatsViewModel,
                    AddToMyRecipeController addToMyRecipeController,
                    CoreFunctionalityController coreFunctionalityController,
                    RecentlyViewedRecipesController recentlyViewedRecipesController,
                    AddNewGroceryListController addNewGroceryListController,
                    DisplayRecipeDetailController displayRecipeDetailController) {
        this.viewManagerModel = viewManagerModel;
        this.displayRecipeDetailController = displayRecipeDetailController;
        this.addNewGroceryListController = addNewGroceryListController;
        this.coreFunctionalityController = coreFunctionalityController;
        this.recentlyViewedRecipesController = recentlyViewedRecipesController;
        this.addToMyRecipeController = addToMyRecipeController;

        this.viewname = "Home";
        this.nutritionStatsController = nutritionStatsController;
        this.nutritionStatsViewModel = nutritionStatsViewModel;

        // Register HomeView as a PropertyChangeListener
        nutritionStatsViewModel.addPropertyChangeListener(this);

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
        nutritionPanel = new JPanel();

        displayNutritionStats();
        System.out.println(nutritionStatsPanel.getComponentCount());
    }

    private void initializeRecentlyViewedPanel() {
        recentlyViewedPanel = new JPanel();
        recentlyViewedPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        recentlyViewedPanel.setLayout(new BoxLayout(recentlyViewedPanel, BoxLayout.Y_AXIS));
        recentlyViewedPanel.setBackground(claudeWhite);
        loadRecentlyViewedRecipes();
    }

    private void displayNutritionStats() {
        HashMap<String, ShoppingList> userGroceryLists = user.getShoppingLists();

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
                // Convert values to a list
                List<ShoppingList> groceryLists = new ArrayList<>(userGroceryLists.values());
                showGroceryListDropdown(selectGroceryListButton, groceryLists);
            }
        });

        nutritionStatsPanel.add(selectGroceryListButton);
        nutritionStatsPanel.revalidate();
        nutritionStatsPanel.repaint();
    }

    private void showGroceryListDropdown(JButton selectGroceryListButton, Collection<ShoppingList> userGroceryLists) {
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
        nutritionStatsPanel.remove(nutritionPanel);

        nutritionPanel = new JPanel();
        nutritionPanel.setLayout(new BoxLayout(nutritionPanel, BoxLayout.Y_AXIS)); // Set layout to vertical BoxLayout

        for (Nutrition nutrition : outputData.getNutrition()) {
            // Create a panel to hold the label and progress bar
            System.out.println(nutrition);
            JPanel nutritionBarPanel = new JPanel();
            nutritionBarPanel.setLayout(new BorderLayout());
            nutritionBarPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
            nutritionBarPanel.setBackground(claudeWhite);

            JLabel nutritionLabel = new JLabel(nutrition.getLabel() + ": " + nutrition.getQuantity() + " " + nutrition.getUnit());
            nutritionLabel.setFont(new Font(defaultFont, Font.PLAIN, 14));
            nutritionBarPanel.add(nutritionLabel, BorderLayout.WEST);

            JProgressBar nutritionBar = new JProgressBar();
            nutritionBar.setMinimum(0);
            nutritionBar.setMaximum(Math.round(nutrition.getQuantity() + 10));
            nutritionBar.setValue(Math.round(nutrition.getQuantity()));
            nutritionBar.setStringPainted(true);
            nutritionBarPanel.add(nutritionBar, BorderLayout.CENTER);

            nutritionPanel.add(nutritionBarPanel);
            System.out.println(nutritionStatsPanel.getComponentCount());
        }
        nutritionStatsPanel.add(nutritionPanel);

        nutritionStatsPanel.revalidate();
        nutritionStatsPanel.repaint();
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

                recipeNameLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        DisplayRecipeDetailViewModel viewModel = new DisplayRecipeDetailViewModel(recipe.getName() + "-view-model");
                        DisplayRecipeDetailSearchResultView display = new DisplayRecipeDetailSearchResultView((JFrame) SwingUtilities.getWindowAncestor(recipeNameLabel),
                                viewModel, coreFunctionalityController, addNewGroceryListController, addToMyRecipeController);
                        displayRecipeDetailController.execute(recipe, viewModel);
                        recentlyViewedRecipesController.execute(recipe);
                        loadRecentlyViewedRecipes();
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