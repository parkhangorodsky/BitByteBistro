package use_cases.recipe_to_grocery.gui.view_component;

import use_cases._common.gui_common.abstractions.ThemeColoredObject;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * ShoppingListContainer is a specialized JScrollPane that wraps around a JPanel
 * to display a shopping list with specific styling and behavior.
 * It implements the ThemeColoredObject interface for consistent theme support.
 */
public class ShoppingListContainer extends JScrollPane implements ThemeColoredObject {

    /**
     * Constructs a ShoppingListContainer with the specified JPanel as its target.
     *
     * @param target The JPanel to be displayed inside the scroll pane.
     */
    public ShoppingListContainer(JPanel target) {
        super(target);

        // Set vertical scroll bar properties
        this.getVerticalScrollBar().setUnitIncrement(5);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        // Set border with a specific line color and thickness
        this.setBorder(new LineBorder(claudeWhite, 30));
    }
}