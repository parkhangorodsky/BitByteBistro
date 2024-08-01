package use_cases.search_recipe.gui.view_component;

import use_cases._common.gui_common.abstractions.NightModeObject;
import use_cases._common.gui_common.view_components.round_component.RoundButton;

import java.awt.*;
import java.beans.PropertyChangeEvent;

public class SearchButton extends RoundButton implements NightModeObject {

    public SearchButton() {
        super("Search");

        observeNight();
        this.setPreferredSize(new Dimension(80, 40));
        this.setFont(new Font(defaultFont, Font.PLAIN, 12));
        toggleNightMode();
    }

    @Override
    public void setNightMode() {
        this.setBorderColor(neonPurple);
        this.setHoverColor(neonPink, darkPurple, white, white);
    }

    @Override
    public void setDayMode() {
        this.setHoverColor(claudeWhite, claudeWhiteEmph, claudeBlack, claudeBlack);
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
