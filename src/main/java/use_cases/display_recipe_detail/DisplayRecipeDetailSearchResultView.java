package use_cases.display_recipe_detail;

import entity.Recipe;
import use_cases._common.gui_common.abstractions.ImageLoader;
import use_cases._common.gui_common.view_components.round_component.RoundButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DisplayRecipeDetailSearchResultView extends DisplayRecipeDetailView {

    public DisplayRecipeDetailSearchResultView(JFrame parent, DisplayRecipeDetailViewModel viewModel) {
        super(parent, viewModel);
    }


    @Override
    public JPanel createControlPanel() {

        JPanel controlPanel = new JPanel(new BorderLayout());
        controlPanel.setBorder(new EmptyBorder(10, 30, 10, 30));
        controlPanel.setBackground(claudeWhite);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        RoundButton closeButton = new RoundButton("Close");
        RoundButton addToButton = new RoundButton("Add To");

        closeButton.addActionListener(e -> {
            this.dispose();
        });

        buttonPanel.add(addToButton);
        buttonPanel.add(closeButton);

        controlPanel.add(buttonPanel, BorderLayout.EAST);

        return controlPanel;
    };


}
