package use_cases.search_recipe.gui.view;

import entity.LoggedUserData;
import entity.Recipe;
import entity.User;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.nutrition_display.interface_adapter.controller.NutritionDisplayController;
import use_cases.search_recipe.gui.view_component.*;
import use_cases.search_recipe.interface_adapter.controller.SearchRecipeController;
import use_cases.search_recipe.interface_adapter.presenter.SearchRecipePresenter;
import use_cases.search_recipe.interface_adapter.view_model.AdvancedSearchRecipeViewModel;
import use_cases.search_recipe.interface_adapter.view_model.SearchRecipeViewModel;
import use_cases.search_recipe.use_case.output_data.SearchRecipeOutputData;
import use_cases._common.gui_common.abstractions.View;
import use_cases._common.gui_common.view.Sidebar;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases._common.gui_common.view_components.round_component.RoundTextField;
import use_cases._common.gui_common.abstractions.ImageLoader;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;


public class SearchRecipeView extends View {

    private ViewManagerModel viewManagerModel;
    private SearchRecipeViewModel searchRecipeViewModel;
    private SearchRecipeController searchRecipeController;
    private SearchRecipePresenter searchRecipePresenter;


    public final String viewname = "search recipe";

    // Components
    private RoundTextField recipeName;
    private RoundButton searchButton;
    private JPanel inputPanel;
    private JPanel outputPanel;
    private JScrollPane recipeContainer;
    private JLabel userIDLabel; //added this label to display the userid




    public SearchRecipeView(SearchRecipeViewModel searchRecipeViewModel,
                            SearchRecipeController searchRecipeController,
                            NutritionDisplayController nutritionDisplayController,
                            AdvancedSearchRecipeViewModel advancedSearchRecipeViewModel,
                            ViewManagerModel viewManagerModel) {

        this.viewManagerModel = viewManagerModel;

        // Add PropertyChangeListener to corresponding ViewModel
        this.searchRecipeViewModel = searchRecipeViewModel;
        searchRecipeViewModel.addPropertyChangeListener(this);

        // Make connection to Controller
        this.searchRecipeController = searchRecipeController;

        // Set Layout
        this.setLayout(new BorderLayout());

        // Sidebar
        Sidebar sidebar = new Sidebar();

        // MainPanel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Initialize input & output panel
        inputPanel = new JPanel();
        inputPanel.setBackground(claudeWhite);
        inputPanel.setPreferredSize(new Dimension(800,150));
        inputPanel.setMaximumSize(inputPanel.getPreferredSize());
        inputPanel.setBorder(BorderFactory.createLineBorder(claudeWhite, 20));
        inputPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 3, 5));

        outputPanel = new JPanel();
        outputPanel.setBackground(claudeWhite);
        outputPanel.setBorder(BorderFactory.createLineBorder(claudeWhite, 20));
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

        // user label
        userIDLabel = new JLabel(); // creates the user id label

        // Search Text Field
        recipeName = new SearchTextField();
        recipeName.addActionListener( e -> {
            if (e.getSource().equals(recipeName)) {
                String queryString = recipeName.getText();
                if (queryString != null && !queryString.isEmpty()) {
                    searchRecipeController.execute(queryString);
                    SearchRecipeOutputData recipes = searchRecipeViewModel.getRecipeSearchResult();
                    for (Recipe recipe : recipes) {
                        nutritionDisplayController.execute(recipe);
                    }
                }
            }
        });

        // Search Button
        searchButton = new SearchButton("Search");
        searchButton.addActionListener(e -> {
            if (e.getSource().equals(searchButton)) {
                String queryString = recipeName.getText();
                if (queryString != null && !queryString.isEmpty()) {
                    searchRecipeController.execute(queryString);
                    SearchRecipeOutputData recipes = searchRecipeViewModel.getRecipeSearchResult();
                    for (Recipe recipe : recipes) {
                        nutritionDisplayController.execute(recipe);
                    }
                }
            }
        });

        // Output Components
        recipeContainer = new RecipeContainer(outputPanel);

        // Navigate to RecipeToGroceryView
        JButton convertToGroceryButton = new JButton("Convert Recipes to Grocery List");
        convertToGroceryButton.addActionListener(e -> {
            if (e.getSource().equals(convertToGroceryButton)) {
                viewManagerModel.setActiveView("recipe to grocery");
                viewManagerModel.firePropertyChanged();
            }
        });


        // Pack input & output panel
//        inputPanel.add(title);
        inputPanel.add(advancedSearchButton);
        inputPanel.add(recipeName);
        inputPanel.add(searchButton);
        inputPanel.add(convertToGroceryButton);
        inputPanel.add(userIDLabel); //added the userlabel to the panel

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(recipeContainer, BorderLayout.CENTER);

        this.add(sidebar, BorderLayout.WEST);
        this.add(mainPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("loggedInUser".equals(evt.getPropertyName())) {
            User loggedInUser = LoggedUserData.getLoggedInUser();
            updateUserIDLabel(loggedInUser);
        } else if ("search recipe".equals(evt.getPropertyName())) {
            SearchRecipeOutputData response = (SearchRecipeOutputData) evt.getNewValue();
            loadSearchResult(response);
        } else if ("empty result".equals(evt.getPropertyName())) {
            loadEmptyResult();
        } else if ("convert".equals(evt.getPropertyName())) {
            viewManagerModel.setActiveView("recipe to grocery");
            viewManagerModel.firePropertyChanged();
        }

        outputPanel.revalidate();
        outputPanel.repaint();
    }

    public void loadSearchResult (SearchRecipeOutputData response) {

        outputPanel.removeAll();
        for (Recipe recipe : response) {
            JPanel recipePanel = new RecipePanel(recipe);
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

    private void updateUserIDLabel(User user) { //updates the user label
        if (user != null) {
            userIDLabel.setText("Logged in as: " + user.getUserName());
        } else {
            userIDLabel.setText("Not logged in");
        }
    }

    @Override
    public String getViewName() {
        return this.viewname;
    }

    private void actionPer() {

    }


}


