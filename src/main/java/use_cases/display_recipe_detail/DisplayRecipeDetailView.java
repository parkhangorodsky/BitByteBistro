package use_cases.display_recipe_detail;

import app.local.LocalAppSetting;
import app.local.LoggedUserData;
import entity.Nutrition;
import entity.Recipe;
import entity.ShoppingList;
import entity.User;
import use_cases._common.gui_common.abstractions.NightModeObject;
import use_cases._common.gui_common.abstractions.PopUpView;
import use_cases._common.gui_common.view_components.IngredientPanel;
import use_cases._common.gui_common.view_components.layouts.VerticalFlowLayout;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases._common.gui_common.view_components.round_component.RoundPanel;
import use_cases.add_new_grocery_list.AddNewGroceryListController;
import use_cases.add_to_my_recipe.AddToMyRecipeController;
import use_cases.core_functionality.CoreFunctionalityController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import static java.lang.Math.round;

public abstract class DisplayRecipeDetailView extends PopUpView implements PropertyChangeListener, NightModeObject {
    protected DisplayRecipeDetailViewModel viewModel;
    private JFrame parent;

    protected JPanel mainPanel;
    protected JPanel controlPanel;
    protected JPanel buttonPanel;
    protected RoundButton closeButton;
    protected RoundButton addToGroceryButton;

    JPanel contentPanel;
    JScrollPane contentScrollPane;

    JLabel titleLabel;
    RoundButton goToWebsiteButton;

    private CoreFunctionalityController coreFunctionalityController;
    private AddNewGroceryListController addNewGroceryListController;
    private HashMap<String, ShoppingList> userGroceryLists;
    User user = LoggedUserData.getLoggedInUser();



