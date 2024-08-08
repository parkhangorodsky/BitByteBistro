package use_cases.core_functionality;

import app.local.LocalAppSetting;
import app.local.LoggedUserData;
import entity.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import use_cases._common.gui_common.abstractions.NightModeObject;
import use_cases._common.gui_common.abstractions.ThemeColoredObject;
import use_cases._common.gui_common.abstractions.View;
import use_cases._common.gui_common.view_components.layouts.VerticalFlowLayout;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases._common.gui_common.view_components.round_component.RoundPanel;
import use_cases.add_new_grocery_list.AddNewGroceryListController;
import use_cases.core_functionality.CoreFunctionalityInteractor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;


public class MyGroceryView extends View implements ThemeColoredObject, NightModeObject {
    protected MyGroceryViewModel viewModel;

    private JPanel myGroceryContainer;
    private JScrollPane myGroceryScrollPane;
    private JPanel inputPanel;
    private JTextField newListNameTextField;
    private JButton confirmButton;
    private JLabel promptLabel;
    private AddNewGroceryListController addNewGroceryListController;
    private boolean isTextBarOpen = false; // Add flag to check if text bar is open


    public MyGroceryView(MyGroceryViewModel viewModel, AddNewGroceryListController addNewGroceryListController) {

        observeNight();
        this.addNewGroceryListController = addNewGroceryListController;
        this.setLayout(new BorderLayout());
        this.viewModel = viewModel;
        this.setViewName(viewModel.getViewName());
        this.viewModel.addPropertyChangeListener(this);

        JPanel viewPanel = setUpContentView();

        toggleNightMode();

        this.add(viewPanel, BorderLayout.CENTER);
        this.setVisible(true);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                updateMyGrocery(); // Reload grocery list when view is shown
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("init")) {
            viewModel.setUser(LoggedUserData.getLoggedInUser());
            updateMyGrocery();
        } else if (evt.getPropertyName().equals("grocery") || evt.getPropertyName().equals("subtractFridgeFromGrocery")) {
            System.out.println("Property change detected: " + evt.getPropertyName()); // Debugging output
            updateMyGrocery();  // Always update the view when grocery list or setting changes
        } else if (evt.getPropertyName().equals("nightMode")) {
            toggleNightMode();
            this.revalidate();
            this.repaint();
        }
    }

    private JPanel setUpContentView() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.setPreferredSize(new Dimension(800,100));

        inputPanel.setMaximumSize(inputPanel.getPreferredSize());
        inputPanel.setBorder(new EmptyBorder(20,20,20,20));
        inputPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 3, 5));

        inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.setPreferredSize(new Dimension(800,100));
        inputPanel.setMaximumSize(inputPanel.getPreferredSize());
        inputPanel.setBorder(new EmptyBorder(20,20,20,20));
        inputPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 3, 5));

        // Add "Make new grocery list..." button
        JButton addNewGroceryListButton = new JButton("Make new grocery list...");
        addNewGroceryListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNewGroceryListInput();
            }
        });
        inputPanel.add(addNewGroceryListButton);


        JPanel outputPanel = new JPanel();
        outputPanel.setOpaque(false);
        outputPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        outputPanel.setLayout(new BorderLayout());

        // duplicated code
        myGroceryContainer = new JPanel(new VerticalFlowLayout(10));
        myGroceryScrollPane = new JScrollPane(myGroceryContainer);
        myGroceryScrollPane.setOpaque(false);
        myGroceryScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        myGroceryScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        myGroceryScrollPane.setBorder(new LineBorder(claudeWhite, 0));

        outputPanel.add(myGroceryScrollPane);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(outputPanel, BorderLayout.CENTER);

        return mainPanel;
    }

    private void showNewGroceryListInput() {
        if (isTextBarOpen) return; // Prevent opening multiple text bars
        isTextBarOpen = true; // Set flag when text bar is opened

        promptLabel = new JLabel("Enter grocery list name...");
        newListNameTextField = new JTextField(20);
        newListNameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    createNewGroceryList();
                    String newGroceryListName = newListNameTextField.getText();
                    addNewGroceryListController.execute(newGroceryListName, viewModel);
                    updateMyGrocery();
                }

            }
        });

        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewGroceryList();
                String newGroceryListName = newListNameTextField.getText();
                addNewGroceryListController.execute(newGroceryListName, viewModel);
                updateMyGrocery();
            }
        });

        inputPanel.add(newListNameTextField);
        inputPanel.add(confirmButton);
        inputPanel.revalidate();
        inputPanel.repaint();
    }

    private void createNewGroceryList() {
        inputPanel.remove(promptLabel);
        inputPanel.remove(newListNameTextField);
        inputPanel.remove(confirmButton);
        inputPanel.revalidate();
        inputPanel.repaint();
        isTextBarOpen = false; // Reset flag when text bar is removed
    }

    private void updateMyGrocery() {
        myGroceryContainer.removeAll();

        User user = viewModel.getUser();
        boolean subtractFridgeFromGrocery = LocalAppSetting.isSubtractFridgeFromGrocery();
        System.out.println("Subtract Fridge from Grocery Setting: " + subtractFridgeFromGrocery);

        if (user != null && !user.getShoppingLists().isEmpty()) {
            for (HashMap.Entry<String, ShoppingList> shoppingList : user.getShoppingLists().entrySet()) {
                ShoppingList items = shoppingList.getValue();
                JPanel shoppingListItem;
                if (subtractFridgeFromGrocery) {
                    ShoppingList adjustedItems = getAdjustedGroceryListForDisplay(items);
                    shoppingListItem = createShoppingListItem(adjustedItems);
                } else {
                    shoppingListItem = createShoppingListItem(items); // Display original list
                }
                myGroceryContainer.add(shoppingListItem);
            }
        } else {
            JLabel emptyLabel = new JLabel("No shopping lists available.");
            emptyLabel.setFont(new Font(defaultFont, Font.PLAIN, 18));
            emptyLabel.setForeground(LocalAppSetting.isNightMode() ? neonPinkEmph : black);
            myGroceryContainer.add(emptyLabel);
        }

        SwingUtilities.invokeLater(() -> myGroceryScrollPane.getVerticalScrollBar().setValue(0));

        myGroceryContainer.revalidate();
        myGroceryContainer.repaint();
    }

    private ShoppingList getAdjustedGroceryListForDisplay(ShoppingList originalList) {
        List<Ingredient> adjustedIngredients = new ArrayList<>();
        List<Ingredient> fridgeItems = LoggedUserData.getLoggedInUser().getFridge().getIngredients();

        for (Ingredient grocery : originalList.getListItems()) {
            float adjustedQuantity = grocery.getQuantity();
            for (Ingredient fridgeItem : fridgeItems) {
                if (grocery.getIngredientName().equalsIgnoreCase(fridgeItem.getIngredientName()) &&
                        grocery.getQuantityUnit().equalsIgnoreCase(fridgeItem.getQuantityUnit())) {
                    adjustedQuantity -= fridgeItem.getQuantity();
                }
            }
            if (adjustedQuantity > 0) {
                Ingredient adjustedIngredient = new Ingredient(grocery.getIngredientID(), grocery.getIngredientName(),
                        grocery.getQuantityUnit(), grocery.getCategory(), adjustedQuantity);
                adjustedIngredients.add(adjustedIngredient);
            }
        }

        // Consolidate like ingredients
        Map<String, Ingredient> consolidatedIngredients = new LinkedHashMap<>();
        for (Ingredient ingredient : adjustedIngredients) {
            String key = ingredient.getIngredientName().toLowerCase() + ingredient.getQuantityUnit().toLowerCase();
            if (consolidatedIngredients.containsKey(key)) {
                consolidatedIngredients.get(key).addIngredientQuantity(ingredient.getQuantity());
            } else {
                consolidatedIngredients.put(key, ingredient);
            }
        }

        ShoppingList adjustedShoppingList = new ShoppingList(originalList.getListOwner(), originalList.getShoppingListName());
        adjustedShoppingList.setListItems(new ArrayList<>(consolidatedIngredients.values()));
        adjustedShoppingList.setEstimatedTotalCost(originalList.getEstimatedTotalCost());
        adjustedShoppingList.setRecipes(originalList.getRecipes());
        return adjustedShoppingList;
    }


    private ShoppingList getAggregatedGroceryListForDisplay(ShoppingList originalList) {
        Map<String, Ingredient> aggregatedIngredients = new LinkedHashMap<>();

        for (Ingredient ingredient : originalList.getListItems()) {
            String key = ingredient.getIngredientName() + ingredient.getQuantityUnit();
            if (aggregatedIngredients.containsKey(key)) {
                Ingredient existing = aggregatedIngredients.get(key);
                existing.addIngredientQuantity(ingredient.getQuantity());
            } else {
                // Create a new Ingredient object to avoid mutating the original
                Ingredient newIngredient = new Ingredient(ingredient.getIngredientID(), ingredient.getIngredientName(),
                        ingredient.getQuantityUnit(), ingredient.getCategory(),
                        ingredient.getQuantity());
                aggregatedIngredients.put(key, newIngredient);
            }
        }

        ShoppingList aggregatedShoppingList = new ShoppingList(originalList.getListOwner(), originalList.getShoppingListName());
        aggregatedShoppingList.setListItems(new ArrayList<>(aggregatedIngredients.values()));
        aggregatedShoppingList.setEstimatedTotalCost(originalList.getEstimatedTotalCost());
        aggregatedShoppingList.setRecipes(originalList.getRecipes());

        return aggregatedShoppingList;
    }



    private JPanel createShoppingListItem(ShoppingList shoppingList) {
        RoundPanel shoppingListItem = new RoundPanel();
        shoppingListItem.setLayout(new BorderLayout());
        shoppingListItem.setBorder(new EmptyBorder(10, 10, 10, 10));
        shoppingListItem.setBackground(LocalAppSetting.isNightMode() ? neonPurpleEmph : claudewhiteBright);
        shoppingListItem.setBorderColor(LocalAppSetting.isNightMode() ? neonPurple : claudeWhiteEmph);

        // Shopping List Name
        JPanel shoppingListNamePanel = new JPanel(new BorderLayout());
        shoppingListNamePanel.setOpaque(false);
        JLabel shoppingListNameLabel = new JLabel(shoppingList.getShoppingListName());
        shoppingListNameLabel.setFont(new Font(defaultFont, Font.PLAIN, 20));
        shoppingListNameLabel.setForeground(LocalAppSetting.isNightMode() ? neonPinkEmph : black);
        shoppingListNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        shoppingListNameLabel.setVerticalTextPosition(SwingConstants.CENTER);
        shoppingListNamePanel.add(shoppingListNameLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setOpaque(false);
        RoundButton showRecipeButton = new RoundButton("∨");
        showRecipeButton.setHorizontalAlignment(SwingConstants.CENTER);
        showRecipeButton.setVerticalAlignment(SwingConstants.CENTER);
        showRecipeButton.setPreferredSize(new Dimension(30, 30));
        showRecipeButton.setBorderColor(LocalAppSetting.isNightMode() ? neonPinkEmph : claudeBlack);

        if (LocalAppSetting.isNightMode()) {
            showRecipeButton.setHoverColor(darkPurple, neonPinkEmph, white, white);
        } else {
            showRecipeButton.setHoverColor(claudeBlack, sunflower, claudeWhite, claudewhiteBright);
        }

        buttonPanel.add(showRecipeButton, BorderLayout.SOUTH);

        // Add button action to expand view
        showRecipeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showRecipeButton.getText().equals("∨")) {
                    showRecipeButton.setText("∧"); // Change to collapse symbol
                    JPanel ingredientsPanel = createIngredientsPanel(shoppingList.getListItems());
                    shoppingListItem.add(ingredientsPanel, BorderLayout.SOUTH);
                } else {
                    showRecipeButton.setText("∨"); // Change back to expand symbol
                    shoppingListItem.remove(2); // Remove ingredients panel
                }
                shoppingListItem.revalidate();
                shoppingListItem.repaint();
            }
        });

        buttonPanel.add(showRecipeButton, BorderLayout.SOUTH);

        shoppingListItem.add(buttonPanel, BorderLayout.EAST);
        shoppingListItem.add(shoppingListNamePanel, BorderLayout.WEST);


        return shoppingListItem;


    }

    private JPanel createIngredientsPanel(List<Ingredient> ingredients) {
        JPanel ingredientsPanel = new JPanel(new VerticalFlowLayout(5));
        ingredientsPanel.setOpaque(false);
        for (Ingredient ingredient : ingredients) {
            JLabel ingredientLabel = new JLabel(ingredient.toString());
            ingredientLabel.setFont(new Font(defaultFont, Font.PLAIN, 16));
            ingredientLabel.setForeground(LocalAppSetting.isNightMode() ? neonPinkEmph : black);
            ingredientsPanel.add(ingredientLabel);
        }
        return ingredientsPanel;
    }

    @Override
    public void setNightMode() {
        this.setBackground(black);
        if (LoggedUserData.getLoggedInUser() != null) {
            viewModel.setUser(LoggedUserData.getLoggedInUser());
            updateMyGrocery();
        }
        myGroceryContainer.setBackground(black);

    }

    @Override
    public void setDayMode() {
        this.setBackground(claudeWhite);
        if (LoggedUserData.getLoggedInUser() != null) {
            viewModel.setUser(LoggedUserData.getLoggedInUser());
            updateMyGrocery();
        }
        myGroceryContainer.setBackground(claudeWhite);
    }

    public void revalidateEverything(JComponent component) {
        System.out.println("ran");

        for (Component c : component.getComponents()) {
            if (c instanceof JComponent) {
                revalidateEverything((JComponent) c);
            }
        }

        component.revalidate();
        component.repaint();
    }

    private void resetGroceryList() {
        myGroceryContainer.removeAll();

        User user = viewModel.getUser();

        if (user != null && !user.getShoppingLists().isEmpty()) {
            for (HashMap.Entry<String, ShoppingList> shoppingList : user.getShoppingLists().entrySet()) {
                String owner = shoppingList.getKey();
                ShoppingList items = shoppingList.getValue();
                JPanel shoppingListItem = createShoppingListItem(items); // Display the original list without adjustments
                myGroceryContainer.add(shoppingListItem);
            }
        } else {
            JLabel emptyLabel = new JLabel("No shopping lists available.");
            emptyLabel.setFont(new Font(defaultFont, Font.PLAIN, 18));
            emptyLabel.setForeground(LocalAppSetting.isNightMode() ? neonPinkEmph : black);
            myGroceryContainer.add(emptyLabel);
        }

        SwingUtilities.invokeLater(() -> myGroceryScrollPane.getVerticalScrollBar().setValue(0));

        myGroceryContainer.revalidate();
        myGroceryContainer.repaint();
    }


}
