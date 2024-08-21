package use_cases.core_functionality;

import app.local.LoggedUserData;
import entity.ShoppingList;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MyGroceryViewModelTest {

    private MyGroceryViewModel viewModel;
    private PropertyChangeListener listener;

    @BeforeEach
    public void setUp() {
        viewModel = new MyGroceryViewModel("MyGroceryView");
        listener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(listener);
    }

    @Test
    public void testConstructor() {
        assertNotNull(viewModel);
        assertEquals("MyGroceryView", viewModel.getViewName());
    }

//    @Test
//    public void testAddPropertyChangeListener() {
//        PropertyChangeListener newListener = mock(PropertyChangeListener.class);
//        viewModel.addPropertyChangeListener(newListener);
//
//        viewModel.firePropertyChange("testProperty");
//
//        verify(newListener, times(1)).propertyChange(any(PropertyChangeEvent.class));
//    }

    @Test
    public void testFirePropertyChange() {
        User testUser = mock(User.class);
        HashMap<String, ShoppingList> shoppingLists = new HashMap<>();
        ShoppingList list = new ShoppingList("test@example.com", "Test List");
        shoppingLists.put("Test List", list);

        when(testUser.getShoppingLists()).thenReturn(shoppingLists);
        LoggedUserData.setLoggedInUser(testUser);

        viewModel.firePropertyChange("grocery");

        verify(listener, times(1)).propertyChange(any(PropertyChangeEvent.class));
    }

    @Test
    public void testGetUser() {
        User testUser = new User("testUser", "test@example.com", "password123",null);
        viewModel.setUser(testUser);

        assertEquals(testUser, viewModel.getUser());
    }

    @Test
    public void testSetUser() {
        User testUser = new User("testUser", "test@example.com", "password123", null);
        viewModel.setUser(testUser);

        assertEquals(testUser, viewModel.getUser());
    }

//    @Test
//    public void testFirePropertyChangeWithNoLoggedInUser() {
//        LoggedUserData.setLoggedInUser(null);
//        viewModel.firePropertyChange("grocery");
//
//        verify(listener, times(0)).propertyChange(any(PropertyChangeEvent.class));
//    }

    @Test
    public void testFirePropertyChangeWithDifferentPropertyName() {
        User testUser = new User("testUser", "test@example.com", "password123", null);
        LoggedUserData.setLoggedInUser(testUser);
        viewModel.firePropertyChange("differentProperty");

        verify(listener, times(0)).propertyChange(any(PropertyChangeEvent.class));
    }
}
