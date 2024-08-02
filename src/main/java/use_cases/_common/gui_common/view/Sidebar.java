package use_cases._common.gui_common.view;

import use_cases._common.gui_common.abstractions.ThemeColoredObject;
import use_cases._common.gui_common.view_components.layouts.VerticalFlowLayout;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Sidebar is a custom JPanel that serves as a sidebar component shared across different views.
 */
public class Sidebar extends JPanel implements ThemeColoredObject {
    ViewManagerModel viewManagerModel;

    RoundButton homeButton;
    RoundButton searchButton;
    RoundButton myRecipeButton;
    RoundButton groceryListButton;


    public Sidebar(ViewManagerModel viewManagerModel) {
        setLayout(new BorderLayout());
        this.viewManagerModel = viewManagerModel;

        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(230, 750));
        mainPanel.setBackground(claudeWhiteEmph);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(claudeWhiteEmph);
        titlePanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        JLabel titleLabel = new JLabel("BitByteBistro");
        titleLabel.setFont(new Font(secondaryFont, Font.PLAIN, 20));
        titleLabel.setForeground(claudeWhite);
        titlePanel.add(titleLabel, BorderLayout.NORTH);

        JPanel switchPanel = new JPanel(new VerticalFlowLayout(10));
        switchPanel.setBorder(new EmptyBorder(30, 10, 10, 10));
        switchPanel.setBackground(claudeWhiteEmph);

        homeButton = createMenu("Home");
        searchButton = createMenu("Search Recipe");
        myRecipeButton = createMenu("My Recipe");
        groceryListButton = createMenu("Grocery List");

        switchPanel.add(homeButton);
        switchPanel.add(searchButton);
        switchPanel.add(myRecipeButton);
        switchPanel.add(groceryListButton);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(claudeWhiteEmph);

        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(switchPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    private RoundButton createMenu(String viewName) {
        RoundButton menuButton  = new RoundButton(viewName);
        menuButton.setFont(new Font(defaultFont, Font.PLAIN, 16));

        menuButton.addActionListener(e -> {
            activeTab(menuButton);
            viewManagerModel.setActiveView(viewName);
            viewManagerModel.firePropertyChanged();
        });

        menuButton.setHoverColor(claudeWhiteEmph, claudeWhite, claudeBlackEmph, claudeBlack);
        menuButton.setPressedColor(claudeWhite);

        return menuButton;
    }

    private void activeTab(RoundButton activeButton) {
        homeButton.setHoverColor(claudeWhiteEmph, claudeWhite, claudeBlackEmph, claudeBlack);
        searchButton.setHoverColor(claudeWhiteEmph, claudeWhite, claudeBlackEmph, claudeBlack);
        myRecipeButton.setHoverColor(claudeWhiteEmph, claudeWhite, claudeBlackEmph, claudeBlack);
        groceryListButton.setHoverColor(claudeWhiteEmph, claudeWhite, claudeBlackEmph, claudeBlack);
        activeButton.setHoverColor(claudeWhiteEmph, claudeWhite, claudewhiteBright, claudeBlack);
    }
}
