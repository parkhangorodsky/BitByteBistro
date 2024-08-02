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
import use_cases.search_recipe.gui.view_component.SearchButton;
import use_cases.search_recipe.gui.view_component.SearchTextField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;

public class MyRecipeView extends View implements ThemeColoredObject, NightModeObject {
    private MyRecipeViewModel viewModel;

    private JPanel myRecipeContainer;
    private JScrollPane myRecipeScrollPane;

    public MyRecipeView(MyRecipeViewModel viewModel) {

        observeNight();
        this.setLayout(new BorderLayout());
        this.viewModel = viewModel;
        this.setViewName(viewModel.getViewName());
        this.viewModel.addPropertyChangeListener(this);

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
            viewModel.setUser(LoggedUserData.getLoggedInUser());
            updateMyRecipe();
        } else if (evt.getPropertyName().equals("added recipe")) {
            updateMyRecipe();
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

        JTextField textField = new SearchTextField();
        JButton searchButton = new SearchButton();

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

        System.out.println(LocalAppSetting.isNightMode());
        return recipeItem;


    }


    @Override
    public void setNightMode() {
        this.setBackground(black);
        if (LoggedUserData.getLoggedInUser() != null) {
            viewModel.setUser(LoggedUserData.getLoggedInUser());
            updateMyRecipe();
        }
        myRecipeContainer.setBackground(black);


    }

    @Override
    public void setDayMode() {
        this.setBackground(claudeWhite);
        if (LoggedUserData.getLoggedInUser() != null) {
            viewModel.setUser(LoggedUserData.getLoggedInUser());
            updateMyRecipe();
        }
        myRecipeContainer.setBackground(claudeWhite);
//
    }

    public void revalidateEverything(JComponent component) {
        System.out.println("ran");

        for (Component c : component.getComponents()) {
            if (c instanceof JComponent) {
                revalidateEverything((JComponent) c);
            }
        }

        component.revalidate();
        component.repaint();
    }
}