package testing.login.interface_adapter.view_model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases.log_in.interface_adapter.view_model.LoginViewModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Test class for LoginViewModel.
 */
public class LoginViewModelTest {
    private LoginViewModel viewModel;

    /**
     * Set up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        viewModel = new LoginViewModel("LoginView");
    }

    /**
     * Test adding and removing property change listeners.
     * Ensure that listeners can be added and removed, and that they are notified of changes appropriately.
     */
    @Test
    void testAddRemovePropertyChangeListener() {
        PropertyChangeListener listener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(listener);
        String newErrorMessage = "Invalid credentials";
        viewModel.setErrorMessage(newErrorMessage);

        verify(listener, times(1)).propertyChange(any(PropertyChangeEvent.class));

        viewModel.removePropertyChangeListener(listener);
        viewModel.setErrorMessage("Another error message");

        verifyNoMoreInteractions(listener);
    }

    /**
     * Test getting the error message.
     * Ensure that the correct error message is returned.
     */
    @Test
    void testGetErrorMessage() {
        String errorMessage = "Invalid credentials";
        viewModel.setErrorMessage(errorMessage);

        assertEquals(errorMessage, viewModel.getErrorMessage());
    }
}
