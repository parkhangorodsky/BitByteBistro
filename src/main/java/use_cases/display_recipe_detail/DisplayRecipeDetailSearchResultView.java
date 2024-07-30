package use_cases.display_recipe_detail;

import entity.LoggedUserData;
import entity.Recipe;
import use_cases._common.gui_common.abstractions.ImageLoader;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;
import use_cases.add_to_my_recipe.AddToMyRecipeController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class DisplayRecipeDetailSearchResultView extends DisplayRecipeDetailView {
    private AddToMyRecipeController addToMyRecipeController;

    public DisplayRecipeDetailSearchResultView(JFrame parent, DisplayRecipeDetailViewModel viewModel, AddToMyRecipeController addToMyRecipeController) {
        super(parent, viewModel);
        this.addToMyRecipeController = addToMyRecipeController;
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

        addToButton.addActionListener(e -> {
            addToMyRecipeController.execute(viewModel.getRecipe(), viewModel);
        });

        buttonPanel.add(addToButton);
        buttonPanel.add(closeButton);

        controlPanel.add(buttonPanel, BorderLayout.EAST);

        return controlPanel;
    };
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("recipe already exists")) {
            JOptionPane.showMessageDialog((JFrame) SwingUtilities.getWindowAncestor(this),
                    "Recipe already exists!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            super.propertyChange(evt);
        }
    }


}
