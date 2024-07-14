package use_cases.search_recipe.gui.view_component;

import use_cases._common.gui_common.abstractions.PopUpView;
import use_cases._common.gui_common.view_components.layouts.VerticalFlowLayout;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases.search_recipe.gui.view.AdvancedSearchView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdvancedSearchTypeOptionConfigPopUp extends PopUpView {

    private final AdvancedSearchView parent;
    private final String labelName;
    private final Map<String, String> data;
    private final List<String> selectedOptions;
    private final List<JCheckBox> checkBoxes = new ArrayList<>();

    public AdvancedSearchTypeOptionConfigPopUp(AdvancedSearchView parent, String labelName, Map<String, String>data, List<String> selectedOptions) {
        super(parent);
        this.parent = parent;
        this.labelName = labelName;
        this.data = data;
        this.selectedOptions = selectedOptions;

        JPanel mainPanel =  createMainPanel();
        JPanel titlePanel = createTitlePanel();
        JScrollPane inputPanel = createInputPanel();
        JPanel buttonPanel = createButtonPanel();

        mainPanel.add(titlePanel);
        mainPanel.add(inputPanel);
        mainPanel.add(buttonPanel);

        this.add(mainPanel);
        this.pack();

        positionFrameAtCenter(parent);
        this.setEnabled(false);
        this.setVisible(false);
    }

    private JPanel createMainPanel(){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(claudeWhite);
        mainPanel.setBorder(new EmptyBorder(30, 20, 20, 20));
        mainPanel.setPreferredSize(new Dimension(400, 400));
        mainPanel.setMinimumSize(mainPanel.getPreferredSize());
        mainPanel.setMaximumSize(mainPanel.getPreferredSize());
        return mainPanel;
    }

    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(claudeWhite);
        titlePanel.setBorder(new EmptyBorder(0,0,10,0));
        JLabel titleLabel = new JLabel("Select " + labelName + " Preference");
        titleLabel.setFont(new Font(defaultFont, Font.PLAIN, 12));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        titlePanel.setBackground(claudeWhite);
        titleLabel.setForeground(claudeBlack);
        titlePanel.add(titleLabel);
        return titlePanel;
    }

    private JScrollPane createInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new VerticalFlowLayout(5));
        inputPanel.setBackground(claudeWhite);
        List<String> options = new ArrayList<>(data.keySet());
        options.sort(String::compareToIgnoreCase);
        for (String option : options) {
            JCheckBox checkBox = new JCheckBox(option);
            checkBox.setFont(new Font(secondaryFont, Font.PLAIN, 12));
            checkBox.setBackground(claudewhiteBright);
            checkBox.setForeground(claudeBlack);
            checkBoxes.add(checkBox);
            inputPanel.add(checkBox);
        }
        JScrollPane scrollInputPanel = new JScrollPane(inputPanel);
        scrollInputPanel.getVerticalScrollBar().setUnitIncrement(5);
        scrollInputPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollInputPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        return scrollInputPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(claudeWhite);
        RoundButton applyButton = new RoundButton("Apply");
        applyButton.setFont(new Font(defaultFont, Font.PLAIN, 12));
        applyButton.setHoverColor(claudeWhite, claudeWhiteEmph, claudeBlackEmph, claudeBlack);
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
}
