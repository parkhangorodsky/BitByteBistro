package testing.signup.use_cases.interactor;

import entity.User;
import frameworks.data_access.UserDataAccessInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases.sign_up.use_case.input_data.SignUpInputData;
import use_cases.sign_up.use_case.interactor.SignUpInteractor;
import use_cases.sign_up.use_case.output_data.SignUpOutputBoundary;
import use_cases.sign_up.use_case.output_data.SignUpOutputData;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for SignUpInteractor class.
 * This class tests the execute method of the SignUpInteractor class.
 */
public class SignupInteractorTest {
    private SignUpInteractor interactor;
    private SignUpOutputBoundary outputBoundary;
    private UserDataAccessInterface dao;

    @BeforeEach
    void setUp() {
        outputBoundary = mock(SignUpOutputBoundary.class);
        dao = mock(UserDataAccessInterface.class);
        interactor = new SignUpInteractor(outputBoundary, dao);
    }

    /**
     * Test for successful sign-up process.
     * Ensures that a new user is created and added to the repository, and the success view is prepared.
     */
    @Test
    void testSuccessfulSignUp() {
        when(dao.existsByEmail("user@example.com")).thenReturn(false);

        SignUpInputData inputData = new SignUpInputData("userID", "user@example.com", "password");
        interactor.execute(inputData);

        verify(dao).addUser(any(User.class));
        verify(outputBoundary).prepareSuccessView(any(SignUpOutputData.class));
    }

    /**
     * Test for sign-up process with invalid input data.
     * Ensures that an error view is prepared when the input data is invalid.
     */
    @Test
    void testInvalidInputData() {
        SignUpInputData inputData = new SignUpInputData("", "", "");
        interactor.execute(inputData);

        verify(outputBoundary).prepareErrorView("Invalid input data provided. All fields are required.");
    }

    /**
     * Test for sign-up process with existing user.
     * Ensures that an error view is prepared when the user already exists.
     */
    @Test
    void testUserAlreadyExists() {
        when(dao.existsByEmail("user@example.com")).thenReturn(true);

        SignUpInputData inputData = new SignUpInputData("userID", "user@example.com", "password");
        interactor.execute(inputData);

        verify(outputBoundary).prepareErrorView("User already exists.");
    }
}
