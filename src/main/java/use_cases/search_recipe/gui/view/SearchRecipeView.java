package use_cases.search_recipe.gui.view;

import app.local.LoggedUserData;
import entity.Recipe;
import use_cases._common.gui_common.abstractions.NightModeObject;
import use_cases._common.gui_common.abstractions.ThemeColoredObject;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.add_new_grocery_list.AddNewGroceryListController;
import use_cases.add_to_my_recipe.AddToMyRecipeController;
import use_cases.core_functionality.CoreFunctionalityController;
import use_cases.display_recipe_detail.DisplayRecipeDetailController;
import use_cases.recently_viewed_recipes.RecentlyViewedRecipesController;
import use_cases.search_recipe.gui.view_component.*;
import use_cases.search_recipe.interface_adapter.controller.SearchRecipeController;
import use_cases.search_recipe.interface_adapter.presenter.SearchRecipePresenter;
import use_cases.search_recipe.interface_adapter.view_model.AdvancedSearchRecipeViewModel;
import use_cases.search_recipe.interface_adapter.view_model.SearchRecipeViewModel;
import use_cases.search_recipe.use_case.output_data.SearchRecipeOutputData;
import use_cases._common.gui_common.abstractions.View;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases._common.gui_common.view_components.round_component.RoundTextField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;


public class SearchRecipeView extends View implements ThemeColoredObject, NightModeObject {

    private ViewManagerModel viewManagerModel;
    private SearchRecipeViewModel searchRecipeViewModel;
    private SearchRecipeController searchRecipeController;
    private SearchRecipePresenter searchRecipePresenter;

    private DisplayRecipeDetailController displayDetailController;
    private AddToMyRecipeController addToMyRecipeController;
    private CoreFunctionalityController coreFunctionalityController;
    private RecentlyViewedRecipesController recentlyViewedRecipesController;
    private AddNewGroceryListController addNewGroceryListController;


    public final String viewname;

    // Components
    private RoundTextField recipeName;
    private RoundButton searchButton;

    private JPanel mainPanel;
    private JPanel inputPanel;
    private JPanel outputPanel;
    private JScrollPane recipeContainer;



    public SearchRecipeView(SearchRecipeViewModel searchRecipeViewModel,
                            SearchRecipeController searchRecipeController,
                            DisplayRecipeDetailController displayDetailController,
                            AddToMyRecipeController addToMyRecipeController,
                            RecentlyViewedRecipesController recentlyViewedRecipesController,
                            AddNewGroceryListController addNewGroceryListController,
                            CoreFunctionalityController coreFunctionalityController,
                            AdvancedSearchRecipeViewModel advancedSearchRecipeViewModel,
                            ViewManagerModel viewManagerModel) {

        this.viewManagerModel = viewManagerModel;

        // Add PropertyChangeListener to corresponding ViewModel
        this.searchRecipeViewModel = searchRecipeViewModel;
        searchRecipeViewModel.addPropertyChangeListener(this);
        this.viewname = searchRecipeViewModel.getViewName();

        // Make connection to Controller
        this.searchRecipeController = searchRecipeController;
        this.displayDetailController = displayDetailController;
        this.addToMyRecipeController = addToMyRecipeController;
        this.coreFunctionalityController = coreFunctionalityController;
        this.recentlyViewedRecipesController = recentlyViewedRecipesController;
        this.addNewGroceryListController = addNewGroceryListController;

        observeNight();


        // Set Layout
        this.setLayout(new BorderLayout());

        // MainPanel
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Initialize input & output panel
        inputPanel = new JPanel();
        inputPanel.setPreferredSize(new Dimension(800,100));
        inputPanel.setMaximumSize(inputPanel.getPreferredSize());
        inputPanel.setBorder(new EmptyBorder(20,20,20,20));
        inputPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 3, 5));

        outputPanel = new JPanel();
        outputPanel.setBorder(new EmptyBorder(20,20,20,20));
        outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));


        // Input Components

        // Advanced Search button
        JButton advancedSearchButton = new AdvancedSearchButton("Advanced Search");
        advancedSearchButton.addActionListener(e -> {
            if (e.getSource() == advancedSearchButton) {
                AdvancedSearchView advancedSearchView = new AdvancedSearchView((JFrame) SwingUtilities.getWindowAncestor(this),
                        advancedSearchRecipeViewModel, searchRecipeController);
                advancedSearchView.setVisible(true);
            }
        });

        // Search Text Field
        recipeName = new SearchTextField();
        recipeName.addActionListener( e -> {
            if (e.getSource().equals(recipeName)) {
                getRecipeResult(searchRecipeViewModel, searchRecipeController);
            }
        });

        // Search Button
        searchButton = new SearchButton();
        searchButton.addActionListener(e -> {
            if (e.getSource().equals(searchButton)) {
                getRecipeResult(searchRecipeViewModel, searchRecipeController);
            }
        });

        // Output Components
        recipeContainer = new RecipeContainer(outputPanel);

        // Pack input & output panel
