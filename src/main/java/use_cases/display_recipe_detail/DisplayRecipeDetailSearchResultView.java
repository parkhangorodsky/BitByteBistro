package use_cases.display_recipe_detail;

import use_cases._common.gui_common.abstractions.NightModeObject;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases.add_to_my_recipe.AddToMyRecipeController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class DisplayRecipeDetailSearchResultView extends DisplayRecipeDetailView implements NightModeObject {
    private AddToMyRecipeController addToMyRecipeController;

    JPanel controlPanel;

    JPanel buttonPanel;
    RoundButton closeButton;
    RoundButton addToButton;

    public DisplayRecipeDetailSearchResultView(JFrame parent, DisplayRecipeDetailViewModel viewModel, AddToMyRecipeController addToMyRecipeController) {
        super(parent, viewModel);
        this.addToMyRecipeController = addToMyRecipeController;
    }

    protected void initialize() {
        super.initialize();
    }

    @Override
    public JPanel createControlPanel() {

        controlPanel = new JPanel(new BorderLayout());
        controlPanel.setBorder(new EmptyBorder(10, 30, 10, 30));

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        closeButton = new RoundButton("Close");
        addToButton = new RoundButton("Add To");

        closeButton.addActionListener(e -> {
            this.dispose();
        });

        addToButton.addActionListener(e -> {
            addToMyRecipeController.execute(viewModel.getRecipe(), viewModel);
        });

        buttonPanel.add(addToButton);
        buttonPanel.add(closeButton);

        controlPanel.add(buttonPanel, BorderLayout.EAST);

        toggleNightMode();

        return controlPanel;
    };
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("recipe already exists")) {
            JOptionPane.showMessageDialog((JFrame) SwingUtilities.getWindowAncestor(this),
                    "Recipe already exists.",
                    "",
                    JOptionPane.ERROR_MESSAGE);
        } else if (evt.getPropertyName().equals("added recipe")) {
            JOptionPane.showMessageDialog((JFrame) SwingUtilities.getWindowAncestor(this),
                    "Succesfully added.", "",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (evt.getPropertyName().equals("nightMode")) {
            toggleNightMode();
            this.revalidate();
            this.repaint();
        } else {
            super.propertyChange(evt);
        }
    }

    @Override
    public void setNightMode() {
        controlPanel.setBackground(black);
        buttonPanel.setBackground(black);

        closeButton.setHoverColor(neonPink, darkPurple, white, white);
        closeButton.setBorderColor(neonPurple);
        addToButton.setHoverColor(neonPink, darkPurple, white, white);
        addToButton.setBorderColor(neonPurple);

        super.setNightMode();
    }

    @Override
    public void setDayMode() {
        controlPanel.setBackground(claudeWhite);
        buttonPanel.setBackground(claudeWhite);

        closeButton.setHoverColor(claudeWhite, claudeWhiteEmph, claudeBlackEmph, claudeWhite);
        closeButton.setBorderColor(claudeWhite);
        addToButton.setHoverColor(claudeWhite, claudeWhiteEmph, claudeBlackEmph, claudeWhite);
        addToButton.setBorderColor(claudeWhite);

        super.setDayMode();
    }


}
