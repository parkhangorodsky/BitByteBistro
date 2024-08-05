package use_cases.search_recipe.gui.view_component;

import app.local.LocalAppSetting;
import use_cases._common.gui_common.abstractions.NightModeObject;
import use_cases._common.gui_common.abstractions.PopUpView;
import use_cases._common.gui_common.view_components.layouts.VerticalFlowLayout;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases.search_recipe.gui.view.AdvancedSearchView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdvancedSearchTypeOptionConfigPopUp extends PopUpView implements NightModeObject {

    private final AdvancedSearchView parent;
    private final String labelName;
    private final Map<String, String> data;
    private final List<String> selectedOptions;
    private final List<JCheckBox> checkBoxes = new ArrayList<>();

    JPanel mainPanel;
    JPanel titlePanel;
    JLabel titleLabel;

    JPanel checkBoxPanel;
    JScrollPane inputScrollPane;

    JPanel buttonPanel;
    RoundButton applyButton;

    public AdvancedSearchTypeOptionConfigPopUp(AdvancedSearchView parent, String labelName, Map<String, String>data, List<String> selectedOptions) {
        super(parent);

        observeNight();

        this.parent = parent;
        this.labelName = labelName;
        this.data = data;
        this.selectedOptions = selectedOptions;

        mainPanel =  createMainPanel();
        titlePanel = createTitlePanel();
        inputScrollPane = createInputPanel();
        buttonPanel = createButtonPanel();

        mainPanel.add(titlePanel);
        mainPanel.add(inputScrollPane);
        mainPanel.add(buttonPanel);

        toggleNightMode();

        this.add(mainPanel);
        this.pack();

        positionFrameAtCenter(parent);
        this.setEnabled(false);
        this.setVisible(false);
    }

    private JPanel createMainPanel(){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(30, 20, 20, 20));
        mainPanel.setPreferredSize(new Dimension(400, 400));
        mainPanel.setMinimumSize(mainPanel.getPreferredSize());
        mainPanel.setMaximumSize(mainPanel.getPreferredSize());
        return mainPanel;
    }

    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(new EmptyBorder(0,0,10,0));
        titleLabel = new JLabel("Select " + labelName + " Preference");
        titleLabel.setFont(new Font(defaultFont, Font.PLAIN, 16));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        titlePanel.add(titleLabel);
        return titlePanel;
    }

    private JScrollPane createInputPanel() {
        checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new VerticalFlowLayout(5));
        List<String> options = new ArrayList<>(data.keySet());
        options.sort(String::compareToIgnoreCase);
        for (String option : options) {
            JCheckBox checkBox = new JCheckBox(option);
            checkBox.setFont(new Font(secondaryFont, Font.PLAIN, 12));
            checkBox.setOpaque(false);
            checkBox.setForeground(LocalAppSetting.isNightMode() ? neonPink : claudeBlack);
            checkBoxes.add(checkBox);
            checkBoxPanel.add(checkBox);
        }
        inputScrollPane = new JScrollPane(checkBoxPanel);
        inputScrollPane.getVerticalScrollBar().setUnitIncrement(5);
        inputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        inputScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        return inputScrollPane;
    }

    private JPanel createButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(claudeWhite);
        applyButton = new RoundButton("Apply");
        applyButton.setFont(new Font(defaultFont, Font.PLAIN, 12));
        applyButton.setPreferredSize(new Dimension(70, 30));
        applyButton.addActionListener(e -> {
            if (e.getSource() == applyButton) {
                for (JCheckBox checkBox : checkBoxes) {
                    if (checkBox.isSelected()) {
                        if (!selectedOptions.contains(data.get(checkBox.getText()))) {
                            selectedOptions.add(data.get(checkBox.getText()));
                        }
                    }
                }
                parent.displaySummary();
                this.setEnabled(false);
                this.setVisible(false);
            }
        });
        buttonPanel.add(applyButton);
        return buttonPanel;
    }

    @Override
    public void setNightMode() {
        mainPanel.setBackground(black);

        titlePanel.setBackground(black);
        titleLabel.setForeground(lightPurple);

        inputScrollPane.setBackground(black);
        checkBoxPanel.setBackground(black);

        buttonPanel.setBackground(black);
        applyButton.setHoverColor(neonPink, darkPurple, white, white);
        applyButton.setBorderColor(neonPurple);
    }

    @Override
    public void setDayMode() {
        mainPanel.setBackground(claudeWhite);

        titlePanel.setBackground(claudeWhite);
        titleLabel.setForeground(claudeBlack);

        inputScrollPane.setBackground(claudeWhite);
        checkBoxPanel.setBackground(claudeWhite);

        buttonPanel.setBackground(claudeWhite);
        applyButton.setHoverColor(claudeWhite, claudeWhiteEmph, claudeBlackEmph, claudeBlack);
        applyButton.setBorderColor(claudeWhite);
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