//        inputPanel.add(title);
        inputPanel.add(advancedSearchButton);
        inputPanel.add(recipeName);
        inputPanel.add(searchButton);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(recipeContainer, BorderLayout.CENTER);

        toggleNightMode();

        this.add(mainPanel, BorderLayout.CENTER);
    }

    private void getRecipeResult(SearchRecipeViewModel searchRecipeViewModel, SearchRecipeController searchRecipeController) {
        String queryString = recipeName.getText();
        if (queryString != null && !queryString.isEmpty()) {
            searchRecipeController.execute(queryString);
            SearchRecipeOutputData recipes = searchRecipeViewModel.getRecipeSearchResult();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("search recipe")) {
            SearchRecipeOutputData response = (SearchRecipeOutputData) evt.getNewValue();
            loadSearchResult(response);
        } else if (evt.getPropertyName().equals("empty result")) {
            loadEmptyResult();
        } else if (evt.getPropertyName().equals("api fail")) {
            JOptionPane.showMessageDialog((JFrame) SwingUtilities.getWindowAncestor(this),
                    "Unable to connect to API. Please try again later",
                    "API Error",
                    JOptionPane.ERROR_MESSAGE);
        } else if (evt.getPropertyName().equals("convert")) {
            viewManagerModel.setActiveView("recipe to grocery");
            viewManagerModel.firePropertyChanged();
        } else if (evt.getPropertyName().equals("nightMode")) {
            toggleNightMode();
            this.revalidate();
            this.repaint();
        }
        outputPanel.revalidate();
        outputPanel.repaint();
    }

    public void loadSearchResult (SearchRecipeOutputData response) {

        outputPanel.removeAll();
        for (Recipe recipe : response) {
            JPanel recipePanel = new RecipePanel(recipe, displayDetailController, addToMyRecipeController,
                    coreFunctionalityController, recentlyViewedRecipesController, addNewGroceryListController);
            outputPanel.add(recipePanel);
        }
        SwingUtilities.invokeLater(() -> recipeContainer.getVerticalScrollBar().setValue(0));
    }

    public void loadEmptyResult () {
        outputPanel.removeAll();
        JPanel emptyResultPanel = new JPanel();
        emptyResultPanel.setBackground(claudeWhite);
        JLabel emptyResultLabel = new JLabel("No recipe found...");
        emptyResultLabel.setFont(new Font(defaultFont, Font.PLAIN, 14));
        emptyResultLabel.setForeground(claudeBlackEmph);
        emptyResultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        emptyResultPanel.add(emptyResultLabel);
        outputPanel.add(emptyResultPanel);
    }

    @Override
    public String getViewName() {
        return this.viewname;
    }

    private void actionPer() {

    }


    @Override
    public void setNightMode() {
        mainPanel.setBackground(Color.BLACK);
        inputPanel.setBackground(Color.BLACK);
        outputPanel.setBackground(Color.BLACK);

    }

    @Override
    public void setDayMode() {
        mainPanel.setBackground(claudeWhite);
        inputPanel.setBackground(claudeWhite);
        outputPanel.setBackground(claudeWhite);
    }
}


