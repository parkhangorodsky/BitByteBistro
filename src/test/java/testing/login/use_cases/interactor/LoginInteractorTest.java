package testing.login.use_cases.interactor;

import entity.User;
import frameworks.data_access.UserDataAccessInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases.log_in.use_case.input_data.LoginInputData;
import use_cases.log_in.use_case.output_data.LoginOutputBoundary;
import use_cases.log_in.use_case.output_data.LoginOutputData;
import use_cases.log_in.use_case.interactor.LoginInteractor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the LoginInteractor class.
 */
public class LoginInteractorTest {
    private LoginInteractor interactor;
    private LoginOutputBoundary presenter;
    private UserDataAccessInterface dao;

    /**
     * Sets up the test environment before each test.
     * Mocks the dependencies of the LoginInteractor.
     */
    @BeforeEach
    void setUp() {
        presenter = mock(LoginOutputBoundary.class);
        dao = mock(UserDataAccessInterface.class);
    }

    /**
     * Tests the successful login scenario.
     * Verifies that the presenter prepares a success view when the login is successful.
     */
    @Test
    void testSuccessfulLogin() {
        User user = new User("userId", "test@example.com", "password", null);
        when(dao.getUserByEmail("test@example.com")).thenReturn(user);

        interactor.execute(new LoginInputData("test@example.com", "password"));

        verify(presenter).prepareSuccessView(any(LoginOutputData.class));
    }

    /**
     * Tests the failed login scenario with an incorrect email.
     * Verifies that the presenter prepares a fail view with the appropriate error message.
     */
    @Test
    void testFailedLoginWithIncorrectEmail() {
        when(dao.getUserByEmail("test@example.com")).thenReturn(null);

        interactor.execute(new LoginInputData("test@example.com", "password"));

        verify(presenter).prepareFailView("Invalid email or password.");
    }

    /**
     * Tests the failed login scenario with an incorrect password.
     * Verifies that the presenter prepares a fail view with the appropriate error message.
     */
    @Test
    void testFailedLoginWithIncorrectPassword() {
        User user = new User("userId", "test@example.com", "correctPassword", null);
        when(dao.getUserByEmail("test@example.com")).thenReturn(user);

        interactor.execute(new LoginInputData("test@example.com", "wrongPassword"));

        verify(presenter).prepareFailView("Invalid email or password.");
    }
}
