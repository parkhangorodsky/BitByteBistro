package use_cases.display_recipe_detail;

import entity.Nutrition;
import entity.Recipe;
import use_cases._common.gui_common.abstractions.ImageLoader;
import use_cases._common.gui_common.abstractions.PopUpView;
import use_cases._common.gui_common.view_components.IngredientPanel;
import use_cases._common.gui_common.view_components.layouts.VerticalFlowLayout;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases._common.gui_common.view_components.round_component.RoundPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.List;

import static java.lang.Math.round;

public abstract class DisplayRecipeDetailView extends PopUpView implements PropertyChangeListener, ImageLoader {
    protected DisplayRecipeDetailViewModel viewModel;
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
        this.setSize(this.getSize().width, 730);
        this.revalidate();
        this.repaint();
        this.positionFrameAtCenter(parent);
    }

    private JScrollPane createContentPanel(Recipe recipe) {
        JPanel contentPanel = new JPanel(new BorderLayout(30,0));
        contentPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        contentPanel.setBackground(claudeWhite);

        JScrollPane contentScrollPane = new JScrollPane(contentPanel);
        contentScrollPane.setBackground(claudeWhite);
        contentScrollPane.setBorder(new LineBorder(contentScrollPane.getBackground(), 0));
        contentScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        contentScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel leftPanel = new JPanel(new VerticalFlowLayout(20));
        leftPanel.setBackground(claudeWhite);

        leftPanel.add(createImagePanel());
        leftPanel.add(createGoToWebsiteButton());
        leftPanel.add(createFoodTypePanel());
        leftPanel.add(createHealthInfoPanel());
        leftPanel.add(createTypePanel("Tags", recipe.getTags(), claudeWhite));

        leftPanel.setPreferredSize(new Dimension(300,0));

        JPanel rightPanel = new JPanel(new VerticalFlowLayout(20));
        rightPanel.setBackground(claudeWhite);

        rightPanel.add(createTitlePanel());
        rightPanel.add(new IngredientPanel(recipe.getIngredientList(), claudeWhiteEmph));
        rightPanel.add(createNutritionPanel());

        contentPanel.add(leftPanel, BorderLayout.WEST);
        contentPanel.add(rightPanel, BorderLayout.CENTER);

        return contentScrollPane;
    }

    private JPanel createImagePanel() {
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(claudeWhite);
        ImageIcon image = this.loadRoundImage(viewModel.getRecipe().getImage());
        JLabel imageLabel = new JLabel(image);
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        return imagePanel;
    }

    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(claudeWhite);
        JLabel titleLabel = new JLabel(viewModel.getRecipe().getName());
        titleLabel.setFont(new Font(defaultFont, Font.PLAIN, 24));
        titlePanel.add(titleLabel, BorderLayout.EAST);
        titlePanel.setBorder(new EmptyBorder(10, 0, 20, 0));
        return titlePanel;
    }

    private JPanel createNutritionPanel(){
        Map<String, Nutrition> nutritionMap = viewModel.getRecipe().getNutritionMap();
        JPanel nutritionPanel = new JPanel(new VerticalFlowLayout(5));
        nutritionPanel.setBackground(claudeWhite);
        JLabel titleLabel = new JLabel("Nutrition");
        titleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        titleLabel.setFont(new Font(defaultFont, Font.PLAIN, 20));
        titleLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
        titleLabel.setBackground(claudeWhite);
        nutritionPanel.add(titleLabel);

        for (String key : nutritionMap.keySet()) {
            JPanel nutritionRow = createNutritionRow(nutritionMap.get(key));
            nutritionPanel.add(nutritionRow);
        }

        nutritionPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        return nutritionPanel;

    }

    private JPanel createNutritionRow(Nutrition nutrition){
        JPanel nutritionRow = new RoundPanel();
        nutritionRow.setLayout(new BorderLayout(10,0));
        nutritionRow.setBackground(claudeWhite);

        JPanel namePanel = new RoundPanel();
        namePanel.setLayout(new BorderLayout());
        namePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        namePanel.setBackground(claudeWhite);
        JLabel nameLabel = new JLabel(nutrition.getLabel());
        nameLabel.setFont(new Font(secondaryFont, Font.PLAIN, 12));
        nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        namePanel.add(nameLabel, BorderLayout.WEST);

        JPanel quantityPanel = new RoundPanel();
        quantityPanel.setLayout(new BorderLayout());
        quantityPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        quantityPanel.setBackground(claudeWhiteEmph);
        String quant = String.format("%.2f", nutrition.getQuantity()) + " " +  nutrition.getUnit();
        JLabel quantityLabel = new JLabel(quant);
        quantityLabel.setFont(new Font(secondaryFont, Font.PLAIN, 12));
        quantityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        quantityPanel.add(quantityLabel, BorderLayout.EAST);

        JPanel percentPanel = new RoundPanel();
        percentPanel.setLayout(new BorderLayout());
        percentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        percentPanel.setBackground(sunflower);
        JLabel percentLabel;
        if (nutrition.getPercentage() != null) {
            String percent = String.format("%.2f", nutrition.getPercentage()) + " %";
            percentLabel = new JLabel(percent);

        } else {
            percentLabel = new JLabel("N/A");
        }
        percentLabel.setFont(new Font(secondaryFont, Font.PLAIN, 12));
        percentLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        percentPanel.add(percentLabel, BorderLayout.WEST);

        nutritionRow.add(namePanel, BorderLayout.WEST);
        nutritionRow.add(quantityPanel, BorderLayout.CENTER);
        nutritionRow.add(percentPanel, BorderLayout.EAST);

        return nutritionRow;
    }

    private JPanel createFoodTypePanel() {
        Recipe recipe = viewModel.getRecipe();
        JPanel typePanel = new JPanel(new VerticalFlowLayout(10));
        typePanel.setBackground(claudeWhite);

        JPanel cuisineTypePanel = createTypePanel("Cuisine", recipe.getCuisineType(), claudeWhiteEmph);
        JPanel mealTypePanel = createTypePanel("Meal", recipe.getMealType(), claudeWhiteEmph);
        JPanel dishTypePanel = createTypePanel("Dish", recipe.getDishType(), claudeWhiteEmph);

        typePanel.add(cuisineTypePanel);
        typePanel.add(mealTypePanel);
        typePanel.add(dishTypePanel);

        return typePanel;
    }

    private JPanel createHealthInfoPanel() {
        Recipe recipe = viewModel.getRecipe();
        JPanel typePanel = new JPanel(new VerticalFlowLayout(10));
        typePanel.setBackground(claudeWhite);

        JPanel dietPanel = createTypePanel("Diet", recipe.getDietLabels(), claudeWhiteEmph);
        JPanel healthPanel = createTypePanel("Health", recipe.getHealthLabels(), claudeWhiteEmph);
        JPanel cautionPanel = createTypePanel("Caution", recipe.getCautions(), claudeWhiteEmph);

        typePanel.add(dietPanel);
        typePanel.add(healthPanel);
        typePanel.add(cautionPanel);

        return typePanel;
    }

    private JPanel createTypePanel(String labelName, List<String> types, Color tagColor) {
        JPanel typePanel = new JPanel(new BorderLayout());
        JPanel labelPanel = new JPanel(new BorderLayout());
        JLabel typeLabel = new JLabel(labelName);
        typeLabel.setFont(new Font(secondaryFont, Font.PLAIN, 12));
        labelPanel.setBackground(claudeWhite);
        labelPanel.add(typeLabel, BorderLayout.CENTER);
        labelPanel.setPreferredSize(new Dimension(70, 0));
        JPanel typeTags = createTagPanel(types, tagColor);
        typePanel.add(labelPanel, BorderLayout.WEST);
        typePanel.add(typeTags, BorderLayout.CENTER);

        return typePanel;
    }

    private JPanel createTagPanel(List<String> tags, Color color) {
        JPanel tagContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tagContainer.setBackground(claudeWhite);
        for (String tag : tags) {
            JPanel tagPanel = new RoundPanel();
            tagPanel.setBackground(color);
            tagPanel.setBorder(new EmptyBorder(0, 5, 0, 5));
            JLabel tagLabel = new JLabel(tag);
            tagLabel.setFont(new Font(secondaryFont, Font.PLAIN, 9));
            tagPanel.add(tagLabel);
            tagContainer.add(tagPanel);
        }
        return tagContainer;
    }


    private JPanel createGoToWebsiteButton(){
        JPanel goToWebsitePanel = new JPanel(new BorderLayout());
        RoundButton goToWebsiteButton = new RoundButton("Open in browser");
        goToWebsiteButton.setFont(new Font(defaultFont, Font.PLAIN, 12));
        goToWebsiteButton.setHoverColor(claudeWhite, claudeBlackEmph, claudeBlackEmph, claudeWhite);

        goToWebsiteButton.addActionListener(e -> {
            if (Desktop.isDesktopSupported()) {

                Desktop desktop = Desktop.getDesktop();
                try {
                    System.out.println(viewModel.getRecipe().getInstructions());
                    desktop.browse(new URI(viewModel.getRecipe().getInstructions()));

                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                    System.out.println(ex.getMessage());
                }

            } else {
                System.out.println("Desktop is not supported");
            }
        });
        goToWebsitePanel.add(goToWebsiteButton, BorderLayout.CENTER);

        return goToWebsitePanel;

    }



     abstract JPanel createControlPanel();

}
