package use_cases.search_recipe.gui.view_component;

import use_cases._common.gui_common.abstractions.NightModeObject;
import use_cases._common.gui_common.abstractions.PopUpView;
import use_cases._common.gui_common.abstractions.ThemeColoredObject;
import use_cases._common.gui_common.abstractions.ViewComponent;
import use_cases._common.gui_common.view_components.round_component.RoundButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class AdvancedSearchTypeOptionButton extends ViewComponent implements ThemeColoredObject, NightModeObject {

    JLabel label;
    RoundButton configureButton;

    public AdvancedSearchTypeOptionButton(String labelName, PopUpView popUp) {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            label = new JLabel(labelName);

            label.setOpaque(false);
            label.setFont(new Font(defaultFont, Font.PLAIN, 12));
            label.setAlignmentX(LEFT_ALIGNMENT);
            label.setBorder(new EmptyBorder(0, 0, 5, 0));

            configureButton = new RoundButton("Configure" + " " + labelName);
            configureButton.setFont(new Font(defaultFont, Font.PLAIN, 12));

            configureButton.setPreferredSize(new Dimension(390, 30));
            configureButton.setMaximumSize(configureButton.getPreferredSize());
            configureButton.setMinimumSize(configureButton.getPreferredSize());

            configureButton.addActionListener(e -> {
                if (e.getSource() == configureButton) {
                    popUp.setEnabled(true);
                    popUp.setVisible(true);
                }
            });

            toggleNightMode();

            this.add(label);
            this.add(configureButton);
        }

    @Override
    public void setNightMode() {
        this.setBackground(black);
        label.setForeground(neonPinkEmph);
        configureButton.setHoverColor(neonPink, darkPurple, white, white);
        configureButton.setBorderColor(neonPurple);
    }

    @Override
    public void setDayMode() {
        this.setBackground(claudeWhite);
        label.setForeground(claudeBlackEmph);
        configureButton.setHoverColor(claudewhiteBright, claudeWhiteEmph, claudeBlackEmph, claudeBlack);
        configureButton.setBorderColor(claudeWhiteEmph);
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

