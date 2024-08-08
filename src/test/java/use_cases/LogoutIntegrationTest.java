package use_cases;

import app.local.LoggedUserData;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases._common.authentication.AuthenticationViewModel;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.logout.interface_adapter.controller.LogoutController;
import use_cases.logout.interface_adapter.presenter.LogoutPresenter;
import use_cases.logout.use_case.interactor.LogoutInteractor;
import use_cases.logout.use_case.output.LogoutOutputBoundary;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

/**
 * Integration test for the logout use case.
 * This class tests the interaction between the LogoutController, LogoutInteractor, and LogoutPresenter.
 */
class LogoutIntegrationTest {
    private LogoutController controller;
    private LogoutInteractor interactor;
    private LogoutPresenter presenter;
    private AuthenticationViewModel authenticationViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        authenticationViewModel = new AuthenticationViewModel("login view");
        viewManagerModel = new ViewManagerModel();
        presenter = new LogoutPresenter(authenticationViewModel, viewManagerModel);
        interactor = new LogoutInteractor(presenter);
        controller = new LogoutController(interactor);
    }

    /**
     * Tests the successful logout scenario.
     */
    @Test
    void testLogoutSuccess() {
        // Arrange
        User user = new User("testUser", "test@example.com", "password123", null);
        LoggedUserData.setLoggedInUser(user);
        authenticationViewModel.addPropertyChangeListener(evt -> {
            if (evt.getPropertyName().equals("logoutSuccess")) {
                // Assert
                assertNull(LoggedUserData.getLoggedInUser());
            }
        });

        // Act
        controller.logout();
    }

    /**
     * Tests the failure logout scenario.
     */
    @Test
    void testLogoutFailure() {
        // Arrange
        LogoutOutputBoundary mockOutputBoundary = mock(LogoutOutputBoundary.class);
        LogoutInteractor mockInteractor = new LogoutInteractor(mockOutputBoundary);
        LogoutController mockController = new LogoutController(mockInteractor);

        // Simulate failure by throwing an exception
        doThrow(new RuntimeException("Logout failed")).when(mockOutputBoundary).logoutSuccess();

        // Act
        mockController.logout();

        // Assert
        verify(mockOutputBoundary).logoutFailure("Logout failed: Logout failed");
    }
}
