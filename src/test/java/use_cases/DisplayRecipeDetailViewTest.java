package use_cases;

import app.local.LoggedUserData;
import entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_cases.add_new_grocery_list.AddNewGroceryListController;
import use_cases.core_functionality.CoreFunctionalityController;
import use_cases.display_recipe_detail.DisplayRecipeDetailView;
import use_cases.display_recipe_detail.DisplayRecipeDetailViewModel;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DisplayRecipeDetailViewTest {
    private DisplayRecipeDetailView displayRecipeDetailView;
    private DisplayRecipeDetailViewModel viewModel;
    private CoreFunctionalityController coreFunctionalityController;
    private AddNewGroceryListController addNewGroceryListController;
    private JFrame parent;
    private Recipe recipe;
    private User user;
    private ShoppingList shoppingList;

    @BeforeEach
    void setUp() {
        parent = new JFrame();
        viewModel = mock(DisplayRecipeDetailViewModel.class);
        coreFunctionalityController = mock(CoreFunctionalityController.class);
        addNewGroceryListController = mock(AddNewGroceryListController.class);
        recipe = new Recipe("test-id");
        recipe.setName("Test Recipe");
        recipe.setIngredientList(Collections.singletonList(new Ingredient("1", "Tomato", "kg", "Vegetable", 1)));
        recipe.setNutritionMap(Collections.singletonMap("Calories", new Nutrition("Calories", 200, "kcal")));

        user = new User("John", "john@example.com", "password", LocalDateTime.now());
        shoppingList = new ShoppingList("John", "Weekly Shopping");

        Map<String, ShoppingList> shoppingLists = new HashMap<>();
        shoppingLists.put("Weekly Shopping", shoppingList);
        user.setShoppingLists((List<ShoppingList>) shoppingLists);
        LoggedUserData.setLoggedInUser(user);

        displayRecipeDetailView = new DisplayRecipeDetailView(parent, viewModel, coreFunctionalityController, addNewGroceryListController) {
        };
    }

    @Test
    void testPropertyChangeInitialized() {
        when(viewModel.getRecipe()).thenReturn(recipe);
        displayRecipeDetailView.propertyChange(new PropertyChangeEvent(this, "initialized", null, null));
        assertEquals(displayRecipeDetailView.mainPanel.isVisible(), true);
    }

    @Test
    void testPropertyChangeNightMode() {
        displayRecipeDetailView.propertyChange(new PropertyChangeEvent(this, "nightMode", null, null));
        // Check that the night mode has been toggled
    }

    @Test
    void testInitialize() {
        when(viewModel.getRecipe()).thenReturn(recipe);
        displayRecipeDetailView.initialize();
        // Assertions to verify that the UI components are correctly initialized
        assertEquals(displayRecipeDetailView.titleLabel.getText(), "Test Recipe");
    }

    @Test
    void testCreateContentPanel() {
        when(viewModel.getRecipe()).thenReturn(recipe);
        JScrollPane contentScrollPane = displayRecipeDetailView.createContentPanel(recipe);
        // Assertions to verify the content of the scroll pane
        assertEquals(contentScrollPane.isVisible(), true);
    }

    @Test
    void testCreateButtonPanel() {
        JPanel buttonPanel = displayRecipeDetailView.createButtonPanel();
        // Assertions to verify the buttons in the panel
        assertEquals(buttonPanel.isVisible(), true);
    }

    @Test
    void testCreateControlPanel() {
        JPanel controlPanel = displayRecipeDetailView.createControlPanel();
        // Assertions to verify the control panel
        assertEquals(controlPanel.isVisible(), true);
    }

    @Test
    void testAddToGroceryList() {
        when(viewModel.getRecipe()).thenReturn(recipe);
        displayRecipeDetailView.initialize();
        displayRecipeDetailView.addToGroceryList(recipe, shoppingList);
        verify(coreFunctionalityController, times(1)).execute(shoppingList, recipe, viewModel);
    }

    @Test
    void testCreateNewGroceryListAndAdd() {
        when(viewModel.getRecipe()).thenReturn(recipe);
        displayRecipeDetailView.initialize();
        doAnswer(invocation -> {
            LoggedUserData.getLoggedInUser().getShoppingLists().put("New List", shoppingList);
            return null;
        }).when(addNewGroceryListController).execute(anyString(), any());

        displayRecipeDetailView.createNewGroceryListAndAdd(recipe);
        verify(coreFunctionalityController, times(1)).execute(any(ShoppingList.class), eq(recipe), eq(viewModel));
    }
}
