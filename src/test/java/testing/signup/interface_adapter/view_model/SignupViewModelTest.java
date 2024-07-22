package testing.signup.interface_adapter.view_model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases.sign_up.interface_adapter.view_model.SignUpViewModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Unit tests for SignUpViewModel class.
 * This class tests the getter, setter, and listener methods of the SignUpViewModel class.
 */
public class SignupViewModelTest {
    private SignUpViewModel viewModel;
    private PropertyChangeListener mockListener;

    @BeforeEach
    void setUp() {
        viewModel = new SignUpViewModel("SignUpView");
        mockListener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(mockListener);
    }

    /**
     * Test for the setUserID method.
     * Ensures that the userID is correctly set and listeners are notified.
     */
    @Test
    void testSetUserID() {
        String userID = "userID";
        viewModel.setUserID(userID);

        assertEquals(userID, viewModel.getUserID());
        verify(mockListener).propertyChange(any(PropertyChangeEvent.class));
    }

    /**
     * Test for the setUserEmail method.
     * Ensures that the userEmail is correctly set and listeners are notified.
     */
    @Test
    void testSetUserEmail() {
        String userEmail = "user@example.com";
        viewModel.setUserEmail(userEmail);

        assertEquals(userEmail, viewModel.getUserEmail());
        verify(mockListener).propertyChange(any(PropertyChangeEvent.class));
    }

    /**
     * Test for the setSuccessMessage method.
     * Ensures that the successMessage is correctly set and listeners are notified.
     */
    @Test
    void testSetSuccessMessage() {
        String successMessage = "User signed up successfully!";
        viewModel.setSuccessMessage(successMessage);

        assertEquals(successMessage, viewModel.getSuccessMessage());
        verify(mockListener).propertyChange(any(PropertyChangeEvent.class));
    }

    /**
     * Test for the setErrorMessage method.
     * Ensures that the errorMessage is correctly set and listeners are notified.
     */
    @Test
    void testSetErrorMessage() {
        String errorMessage = "Error occurred";
        viewModel.setErrorMessage(errorMessage);

        assertEquals(errorMessage, viewModel.getErrorMessage());
        verify(mockListener).propertyChange(any(PropertyChangeEvent.class));
    }
}
