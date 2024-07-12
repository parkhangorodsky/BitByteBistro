package view;

import entity.Recipe;
import interface_adapter.controller.RecipeToGroceryController;
import interface_adapter.view_model.RecipeToGroceryViewModel;
import use_case.output_data.RecipeToGroceryOutputData;
import view.view_components.*;
import view.view_components.round_component.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;


public class RecipeToGroceryView extends JPanel implements View, ImageLoader {

    private RecipeToGroceryViewModel recipeToGroceryViewModel;
    private RecipeToGroceryController recipeToGroceryController;

    public final String viewname = "recipe to grocery";

    // Components
    private RoundedJTextField recipeName;
    private JButton groceryButton;
    private JPanel inputPanel;
    private JPanel outputPanel;
    private JScrollPane recipeContainer;



    public RecipeToGroceryView(RecipeToGroceryViewModel recipeToGroceryViewModel, RecipeToGroceryController recipeToGroceryController) {

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

        // Search bar label
//        JLabel title = new JLabel("Recipe To Grocery"); // Create label
//        title.setFont(new Font(defaultFont, Font.PLAIN, 20)); // Set font, style, size
//        title.setForeground(claudeBlack); // set font color

        // Search Box
        recipeName = new RoundedJTextField(30);
        recipeName.setPlaceholder("Type in the name of the recipe");
        recipeName.setPreferredSize(new Dimension(30, 40));
        recipeName.setBackground(claudeWhite);
        recipeName.setForeground(claudeBlack);
        recipeName.setBorder(new LineBorder(claudeWhiteEmph, 1));
        recipeName.setMargin(new Insets(5, 15, 5, 15));
        recipeName.setFont(new Font(secondaryFont, Font.PLAIN, 14));
        recipeName.setMaximumSize(recipeName.getPreferredSize());
        recipeName.addActionListener( e -> {
            if (e.getSource().equals(recipeName)) {
                String queryString = recipeName.getText();
                if (queryString != null && !queryString.isEmpty()) {
                    recipeToGroceryController.execute(queryString);
                }
            }
        });

        // Search Button
        groceryButton = new RoundButton("Grocery");
        groceryButton.setPreferredSize(new Dimension(40, 40));
        groceryButton.setBackground(claudeWhite);
        groceryButton.setForeground(claudeBlack);
        groceryButton.setBorder(new RoundBorder(claudeWhiteEmph, 10));
        groceryButton.setOpaque(false);
        groceryButton.setFont(new Font(defaultFont, Font.PLAIN, 12));
        groceryButton.addActionListener(e -> {
            if (e.getSource().equals(groceryButton)) {
                String queryString = recipeName.getText();
                if (queryString != null && !queryString.isEmpty()) {
                    recipeToGroceryController.execute(queryString);
                }
            }
        });

        // Output Components
        recipeContainer = new JScrollPane(outputPanel);
        recipeContainer.getVerticalScrollBar().setUnitIncrement(5);
        recipeContainer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        recipeContainer.setBorder(new LineBorder(claudeWhite, 30));


        // Pack input & output panel
//        inputPanel.add(title);
        inputPanel.add(recipeName);
        inputPanel.add(groceryButton);


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
        if ("recipe to grocery".equals(evt.getPropertyName())) {
            RecipeToGroceryOutputData response =  (RecipeToGroceryOutputData) evt.getNewValue();

            outputPanel.removeAll();

            for (Recipe recipe : response) {

                // Main Panel
                JPanel recipePanel = new RoundPanel(claudeWhite);
                recipePanel.setBackground(claudeWhite);
                recipePanel.setLayout(new BorderLayout(2, 3));
                recipePanel.setBorder(new EmptyBorder(30, 20, 20, 20));

                // Layout Panel
                JPanel topPanel = new RoundPanel(getBackground());
                topPanel.setPreferredSize(new Dimension(100, 40));
                topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 3, 5));
                topPanel.setBorder(new EmptyBorder(5,15,5,15));
                topPanel.setBackground(claudeWhite);

                JPanel leftPanel = new RoundPanel(claudeWhite);
                leftPanel.setLayout(new BorderLayout(3, 4));

                JPanel rightPanel = new RoundPanel(claudeWhite);
                rightPanel.setLayout(new BorderLayout(3, 4));

                // Components
                JLabel recipeNameLabel = new JLabel(recipe.getName());
                recipeNameLabel.setFont(new Font(defaultFont, Font.PLAIN, 24));
                recipeNameLabel.setForeground(claudeBlack);
                recipeNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);

                JPanel imagePanel = new RoundPanel(getBackground());
                ImageIcon image = this.loadRoundImage(recipe.getImage());
                JLabel imageLabel = new JLabel();
                imageLabel.setIcon(image);
                imagePanel.add(imageLabel);

                JPanel nutritionPanel = new RoundPanel(getBackground());
                nutritionPanel.setBackground(claudeWhiteEmph);
                nutritionPanel.setPreferredSize(new Dimension(30, 40));

                JPanel ingredientPanel = new IngredientPanel(recipe.getIngredientList(), claudeWhiteEmph);
                ingredientPanel.setBackground(claudeWhiteEmph);

                JPanel extraInfoPanel = new RoundPanel(claudeWhite);
                extraInfoPanel.setPreferredSize(new Dimension(100, 30));
                extraInfoPanel.setBackground(claudeOrange);

//                JTextArea textArea = new JTextArea(recipe.toString());
//                textArea.setWrapStyleWord(true);
//                textArea.setLineWrap(true);
//                textArea.setEditable(false); // Optional: Set to false if text is not editable
//                textArea.setBackground(sunflower); // Match background color
////                textArea.setBorder(BorderFactory.createEmptyBorder(30,30,30,30)); // Optional: Remove border for cleaner look
//
//                // Set fixed width and dynamically adjust height
//                int fixedWidth = 200; // Set the desired fixed width
//                textArea.setSize(fixedWidth, Short.MAX_VALUE); // Temporarily set size to maximum height
//                int preferredHeight = textArea.getPreferredSize().height; // Get the calculated preferred height
//                textArea.setPreferredSize(new Dimension(fixedWidth, preferredHeight)); // Set the new preferred size
//
//                recipePanel.setPreferredSize(new Dimension(fixedWidth, preferredHeight + 10)); // Adjust panel height based on textArea height
//
//                ingredientPanel.add(textArea);

                topPanel.add(recipeNameLabel);

                leftPanel.add(imageLabel, BorderLayout.NORTH);
                leftPanel.add(nutritionPanel, BorderLayout.CENTER);

                rightPanel.add(ingredientPanel, BorderLayout.CENTER);
                rightPanel.add(extraInfoPanel, BorderLayout.SOUTH);

                recipePanel.add(topPanel, BorderLayout.NORTH);
                recipePanel.add(leftPanel, BorderLayout.WEST);
                recipePanel.add(rightPanel, BorderLayout.CENTER);



                outputPanel.add(recipePanel);

            }
            SwingUtilities.invokeLater(() -> recipeContainer.getVerticalScrollBar().setValue(0));
            outputPanel.revalidate();
            outputPanel.repaint();
        }
    }


    @Override
    public String getViewName() {
        return this.viewname;
    }
}


