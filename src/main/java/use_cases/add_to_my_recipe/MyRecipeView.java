package use_cases.add_to_my_recipe;

import entity.Recipe;
import use_cases._common.gui_common.abstractions.ImageLoader;
import use_cases._common.gui_common.abstractions.ThemeColoredObject;
import use_cases._common.gui_common.abstractions.View;
import use_cases._common.gui_common.view_components.layouts.VerticalFlowLayout;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases._common.gui_common.view_components.round_component.RoundPanel;
import use_cases.search_recipe.gui.view_component.SearchButton;
import use_cases.search_recipe.gui.view_component.SearchTextField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.foreign.PaddingLayout;

public class MyRecipeView extends View implements ImageLoader {
    private MyRecipeViewModel viewModel;
    private JPanel myRecipeContainer;
    private JScrollPane myRecipeScrollPane;

    public MyRecipeView(MyRecipeViewModel viewModel) {

        this.setLayout(new BorderLayout());
        this.setBackground(claudeWhite);
        this.viewModel = viewModel;
        this.setViewName(viewModel.getViewName());
        this.viewModel.addPropertyChangeListener(this);

        JPanel viewPanel = setUpContentView();
        this.add(viewPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("init")) {
            updateMyRecipe();
        } else if (evt.getPropertyName().equals("added recipe")) {
            updateMyRecipe();
        }
    }

    private JPanel setUpContentView() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(claudeWhite);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(claudeWhite);
        inputPanel.setPreferredSize(new Dimension(800,100));

        inputPanel.setMaximumSize(inputPanel.getPreferredSize());
        inputPanel.setBorder(BorderFactory.createLineBorder(claudeWhite, 20));
        inputPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 3, 5));

        JPanel outputPanel = new JPanel();
        outputPanel.setBackground(claudeWhite);
        outputPanel.setBorder(BorderFactory.createLineBorder(claudeWhite, 20));
        outputPanel.setLayout(new BorderLayout());

        JTextField textField = new SearchTextField();
        JButton searchButton = new SearchButton();

        inputPanel.add(textField);
        inputPanel.add(searchButton);

        myRecipeContainer = new JPanel(new VerticalFlowLayout(10));
        myRecipeContainer.setBackground(claudeWhite);
        myRecipeScrollPane = new JScrollPane(myRecipeContainer);
        myRecipeScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        myRecipeScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        myRecipeScrollPane.setBorder(new LineBorder(claudeWhite, 0));

        outputPanel.add(myRecipeScrollPane);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(outputPanel, BorderLayout.CENTER);

        return mainPanel;
    }

    private void updateMyRecipe() {
        myRecipeContainer.removeAll();

        for (Recipe recipe : viewModel.getUser().getRecipes()) {
            JPanel recipeItem = createRecipeItem(recipe);
            myRecipeContainer.add(recipeItem);
        }
        SwingUtilities.invokeLater(() -> myRecipeScrollPane.getVerticalScrollBar().setValue(0));

        myRecipeContainer.revalidate();
        myRecipeContainer.repaint();

    }

    private JPanel createRecipeItem(Recipe recipe) {
        JPanel recipeItem = new RoundPanel();
        recipeItem.setLayout(new BorderLayout());
        recipeItem.setBackground(claudeWhiteEmph);
        recipeItem.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(claudeWhiteEmph);
        ImageIcon image = loadRoundImage(recipe.getSmallImage());
        imagePanel.add(new JLabel(image));

        JPanel recipeNamePanel = new JPanel(new BorderLayout());
        recipeNamePanel.setBackground(claudeWhiteEmph);
        JLabel recipeNameLabel = new JLabel(recipe.getName());
        recipeNameLabel.setFont(new Font(defaultFont, Font.PLAIN, 20));
        recipeNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        recipeNameLabel.setVerticalTextPosition(SwingConstants.CENTER);
        recipeNamePanel.add(recipeNameLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(claudeWhiteEmph);
        RoundButton showRecipeButton = new RoundButton("+");
        showRecipeButton.setPreferredSize(new Dimension(20, 20));
        showRecipeButton.setBorderColor(claudeBlack);
        showRecipeButton.setHoverColor(claudeBlack, sunflower, claudeWhite, claudewhiteBright);
        buttonPanel.add(showRecipeButton, BorderLayout.SOUTH);

        recipeItem.add(imagePanel, BorderLayout.WEST);
        recipeItem.add(recipeNamePanel, BorderLayout.CENTER);
        recipeItem.add(buttonPanel, BorderLayout.EAST);

        return recipeItem;
    }


}
