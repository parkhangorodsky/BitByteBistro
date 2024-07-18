package use_cases.search_recipe.gui.view_component;

import use_cases._common.gui_common.abstractions.ThemeColoredObject;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class RecipeContainer extends  JScrollPane implements ThemeColoredObject {
    public RecipeContainer(JPanel target) {
        super(target);
        this.getVerticalScrollBar().setUnitIncrement(5);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        this.setBorder(new LineBorder(claudeWhite, 30));
    }
}
