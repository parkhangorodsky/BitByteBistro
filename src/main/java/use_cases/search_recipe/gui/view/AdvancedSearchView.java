package use_cases.search_recipe.gui.view;

import use_cases._common.gui_common.abstractions.PopUpView;
import use_cases.search_recipe.gui.view_component.AdvancedSearchInputSummarizer;
import use_cases.search_recipe.gui.view_component.AdvancedSearchTextOptionField;
import use_cases.search_recipe.gui.view_component.AdvancedSearchTypeOptionButton;
import use_cases.search_recipe.gui.view_component.AdvancedSearchTypeOptionConfigPopUp;
import use_cases.search_recipe.interface_adapter.controller.SearchRecipeController;
import use_cases.search_recipe.interface_adapter.view_model.AdvancedSearchRecipeViewModel;
import use_cases._common.gui_common.abstractions.StringCaseEditor;
import use_cases._common.gui_common.view_components.layouts.VerticalFlowLayout;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases._common.gui_common.view_components.round_component.RoundPanel;
import use_cases._common.gui_common.view_components.round_component.RoundTextField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.*;
import java.util.List;

public class AdvancedSearchView extends PopUpView implements StringCaseEditor{

    private AdvancedSearchRecipeViewModel advancedSearchRecipeViewModel;
    private SearchRecipeController searchRecipeController;

    JPanel summaryTextPanel;

    private String recipeName;
    private Map<String, Integer> ingredientsQuantity = new HashMap<>();
    private List<String> excludedIngredients = new ArrayList<>();
    private List<String> dietSelection = new ArrayList<>();
    private List<String> healthSelection = new ArrayList<>();
    private List<String> cuisineTypeSelection = new ArrayList<>();
    private List<String> dishTypeSelection = new ArrayList<>();
    private List<String> mealTypeSelection = new ArrayList<>();

