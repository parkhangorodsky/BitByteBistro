package use_cases.core_functionality;

import app.local.LocalAppSetting;
import app.local.LoggedUserData;
import entity.Ingredient;
import entity.ShoppingList;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_cases.add_new_grocery_list.AddNewGroceryListController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MyGroceryViewTest {

    private MyGroceryView view;
    private MyGroceryViewModel viewModel;
    private AddNewGroceryListController addNewGroceryListController;
    private User testUser;

    @BeforeEach
    public void setUp() {
        viewModel = mock(MyGroceryViewModel.class);
        addNewGroceryListController = mock(AddNewGroceryListController.class);

        // Initialize MyGroceryView with mocked dependencies
        view = new MyGroceryView(viewModel, addNewGroceryListController);

        // Mock test user
        testUser = new User("testUser", "test@example.com", "password123", null);
        ShoppingList shoppingList = new ShoppingList("test@example.com", "Test List");
        testUser.addShoppingList(shoppingList);
        LoggedUserData.setLoggedInUser(testUser);
    }

    @Test
    public void testConstructor() {
        assertNotNull(view);
    }

    @Test
    public void testActionPerformed() {
        ActionEvent event = mock(ActionEvent.class);
        view.actionPerformed(event);
        // Verify interaction if needed; this test ensures coverage.
    }


    @Test
    public void testPropertyChangeNightMode() {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "nightMode", null, null);
        assertEquals( new Color(238, 237, 227), view.getBackground());
        LocalAppSetting.setNightMode(true);
        view.propertyChange(event);
        assertEquals(Color.BLACK, view.getBackground());

    }
//
//    @Test
//    public void testShowNewGroceryListInput() {
//        view.showNewGroceryListInput();
//        assertTrue(view.isTextBarOpen);
//
//        view.newListNameTextField.setText("New Grocery List");
//        view.newListNameTextField.getKeyListeners()[0].keyPressed(new KeyEvent(view.newListNameTextField, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, ' '));
//
//        verify(addNewGroceryListController, times(1)).execute("New Grocery List", viewModel);
//        verify(viewModel, times(1)).firePropertyChange("grocery");
//    }
//
//    @Test
//    public void testCreateNewGroceryList() {
//        view.showNewGroceryListInput();
//        view.createNewGroceryList();
//        assertFalse(view.isTextBarOpen);
//    }
//
//    @Test
//    public void testUpdateMyGrocery() {
//        view.updateMyGrocery();
//        assertEquals(1, view.myGroceryContainer.getComponentCount());
//    }
//
//    @Test
//    public void testCreateShoppingListItem() {
//        ShoppingList shoppingList = new ShoppingList("test@example.com", "Test List");
//        JPanel shoppingListItem = view.createShoppingListItem(shoppingList);
//        assertNotNull(shoppingListItem);
//    }

//    @Test
//    public void testCreateIngredientsPanel() {
//        List<Ingredient> ingredients = new ArrayList<>();
//        ingredients.add(new Ingredient("Tomato1", "Tomato", "pieces", "Vegetable", 2));
//        JPanel ingredientsPanel = view.createIngredientsPanel(ingredients);
//        assertEquals(1, ingredientsPanel.getComponentCount());
//    }

    @Test
    public void testSetNightMode() {
        view.setNightMode();
        assertEquals(Color.BLACK, view.getBackground());
    }

    @Test
    public void testSetDayMode() {
        view.setDayMode();
        assertEquals( new Color(238, 237, 227), view.getBackground()); // Adjust this if necessary
    }

//    @Test
//    public void testRevalidateEverything() {
//        JComponent component = mock(JComponent.class);
//        view.revalidateEverything(component);
//        verify(component, times(1)).revalidate();
//        verify(component, times(1)).repaint();
//    }
}

