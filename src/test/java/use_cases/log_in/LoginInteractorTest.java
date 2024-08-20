package use_cases.log_in;

import app.local.LocalAppSetting;
import app.local.LoggedUserData;
import entity.User;
import frameworks.data_access.UserDataAccessInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases.log_in.use_case.input_data.LoginInputData;
import use_cases.log_in.use_case.interactor.LoginInteractor;
import use_cases.log_in.use_case.output_data.LoginOutputBoundary;
import use_cases.log_in.use_case.output_data.LoginOutputData;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the LoginInteractor class.
 */
public class LoginInteractorTest {

    private LoginInteractor loginInteractor;
    private LoginOutputBoundary loginOutputBoundary;
    private UserDataAccessInterface userDataAccessInterface;

    @BeforeEach
    void setUp() {
        loginOutputBoundary = mock(LoginOutputBoundary.class);
        userDataAccessInterface = mock(UserDataAccessInterface.class);
        loginInteractor = new LoginInteractor(loginOutputBoundary, userDataAccessInterface);
    }

    /**
     * Tests a successful login process.
     */
    @Test
    void testSuccessfulLogin() {
        // Arrange
        User user = new User("testUser", "testUser@example.com", "password123", LocalDateTime.now());
        when(userDataAccessInterface.getUserByEmail("testUser@example.com")).thenReturn(user);
        LoginInputData inputData = new LoginInputData("testUser@example.com", "password123");

        // Act
        loginInteractor.execute(inputData);

        // Assert
        verify(loginOutputBoundary).prepareSuccessView(any(LoginOutputData.class));
        assertEquals(user, LoggedUserData.getLoggedInUser());
        assertEquals(user.getPreference().get("nightMode"), LocalAppSetting.isNightMode());
    }

    /**
     * Tests a failed login process due to invalid password.
     */
    @Test
    void testFailedLoginInvalidPassword() {
        // Arrange
        LoggedUserData.setLoggedInUser(null);

        User user = new User("testUser", "testUser@example.com", "password123", LocalDateTime.now());
        when(userDataAccessInterface.getUserByEmail("testUser@example.com")).thenReturn(user);
        LoginInputData inputData = new LoginInputData("testUser@example.com", "wrongPassword");

        // Act
        loginInteractor.execute(inputData);

        // Assert
        verify(loginOutputBoundary).prepareFailView("Invalid email or password.");
        assertNull(LoggedUserData.getLoggedInUser());
    }

    /**
     * Tests a failed login process due to non-existing user.
     */
    @Test
    void testFailedLoginUserNotFound() {
        // Arrange
        LoggedUserData.setLoggedInUser(null);
        when(userDataAccessInterface.getUserByEmail("testUser@example.com")).thenReturn(null);
        LoginInputData inputData = new LoginInputData("testUser@example.com", "password123");

        // Act
        loginInteractor.execute(inputData);

        // Assert
        verify(loginOutputBoundary).prepareFailView("Invalid email or password.");
        assertNull(LoggedUserData.getLoggedInUser());
    }

    /**
     * Tests a failed login process due to missing email.
     */
    @Test
    void testFailedLoginMissingEmail() {
        // Arrange
        LoggedUserData.setLoggedInUser(null);
        LoginInputData inputData = new LoginInputData("", "password123");

        // Act
        loginInteractor.execute(inputData);

        // Assert
        verify(loginOutputBoundary).prepareFailView("Invalid email or password.");
        assertNull(LoggedUserData.getLoggedInUser());
    }

    /**
     * Tests a failed login process due to missing password.
     */
    @Test
    void testFailedLoginMissingPassword() {
        // Arrange
        LoggedUserData.setLoggedInUser(null);
        LoginInputData inputData = new LoginInputData("testUser@example.com", "");

        // Act
        loginInteractor.execute(inputData);

        // Assert
        verify(loginOutputBoundary).prepareFailView("Invalid email or password.");
        assertNull(LoggedUserData.getLoggedInUser());
    }

    /**
     * Tests a failed login process due to null email.
     */
    @Test
    void testFailedLoginNullEmail() {
        // Arrange
        LoggedUserData.setLoggedInUser(null);
        LoginInputData inputData = new LoginInputData(null, "password123");

        // Act
        loginInteractor.execute(inputData);

        // Assert
        verify(loginOutputBoundary).prepareFailView("Invalid email or password.");
        assertNull(LoggedUserData.getLoggedInUser());
    }

    /**
     * Tests a failed login process due to null password.
     */
    @Test
    void testFailedLoginNullPassword() {
        // Arrange
        LoggedUserData.setLoggedInUser(null);
        LoginInputData inputData = new LoginInputData("testUser@example.com", null);

        // Act
        loginInteractor.execute(inputData);

        // Assert
        verify(loginOutputBoundary).prepareFailView("Invalid email or password.");
        assertNull(LoggedUserData.getLoggedInUser());
    }
}
