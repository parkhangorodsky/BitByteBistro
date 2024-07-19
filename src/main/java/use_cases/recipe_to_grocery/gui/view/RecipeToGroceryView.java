package use_cases.recipe_to_grocery.gui.view;

import entity.Recipe;
import use_cases._common.gui_common.abstractions.View;
import use_cases._common.gui_common.view.Sidebar;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases._common.gui_common.view_components.round_component.RoundTextField;
import use_cases.recipe_to_grocery.interface_adapter.controller.RecipeToGroceryController;
import use_cases.recipe_to_grocery.interface_adapter.presenter.RecipeToGroceryPresenter;
import use_cases.recipe_to_grocery.interface_adapter.view_model.RecipeToGroceryViewModel;
import use_cases.recipe_to_grocery.use_case.output_data.RecipeToGroceryOutputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;


public class RecipeToGroceryView extends View {

    private RecipeToGroceryViewModel recipeToGroceryViewModel;
    private RecipeToGroceryController recipeToGroceryController;
    private RecipeToGroceryPresenter recipeToGroceryPresenter;


    public final String viewname = "recipe to grocery";

    // Components
    private RoundTextField recipeName;
    private RoundButton searchButton;
    private JPanel inputPanel;
    private JPanel outputPanel;
    private JScrollPane recipeContainer;



    public RecipeToGroceryView(RecipeToGroceryViewModel recipeToGroceryViewModel,
                            RecipeToGroceryController recipeToGroceryController) {

        // Add PropertyChangeListener to corresponding ViewModel
        this.recipeToGroceryViewModel = recipeToGroceryViewModel;
        recipeToGroceryViewModel.addPropertyChangeListener(this);

        // Make connection to Controller
        this.recipeToGroceryController = recipeToGroceryController;

        // Set Layout
        this.setLayout(new BorderLayout());

        // Sidebar
        Sidebar sidebar = new Sidebar();

        // MainPanel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Initialize input & output panel
        inputPanel = new JPanel();
        inputPanel.setBackground(claudeWhite);
        inputPanel.setPreferredSize(new Dimension(800,80));
        inputPanel.setMaximumSize(inputPanel.getPreferredSize());
        inputPanel.setBorder(BorderFactory.createLineBorder(claudeWhite, 20));
        inputPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 3, 5));

        outputPanel = new JPanel();
        outputPanel.setBackground(claudeWhite);
        outputPanel.setBorder(BorderFactory.createLineBorder(claudeWhite, 20));
        outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));


        // Input Components

        // Output Components


        // Pack input & output panel
//        inputPanel.add(title);
        inputPanel.add(recipeName);
        inputPanel.add(searchButton);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(recipeContainer, BorderLayout.CENTER);

        this.add(sidebar, BorderLayout.WEST);
        this.add(mainPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("recipe to grocery".equals(evt.getPropertyName())) {}
        outputPanel.revalidate();
        outputPanel.repaint();
    }

    @Override
    public String getViewName() {
        return this.viewname;
    }


}


