package use_cases.setting_preference;

import app.local.LocalAppSetting;
import app.local.LoggedUserData;
import use_cases._common.gui_common.abstractions.NightModeObject;
import use_cases._common.gui_common.abstractions.PopUpView;
import use_cases._common.gui_common.view_components.layouts.VerticalFlowLayout;
import use_cases._common.gui_common.view_components.round_component.RoundButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class PreferenceView extends PopUpView implements NightModeObject {

    JFrame parent;
    SetPreferenceController controller;

    JPanel mainPanel;

    JLabel titleLabel;

    RoundButton applyButton;
    RoundButton closeButton;

    JCheckBox nightModeCheckBox;
    JCheckBox subtractFridgeFromGroceryCheckBox;

    public PreferenceView(JFrame parent, SetPreferenceController setPreferenceController) {
        super(parent);
        observeNight();

        this.parent = parent;
        this.controller = setPreferenceController;

        mainPanel = new JPanel(new BorderLayout(10,10));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        mainPanel.setPreferredSize(new Dimension(300, 300));

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);
        titleLabel = new JLabel("Preferences");
        titlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        titleLabel.setFont(new Font(defaultFont, Font.PLAIN, 16));
        titlePanel.add(titleLabel, BorderLayout.NORTH);

        JPanel settingPanel = new JPanel(new VerticalFlowLayout(5));
        settingPanel.setOpaque(false);
        settingPanel.setBorder(new EmptyBorder(30, 5, 5, 5));
        nightModeCheckBox = new JCheckBox("Night Mode");
        nightModeCheckBox.setFont(new Font(defaultFont, Font.PLAIN, 12));
        subtractFridgeFromGroceryCheckBox = new JCheckBox("Subtract Fridge from Grocery");
        subtractFridgeFromGroceryCheckBox.setFont(new Font(defaultFont, Font.PLAIN, 12));
        settingPanel.add(nightModeCheckBox);
        settingPanel.add(subtractFridgeFromGroceryCheckBox);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        applyButton = new RoundButton("Apply");
        applyButton.addActionListener(e -> {
            boolean nightModeSelected = nightModeCheckBox.isSelected();
            boolean subtractFridgeSelected = subtractFridgeFromGroceryCheckBox.isSelected();

            // Debugging output to verify the settings are being applied
            System.out.println("Night Mode Selected: " + nightModeSelected);
            System.out.println("Subtract Fridge Selected: " + subtractFridgeSelected);

            // This will apply the settings and ensure the view reflects the changes
            controller.execute(nightModeSelected, subtractFridgeSelected);

            // Manually trigger an update for grocery view to reflect the setting change
            LocalAppSetting.setSubtractFridgeFromGrocery(subtractFridgeSelected); // Update the local app setting
            this.firePropertyChange("subtractFridgeFromGrocery", !subtractFridgeSelected, subtractFridgeSelected);

            this.hidePopUp();
        });

        closeButton = new RoundButton("Close");
        closeButton.addActionListener(e -> {
            this.hidePopUp();
        });

        loadPreference();

        buttonPanel.add(applyButton);
        buttonPanel.add(closeButton);

        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(settingPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        toggleNightMode();

        this.add(mainPanel);
        this.pack();
        this.positionFrameAtCenter(parent);
    }

    private void loadPreference() {
        if ((boolean) LoggedUserData.getLoggedInUser().getPreference().get("nightMode")) {
            nightModeCheckBox.setSelected(true);
        }
    }



    private void updateNightModeCheckBox(boolean nightMode) {
        if (nightMode) {
            nightModeCheckBox.setSelected(true);
        } else {
            nightModeCheckBox.setSelected(false);
        }
    }

    private void updateSubtractFridgeFromGroceryCheckBox(boolean subtractFridgeFromGrocery) {
        subtractFridgeFromGroceryCheckBox.setSelected(subtractFridgeFromGrocery);
    }

    @Override
    public void setNightMode() {
        updateNightModeCheckBox(LocalAppSetting.isNightMode());
        mainPanel.setBackground(black);
        nightModeCheckBox.setForeground(neonPinkEmph);

        titleLabel.setForeground(neonPurpleEmph);

        closeButton.setHoverColor(darkPurple, neonPinkEmph, white, white);
        closeButton.setBorderColor(neonPurple);

        applyButton.setHoverColor(darkPurple, neonPinkEmph, white, white);
        applyButton.setBorderColor(neonPurple);

        updateSubtractFridgeFromGroceryCheckBox(LocalAppSetting.isSubtractFridgeFromGrocery());
    }

    @Override
    public void setDayMode() {
        updateNightModeCheckBox(LocalAppSetting.isNightMode());
        updateSubtractFridgeFromGroceryCheckBox(LocalAppSetting.isSubtractFridgeFromGrocery());
        mainPanel.setBackground(claudeWhite);
        nightModeCheckBox.setForeground(claudeBlack);

        titleLabel.setForeground(claudeBlack);

        closeButton.setHoverColor(claudeWhite, claudeWhiteEmph, claudeBlackEmph, claudeWhite);
        closeButton.setBorderColor(claudeWhiteEmph);
        applyButton.setHoverColor(claudeWhite, claudeWhiteEmph, claudeBlackEmph, claudeWhite);
        applyButton.setBorderColor(claudeWhiteEmph);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("nightMode".equals(evt.getPropertyName())) {
            toggleNightMode();
            this.revalidate();
            this.repaint();
        } else if ("subtractFridgeFromGrocery".equals(evt.getPropertyName())) {
            updateSubtractFridgeFromGroceryCheckBox((Boolean) evt.getNewValue());
            this.revalidate();
            this.repaint();
        }
    }
}
