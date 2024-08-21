package use_cases.display_recipe_detail;

import app.local.LoggedUserData;
import entity.Recipe;
import entity.ShoppingList;
import entity.User;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases.add_to_my_recipe.AddToMyRecipeController;
import use_cases.core_functionality.CoreFunctionalityController;
import use_cases.add_new_grocery_list.AddNewGroceryListController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DisplayRecipeDetailSearchResultViewTest {
    private DisplayRecipeDetailSearchResultView view;
    private DisplayRecipeDetailViewModel viewModel;
    private CoreFunctionalityController coreFunctionalityController;
    private AddNewGroceryListController addNewGroceryListController;
    private AddToMyRecipeController addToMyRecipeController;
    private Recipe recipe;
    private User user;
    private Map<String, ShoppingList> userGroceryLists;

    @BeforeEach
    void setUp() {
        // Mock dependencies
        recipe = mock(Recipe.class);
        viewModel = mock(DisplayRecipeDetailViewModel.class);
        when(viewModel.getRecipe()).thenReturn(recipe);
        when(viewModel.getRecipe().getImage()).thenReturn(new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB));
        coreFunctionalityController = mock(CoreFunctionalityController.class);
        addNewGroceryListController = mock(AddNewGroceryListController.class);
        addToMyRecipeController = mock(AddToMyRecipeController.class);

        user = mock(User.class);
        userGroceryLists = new HashMap<>();
        when(user.getShoppingLists()).thenReturn(userGroceryLists);
        LoggedUserData.setLoggedInUser(user);

        // Initialize the view with mocked dependencies
        view = new DisplayRecipeDetailSearchResultView(new JFrame(), viewModel, coreFunctionalityController, addNewGroceryListController, addToMyRecipeController);
    }

    @Test
    void testInitialize() {
        view.initialize();

        assertNotNull(view.mainPanel);
        assertNotNull(view.controlPanel);
        assertNotNull(view.buttonPanel);
        assertNotNull(view.mainPanel);
        assertNotNull(view.controlPanel);
    }

    @Test
    void testButtonActions() {
        view.initialize();
        view.show();

        RoundButton addToRecipesButton = (RoundButton) view.buttonPanel.getComponent(0);
        RoundButton addToGroceryButton = (RoundButton) view.buttonPanel.getComponent(1);

        addToRecipesButton.doClick();
        verify(addToMyRecipeController).execute(recipe, viewModel);

        addToGroceryButton.doClick();
        assertNotNull(view);
    }

    @Test
    void testShowAddToMenu() {
        ShoppingList shoppingList = mock(ShoppingList.class);
        when(shoppingList.getShoppingListName()).thenReturn("Test List");
        userGroceryLists.put("testKey", shoppingList);

        JPopupMenu menu = view.showAddToMenu(recipe);
        assertEquals(2, menu.getComponentCount()); // One for the existing list, one for creating new

        JMenuItem existingListItem = (JMenuItem) menu.getComponent(0);
        assertEquals("Add to Test List", existingListItem.getText());

        JMenuItem createNewListItem = (JMenuItem) menu.getComponent(1);
        assertEquals("Create New Grocery List And Add", createNewListItem.getText());
    }


    @Test
    void testSetNightMode() {
        view.initialize();
        view.setNightMode();

        // Check if the colors and styles are set correctly
        assertEquals(Color.BLACK, view.mainPanel.getBackground());
        assertEquals(Color.BLACK, view.contentPanel.getBackground());
        assertEquals(Color.BLACK, view.contentScrollPane.getBackground());

    }

    @Test
    void testSetDayMode() {
        view.initialize();
        view.setDayMode();

        // Check if the colors and styles are set correctly
        assertEquals(new Color(238, 237, 227), view.mainPanel.getBackground());
        assertEquals(new Color(238, 237, 227), view.contentPanel.getBackground());
        assertEquals(new Color(238, 237, 227), view.contentScrollPane.getBackground());

    }
}
