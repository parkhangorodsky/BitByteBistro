package use_cases;

import entity.User;
import frameworks.data_access.UserDataAccessInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_cases.sign_up.use_case.input_data.SignUpInputData;
import use_cases.sign_up.use_case.interactor.SignUpInteractor;
import use_cases.sign_up.use_case.output_data.SignUpOutputBoundary;
import use_cases.sign_up.use_case.output_data.SignUpOutputData;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the SignUpInteractor class.
 */
class SignUpInteractorTest {

    private SignUpInteractor signUpInteractor;
    private SignUpOutputBoundary outputBoundary;
    private UserDataAccessInterface userDataAccess;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        outputBoundary = Mockito.mock(SignUpOutputBoundary.class);
        userDataAccess = Mockito.mock(UserDataAccessInterface.class);
        signUpInteractor = new SignUpInteractor(outputBoundary, userDataAccess);
    }

    /**
     * Tests the execute method with a null userID.
     */
    @Test
    void testExecuteWithNullUserID() {
        SignUpInputData inputData = new SignUpInputData(null, "test@example.com", "password123");

        signUpInteractor.execute(inputData);

        verify(outputBoundary).prepareErrorView("Invalid input data provided. All fields are required.");
    }

    /**
     * Tests the execute method with an empty userID.
     */
    @Test
    void testExecuteWithEmptyUserID() {
        SignUpInputData inputData = new SignUpInputData("", "test@example.com", "password123");

        signUpInteractor.execute(inputData);

        verify(outputBoundary).prepareErrorView("Invalid input data provided. All fields are required.");
    }

    /**
     * Tests the execute method with a null email.
     */
    @Test
    void testExecuteWithNullEmail() {
        SignUpInputData inputData = new SignUpInputData("testUser", null, "password123");

        signUpInteractor.execute(inputData);

        verify(outputBoundary).prepareErrorView("Invalid input data provided. All fields are required.");
    }

    /**
     * Tests the execute method with an empty email.
     */
    @Test
    void testExecuteWithEmptyEmail() {
        SignUpInputData inputData = new SignUpInputData("testUser", "", "password123");

        signUpInteractor.execute(inputData);

        verify(outputBoundary).prepareErrorView("Invalid input data provided. All fields are required.");
    }

    /**
     * Tests the execute method with a null password.
     */
    @Test
    void testExecuteWithNullPassword() {
        SignUpInputData inputData = new SignUpInputData("testUser", "test@example.com", null);

        signUpInteractor.execute(inputData);

        verify(outputBoundary).prepareErrorView("Invalid input data provided. All fields are required.");
    }

    /**
     * Tests the execute method with an empty password.
     */
    @Test
    void testExecuteWithEmptyPassword() {
        SignUpInputData inputData = new SignUpInputData("testUser", "test@example.com", "");

        signUpInteractor.execute(inputData);

        verify(outputBoundary).prepareErrorView("Invalid input data provided. All fields are required.");
    }

    /**
     * Tests the execute method with an existing user.
     */
    @Test
    void testExecuteWithExistingUser() {
        SignUpInputData inputData = new SignUpInputData("testUser", "test@example.com", "password123");
        when(userDataAccess.existsByEmail(inputData.getUserEmail())).thenReturn(true);

        signUpInteractor.execute(inputData);

        verify(outputBoundary).prepareErrorView("User already exists.");
    }

    /**
     * Tests the execute method with valid input.
     */
    @Test
    void testExecuteWithValidInput() {
        SignUpInputData inputData = new SignUpInputData("testUser", "test@example.com", "password123");
        when(userDataAccess.existsByEmail(inputData.getUserEmail())).thenReturn(false);

        signUpInteractor.execute(inputData);

        ArgumentCaptor<SignUpOutputData> captor = ArgumentCaptor.forClass(SignUpOutputData.class);
        verify(outputBoundary).prepareSuccessView(captor.capture());

        SignUpOutputData outputData = captor.getValue();
        User createdUser = outputData.getUser();

        assertNotNull(createdUser);
        assertEquals("testUser", createdUser.getUserName());
        assertEquals("test@example.com", createdUser.getUserEmail());
        assertEquals("password123", createdUser.getUserPassword());
        assertNotNull(createdUser.getCreatedAt());

        verify(userDataAccess).addUser(createdUser);
    }
}
