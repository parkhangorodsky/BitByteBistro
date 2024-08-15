package use_cases.core_functionality;

import app.local.LocalAppSetting;
import app.local.LoggedUserData;
import entity.ShoppingList;
import entity.User;
import use_cases._common.gui_common.abstractions.NightModeObject;
import use_cases._common.gui_common.abstractions.ThemeColoredObject;
import use_cases._common.gui_common.abstractions.View;
import use_cases.add_new_grocery_list.AddNewGroceryListController;
import use_cases.add_to_my_recipe.AddToMyRecipeController;
import use_cases.display_recipe_detail.DisplayRecipeDetailController;
import use_cases.core_functionality.CoreFunctionalityController;
import use_cases.display_recipe_detail.DisplayRecipeDetailSearchResultView;
import use_cases.display_recipe_detail.DisplayRecipeDetailViewModel;
import use_cases.core_functionality.view_component.InputPanel;
import use_cases.core_functionality.view_component.OutputPanel;
import use_cases.core_functionality.view_component.ShoppingListItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.util.HashMap;

public class MyGroceryView extends View implements ThemeColoredObject, NightModeObject {
    protected MyGroceryViewModel viewModel;
    private JPanel myGroceryContainer;
    private JScrollPane myGroceryScrollPane;
    private JPanel inputPanel;
    private DisplayRecipeDetailController displayRecipeDetailController;
    private AddToMyRecipeController addToMyRecipeController;
    private CoreFunctionalityController coreFunctionalityController;
    private AddNewGroceryListController addNewGroceryListController;
    private boolean isTextBarOpen = false; // Add flag to check if text bar is open

    public MyGroceryView(MyGroceryViewModel viewModel,
                         AddNewGroceryListController addNewGroceryListController,
                         AddToMyRecipeController addToMyRecipeController,
                         CoreFunctionalityController coreFunctionalityController,
                         DisplayRecipeDetailController displayRecipeDetailController) {
        observeNight();
        this.addNewGroceryListController = addNewGroceryListController;
        this.displayRecipeDetailController = displayRecipeDetailController;
        this.addToMyRecipeController = addToMyRecipeController;
        this.coreFunctionalityController = coreFunctionalityController;
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
    public void actionPerformed(ActionEvent e) {}

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

        // Pass 'this' as the reference to InputPanel
        inputPanel = new InputPanel(this);
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        OutputPanel outputPanel = new OutputPanel();
        myGroceryContainer = outputPanel.getMyGroceryContainer();
        myGroceryScrollPane = outputPanel.getMyGroceryScrollPane();
        mainPanel.add(outputPanel, BorderLayout.CENTER);

        return mainPanel;
    }


    public void showNewGroceryListInput() {
        if (isTextBarOpen) return; // Prevent opening multiple text bars
        isTextBarOpen = true; // Set flag when text bar is opened

        JTextField newListNameTextField = new JTextField(20);
        JButton confirmButton = new JButton("Confirm");

        newListNameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    createNewGroceryList(newListNameTextField);
                }
            }
        });

        confirmButton.addActionListener(e -> createNewGroceryList(newListNameTextField));

        inputPanel.add(newListNameTextField);
        inputPanel.add(confirmButton);
        inputPanel.revalidate();
        inputPanel.repaint();
    }

    private void createNewGroceryList(JTextField newListNameTextField) {
        String newGroceryListName = newListNameTextField.getText();
        addNewGroceryListController.execute(newGroceryListName, viewModel);
        updateMyGrocery();
        inputPanel.removeAll(); // Clear input panel
        isTextBarOpen = false; // Reset flag when text bar is removed
    }

    private void updateMyGrocery() {
        myGroceryContainer.removeAll();

        User user = viewModel.getUser();
        if (user != null && !user.getShoppingLists().isEmpty()) {
            for (HashMap.Entry<String, ShoppingList> shoppingList : user.getShoppingLists().entrySet()) {
                JPanel shoppingListItem = createShoppingListItem(shoppingList.getValue());
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
        return new ShoppingListItem(shoppingList, addNewGroceryListController, addToMyRecipeController,
                coreFunctionalityController, displayRecipeDetailController);
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
        for (Component c : component.getComponents()) {
            if (c instanceof JComponent) {
                revalidateEverything((JComponent) c);
            }
        }
        component.revalidate();
        component.repaint();
    }
}