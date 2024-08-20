package use_cases;

import app.local.LoggedUserData;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases._common.authentication.AuthenticationViewModel;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.log_in.gui.view.LoginView;
import use_cases.log_in.interface_adapter.controller.LoginController;
import use_cases.log_in.interface_adapter.presenter.LoginPresenter;
import use_cases.log_in.interface_adapter.view_model.LoginViewModel;
import use_cases.log_in.use_case.interactor.LoginInteractor;
import use_cases.log_in.use_case.output_data.LoginOutputBoundary;
import frameworks.data_access.UserDataAccessInterface;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * End-to-end test for the login use case.
 */
public class LoginEndToEndTest {

    private LoginController loginController;
    private LoginViewModel loginViewModel;
    private LoginPresenter loginPresenter;
    private LoginInteractor loginInteractor;
    private AuthenticationViewModel authenticationViewModel;
    private ViewManagerModel viewManagerModel;
    private UserDataAccessInterface userDAO;
    private LoginView loginView;

    @BeforeEach
    void setUp() {
        // Initialize the view models and the mock DAO
        authenticationViewModel = new AuthenticationViewModel("LoginView");
        viewManagerModel = new ViewManagerModel();
        loginViewModel = new LoginViewModel("LoginView");
        userDAO = mock(UserDataAccessInterface.class);

        // Set up the presenter, interactor, and controller
        loginPresenter = new LoginPresenter(loginViewModel, viewManagerModel, authenticationViewModel);
        loginInteractor = new LoginInteractor(loginPresenter, userDAO);
        loginController = new LoginController(loginInteractor);

        // Set up the login view
        loginView = new LoginView(loginController, loginViewModel, viewManagerModel);
    }

    @Test
    void testSuccessfulLogin() {
        // Arrange
        User user = new User("testUser", "test@example.com", "password123", null);
        when(userDAO.getUserByEmail("test@example.com")).thenReturn(user);

        // Act
        loginView.emailField.setText("test@example.com");
        loginView.passwordField.setText("password123");
        loginView.loginButton.doClick();

        // Assert
        assertEquals(loginViewModel.getErrorMessage(), "");
        assertEquals(user, LoggedUserData.getLoggedInUser());
        verify(userDAO).getUserByEmail("test@example.com");
    }

    @Test
    void testFailedLogin() {
        // Arrange
        LoggedUserData.setLoggedInUser(null);
        when(userDAO.getUserByEmail("wrong@example.com")).thenReturn(null);

        // Act
        loginView.emailField.setText("wrong@example.com");
        loginView.passwordField.setText("wrongpassword");
        loginView.loginButton.doClick();

        // Assert
        assertEquals("Invalid email or password.", loginViewModel.getErrorMessage());
        assertNull(LoggedUserData.getLoggedInUser());
        verify(userDAO).getUserByEmail("wrong@example.com");
    }
}