    public DisplayRecipeDetailView(JFrame parent, DisplayRecipeDetailViewModel viewModel, CoreFunctionalityController coreFunctionalityController, AddNewGroceryListController addNewGroceryListController) {
        super(parent);
        this.coreFunctionalityController = coreFunctionalityController;
        this.addNewGroceryListController = addNewGroceryListController;
        this.userGroceryLists = user.getShoppingLists();
        this.parent = parent;
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);
        observeNight();

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("initialized")) {
            initialize();
        } else if (evt.getPropertyName().equals("nightMode")) {
            toggleNightMode();
            this.revalidate();
            this.repaint();
        }
    }

    protected void initialize() {
        Recipe recipe = viewModel.getRecipe();
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        contentScrollPane = createContentPanel(recipe);
        JPanel controlPanel = createControlPanel();

        mainPanel.add(contentScrollPane, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        toggleNightMode();

        this.add(mainPanel);

        this.pack();
        this.setSize(this.getSize().width, 730);
        this.revalidate();
        this.repaint();
        this.positionFrameAtCenter(parent);
    }

    private JScrollPane createContentPanel(Recipe recipe) {
        contentPanel = new JPanel(new BorderLayout(30,0));
        contentPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        JScrollPane contentScrollPane = new JScrollPane(contentPanel);
        contentScrollPane.setOpaque(false);
        contentScrollPane.setBorder(new LineBorder(contentScrollPane.getBackground(), 0));
        contentScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        contentScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel leftPanel = new JPanel(new VerticalFlowLayout(20));
        leftPanel.setOpaque(false);

        leftPanel.add(createImagePanel());
        leftPanel.add(createGoToWebsiteButton());
        leftPanel.add(createFoodTypePanel());
        leftPanel.add(createHealthInfoPanel());
        leftPanel.add(createTypePanel("Tags", recipe.getTags(), LocalAppSetting.isNightMode() ? darkPurple : claudeWhiteEmph));

        leftPanel.setPreferredSize(new Dimension(300,0));

        JPanel rightPanel = new JPanel(new VerticalFlowLayout(20));
        rightPanel.setOpaque(false);

        rightPanel.add(createTitlePanel());
        rightPanel.add(new IngredientPanel(recipe.getIngredientList()));
        rightPanel.add(createNutritionPanel());

        contentPanel.add(leftPanel, BorderLayout.WEST);
        contentPanel.add(rightPanel, BorderLayout.CENTER);

        return contentScrollPane;
    }

    private JPanel createImagePanel() {
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setOpaque(false);
        BufferedImage image = viewModel.getRecipe().getImage();
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        return imagePanel;
    }

    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);
        titleLabel = new JLabel(viewModel.getRecipe().getName());
        titleLabel.setFont(new Font(defaultFont, Font.PLAIN, 24));
        titlePanel.add(titleLabel, BorderLayout.EAST);
        titlePanel.setBorder(new EmptyBorder(10, 0, 20, 0));
        return titlePanel;
    }

    private JPanel createNutritionPanel(){
        Map<String, Nutrition> nutritionMap = viewModel.getRecipe().getNutritionMap();
        JPanel nutritionPanel = new JPanel(new VerticalFlowLayout(5));
        nutritionPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("Nutrition");
        titleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        titleLabel.setFont(new Font(defaultFont, Font.PLAIN, 20));
        titleLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
        titleLabel.setForeground(LocalAppSetting.isNightMode() ? neonPinkEmph : claudeBlack);
        nutritionPanel.add(titleLabel);

        for (String key : nutritionMap.keySet()) {
            JPanel nutritionRow = createNutritionRow(nutritionMap.get(key));
            nutritionPanel.add(nutritionRow);
        }

        nutritionPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        return nutritionPanel;

    }

    private JPanel createNutritionRow(Nutrition nutrition){
        JPanel nutritionRow = new JPanel();
        nutritionRow.setLayout(new BorderLayout(10,0));
        nutritionRow.setBackground(LocalAppSetting.isNightMode() ? black : claudeWhite);

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BorderLayout());
        namePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        namePanel.setBackground(LocalAppSetting.isNightMode() ? black : claudeWhite);

        JLabel nameLabel = new JLabel(nutrition.getLabel());
        nameLabel.setFont(new Font(secondaryFont, Font.PLAIN, 12));
        nameLabel.setForeground(LocalAppSetting.isNightMode() ? white : claudeBlack);
        nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        namePanel.add(nameLabel, BorderLayout.WEST);

        JPanel quantityPanel = new RoundPanel();
        quantityPanel.setLayout(new BorderLayout());
        quantityPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        quantityPanel.setBackground(LocalAppSetting.isNightMode() ? darkPurple : claudeWhiteEmph);
        String quant = String.format("%.2f", nutrition.getQuantity()) + " " +  nutrition.getUnit();
        JLabel quantityLabel = new JLabel(quant);
        quantityLabel.setFont(new Font(secondaryFont, Font.PLAIN, 12));
        quantityLabel.setForeground(LocalAppSetting.isNightMode() ? white : claudeBlack);
        quantityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        quantityPanel.add(quantityLabel, BorderLayout.EAST);

        JPanel percentPanel = new RoundPanel();
        percentPanel.setLayout(new BorderLayout());
        percentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        percentPanel.setBackground(LocalAppSetting.isNightMode() ? neonPink : sunflower);
        JLabel percentLabel;
        if (nutrition.getPercentage() != null) {
            String percent = String.format("%.2f", nutrition.getPercentage()) + " %";
            percentLabel = new JLabel(percent);

        } else {
            percentLabel = new JLabel("N/A");
        }
        percentLabel.setFont(new Font(secondaryFont, Font.PLAIN, 12));
        percentLabel.setForeground(LocalAppSetting.isNightMode() ? white : claudeBlack);
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
        typePanel.setOpaque(false);

        Color tagColor = LocalAppSetting.isNightMode() ? darkPurple : claudeWhiteEmph;

        JPanel cuisineTypePanel = createTypePanel("Cuisine", recipe.getCuisineType(), tagColor);
        JPanel mealTypePanel = createTypePanel("Meal", recipe.getMealType(), tagColor);
        JPanel dishTypePanel = createTypePanel("Dish", recipe.getDishType(), tagColor);

        typePanel.add(cuisineTypePanel);
        typePanel.add(mealTypePanel);
        typePanel.add(dishTypePanel);

        return typePanel;
    }

    private JPanel createHealthInfoPanel() {
        Recipe recipe = viewModel.getRecipe();
        JPanel typePanel = new JPanel(new VerticalFlowLayout(10));
        typePanel.setOpaque(false);

        Color tagColor = LocalAppSetting.isNightMode() ? darkPurple : claudeWhiteEmph;

        JPanel dietPanel = createTypePanel("Diet", recipe.getDietLabels(), tagColor);
        JPanel healthPanel = createTypePanel("Health", recipe.getHealthLabels(), tagColor);
        JPanel cautionPanel = createTypePanel("Caution", recipe.getCautions(), tagColor);

        typePanel.add(dietPanel);
        typePanel.add(healthPanel);
        typePanel.add(cautionPanel);

        return typePanel;
    }

    private JPanel createTypePanel(String labelName, List<String> types, Color tagColor) {
        JPanel typePanel = new JPanel(new BorderLayout());
        JPanel labelPanel = new JPanel(new BorderLayout());
        JLabel typeLabel = new JLabel(labelName);

        typePanel.setOpaque(false);
        labelPanel.setOpaque(false);
        typeLabel.setFont(new Font(secondaryFont, Font.PLAIN, 12));
        typeLabel.setForeground(LocalAppSetting.isNightMode() ? white : claudeBlack);

        labelPanel.add(typeLabel, BorderLayout.CENTER);
        labelPanel.setPreferredSize(new Dimension(70, 0));
        JPanel typeTags = createTagPanel(types, tagColor);
        typePanel.add(labelPanel, BorderLayout.WEST);
        typePanel.add(typeTags, BorderLayout.CENTER);

        return typePanel;
    }

    private JPanel createTagPanel(List<String> tags, Color color) {
        JPanel tagContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tagContainer.setOpaque(false);
        for (String tag : tags) {
            JPanel tagPanel = new RoundPanel();
            tagPanel.setBackground(color);
            tagPanel.setBorder(new EmptyBorder(0, 5, 0, 5));
            JLabel tagLabel = new JLabel(tag);
            tagLabel.setFont(new Font(secondaryFont, Font.PLAIN, 9));
            tagLabel.setForeground(LocalAppSetting.isNightMode() ? white : claudeBlack);
            tagPanel.add(tagLabel);
            tagContainer.add(tagPanel);
        }
        return tagContainer;
    }

    private JPanel createGoToWebsiteButton(){
        JPanel goToWebsitePanel = new JPanel(new BorderLayout());
        goToWebsitePanel.setOpaque(false);

        goToWebsiteButton = new RoundButton("Open in browser");
        goToWebsiteButton.setFont(new Font(defaultFont, Font.PLAIN, 12));

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

    protected JPanel createButtonPanel() {
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        closeButton = new RoundButton("Close");
        addToGroceryButton = new RoundButton("Add To My Grocery List(s)");

        closeButton.addActionListener(e -> {
            this.dispose();
        });

        Recipe recipe = viewModel.getRecipe();

        JPopupMenu addToMenu = new JPopupMenu();
        JMenuItem addToGroceryButton = new JMenuItem("Add To My Grocery List(s)");

        if (userGroceryLists != null && !userGroceryLists.isEmpty()) {
            for (Map.Entry<String, ShoppingList> entry : userGroceryLists.entrySet()) {
                ShoppingList list = entry.getValue();
                JMenuItem groceryListItem = new JMenuItem("Add to " + list.getShoppingListName());
                groceryListItem.addActionListener(e -> {
                    addToGroceryList(recipe, list);
                });
                addToMenu.add(groceryListItem);
            }
        }


        // Option to create a new grocery list
        JMenuItem createNewGroceryListItem = new JMenuItem("Create New Grocery List");
        createNewGroceryListItem.addActionListener(e -> {
            createNewGroceryListAndAdd(recipe);
        });
        addToMenu.add(createNewGroceryListItem);

        addToGroceryButton.addActionListener(e -> {
            addToMenu.show(addToGroceryButton, addToGroceryButton.getWidth() / 2, addToGroceryButton.getHeight() / 2);
        });

        buttonPanel.add(addToGroceryButton);
        buttonPanel.add(closeButton);

        return buttonPanel;
    }

    protected JPanel createControlPanel() {
        controlPanel = new JPanel(new BorderLayout());
        controlPanel.setBorder(new EmptyBorder(10, 30, 10, 30));

        // Add button panel to control panel
        controlPanel.add(createButtonPanel(), BorderLayout.EAST);

        return controlPanel;
    }


    private void addToGroceryList(Recipe recipe, ShoppingList shoppingList) {
        coreFunctionalityController.execute(shoppingList, recipe, viewModel);
    }

    private void createNewGroceryListAndAdd(Recipe recipe) {
        String newListName = JOptionPane.showInputDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Enter name for new grocery list:");
        if (newListName != null && !newListName.trim().isEmpty()) {
            addNewGroceryListController.execute(newListName, viewModel);
        }
        ShoppingList newShoppingList = user.getShoppingList(newListName);
        coreFunctionalityController.execute(newShoppingList, recipe, viewModel);
    }


    public void setNightMode() {
        mainPanel.setBackground(black);
        contentPanel.setBackground(black);
        contentScrollPane.setBackground(black);

        titleLabel.setForeground(neonPinkEmph);

        goToWebsiteButton.setHoverColor(darkPurple, neonPink, white, darkPurple);
        goToWebsiteButton.setBorderColor(neonPinkEmph);

        controlPanel.setBackground(black);
        buttonPanel.setBackground(black);

        closeButton.setHoverColor(neonPink, darkPurple, white, white);
        closeButton.setBorderColor(neonPurple);

    }

    public void setDayMode() {
        mainPanel.setBackground(claudeWhite);
        contentPanel.setBackground(claudeWhite);
        contentScrollPane.setBackground(claudeWhite);

        titleLabel.setForeground(claudeBlack);

        goToWebsiteButton.setHoverColor(claudeWhite, claudeBlackEmph, claudeBlackEmph, claudeWhite);
        goToWebsiteButton.setBorderColor(claudeWhiteEmph);

        controlPanel.setBackground(claudeWhite);
        buttonPanel.setBackground(claudeWhite);

        closeButton.setHoverColor(claudeWhite, claudeWhiteEmph, claudeBlackEmph, claudeWhite);
        closeButton.setBorderColor(claudeWhite);
    }

}
