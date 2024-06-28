package view;

import entity.Recipe;
import interface_adapter.controller.SearchRecipeController;
import interface_adapter.view_model.SearchRecipeViewModel;
import use_case.interactor.SearchRecipeInteractor;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class SearchRecipeView extends JPanel implements ActionListener, PropertyChangeListener {

    private SearchRecipeViewModel searchRecipeViewModel;
    private SearchRecipeInteractor searchRecipeInteractor;

    public final String viewname = "search recipe";
    private final String fieldLabel = "Search Recipe";
    private final int fieldWidth = 30;
    private final String buttonLabel = "Search";

    private JTextField recipeName;
    private JButton searchButton;
    private JPanel inputPanel;
    private JPanel outputPanel;

    public SearchRecipeView(SearchRecipeViewModel searchRecipeViewModel, SearchRecipeInteractor searchRecipeInteractor) {
        this.searchRecipeInteractor = searchRecipeInteractor;
        this.searchRecipeViewModel = searchRecipeViewModel;
        searchRecipeViewModel.addPropertyChangeListener(this);

        // Initialize input & output panel
        inputPanel = new JPanel();
        inputPanel.setPreferredSize(new Dimension(800,50));
        inputPanel.setMaximumSize(inputPanel.getPreferredSize());

        outputPanel = new JPanel();
        outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));

        // Input Components
        JLabel title = new JLabel(fieldLabel);
        title.setAlignmentX(CENTER_ALIGNMENT);

        recipeName = new JTextField(fieldWidth);
        recipeName.setPreferredSize(new Dimension(100, 30));
        recipeName.setMaximumSize(recipeName.getPreferredSize());

        searchButton = new JButton(buttonLabel);
        searchButton.addActionListener(this);

        // Output Components
        JScrollPane recipeContainer = new JScrollPane(outputPanel);
        recipeContainer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);



        // Pack input & output panel
        inputPanel.add(title);
        inputPanel.add(recipeName);
        inputPanel.add(searchButton);

        // Set Layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(inputPanel);
        this.add(recipeContainer);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(searchButton)) {
            String queryString = recipeName.getText();
            SearchRecipeController controller = new SearchRecipeController(searchRecipeInteractor);
            controller.execute(queryString);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("search recipe".equals(evt.getPropertyName())) {
            List<Recipe> response = (List<Recipe>) evt.getNewValue();

            outputPanel.removeAll();

            for (Recipe recipe : response) {
                System.out.println(recipe.getName());
                JPanel recipePanel = new JPanel();

                JTextArea textArea = new JTextArea(recipe.toString());
                textArea.setWrapStyleWord(true);
                textArea.setLineWrap(true);
                textArea.setEditable(false); // Optional: Set to false if text is not editable
                textArea.setBackground(Color.orange); // Match background color
                textArea.setBorder(BorderFactory.createEmptyBorder(30,30,30,30)); // Optional: Remove border for cleaner look

                // Set fixed width and dynamically adjust height
                int fixedWidth = 600; // Set the desired fixed width
                textArea.setSize(fixedWidth, Short.MAX_VALUE); // Temporarily set size to maximum height
                int preferredHeight = textArea.getPreferredSize().height; // Get the calculated preferred height
                textArea.setPreferredSize(new Dimension(fixedWidth, preferredHeight)); // Set the new preferred size

                recipePanel.setPreferredSize(new Dimension(fixedWidth, preferredHeight + 10)); // Adjust panel height based on textArea height

                recipePanel.add(textArea);
                outputPanel.add(recipePanel);

            }
            outputPanel.revalidate();
            outputPanel.repaint();
            }
        }
    }


