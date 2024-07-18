package use_cases.search_recipe.gui.view_component;

import use_cases._common.gui_common.view_components.round_component.RoundTextField;

import javax.swing.border.LineBorder;
import java.awt.*;

public class SearchTextField extends RoundTextField {
    public SearchTextField() {
        super();
        this.setPreferredSize(new Dimension(400, 40));
        this.setPlaceholder("Type in the name of the recipe");
        this.setBackground(claudeWhite);
        this.setForeground(claudeBlack);
        this.setBorder(new LineBorder(claudeWhiteEmph, 1));
        this.setMargin(new Insets(5, 15, 5, 15));
        this.setFont(new Font(secondaryFont, Font.PLAIN, 14));
        this.setMaximumSize(this.getPreferredSize());
    }
}
