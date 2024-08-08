package use_cases;

import app.local.LoggedUserData;
import entity.Ingredient;
import entity.ShoppingList;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases.add_new_grocery_list.AddNewGroceryListController;
import use_cases.core_functionality.MyGroceryView;
import use_cases.core_functionality.MyGroceryViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static use_cases._common.gui_common.abstractions.ThemeColoredObject.claudeWhite;

public class MyGroceryViewTest {

    private MyGroceryView view;
    private MyGroceryViewModel viewModel;
    private AddNewGroceryListController addNewGroceryListController;
    private User testUser;

    @BeforeEach
    public void setUp() {
        viewModel = mock(MyGroceryViewModel.class);
        addNewGroceryListController = mock(AddNewGroceryListController.class);
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
        assertEquals(viewModel, view.viewModel);
        assertEquals(addNewGroceryListController, view.addNewGroceryListController);
    }

//    @Test
//    public void testComponentShown() {
//        ComponentEvent event = mock(ComponentEvent.class);
//        view.componentShown(event);
//        verify(viewModel, times(1)).firePropertyChange("init");
//    }

    @Test
    public void testActionPerformed() {
        ActionEvent event = mock(ActionEvent.class);
        view.actionPerformed(event);
        // This test is just to ensure the method is covered since it is not used.
    }

    @Test
    public void testPropertyChangeInit() {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "init", null, null);
        view.propertyChange(event);
        verify(viewModel, times(1)).setUser(any());
        verify(viewModel, times(1)).firePropertyChange("grocery");
    }

    @Test
    public void testPropertyChangeGrocery() {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "grocery", null, null);
        view.propertyChange(event);
        verify(viewModel, times(1)).firePropertyChange("grocery");
    }

    @Test
    public void testPropertyChangeNightMode() {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "nightMode", null, null);
        view.propertyChange(event);
        verify(viewModel, times(1)).firePropertyChange("nightMode");
    }

    @Test
    public void testShowNewGroceryListInput() {
        view.showNewGroceryListInput();
        assertTrue(view.isTextBarOpen);

        view.newListNameTextField.setText("New Grocery List");
        view.newListNameTextField.getKeyListeners()[0].keyPressed(new KeyEvent(view.newListNameTextField, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, ' '));

        verify(addNewGroceryListController, times(1)).execute("New Grocery List", viewModel);
        verify(viewModel, times(1)).firePropertyChange("grocery");
    }

    @Test
    public void testCreateNewGroceryList() {
        view.showNewGroceryListInput();
        view.createNewGroceryList();
        assertFalse(view.isTextBarOpen);
    }

    @Test
    public void testUpdateMyGrocery() {
        view.updateMyGrocery();
        assertEquals(1, view.myGroceryContainer.getComponentCount());
    }

    @Test
    public void testCreateShoppingListItem() {
        ShoppingList shoppingList = new ShoppingList("test@example.com", "Test List");
        JPanel shoppingListItem = view.createShoppingListItem(shoppingList);
        assertNotNull(shoppingListItem);
    }

    @Test
    public void testCreateIngredientsPanel() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Tomato1", "Tomato", "pieces", "Vegetable", 2));
        JPanel ingredientsPanel = view.createIngredientsPanel(ingredients);
        assertEquals(1, ingredientsPanel.getComponentCount());
    }

    @Test
    public void testSetNightMode() {
        view.setNightMode();
        assertEquals(Color.BLACK, view.getBackground());
        verify(viewModel, times(1)).firePropertyChange("grocery");
    }

    @Test
    public void testSetDayMode() {
        view.setDayMode();
        assertEquals(claudeWhite, view.getBackground());
        verify(viewModel, times(1)).firePropertyChange("grocery");
    }

    @Test
    public void testRevalidateEverything() {
        JComponent component = mock(JComponent.class);
        view.revalidateEverything(component);
        verify(component, times(1)).revalidate();
        verify(component, times(1)).repaint();
    }
}
