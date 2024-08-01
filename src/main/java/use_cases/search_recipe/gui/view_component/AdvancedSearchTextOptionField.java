package use_cases.search_recipe.gui.view_component;

import use_cases._common.gui_common.abstractions.NightModeObject;
import use_cases._common.gui_common.abstractions.ThemeColoredObject;
import use_cases._common.gui_common.abstractions.ViewComponent;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases._common.gui_common.view_components.round_component.RoundTextField;
import use_cases.search_recipe.gui.view.AdvancedSearchView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.util.List;

public class AdvancedSearchTextOptionField extends ViewComponent implements ThemeColoredObject, NightModeObject {

    JPanel leftPanel;
    JLabel label;
    RoundTextField textField;
    RoundButton applyButton;

    public AdvancedSearchTextOptionField(AdvancedSearchView parent, String labelName, List<String> excludedIngredients) {
        // Panel
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        // Label
        label = new JLabel(labelName);

        label.setOpaque(false);
        label.setFont(new Font(defaultFont, Font.PLAIN, 12));
        label.setBorder(new EmptyBorder(0, 0, 5, 0));
        label.setAlignmentX(LEFT_ALIGNMENT);
        leftPanel.add(label);

        // Main Textfield
        textField = new RoundTextField();
        textField.setPreferredSize(new Dimension(310, 30));
        textField.setBorder(new EmptyBorder(0, 10, 0, 10));
        textField.setMaximumSize(textField.getPreferredSize());
        textField.setMinimumSize(textField.getPreferredSize());
        textField.setAlignmentX(LEFT_ALIGNMENT);
        textField.setPlaceholder(labelName);
        textField.setFont(new Font(secondaryFont, Font.PLAIN, 12));
        leftPanel.add(textField);

        // Apply Button
        applyButton = new RoundButton("Add");
        applyButton.setFont(new Font(defaultFont, Font.PLAIN, 12));

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

        toggleNightMode();

        this.add(leftPanel);
        this.add(Box.createHorizontalStrut(10));
        this.add(applyButton);
    }

    @Override
    public void setDayMode() {
        this.setBackground(claudeWhite);
        leftPanel.setBackground(claudeWhite);

        label.setForeground(claudeBlackEmph);
        applyButton.setHoverColor(claudewhiteBright, claudeWhiteEmph, claudeBlackEmph, claudeBlack);
        applyButton.setBorderColor(claudeWhiteEmph);

        textField.setColor(claudewhiteBright, claudeWhiteEmph);
        textField.setForeground(claudeBlack);
    }

    @Override
    public void setNightMode() {
        this.setBackground(black);
        leftPanel.setBackground(black);

        label.setForeground(neonPinkEmph);
        applyButton.setHoverColor(neonPink, darkPurple, white, white);
        applyButton.setBorderColor(neonPurple);

        textField.setColor(black, neonPink);
        textField.setForeground(mint);
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
