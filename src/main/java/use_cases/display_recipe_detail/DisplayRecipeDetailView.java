package use_cases.display_recipe_detail;

import entity.Recipe;
import use_cases._common.gui_common.abstractions.ImageLoader;
import use_cases._common.gui_common.abstractions.PopUpView;
import use_cases._common.gui_common.abstractions.ThemeColoredObject;
import use_cases._common.gui_common.view_components.IngredientPanel;
import use_cases._common.gui_common.view_components.layouts.VerticalFlowLayout;
import use_cases._common.gui_common.view_components.round_component.RoundButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public abstract class DisplayRecipeDetailView extends PopUpView implements PropertyChangeListener, ImageLoader {
    private DisplayRecipeDetailViewModel viewModel;
    private JFrame parent;

    public DisplayRecipeDetailView(JFrame parent, DisplayRecipeDetailViewModel viewModel) {
        super(parent);
        this.parent = parent;
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if (evt.getPropertyName().equals("initialized")) {
            initialize();
        }

    }

    private void initialize() {
        Recipe recipe = viewModel.getRecipe();
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(claudeWhite);
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JScrollPane contentScrollPane = createContentPanel(recipe);
        JPanel controlPanel = createControlPanel();

        mainPanel.add(contentScrollPane, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        this.add(mainPanel);

        this.pack();
        this.revalidate();
        this.repaint();
        this.positionFrameAtCenter(parent);
    }

    private JScrollPane createContentPanel(Recipe recipe) {
        JPanel contentPanel = new JPanel(new BorderLayout(30,0));
        contentPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        contentPanel.setBackground(claudeWhite);

        JScrollPane contentScrollPane = new JScrollPane(contentPanel);

        JPanel leftPanel = new JPanel(new VerticalFlowLayout(10));
        leftPanel.setBackground(claudeWhite);

        JPanel imagePanel = new JPanel(new BorderLayout());
        ImageIcon image = this.loadRoundImage(recipe.getImage());
        JLabel imageLabel = new JLabel(image);
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        leftPanel.add(imagePanel);

        JPanel rightPanel = new JPanel(new VerticalFlowLayout(10));
        rightPanel.setBackground(claudeWhite);

        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(recipe.getName());
        titleLabel.setFont(new Font(defaultFont, Font.PLAIN, 18));
        titlePanel.add(titleLabel, BorderLayout.EAST);
        rightPanel.add(titlePanel);

        JPanel ingredientsPanel = new IngredientPanel(recipe.getIngredientList(), claudeWhiteEmph);
        rightPanel.add(ingredientsPanel);

        contentPanel.add(leftPanel, BorderLayout.WEST);
        contentPanel.add(rightPanel, BorderLayout.EAST);

        contentScrollPane.setBackground(claudeWhite);
        contentScrollPane.setBorder(new LineBorder(contentScrollPane.getBackground(), 0));

        return contentScrollPane;
    }

     abstract JPanel createControlPanel();
}
