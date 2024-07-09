package view;

import interface_adapter.controller.AdvancedSearchRecipeController;
import interface_adapter.view_model.AdvancedSearchRecipeViewModel;
import view.view_components.PopupWindow;
import view.view_components.interfaces.StringCaseEditor;
import view.view_components.layouts.VerticalFlowLayout;
import view.view_components.round_component.RoundButton;
import view.view_components.round_component.RoundPanel;
import view.view_components.round_component.RoundTextField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.*;
import java.util.List;

public class AdvancedSearchView extends PopupWindow implements StringCaseEditor {

    private AdvancedSearchRecipeViewModel advancedSearchRecipeViewModel;
    private AdvancedSearchRecipeController advancedSearchRecipeController;

    Map<String, String> dietData;
    Map<String, String> healthData;
    Map<String, String> cuisineData;
    Map<String, String> dishTypeData;
    Map<String, String> mealTypeData;

    private PopupWindow dietPopup;
    private PopupWindow healthPopup;
    private PopupWindow cuisinePopup;
    private PopupWindow dishPopup;
    private PopupWindow mealPopup;

    JPanel summaryTextPanel;

    private String recipeName;
    private Map<String, Integer> ingredientsQuantity = new HashMap<>();
    private List<String> excludedIngredients = new ArrayList<>();
    private List<String> dietSelection = new ArrayList<>();
    private List<String> healthSelection = new ArrayList<>();
    private List<String> cuisineTypeSelection = new ArrayList<>();
    private List<String> dishTypeSelection = new ArrayList<>();
    private List<String> mealTypeSelection = new ArrayList<>();

    Font dfS = new Font(defaultFont, Font.PLAIN, 12);
    Font dfM = new Font(defaultFont, Font.PLAIN, 16);
    Font dfL = new Font(defaultFont, Font.PLAIN, 20);
    Font dfXL = new Font(defaultFont, Font.PLAIN, 26);


