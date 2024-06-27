package view;

import entity.Recipe;
import interface_adapter.controller.SearchRecipeController;
import interface_adapter.view_model.SearchRecipeViewModel;
import use_case.interactor.SearchRecipeInteractor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.*;
import java.util.List;

public class SearchRecipeView extends JPanel implements ActionListener, PropertyChangeListener{

    SearchRecipeInteractor searchRecipeInteractor;

    public final String viewname = "search recipe";
    private final String fieldLabel = "Search Recipe";
    private final int fieldwWidth = 30;
    private final String buttonLabel = "Search";

    public JTextField recipeName;
    public JButton searchButton;


    public SearchRecipeView(SearchRecipeInteractor searchRecipeInteractor) {

        this.searchRecipeInteractor = searchRecipeInteractor;

        // Initialize input & output panel
        JPanel inputPanel = new JPanel();
        JPanel outputPanel = new JPanel();

        // Input Components
        JLabel title = new JLabel(fieldLabel);
        title.setAlignmentX(CENTER_ALIGNMENT);

        this.recipeName = new JTextField(fieldwWidth);

        searchButton = new JButton(buttonLabel);
        searchButton.addActionListener(this);

        // Output Components
        JScrollPane recipePanel = new JScrollPane(recipeName);


        // Pack input & output panel
        inputPanel.add(title);
        inputPanel.add(recipeName);
        inputPanel.add(searchButton);

        // Set Layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(inputPanel);
        this.add(outputPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(searchButton)) {
            String queryString = this.recipeName.getText();
            SearchRecipeController controller = new SearchRecipeController(searchRecipeInteractor);
            controller.execute(queryString);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
