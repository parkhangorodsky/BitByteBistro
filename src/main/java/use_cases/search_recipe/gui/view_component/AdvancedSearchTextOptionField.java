package use_cases.search_recipe.gui.view_component;

import use_cases._common.gui_common.abstractions.ViewComponent;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases._common.gui_common.view_components.round_component.RoundTextField;
import use_cases.search_recipe.gui.view.AdvancedSearchView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class AdvancedSearchTextOptionField extends ViewComponent {

    public AdvancedSearchTextOptionField(AdvancedSearchView parent, String labelName, List<String> excludedIngredients) {
        // Panel
        this.setBackground(claudeWhite);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(claudeWhite);

        // Label
        JLabel label = new JLabel(labelName);
        label.setForeground(claudeBlackEmph);
        label.setOpaque(true);
        label.setFont(new Font(defaultFont, Font.PLAIN, 12));
        label.setBorder(new EmptyBorder(0, 0, 5, 0));
        label.setAlignmentX(LEFT_ALIGNMENT);
        leftPanel.add(label);

        // Main Textfield
        RoundTextField textField = new RoundTextField();
        textField.setPreferredSize(new Dimension(310, 30));
        textField.setBorder(new EmptyBorder(0, 10, 0, 10));
        textField.setMaximumSize(textField.getPreferredSize());
        textField.setMinimumSize(textField.getPreferredSize());
        textField.setAlignmentX(LEFT_ALIGNMENT);
        textField.setPlaceholder(labelName);
        textField.setFont(new Font(secondaryFont, Font.PLAIN, 12));
        textField.setBackground(claudewhiteBright);
        leftPanel.add(textField);

        // Apply Button
        RoundButton applyButton = new RoundButton("Add");
        applyButton.setFont(new Font(defaultFont, Font.PLAIN, 12));
        applyButton.setHoverColor(claudewhiteBright, claudeWhiteEmph, claudeWhiteEmph, claudeBlack);
        applyButton.setPreferredSize(new Dimension(70, 30));
        applyButton.addActionListener(e -> {
            if (e.getSource() == applyButton) {
                String excluded = textField.getText();
                if (!excludedIngredients.contains(excluded)) {
                    excludedIngredients.add(excluded);
                }
                parent.displaySummary();
            }
        });

        leftPanel.setAlignmentY(BOTTOM_ALIGNMENT);
        applyButton.setAlignmentY(BOTTOM_ALIGNMENT);

        this.add(leftPanel);
        this.add(Box.createHorizontalStrut(10));
        this.add(applyButton);
    }
}
