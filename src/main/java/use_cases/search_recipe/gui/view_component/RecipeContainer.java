package use_cases.search_recipe.gui.view_component;

import use_cases._common.gui_common.abstractions.NightModeObject;
import use_cases._common.gui_common.abstractions.ThemeColoredObject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class RecipeContainer extends  JScrollPane implements ThemeColoredObject, NightModeObject {
    public RecipeContainer(JPanel target) {
        super(target);
        observeNight();
        this.getVerticalScrollBar().setUnitIncrement(5);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        toggleNightMode();
    }

    @Override
    public void setNightMode() {
        this.setBackground(Color.BLACK);
        this.setBorder(new EmptyBorder(30,30,30,30));
    }

    @Override
    public void setDayMode() {
        this.setBackground(claudeWhite);
        this.setBorder(new EmptyBorder(30,30,30,30));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("nightMode")) {
            toggleNightMode();
            this.revalidate();
            this.repaint();
        }
    }
}
