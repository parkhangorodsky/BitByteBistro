package use_cases.core_functionality.view_component;

import use_cases._common.gui_common.view_components.layouts.VerticalFlowLayout;
import use_cases._common.gui_common.abstractions.ThemeColoredObject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class OutputPanel extends JPanel {
    private JPanel myGroceryContainer;
    private JScrollPane myGroceryScrollPane;

    public OutputPanel() {
        setOpaque(false);
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setLayout(new BorderLayout());

        myGroceryContainer = new JPanel(new VerticalFlowLayout(10));
        myGroceryScrollPane = new JScrollPane(myGroceryContainer);
        myGroceryScrollPane.setOpaque(false);
        myGroceryScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        myGroceryScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        myGroceryScrollPane.setBorder(new LineBorder(ThemeColoredObject.claudeWhite, 0));

        add(myGroceryScrollPane, BorderLayout.CENTER);
    }

    public JPanel getMyGroceryContainer() {
        return myGroceryContainer;
    }

    public JScrollPane getMyGroceryScrollPane() {
        return myGroceryScrollPane;
    }
}