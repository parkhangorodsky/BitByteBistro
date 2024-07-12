package view;

import entity.Recipe;
import interface_adapter.controller.SearchRecipeController;
import interface_adapter.presenter.SearchRecipePresenter;
import interface_adapter.view_model.AdvancedSearchRecipeViewModel;
import interface_adapter.view_model.SearchRecipeViewModel;
import use_case.output_data.SearchRecipeOutputData;
import view.view_components.*;
import view.view_components.interfaces.ImageLoader;
import view.view_components.round_component.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;


public class SearchRecipeView extends JPanel implements View, ImageLoader {

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



    public SearchRecipeView(SearchRecipeViewModel searchRecipeViewModel,
                            SearchRecipeController searchRecipeController,
                            AdvancedSearchRecipeViewModel advancedSearchRecipeViewModel) {

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
        inputPanel.setPreferredSize(new Dimension(800,80));
        inputPanel.setMaximumSize(inputPanel.getPreferredSize());
        inputPanel.setBorder(BorderFactory.createLineBorder(claudeWhite, 20));
        inputPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 3, 5));

        outputPanel = new JPanel();
        outputPanel.setBackground(claudeWhite);
        outputPanel.setBorder(BorderFactory.createLineBorder(claudeWhite, 20));
        outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));


        // Input Components

        // Advanced Search button
        String text = "Advanced Search";
        RoundButton advancedSearchButton = new RoundButton(text);
        advancedSearchButton.setPreferredSize(new Dimension(140, 40));
        advancedSearchButton.setHoverColor(claudeWhite, claudeWhite, claudeWhiteEmph, claudeBlackEmph);
        advancedSearchButton.setBorderColor(getBackground());
        advancedSearchButton.setPressedColor(getBackground());
        advancedSearchButton.setFont(new Font(defaultFont, Font.PLAIN, 12));
        advancedSearchButton.addActionListener(e -> {
            if (e.getSource() == advancedSearchButton) {
                AdvancedSearchView advancedSearchView = new AdvancedSearchView((JFrame) SwingUtilities.getWindowAncestor(this),
                        advancedSearchRecipeViewModel, searchRecipeController);
                advancedSearchView.setVisible(true);
                System.out.println("HI");
            }
        });


        // Search Box
        recipeName = new RoundTextField();
        recipeName.setPreferredSize(new Dimension(400, 40));
        recipeName.setPlaceholder("Type in the name of the recipe");
        recipeName.setBackground(claudeWhite);
        recipeName.setForeground(claudeBlack);
        recipeName.setBorder(new LineBorder(claudeWhiteEmph, 1));
        recipeName.setMargin(new Insets(5, 15, 5, 15));
        recipeName.setFont(new Font(secondaryFont, Font.PLAIN, 14));
        recipeName.setMaximumSize(recipeName.getPreferredSize());
        recipeName.addActionListener( e -> {
            if (e.getSource().equals(recipeName)) {
                String queryString = recipeName.getText();
                if (queryString != null && !queryString.isEmpty()) {
                    searchRecipeController.execute(queryString);
                }
            }
        });

        // Search Button
        searchButton = new RoundButton("Search");
        searchButton.setPreferredSize(new Dimension(80, 40));
        searchButton.setHoverColor(claudeWhite, claudeWhiteEmph, claudeBlack, claudeBlack);
        searchButton.setForegroundColor(claudeBlack);
        searchButton.setFont(new Font(defaultFont, Font.PLAIN, 12));
        searchButton.addActionListener(e -> {
            if (e.getSource().equals(searchButton)) {
                String queryString = recipeName.getText();
                if (queryString != null && !queryString.isEmpty()) {
                    searchRecipeController.execute(queryString);
                }
            }
        });

        // Output Components
        recipeContainer = new JScrollPane(outputPanel);
        recipeContainer.getVerticalScrollBar().setUnitIncrement(5);
        recipeContainer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        recipeContainer.setBorder(new LineBorder(claudeWhite, 30));


        // Pack input & output panel
//        inputPanel.add(title);
        inputPanel.add(advancedSearchButton);
        inputPanel.add(recipeName);
        inputPanel.add(searchButton);


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
        if ("search recipe".equals(evt.getPropertyName())) {
            SearchRecipeOutputData response = (SearchRecipeOutputData) evt.getNewValue();

            outputPanel.removeAll();

            for (Recipe recipe : response) {

                // Main Panel
                JPanel recipePanel = new RoundPanel();
                recipePanel.setBackground(claudeWhite);
                recipePanel.setLayout(new BorderLayout(2, 3));
                recipePanel.setBorder(new EmptyBorder(30, 20, 20, 20));

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

                recipePanel.add(topPanel, BorderLayout.NORTH);
                recipePanel.add(leftPanel, BorderLayout.WEST);
                recipePanel.add(rightPanel, BorderLayout.CENTER);

                outputPanel.add(recipePanel);

            }
            SwingUtilities.invokeLater(() -> recipeContainer.getVerticalScrollBar().setValue(0));

        } else if (evt.getPropertyName().equals("empty result")) {
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
        outputPanel.revalidate();
        outputPanel.repaint();
    }


    @Override
    public String getViewName() {
        return this.viewname;
    }


}


