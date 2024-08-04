package use_cases.core_functionality;

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

public class MyGroceryView extends View implements ThemeColoredObject, NightModeObject {
    private MyGroceryViewModel viewModel;

    private JPanel myGroceryContainer;
    private JScrollPane myGroceryScrollPane;

    public MyGroceryView(MyGroceryViewModel viewModel) {

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
            updateMyGrocery();
        } else if (evt.getPropertyName().equals("grocery")) {
            updateMyGrocery();
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

        // duplicated code
        myGroceryContainer = new JPanel(new VerticalFlowLayout(10));
        myGroceryScrollPane = new JScrollPane(myGroceryContainer);
        myGroceryScrollPane.setOpaque(false);
        myGroceryScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        myGroceryScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        myGroceryScrollPane.setBorder(new LineBorder(claudeWhite, 0));

        outputPanel.add(myGroceryScrollPane);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(outputPanel, BorderLayout.CENTER);

        return mainPanel;
    }

    private void updateMyGrocery() {
        myGroceryContainer.removeAll();

        if (!viewModel.getUser().getShoppingLists().isEmpty()) {
            for (Recipe recipe : viewModel.getUser().getShoppingLists().getFirst().getRecipes()) {
                JPanel recipeItem = createRecipeItem(recipe);
                myGroceryContainer.add(recipeItem);
            }
            SwingUtilities.invokeLater(() -> myGroceryScrollPane.getVerticalScrollBar().setValue(0));
        } else {
            JLabel emptyLabel = new JLabel("No recipes in your shopping list.");
            emptyLabel.setFont(new Font(defaultFont, Font.PLAIN, 18));
            emptyLabel.setForeground(LocalAppSetting.isNightMode() ? neonPinkEmph : black);
            myGroceryContainer.add(emptyLabel);
        }

        myGroceryContainer.revalidate();
        myGroceryContainer.repaint();
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
            viewModel.setUser(LoggedUserData.getLoggedInUser());
            updateMyGrocery();
        }
        myGroceryContainer.setBackground(black);


    }

    @Override
    public void setDayMode() {
        this.setBackground(claudeWhite);
        if (LoggedUserData.getLoggedInUser() != null) {
            viewModel.setUser(LoggedUserData.getLoggedInUser());
            updateMyGrocery();
        }
        myGroceryContainer.setBackground(claudeWhite);
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