    public AdvancedSearchView(JFrame parent,
                              AdvancedSearchRecipeViewModel advancedSearchRecipeViewModel,
                              AdvancedSearchRecipeController advancedSearchRecipeController) {
        super(parent);
        this.advancedSearchRecipeViewModel = advancedSearchRecipeViewModel;
        this.advancedSearchRecipeController = advancedSearchRecipeController;


        dietData = advancedSearchRecipeViewModel.getDietOptions();
        healthData = advancedSearchRecipeViewModel.getHealthOptions();
        cuisineData = advancedSearchRecipeViewModel.getCuisineTypeOptions();
        dishTypeData = advancedSearchRecipeViewModel.getDishTypeOptions();
        mealTypeData = advancedSearchRecipeViewModel.getMealTypeOptions();

        List<String> dietOption = new ArrayList<>(dietData.keySet());
        dietOption.sort(String::compareToIgnoreCase);
        List<String> healthOption = new ArrayList<>(healthData.keySet());
        healthOption.sort(String::compareToIgnoreCase);
        List<String> cuisineTypeOption = new ArrayList<>(cuisineData.keySet());
        cuisineTypeOption.sort(String::compareToIgnoreCase);
        List<String> dishTypeOption = new ArrayList<>(dishTypeData.keySet());
        dishTypeOption.sort(String::compareToIgnoreCase);
        List<String> mealTypeOption = new ArrayList<>(mealTypeData.keySet());

        dietPopup = configurationPopUp("Diet", dietOption, dietData, dietSelection);
        healthPopup = configurationPopUp("Health", healthOption, healthData, healthSelection);
        cuisinePopup = configurationPopUp("Cuisine Type", cuisineTypeOption, cuisineData, cuisineTypeSelection);
        dishPopup = configurationPopUp("Dish Type", dishTypeOption, dishTypeData, dishTypeSelection);
        mealPopup = configurationPopUp("Meal Type", mealTypeOption, mealTypeData, mealTypeSelection);
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

        JPanel ingredientPanel = createTextQtyApplyComponent("Ingredient");
        JPanel excludedPanel = createTextApplyComponent("Excluded Ingredients");
        JPanel dietPanel = createComboBoxAndStatusPanel("Diet", dietPopup);
        JPanel healthPanel = createComboBoxAndStatusPanel("Health", healthPopup);
        JPanel cuisineTypePanel = createComboBoxAndStatusPanel("Cuisine Type", cuisinePopup);
        JPanel dishTypePanel = createComboBoxAndStatusPanel("Dish Type", dishPopup);
        JPanel mealTypePanel = createComboBoxAndStatusPanel("Meal Type", mealPopup);

        inputPanel.add(stringPanel);
        inputPanel.add(ingredientPanel);
        inputPanel.add(excludedPanel);
        inputPanel.add(dietPanel);
        inputPanel.add(healthPanel);
        inputPanel.add(cuisineTypePanel);
        inputPanel.add(dishTypePanel);
        inputPanel.add(mealTypePanel);

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
                advancedSearchRecipeController.execute(recipeName,
                        ingredientsQuantity,
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
        positionFrameAtCenter(this, parent);
    }

    private void displaySummary() {

        summaryTextPanel.removeAll();

        boolean recipeNameFilled = recipeName != null && !recipeName.isEmpty();
        List<String> ingredients = new ArrayList<>(ingredientsQuantity.keySet()).reversed();
        boolean ingredientFilled = !ingredients.isEmpty();
        boolean excludedFilled = !excludedIngredients.isEmpty();
        boolean dietFilled = !dietSelection.isEmpty();
        boolean healthFilled = !healthSelection.isEmpty();
        boolean cuisineTypeFilled = !cuisineTypeSelection.isEmpty();
        boolean dishTypeFilled = !dishTypeSelection.isEmpty();
        boolean mealTypeFilled = !mealTypeSelection.isEmpty();

        if (recipeNameFilled || ingredientFilled || excludedFilled || dietFilled || healthFilled || cuisineTypeFilled || dishTypeFilled || mealTypeFilled) {
            addToSummary(dfM, "Looking");
            addToSummary(dfS, "for");

            if (recipeNameFilled) {
                for (String word : recipeName.trim().split(" ")) {
                    addToSummary(dfXL, capitalizeWords(word));
                }

            }

            addToSummary(dfM, "recipe");
            addToSummary(dfS, "that");

            int counter = 0;
            if (ingredientFilled) {

                addToSummary(dfM, "contains");

                for (String ing : ingredients) {
                    Integer quantity = ingredientsQuantity.get(ing);
                    if (quantity != null && quantity > 0) {
                        addToSummary(dfL, quantity.toString());
                    }
                    for (String word : ing.trim().split(" ")) {
                        addToSummary(dfL, capitalizeWords(word));
                    }
                    counter += 1;
                    if (counter < ingredients.size()) {
                        addToSummary(dfM,",");
                    } else {
                        counter = 0;
                    }
                }
                addToSummary(dfS, "and");

            }


            if (excludedFilled) {
                addToSummary(dfS, "does");
                addToSummary(dfM, "Not");
                addToSummary(dfM, "Contain");

                for (String excluded : excludedIngredients) {
                    for (String word : excluded.trim().split(" ")) {
                        addToSummary(dfL, capitalizeWords(word));
                    }
                    counter += 1;
                    if (counter < excludedIngredients.size()) {
                        addToSummary(dfM,",");
                    } else {
                        counter = 0;
                    }
                }
                addToSummary(dfS, "that");
            }

            if (dietFilled) {
                addToSummary(dfS, "is");
                for (String selection : dietSelection) {
                    for (String word : selection.trim().split("%20")) {
                        addToSummary(dfL, capitalizeWords(word));
                    }
                    counter += 1;
                    if (counter < dietSelection.size()) {
                        addToSummary(dfM,",");
                    } else {
                        counter = 0;
                    }
                }
            }

            if (healthFilled) {
                addToSummary(dfS, "and");
                for (String selection : healthSelection) {
                    for (String word : selection.trim().split("%20")) {
                        addToSummary(dfM, capitalizeWords(word));
                    }
                    counter += 1;
                    if (counter < healthSelection.size()) {
                        addToSummary(dfM,",");
                    } else {
                        counter = 0;
                    }
                }
            }

            if (cuisineTypeFilled) {
                addToSummary(dfS, "which");
                addToSummary(dfS, "is");
                for (String selection : cuisineTypeSelection) {
                    for (String word : selection.trim().split("%20")) {
                        addToSummary(dfXL, capitalizeWords(word));
                    }
                    counter += 1;
                    if (counter < cuisineTypeSelection.size()) {
                        addToSummary(dfM,",");
                        addToSummary(dfS,"or");
                    } else {
                        counter = 0;
                    }
                }
                addToSummary(dfS,"cuisine");
            }

            if (dishTypeFilled) {

                for (String selection : dishTypeSelection) {
                    for (String word : selection.trim().split("%20")) {
                        addToSummary(dfL, capitalizeWords(word));
                    }
                    counter += 1;
                    if (counter < dishTypeSelection.size()) {
                        addToSummary(dfL,",");
                        addToSummary(dfS,"or");
                    } else {
                        counter = 0;
                    }
                }
            }

            if (mealTypeFilled) {
                addToSummary(dfS, "for");

                for (String selection : mealTypeSelection) {

                    for (String word : selection.trim().split("%20")) {
                        addToSummary(dfL, capitalizeWords(word));
                    }

                    counter += 1;
                    if (counter < mealTypeSelection.size()) {
                        addToSummary(dfM,",");
                        addToSummary(dfS,"or");
                    } else {
                        counter = 0;
                    }
                }

            }

            addToSummary(dfM, "...");
        }

        summaryTextPanel.revalidate();
        summaryTextPanel.repaint();
    }

    private void addToSummary(Font font, String text) {

        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(claudeBlack);
        label.setAlignmentX(RIGHT_ALIGNMENT);
        label.setAlignmentY(BOTTOM_ALIGNMENT);
        summaryTextPanel.add(label);
    }

    private JPanel createTextQtyApplyComponent(String labelName) {
        // Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(claudeWhite);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(claudeWhite);

        // Label
        JLabel label = new JLabel(labelName);
        label.setForeground(claudeBlackEmph);
        label.setOpaque(true);
        label.setFont(new Font(defaultFont, Font.PLAIN, 12));
        label.setBorder(new EmptyBorder(0, 0, 5, 0));
        label.setAlignmentX(LEFT_ALIGNMENT);
        leftPanel.add(label);


        // Main Textfield
        RoundTextField textField = new RoundTextField();
        textField.setPreferredSize(new Dimension(250, 30));
        textField.setBorder(new EmptyBorder(0, 10, 0, 10));
        textField.setMaximumSize(textField.getPreferredSize());
        textField.setMinimumSize(textField.getPreferredSize());
        textField.setAlignmentX(LEFT_ALIGNMENT);
        textField.setPlaceholder(labelName);
        textField.setFont(new Font(secondaryFont, Font.PLAIN, 12));
        textField.setBackground(claudewhiteBright);
        leftPanel.add(textField);


        // Quantity Field
        RoundTextField quantityField = new RoundTextField();
        quantityField.setPreferredSize(new Dimension(50, 30));
        quantityField.setBorder(new EmptyBorder(0, 10, 0, 10));
        quantityField.setMaximumSize(quantityField.getPreferredSize());
        quantityField.setMinimumSize(quantityField.getPreferredSize());
        quantityField.setAlignmentX(LEFT_ALIGNMENT);
        quantityField.setPlaceholder("Qty");
        quantityField.setFont(new Font(secondaryFont, Font.PLAIN, 12));
        quantityField.setBackground(claudewhiteBright);


        // Apply Button
        RoundButton applyButton = new RoundButton("Add");
        applyButton.setFont(new Font(defaultFont, Font.PLAIN, 12));
        applyButton.setHoverColor(claudewhiteBright, claudeWhiteEmph, claudeWhiteEmph, claudeBlack);
        applyButton.setPreferredSize(new Dimension(70, 30));
        applyButton.addActionListener(e -> {
            if (e.getSource() == applyButton) {
                String ingredient = textField.getText();
                Integer quantity = Integer.parseInt(quantityField.getText());
                ingredientsQuantity.put(ingredient, quantity);
                displaySummary();
            }
        });


        leftPanel.setAlignmentY(BOTTOM_ALIGNMENT);
        quantityField.setAlignmentY(BOTTOM_ALIGNMENT);
        applyButton.setAlignmentY(BOTTOM_ALIGNMENT);


        mainPanel.add(leftPanel);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(quantityField);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(applyButton);

        return mainPanel;
    }

    private JPanel createTextApplyComponent(String labelName) {
        // Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(claudeWhite);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(claudeWhite);

        // Label
        JLabel label = new JLabel(labelName);
        label.setForeground(claudeBlackEmph);
        label.setOpaque(true);
        label.setFont(new Font(defaultFont, Font.PLAIN, 12));
        label.setBorder(new EmptyBorder(0, 0, 5, 0));
        label.setAlignmentX(LEFT_ALIGNMENT);
        leftPanel.add(label);

        // Main Textfield
        RoundTextField textField = new RoundTextField();
        textField.setPreferredSize(new Dimension(310, 30));
        textField.setBorder(new EmptyBorder(0, 10, 0, 10));
        textField.setMaximumSize(textField.getPreferredSize());
        textField.setMinimumSize(textField.getPreferredSize());
        textField.setAlignmentX(LEFT_ALIGNMENT);
        textField.setPlaceholder(labelName);
        textField.setFont(new Font(secondaryFont, Font.PLAIN, 12));
        textField.setBackground(claudewhiteBright);
        leftPanel.add(textField);

        // Apply Button
        RoundButton applyButton = new RoundButton("Add");
        applyButton.setFont(new Font(defaultFont, Font.PLAIN, 12));
        applyButton.setHoverColor(claudewhiteBright, claudeWhiteEmph, claudeWhiteEmph, claudeBlack);
        applyButton.setPreferredSize(new Dimension(70, 30));
        applyButton.addActionListener(e -> {
            if (e.getSource() == applyButton) {
                String excluded = textField.getText();
                excludedIngredients.add(excluded);
                displaySummary();
            }
        });

        leftPanel.setAlignmentY(BOTTOM_ALIGNMENT);
        applyButton.setAlignmentY(BOTTOM_ALIGNMENT);

        mainPanel.add(leftPanel);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(applyButton);

        return mainPanel;
    }

    private JPanel createComboBoxAndStatusPanel(String labelName, PopupWindow popUp) {
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(claudeWhite);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel(labelName);
        label.setForeground(claudeBlackEmph);
        label.setOpaque(true);
        label.setFont(new Font(defaultFont, Font.PLAIN, 12));
        label.setAlignmentX(LEFT_ALIGNMENT);
        label.setBorder(new EmptyBorder(0, 0, 5, 0));

        RoundButton configureButton = new RoundButton("Configure" + " " + labelName);
        configureButton.setFont(new Font(defaultFont, Font.PLAIN, 12));
        configureButton.setHoverColor(claudewhiteBright, claudeWhiteEmph, claudeWhiteEmph, claudeBlack);
        configureButton.setPreferredSize(new Dimension(390, 30));
        configureButton.setMaximumSize(configureButton.getPreferredSize());
        configureButton.setMinimumSize(configureButton.getPreferredSize());

        configureButton.addActionListener(e -> {
            if (e.getSource() == configureButton) {
                popUp.setEnabled(true);
                popUp.setVisible(true);
            }
        });

        mainPanel.add(label);
        mainPanel.add(configureButton);

        return mainPanel;
    }

    private PopupWindow configurationPopUp(String labelName, List<String> options, Map<String, String>data, List<String> selectedOptions) {

        PopupWindow configurePopUp = new PopupWindow(this);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(claudeWhite);
        mainPanel.setBorder(new EmptyBorder(30, 20, 20, 20));
        mainPanel.setPreferredSize(new Dimension(400, 400));
        mainPanel.setMinimumSize(mainPanel.getPreferredSize());
        mainPanel.setMaximumSize(mainPanel.getPreferredSize());

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(claudeWhite);
        titlePanel.setBorder(new EmptyBorder(0,0,10,0));
        JLabel titleLabel = new JLabel("Select " + labelName + " Preference");
        titleLabel.setFont(new Font(defaultFont, Font.PLAIN, 12));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        titlePanel.setBackground(claudeWhite);
        titleLabel.setForeground(claudeBlack);
        titlePanel.add(titleLabel);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new VerticalFlowLayout(5));
        inputPanel.setBackground(claudeWhite);

        List<JCheckBox> checkBoxes = new ArrayList<>();
        for (String option : options) {
            JCheckBox checkBox = new JCheckBox(option);
            checkBox.setFont(new Font(secondaryFont, Font.PLAIN, 12));
            checkBox.setBackground(claudewhiteBright);
            checkBox.setForeground(claudeBlack);
            checkBoxes.add(checkBox);
            inputPanel.add(checkBox);
        }

        JScrollPane scrollInputPanel = new JScrollPane(inputPanel);
        scrollInputPanel.getVerticalScrollBar().setUnitIncrement(5);
        scrollInputPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollInputPanel.setBorder(new EmptyBorder(0, 0, 0, 0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(claudeWhite);
        RoundButton applyButton = new RoundButton("Apply");
        applyButton.setFont(new Font(defaultFont, Font.PLAIN, 12));
        applyButton.setHoverColor(claudeWhite, claudeWhiteEmph, claudeBlackEmph, claudeBlack);
        applyButton.setPreferredSize(new Dimension(70, 30));
        applyButton.addActionListener(e -> {
            if (e.getSource() == applyButton) {
                for (JCheckBox checkBox : checkBoxes) {
                    if (checkBox.isSelected()) {
                        if (!selectedOptions.contains(checkBox.getText())) {
                            selectedOptions.add(data.get(checkBox.getText()));
                        }

                    }
                }

                displaySummary();

                configurePopUp.setEnabled(false);
                configurePopUp.setVisible(false);
            }
        });
        buttonPanel.add(applyButton);

        mainPanel.add(titlePanel);
        mainPanel.add(scrollInputPanel);
        mainPanel.add(buttonPanel);

        configurePopUp.add(mainPanel);
        configurePopUp.pack();

        positionFrameAtCenter(configurePopUp, this);
        configurePopUp.setEnabled(false);
        configurePopUp.setVisible(false);

        return configurePopUp;
    }



}
