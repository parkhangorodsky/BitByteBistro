package use_cases.search_recipe.gui.view_component;

import use_cases._common.gui_common.abstractions.PopUpView;
import use_cases._common.gui_common.abstractions.ViewComponent;
import use_cases._common.gui_common.view_components.round_component.RoundButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdvancedSearchTypeOptionButton extends ViewComponent {

    public AdvancedSearchTypeOptionButton(String labelName, PopUpView popUp) {
            this.setBackground(claudeWhite);
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            JLabel label = new JLabel(labelName);
            label.setForeground(claudeBlackEmph);
            label.setOpaque(true);
            label.setFont(new Font(defaultFont, Font.PLAIN, 12));
            label.setAlignmentX(LEFT_ALIGNMENT);
            label.setBorder(new EmptyBorder(0, 0, 5, 0));

            RoundButton configureButton = new RoundButton("Configure" + " " + labelName);
            configureButton.setFont(new Font(defaultFont, Font.PLAIN, 12));
            configureButton.setHoverColor(claudewhiteBright, claudeWhiteEmph, claudeWhiteEmph, claudeBlack);
            configureButton.setPreferredSize(new Dimension(390, 30));
            configureButton.setMaximumSize(configureButton.getPreferredSize());
            configureButton.setMinimumSize(configureButton.getPreferredSize());

            configureButton.addActionListener(e -> {
                if (e.getSource() == configureButton) {
                    popUp.setEnabled(true);
                    popUp.setVisible(true);
                }
            });
            this.add(label);
            this.add(configureButton);
        }
}

