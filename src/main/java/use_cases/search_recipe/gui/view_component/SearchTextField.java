package use_cases.search_recipe.gui.view_component;

import use_cases._common.gui_common.abstractions.NightModeObject;
import use_cases._common.gui_common.view_components.round_component.RoundTextField;

import javax.swing.border.LineBorder;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class SearchTextField extends RoundTextField implements NightModeObject {
    public SearchTextField() {
        super();
        this.setPreferredSize(new Dimension(400, 40));

        this.setFont(new Font(secondaryFont, Font.PLAIN, 14));
        this.setMaximumSize(this.getPreferredSize());
        toggleNightMode();

        this.setPlaceholder("Type in the name of the recipe");
    }

    @Override
    public void setNightMode() {
        this.setColor(mint, black);
        this.setBorder(new LineBorder(mint, 1));
        this.setMargin(new Insets(5, 15, 5, 15));
        this.setForeground(black);
    }

    @Override
    public void setDayMode() {
        this.setColor(claudeWhite, claudeWhiteEmph);
        this.setBorder(new LineBorder(claudeWhiteEmph, 1));
        this.setMargin(new Insets(5, 15, 5, 15));
        this.setForeground(claudeBlack);
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
