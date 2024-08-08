package use_cases;

import app.local.LoggedUserData;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases.logout.use_case.interactor.LogoutInteractor;
import use_cases.logout.use_case.output.LogoutOutputBoundary;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the LogoutInteractor class.
 */
public class LogoutInteractorTest {

    private LogoutInteractor logoutInteractor;
    private LogoutOutputBoundary logoutOutputBoundary;

    @BeforeEach
    void setUp() {
        logoutOutputBoundary = mock(LogoutOutputBoundary.class);
        logoutInteractor = new LogoutInteractor(logoutOutputBoundary);
    }

    /**
     * Tests a successful logout process.
     */
    @Test
    void testSuccessfulLogout() {
        // Arrange
        User user = new User("testUser", "testUser@example.com", "password123", LocalDateTime.now());
        LoggedUserData.setLoggedInUser(user);

        // Act
        logoutInteractor.logout();

        // Assert
        verify(logoutOutputBoundary).logoutSuccess();
        assertNull(LoggedUserData.getLoggedInUser());
    }

    /**
     * Tests a logout process when no user is logged in.
     */
    @Test
    void testLogoutWhenNoUserLoggedIn() {
        // Arrange
        LoggedUserData.setLoggedInUser(null);

        // Act
        logoutInteractor.logout();

        // Assert
        verify(logoutOutputBoundary).logoutSuccess();
        assertNull(LoggedUserData.getLoggedInUser());
    }

    /**
     * Tests a logout process when an exception occurs.
     */
    @Test
    void testLogoutWithException() {
        // Arrange
        doThrow(new RuntimeException("Logout error")).when(logoutOutputBoundary).logoutSuccess();

        // Act
        logoutInteractor.logout();

        // Assert
        verify(logoutOutputBoundary).logoutFailure("Logout failed: Logout error");
    }
}
