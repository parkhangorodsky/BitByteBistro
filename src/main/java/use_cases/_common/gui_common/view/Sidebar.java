package use_cases._common.gui_common.view;

import app.LocalAppSetting;
import use_cases._common.gui_common.abstractions.NightModeObject;
import use_cases._common.gui_common.abstractions.ThemeColoredObject;
import use_cases._common.gui_common.view_components.layouts.VerticalFlowLayout;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Sidebar is a custom JPanel that serves as a sidebar component shared across different views.
 */
public class Sidebar extends JPanel implements ThemeColoredObject, NightModeObject {
    ViewManagerModel viewManagerModel;

    JPanel mainPanel;
    JPanel titlePanel;
    JPanel switchPanel;
    JPanel bottomPanel;

    RoundButton homeButton;
    RoundButton searchButton;
    RoundButton myRecipeButton;
    RoundButton groceryListButton;

    RoundButton settingButton;


    public Sidebar(ViewManagerModel viewManagerModel) {

        setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(250, 750));
        this.viewManagerModel = viewManagerModel;

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        titlePanel.setPreferredSize(new Dimension(0, 100));
        JLabel titleLabel = new JLabel("BitByteBistro");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font(secondaryFont, Font.PLAIN, 20));
        titleLabel.setForeground(claudeWhite);
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        switchPanel = new JPanel(new VerticalFlowLayout(10));
        switchPanel.setBorder(new EmptyBorder(30, 10, 10, 10));

        homeButton = createMenu("Home");
        searchButton = createMenu("Search Recipe");
        myRecipeButton = createMenu("My Recipe");
        myRecipeButton.addActionListener(e -> {
            viewManagerModel.firePropertyChanged("init", "My Recipe");
        });
        groceryListButton = createMenu("Grocery List");

        switchPanel.add(homeButton);
        switchPanel.add(searchButton);
        switchPanel.add(myRecipeButton);
        switchPanel.add(groceryListButton);

        bottomPanel = new JPanel();
        settingButton = createSettingButton();
        settingButton.addActionListener(e -> {
            viewManagerModel.firePropertyChanged("pop up", "Preference");
        });
        bottomPanel.add(settingButton);

        observeNight();
        toggleNightMode();

        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(switchPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        this.add(mainPanel, BorderLayout.CENTER);
    }

    private RoundButton createSettingButton() {
        RoundButton button = new RoundButton("Settings");
        button.setFont(new Font(defaultFont, Font.PLAIN, 12));
        return button;
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
        toggleNightMode();

        if (LocalAppSetting.isNightMode()){
            activeButton.setHoverColor(black, black, mint, orange);
        } else {
            activeButton.setHoverColor(claudeWhiteEmph, claudeWhite, claudewhiteBright, claudeBlack);
        }

    }

    @Override
    public void setNightMode() {
        mainPanel.setBackground(black);
        titlePanel.setBackground(black);
        switchPanel.setBackground(black);
        bottomPanel.setBackground(black);

        homeButton.setHoverColor(black, black, white, neonPinkEmph);
        searchButton.setHoverColor(black, black, white, neonPinkEmph);
        myRecipeButton.setHoverColor(black, black, white, neonPinkEmph);
        groceryListButton.setHoverColor(black, black, white, neonPinkEmph);

        homeButton.setPressedColor(neonPurpleEmph);
        searchButton.setPressedColor(neonPurpleEmph);
        myRecipeButton.setPressedColor(neonPurpleEmph);
        groceryListButton.setPressedColor(neonPurpleEmph);

        homeButton.setBorderColor(black);
        searchButton.setBorderColor(black);
        myRecipeButton.setBorderColor(black);
        groceryListButton.setBorderColor(black);

        settingButton.setBorderColor(black);
        settingButton.setHoverColor(black, black, white, neonPinkEmph);

    }

    @Override
    public void setDayMode() {
        mainPanel.setBackground(claudeWhiteEmph);
        titlePanel.setBackground(claudeWhiteEmph);
        switchPanel.setBackground(claudeWhiteEmph);
        bottomPanel.setBackground(claudeWhiteEmph);

        homeButton.setHoverColor(claudeWhiteEmph, claudeWhite, claudeBlackEmph, claudeBlack);
        searchButton.setHoverColor(claudeWhiteEmph, claudeWhite, claudeBlackEmph, claudeBlack);
        myRecipeButton.setHoverColor(claudeWhiteEmph, claudeWhite, claudeBlackEmph, claudeBlack);
        groceryListButton.setHoverColor(claudeWhiteEmph, claudeWhite, claudeBlackEmph, claudeBlack);

        homeButton.setPressedColor(claudeWhite);
        searchButton.setPressedColor(claudeWhite);
        myRecipeButton.setPressedColor(claudeWhite);
        groceryListButton.setPressedColor(claudeWhite);

        homeButton.setBorderColor(claudeWhiteEmph);
        searchButton.setBorderColor(claudeWhiteEmph);
        myRecipeButton.setBorderColor(claudeWhiteEmph);
        groceryListButton.setBorderColor(claudeWhiteEmph);

        settingButton.setBorderColor(claudeWhiteEmph);
        settingButton.setHoverColor(claudeWhiteEmph, claudeWhiteEmph, claudeBlackEmph, claudeBlack);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("nightMode")) {
            toggleNightMode();
            this.revalidate();
            this.repaint();
        }
    }
}
