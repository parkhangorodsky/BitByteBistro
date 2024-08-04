package testing.login.interface_adapter.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_cases.log_in.interface_adapter.controller.LoginController;
import use_cases.log_in.use_case.input_data.LoginInputBoundary;
import use_cases.log_in.use_case.input_data.LoginInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test class for LoginController.
 */
public class LoginControllerTest {

    private LoginInputBoundary loginInputBoundary;
    private LoginController loginController;

    /**
     * Set up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        loginInputBoundary = mock(LoginInputBoundary.class);
        loginController = new LoginController(loginInputBoundary);
    }

    /**
     * Test the login method with valid email and password.
     * Ensure it correctly creates a LoginInputData object and calls the execute method on the LoginInputBoundary.
     */
    @Test
    void testLogin() {
        String email = "test@example.com";
        String password = "password";

        // Call the method under test
        loginController.login(email, password);

        // Create an ArgumentCaptor for LoginInputData
        ArgumentCaptor<LoginInputData> argumentCaptor = ArgumentCaptor.forClass(LoginInputData.class);

        // Verify that the execute method of the interactor was called exactly once and capture the argument
        verify(loginInputBoundary, times(1)).execute(argumentCaptor.capture());

        // Retrieve the captured argument
        LoginInputData capturedInputData = argumentCaptor.getValue();

        // Assert that the captured argument has the expected email and password
        assertEquals(email, capturedInputData.getUserEmail());
        assertEquals(password, capturedInputData.getUserPassword());
    }

    /**
     * Test the login method with an empty email.
     * Ensure it correctly creates a LoginInputData object with an empty email.
     */
    @Test
    void testLoginWithEmptyEmail() {
        String email = "";
        String password = "password";

        // Call the method under test
        loginController.login(email, password);

        // Create an ArgumentCaptor for LoginInputData
        ArgumentCaptor<LoginInputData> argumentCaptor = ArgumentCaptor.forClass(LoginInputData.class);

        // Verify that the execute method of the interactor was called exactly once and capture the argument
        verify(loginInputBoundary, times(1)).execute(argumentCaptor.capture());

        // Retrieve the captured argument
        LoginInputData capturedInputData = argumentCaptor.getValue();

        // Assert that the captured argument has the expected empty email and password
        assertEquals(email, capturedInputData.getUserEmail());
        assertEquals(password, capturedInputData.getUserPassword());
    }

    /**
     * Test the login method with an empty password.
     * Ensure it correctly creates a LoginInputData object with an empty password.
     */
    @Test
    void testLoginWithEmptyPassword() {
        String email = "test@example.com";
        String password = "";

        // Call the method under test
        loginController.login(email, password);

        // Create an ArgumentCaptor for LoginInputData
        ArgumentCaptor<LoginInputData> argumentCaptor = ArgumentCaptor.forClass(LoginInputData.class);

        // Verify that the execute method of the interactor was called exactly once and capture the argument
        verify(loginInputBoundary, times(1)).execute(argumentCaptor.capture());

        // Retrieve the captured argument
        LoginInputData capturedInputData = argumentCaptor.getValue();

        // Assert that the captured argument has the expected email and empty password
        assertEquals(email, capturedInputData.getUserEmail());
        assertEquals(password, capturedInputData.getUserPassword());
    }
}
