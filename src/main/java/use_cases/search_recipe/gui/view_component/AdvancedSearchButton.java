package use_cases.search_recipe.gui.view_component;

import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases.search_recipe.gui.view.AdvancedSearchView;

import javax.swing.*;
import java.awt.*;

public class AdvancedSearchButton extends RoundButton {
    public AdvancedSearchButton(String text) {
        super(text);
        this.setPreferredSize(new Dimension(140, 40));
        this.setHoverColor(claudeWhite, claudeWhite, claudeWhiteEmph, claudeBlackEmph);
        this.setBorderColor(getBackground());
        this.setPressedColor(getBackground());
        this.setFont(new Font(defaultFont, Font.PLAIN, 12));
    }
}
