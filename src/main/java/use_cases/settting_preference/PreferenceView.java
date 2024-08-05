package use_cases.settting_preference;

import app.LocalAppSetting;
import entity.LoggedUserData;
import use_cases._common.gui_common.abstractions.NightModeObject;
import use_cases._common.gui_common.abstractions.PopUpView;
import use_cases._common.gui_common.abstractions.View;
import use_cases._common.gui_common.view_components.layouts.VerticalFlowLayout;
import use_cases._common.gui_common.view_components.round_component.RoundButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class PreferenceView extends PopUpView implements NightModeObject {

    JFrame parent;
    SetPreferenceController controller;

    JCheckBox nightModeCheckBox;

    public PreferenceView(JFrame parent, SetPreferenceController setPreferenceController) {
        super(parent);
        observeNight();

        this.parent = parent;
        this.controller = setPreferenceController;

        JPanel mainPanel = new JPanel(new BorderLayout(10,10));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        mainPanel.setPreferredSize(new Dimension(300, 300));

        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Preferences");
        titleLabel.setFont(new Font(defaultFont, Font.PLAIN, 16));
        titlePanel.add(titleLabel, BorderLayout.NORTH);

        JPanel settingPanel = new JPanel(new VerticalFlowLayout(5));
        settingPanel.setBorder(new EmptyBorder(30, 5, 5, 5));
        nightModeCheckBox = new JCheckBox("Night Mode");
        nightModeCheckBox.setFont(new Font(defaultFont, Font.PLAIN, 12));
        settingPanel.add(nightModeCheckBox);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        RoundButton applyButton = new RoundButton("Apply");
        applyButton.addActionListener(e -> {
            controller.execute(nightModeCheckBox.isSelected());
            this.hidePopUp();
        });

        RoundButton closeButton = new RoundButton("Close");
        closeButton.addActionListener(e -> {
            this.hidePopUp();
        });

        loadPreference();

        buttonPanel.add(applyButton);
        buttonPanel.add(closeButton);

        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(settingPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

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


    @Override
    public void setNightMode() {
        updateNightModeCheckBox(LocalAppSetting.isNightMode());

    }

    @Override
    public void setDayMode() {
        updateNightModeCheckBox(LocalAppSetting.isNightMode());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
