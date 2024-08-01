package use_cases.search_recipe.gui.view_component;

import use_cases._common.gui_common.abstractions.NightModeObject;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases.search_recipe.gui.view.AdvancedSearchView;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class AdvancedSearchButton extends RoundButton implements NightModeObject {
    public AdvancedSearchButton(String text) {
        super(text);
        observeNight();
        this.setPreferredSize(new Dimension(140, 40));
        this.setFont(new Font(defaultFont, Font.PLAIN, 12));
        toggleNightMode();
    }

    @Override
    public void setNightMode() {
        this.setHoverColor(black, black, neonPink, neonPinkEmph);
        this.setBorderColor(getBackground());
        this.setPressedColor(getBackground());
    }

    @Override
    public void setDayMode() {
        this.setHoverColor(claudeWhite, claudeWhite, claudeWhiteEmph, claudeBlackEmph);
        this.setBorderColor(getBackground());
        this.setPressedColor(getBackground());
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
