package use_cases.add_to_my_recipe;

import app.LocalAppSetting;
import entity.LoggedUserData;
import entity.Recipe;
import use_cases._common.gui_common.abstractions.NightModeObject;
import use_cases._common.gui_common.abstractions.ThemeColoredObject;
import use_cases._common.gui_common.abstractions.View;
import use_cases._common.gui_common.view_components.layouts.VerticalFlowLayout;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases._common.gui_common.view_components.round_component.RoundPanel;
import use_cases.filter_recipe.FilterRecipeController;
import use_cases.search_recipe.gui.view_component.SearchButton;
import use_cases.search_recipe.gui.view_component.SearchTextField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.util.List;

public class MyRecipeView extends View implements ThemeColoredObject, NightModeObject {

    private MyRecipeViewModel viewModel;
    private JPanel myRecipeContainer;
    private JScrollPane myRecipeScrollPane;

    JTextField textField;
    JButton searchButton;


    private FilterRecipeController filterController;

    public MyRecipeView(MyRecipeViewModel viewModel, FilterRecipeController filterController) {

        observeNight();
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);
        this.setViewName(viewModel.getViewName());
        this.filterController = filterController;

        this.setLayout(new BorderLayout());

        JPanel viewPanel = setUpContentView();

        toggleNightMode();

        this.add(viewPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("init")) {
            LoggedUserData.getLoggedInUser().getRecipes().sort(null);
            viewModel.setRecipes(LoggedUserData.getLoggedInUser().getRecipes());
            filterController.execute("");
        } else if (evt.getPropertyName().equals("added recipe")) {
            filterController.execute(textField.getText());
        } else if (evt.getPropertyName().equals("update")) {
            updateMyRecipe(viewModel.getRecipes());
        } else if (evt.getPropertyName().equals("nightMode")) {
            toggleNightMode();
            this.revalidate();
            this.repaint();
        }
    }

    private JPanel setUpContentView() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.setPreferredSize(new Dimension(800,100));

        inputPanel.setMaximumSize(inputPanel.getPreferredSize());
        inputPanel.setBorder(new EmptyBorder(20,20,20,20));
        inputPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 3, 5));

        JPanel outputPanel = new JPanel();
        outputPanel.setOpaque(false);
        outputPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        outputPanel.setLayout(new BorderLayout());

        textField = new SearchTextField();
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                textChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                textChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                textChanged();
            }

            private void textChanged() {
                filterController.execute(textField.getText());
            }
        });

        searchButton = new SearchButton();
        searchButton.addActionListener(e -> {
            filterController.execute(textField.getText());
        });

        inputPanel.add(textField);
        inputPanel.add(searchButton);

        myRecipeContainer = new JPanel(new VerticalFlowLayout(10));
        myRecipeScrollPane = new JScrollPane(myRecipeContainer);
        myRecipeScrollPane.setOpaque(false);
        myRecipeScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        myRecipeScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        myRecipeScrollPane.setBorder(new LineBorder(claudeWhite, 0));

        outputPanel.add(myRecipeScrollPane);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(outputPanel, BorderLayout.CENTER);

        return mainPanel;
    }

    private void updateMyRecipe(List<Recipe> recipes) {
        myRecipeContainer.removeAll();

        for (Recipe recipe : recipes) {
            JPanel recipeItem = createRecipeItem(recipe);
            myRecipeContainer.add(recipeItem);
        }
        SwingUtilities.invokeLater(() -> myRecipeScrollPane.getVerticalScrollBar().setValue(0));


        myRecipeContainer.revalidate();
        myRecipeContainer.repaint();

    }

    private JPanel createRecipeItem(Recipe recipe) {
        RoundPanel recipeItem = new RoundPanel();
        recipeItem.setLayout(new BorderLayout());
        recipeItem.setBorder(new EmptyBorder(10, 10, 10, 10));
        recipeItem.setBackground(LocalAppSetting.isNightMode() ? neonPurpleEmph : claudewhiteBright);
        recipeItem.setBorderColor(LocalAppSetting.isNightMode() ? neonPurple : claudeWhiteEmph);

        JPanel imagePanel = new JPanel();
        imagePanel.setOpaque(false);
        BufferedImage image = recipe.getSmallImage();
        imagePanel.add(new JLabel(new ImageIcon(image)));

        JPanel recipeNamePanel = new JPanel(new BorderLayout());
        recipeNamePanel.setOpaque(false);
        JLabel recipeNameLabel = new JLabel(recipe.getName());
        recipeNameLabel.setFont(new Font(defaultFont, Font.PLAIN, 20));
        recipeNameLabel.setForeground(LocalAppSetting.isNightMode() ? neonPinkEmph : black);
        recipeNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        recipeNameLabel.setVerticalTextPosition(SwingConstants.CENTER);
        recipeNamePanel.add(recipeNameLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setOpaque(false);
        RoundButton showRecipeButton = new RoundButton("+");
        showRecipeButton.setHorizontalAlignment(SwingConstants.CENTER);
        showRecipeButton.setVerticalAlignment(SwingConstants.CENTER);
        showRecipeButton.setPreferredSize(new Dimension(30, 30));
        showRecipeButton.setBorderColor(LocalAppSetting.isNightMode() ? neonPinkEmph : claudeBlack);

        if (LocalAppSetting.isNightMode()) {
            showRecipeButton.setHoverColor(darkPurple, neonPinkEmph, white, white);
        } else {
            showRecipeButton.setHoverColor(claudeBlack, sunflower, claudeWhite, claudewhiteBright);
        }

        buttonPanel.add(showRecipeButton, BorderLayout.SOUTH);

        recipeItem.add(imagePanel, BorderLayout.WEST);
        recipeItem.add(recipeNamePanel, BorderLayout.CENTER);
        recipeItem.add(buttonPanel, BorderLayout.EAST);

        return recipeItem;


    }


    @Override
    public void setNightMode() {
        this.setBackground(black);
        if (LoggedUserData.getLoggedInUser() != null) {
            viewModel.setRecipes(LoggedUserData.getLoggedInUser().getRecipes());
            updateMyRecipe(viewModel.getRecipes());
        }
        myRecipeContainer.setBackground(black);


    }

    @Override
    public void setDayMode() {
        this.setBackground(claudeWhite);
        if (LoggedUserData.getLoggedInUser() != null) {
            viewModel.setRecipes(LoggedUserData.getLoggedInUser().getRecipes());
            updateMyRecipe(viewModel.getRecipes());
        }
        myRecipeContainer.setBackground(claudeWhite);
//
    }

}
