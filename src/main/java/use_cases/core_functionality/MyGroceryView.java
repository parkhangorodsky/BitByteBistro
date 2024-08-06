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
        } else if (evt.getPropertyName().equals("grocery")) {
            updateMyGrocery();
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
        if (user != null && !user.getShoppingLists().isEmpty()) {
            for (HashMap.Entry<String, ShoppingList> shoppingList : user.getShoppingLists().entrySet()) {
                String owner = shoppingList.getKey();
                ShoppingList items = shoppingList.getValue();
                JPanel shoppingListItem = createShoppingListItem(items);
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
}
