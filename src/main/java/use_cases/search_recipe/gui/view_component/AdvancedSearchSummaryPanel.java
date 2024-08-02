package use_cases.search_recipe.gui.view_component;


import use_cases._common.gui_common.view_components.round_component.RoundPanel;
import use_cases.search_recipe.interface_adapter.view_model.AdvancedSearchRecipeViewModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdvancedSearchSummaryPanel extends RoundPanel {

    JPanel summaryTextPanel;

    public AdvancedSearchSummaryPanel(AdvancedSearchRecipeViewModel advancedSearchRecipeViewModel) {
        this.setLayout(new BorderLayout());
        this.setBackground(claudeWhite);
        this.setPreferredSize(new Dimension(390, 300));
        this.setMinimumSize(this.getPreferredSize());
        this.setBorder(new EmptyBorder(17, 30, 0, 30));
        summaryTextPanel = new RoundPanel();
        summaryTextPanel.setBackground(claudeWhite);
        summaryTextPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 8, 8));
        this.add(summaryTextPanel, BorderLayout.CENTER);
    }

}
