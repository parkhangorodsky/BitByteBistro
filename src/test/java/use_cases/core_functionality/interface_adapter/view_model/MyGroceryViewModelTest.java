package use_cases.core_functionality.interface_adapter.view_model;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import app.local.LoggedUserData;
import use_cases.core_functionality.MyGroceryViewModel;

import java.beans.PropertyChangeListener;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MyGroceryViewModelTest {

    private MyGroceryViewModel viewModel;
    private PropertyChangeListener listener;
    private User mockUser;

    @BeforeEach
    void setUp() {
        // Initialize MyGroceryViewModel with a view name
        viewModel = new MyGroceryViewModel("MyGroceryView");

        // Create a mock PropertyChangeListener
        listener = mock(PropertyChangeListener.class);

        // Create a mock User
        mockUser = mock(User.class);

        // Add the mock listener to the viewModel
        viewModel.addPropertyChangeListener(listener);
    }

    /**
     * Tests that the firePropertyChange method fires the correct property change event when "grocery" is passed.
     */
    @Test
    void testFirePropertyChangeGrocery() {
        // Arrange: Set up the logged in user and their shopping lists
        LoggedUserData.setLoggedInUser(mockUser);
        when(mockUser.getShoppingLists()).thenReturn(Collections.singletonMap("TestList", null));

        // Act: Fire property change with "grocery"
        viewModel.firePropertyChange("grocery");

        // Assert: Verify that the listener was notified with the correct property change event
        verify(listener, times(1)).propertyChange(argThat(event ->
                event.getPropertyName().equals("grocery") && event.getNewValue().equals(mockUser.getShoppingLists().values())
        ));
    }

    /**
     * Tests that the getUser method returns the correct user.
     */
    @Test
    void testGetUser() {
        // Act: Set the user in the viewModel and then get it
        viewModel.setUser(mockUser);

        // Assert: Verify that the user is correctly returned
        assertEquals(mockUser, viewModel.getUser());
    }

    /**
     * Tests that the setUser method correctly updates the user.
     */
    @Test
    void testSetUser() {
        // Act: Set the user in the viewModel
        viewModel.setUser(mockUser);

        // Assert: Verify that the user is set correctly
        assertEquals(mockUser, viewModel.getUser());
    }

}