package use_cases.core_functionality.view_component;

import use_cases.core_functionality.MyGroceryView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class InputPanel extends JPanel {
    private final MyGroceryView parentView; // Reference to MyGroceryView

    public InputPanel(MyGroceryView parentView) {
        this.parentView = parentView; // Initialize reference
        setOpaque(false);
        setPreferredSize(new Dimension(800, 100));
        setMaximumSize(getPreferredSize());
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setLayout(new FlowLayout(FlowLayout.RIGHT, 3, 5));

        JButton addNewGroceryListButton = new JButton("Make new grocery list...");
        addNewGroceryListButton.addActionListener(e -> parentView.showNewGroceryListInput());
        add(addNewGroceryListButton);
    }
}
