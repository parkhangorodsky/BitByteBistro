package use_cases.core_functionality.view_components;

import app.local.LocalAppSetting;
import entity.*;

import java.awt.*;

import use_cases._common.gui_common.view_components.layouts.VerticalFlowLayout;
import use_cases.core_functionality.MyGroceryViewModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.util.HashMap;

import static use_cases._common.gui_common.abstractions.ThemeColoredObject.*;


public class GroceryOutputPanel extends JPanel {
    private JPanel myGroceryContainer;
    private JScrollPane myGroceryScrollPane;
    private MyGroceryViewModel viewModel;

    public GroceryOutputPanel(MyGroceryViewModel viewModel) {
        this.viewModel = viewModel;
        setUpPanel();
    }

    private void setUpPanel() {
        this.setOpaque(false);
        this.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.setLayout(new BorderLayout());

        myGroceryContainer = new JPanel(new VerticalFlowLayout(10));
        myGroceryScrollPane = new JScrollPane(myGroceryContainer);
        myGroceryScrollPane.setOpaque(false);
        myGroceryScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        myGroceryScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        myGroceryScrollPane.setBorder(new LineBorder(claudeWhite, 0));

        this.add(myGroceryScrollPane, BorderLayout.CENTER);
    }

    public void updateMyGrocery() {
        myGroceryContainer.removeAll();

        User user = viewModel.getUser();
        boolean subtractFridgeFromGrocery = LocalAppSetting.isSubtractFridgeFromGrocery();

        if (user != null && !user.getShoppingLists().isEmpty()) {
            for (HashMap.Entry<String, ShoppingList> shoppingList : user.getShoppingLists().entrySet()) {
                ShoppingList items = shoppingList.getValue();
                JPanel shoppingListItem;

                if (subtractFridgeFromGrocery) {
                    ShoppingList adjustedItems = GroceryListHelper.getAdjustedGroceryListForDisplay(items);
                    shoppingListItem = GroceryListItem.createShoppingListItem(adjustedItems);
                } else {
                    shoppingListItem = GroceryListItem.createShoppingListItem(items);
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

    public void setNightMode() {
        myGroceryContainer.setBackground(black);
        updateMyGrocery();
    }

    public void setDayMode() {
        myGroceryContainer.setBackground(claudeWhite);
        updateMyGrocery();
    }
}