    public AdvancedSearchView(JFrame parent,
                              AdvancedSearchRecipeViewModel advancedSearchRecipeViewModel,
                              SearchRecipeController searchRecipeController) {
        super(parent);
        this.advancedSearchRecipeViewModel = advancedSearchRecipeViewModel;
        this.searchRecipeController = searchRecipeController;

        PopUpView dietPopup = new AdvancedSearchTypeOptionConfigPopUp(this,
                "Diet", advancedSearchRecipeViewModel.getDietOptions(), dietSelection);
        PopUpView healthPopup = new AdvancedSearchTypeOptionConfigPopUp(this,
                "Health", advancedSearchRecipeViewModel.getHealthOptions(), healthSelection);
        PopUpView cuisinePopup = new AdvancedSearchTypeOptionConfigPopUp(this,
                "Cuisine Type", advancedSearchRecipeViewModel.getCuisineTypeOptions(), cuisineTypeSelection);
        PopUpView dishPopup = new AdvancedSearchTypeOptionConfigPopUp(this,
                "Dish Type", advancedSearchRecipeViewModel.getDishTypeOptions(), dishTypeSelection);
        PopUpView mealPopup = new AdvancedSearchTypeOptionConfigPopUp(this,
                "Meal Type", advancedSearchRecipeViewModel.getMealTypeOptions(), mealTypeSelection);
        this.setEnabled(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(claudeWhite);
        mainPanel.setLayout(new VerticalFlowLayout(10));
        mainPanel.setBorder(new EmptyBorder(40, 40, 40, 40));

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(claudeWhite);
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBorder(new EmptyBorder(5, 0, 10, 0));

        JLabel titleLabel = new JLabel("Advanced Search");
        titleLabel.setFont(new Font(defaultFont, Font.PLAIN, 16));
        titleLabel.setAlignmentX(LEFT_ALIGNMENT);
        titlePanel.add(titleLabel, BorderLayout.WEST);

        // IO Panel
        JPanel IOPanel = new JPanel();
        IOPanel.setLayout(new BorderLayout(30, 0));
        IOPanel.setBackground(claudeWhite);

        JPanel summaryPanel = new RoundPanel();
        summaryPanel.setLayout(new BorderLayout());
        summaryPanel.setBackground(claudeWhite);
        summaryPanel.setPreferredSize(new Dimension(390, 300));
        summaryPanel.setMinimumSize(summaryPanel.getPreferredSize());
        summaryPanel.setBorder(new EmptyBorder(17, 30, 0, 30));
        summaryTextPanel = new RoundPanel();
        summaryTextPanel.setBackground(claudeWhite);
        summaryTextPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 8, 8));
        summaryPanel.add(summaryTextPanel, BorderLayout.CENTER);


        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new VerticalFlowLayout(10));
        inputPanel.setBackground(claudeWhite);

        // Search String Panel
        JPanel stringPanel = new JPanel();
        stringPanel.setLayout(new BoxLayout(stringPanel, BoxLayout.Y_AXIS));
        stringPanel.setBackground(claudeWhite);
        JLabel stringLabel = new JLabel("Recipe Name");
        stringLabel.setForeground(claudeBlackEmph);
        stringLabel.setOpaque(true);
        stringLabel.setFont(new Font(defaultFont, Font.PLAIN, 12));
        stringLabel.setBorder(new EmptyBorder(0, 0, 5, 0));
        stringLabel.setAlignmentX(LEFT_ALIGNMENT);
        stringPanel.add(stringLabel);

        RoundTextField stringField = new RoundTextField();
        stringField.setPreferredSize(new Dimension(390, 30));
        stringField.setBorder(new EmptyBorder(0, 10, 0, 10));
        stringField.setMaximumSize(stringField.getPreferredSize());
        stringField.setMinimumSize(stringField.getPreferredSize());
        stringField.setAlignmentX(LEFT_ALIGNMENT);
        stringField.setPlaceholder("Name");
        stringField.setFont(new Font(secondaryFont, Font.PLAIN, 12));
        stringField.setBackground(claudewhiteBright);
        stringField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleUpdate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handleUpdate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handleUpdate();
            }

            private void handleUpdate() {
                recipeName = stringField.getText();
                displaySummary();
            }
        });

        stringPanel.add(stringField);

        inputPanel.add(stringPanel);
        inputPanel.add(new AdvancedSearchTextOptionField(this, "Excluded Ingredients", excludedIngredients));
        inputPanel.add(new AdvancedSearchTypeOptionButton("Diet", dietPopup));
        inputPanel.add(new AdvancedSearchTypeOptionButton("Health", healthPopup));
        inputPanel.add(new AdvancedSearchTypeOptionButton("Cuisine Type", cuisinePopup));
        inputPanel.add(new AdvancedSearchTypeOptionButton("Dish Type", dishPopup));
        inputPanel.add(new AdvancedSearchTypeOptionButton("Meal Type", mealPopup));

        IOPanel.add(inputPanel, BorderLayout.WEST);
        IOPanel.add(summaryPanel, BorderLayout.CENTER);


        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(claudeWhite);
        RoundButton closeButton = new RoundButton("Close");
        closeButton.setForegroundColor(claudeBlackEmph);
        closeButton.setFont(new Font(defaultFont, Font.PLAIN, 12));
        closeButton.setPreferredSize(new Dimension(closeButton.getMinimumSize().width, 30));
        closeButton.addActionListener(e -> {
            if (e.getSource() == closeButton) {
                this.dispose();
            }
        });
        RoundButton searchButton = new RoundButton("Search");
        searchButton.setHoverColor(getBackground(), claudeOrange, claudeBlackEmph, claudeWhite);
        searchButton.setFont(new Font(defaultFont, Font.PLAIN, 12));
        searchButton.setPreferredSize(new Dimension(searchButton.getMinimumSize().width, 30));
        searchButton.addActionListener(e -> {
            if (e.getSource() == searchButton) {
                searchRecipeController.execute(recipeName,
                        excludedIngredients,
                        dietSelection,
                        healthSelection,
                        cuisineTypeSelection,
                        dishTypeSelection,
                        mealTypeSelection);
                this.dispose();
            }

        });
        buttonPanel.add(closeButton);
        buttonPanel.add(searchButton);

        mainPanel.add(titlePanel);
        mainPanel.add(IOPanel);
        mainPanel.add(buttonPanel);

        this.add(mainPanel);
        this.pack();
        positionFrameAtCenter(parent);
    }

    public void displaySummary() {
        summaryTextPanel.removeAll();
        new AdvancedSearchInputSummarizer(this, summaryTextPanel).summarize();
        summaryTextPanel.revalidate();
        summaryTextPanel.repaint();
    }

    public String getRecipeName() {return recipeName;}
    public Map<String, Integer> getIngredientsQuantity() {return ingredientsQuantity;}
    public List<String> getExcludedIngredients() {return excludedIngredients;}
    public List<String> getDietSelection() {return dietSelection;}
    public List<String> getHealthSelection() {return healthSelection;}
    public List<String> getCuisineTypeSelection() {return cuisineTypeSelection;}
    public List<String> getDishTypeSelection() {return dishTypeSelection;}
    public List<String> getMealTypeSelection() {return mealTypeSelection;}

}
