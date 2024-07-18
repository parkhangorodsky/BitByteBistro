package use_cases.search_recipe.gui.view_component;

import use_cases._common.gui_common.view_components.round_component.RoundButton;

import java.awt.*;

public class SearchButton extends RoundButton {

    public SearchButton(String text) {
        super(text);
        this.setPreferredSize(new Dimension(80, 40));
        this.setHoverColor(claudeWhite, claudeWhiteEmph, claudeBlack, claudeBlack);
        this.setForegroundColor(claudeBlack);
        this.setFont(new Font(defaultFont, Font.PLAIN, 12));
    }
}
