package use_cases;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.sign_up.gui.view.SignUpView;
import use_cases.sign_up.interface_adapter.controller.SignUpController;
import use_cases.sign_up.interface_adapter.presenter.SignUpPresenter;
import use_cases.sign_up.interface_adapter.view_model.SignUpViewModel;
import use_cases.sign_up.use_case.interactor.SignUpInteractor;
import frameworks.data_access.UserDataAccessInterface;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * End-to-end test for the sign-up use case.
 */
public class SignUpEndToEndTest {

    private SignUpController signUpController;
    private SignUpViewModel signUpViewModel;
    private SignUpPresenter signUpPresenter;
    private SignUpInteractor signUpInteractor;
    private ViewManagerModel viewManagerModel;
    private UserDataAccessInterface userDAO;
    private SignUpView signUpView;

    @BeforeEach
    void setUp() {
        // Initialize the view models and the mock DAO
        viewManagerModel = new ViewManagerModel();
        signUpViewModel = new SignUpViewModel("SignUpView");
        userDAO = mock(UserDataAccessInterface.class);

        // Set up the presenter, interactor, and controller
        signUpPresenter = new SignUpPresenter(signUpViewModel, viewManagerModel);
        signUpInteractor = new SignUpInteractor(signUpPresenter, userDAO);
        signUpController = new SignUpController(signUpInteractor);

        // Set up the sign-up view
        signUpView = new SignUpView(signUpController, signUpViewModel, viewManagerModel);
    }

    /**
     * Tests the successful sign-up process.
     */
    @Test
    void testSuccessfulSignUp() {
        // Arrange
        when(userDAO.existsByEmail("test@example.com")).thenReturn(false);
        when(userDAO.existsByEmail("testUser")).thenReturn(false);

        // Act
        signUpView.userIDField.setText("testUser");
        signUpView.emailField.setText("test@example.com");
        signUpView.passwordField.setText("password123");
        signUpView.signUpButton.doClick();

        // Assert
        assertEquals("User signed up successfully!", signUpViewModel.getSuccessMessage());
        verify(userDAO).addUser(any(User.class));
    }

    /**
     * Tests the sign-up process with an existing email.
     */
    @Test
    void testSignUpWithExistingEmail() {
        // Arrange
        when(userDAO.existsByEmail("test@example.com")).thenReturn(true);

        // Act
        signUpView.userIDField.setText("testUser");
        signUpView.emailField.setText("test@example.com");
        signUpView.passwordField.setText("password123");
        signUpView.signUpButton.doClick();

        // Assert
        assertEquals("User already exists.", signUpViewModel.getErrorMessage());
        verify(userDAO, never()).addUser(any(User.class));
    }

    /**
     * Tests the sign-up process with an existing user ID.
     */
    @Test
    void testSignUpWithExistingUserID() {
        // Arrange
        when(userDAO.existsByEmail("testUser")).thenReturn(true);

        // Act
        signUpView.userIDField.setText("testUser");
        signUpView.emailField.setText("test@example.com");
        signUpView.passwordField.setText("password123");
        signUpView.signUpButton.doClick();

        // Assert
        assertEquals("User already exists.", signUpViewModel.getErrorMessage());
        verify(userDAO, never()).addUser(any(User.class));
    }

    /**
     * Tests the sign-up process with invalid input data.
     */
    @Test
    void testSignUpWithInvalidInput() {
        // Act
        signUpView.userIDField.setText("");
        signUpView.emailField.setText("");
        signUpView.passwordField.setText("");
        signUpView.signUpButton.doClick();

        // Assert
        assertEquals("Invalid input data provided. All fields are required.", signUpViewModel.getErrorMessage());
        verify(userDAO, never()).addUser(any(User.class));
    }
}
