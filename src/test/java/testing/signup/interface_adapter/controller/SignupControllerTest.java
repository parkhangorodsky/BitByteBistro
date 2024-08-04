package testing.signup.interface_adapter.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_cases.sign_up.interface_adapter.controller.SignUpController;
import use_cases.sign_up.use_case.input_data.SignUpInputBoundary;
import use_cases.sign_up.use_case.input_data.SignUpInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for SignUpController class.
 * This class tests the signUp method of the SignUpController class.
 */
public class SignupControllerTest {
    private SignUpController controller;
    private SignUpInputBoundary mockInteractor;

    @BeforeEach
    void setUp() {
        mockInteractor = mock(SignUpInputBoundary.class);
        controller = new SignUpController(mockInteractor);
    }

    /**
     * Test for the signUp method.
     * Ensures that the signUp method calls the execute method on the interactor with the correct input data.
     */
    @Test
    void testSignUp() {
        String userID = "userID";
        String userEmail = "user@example.com";
        String userPassword = "password";

        controller.signUp(userID, userEmail, userPassword);

        ArgumentCaptor<SignUpInputData> argumentCaptor = ArgumentCaptor.forClass(SignUpInputData.class);
        verify(mockInteractor, times(1)).execute(argumentCaptor.capture());

        SignUpInputData capturedInputData = argumentCaptor.getValue();
        assertEquals(userID, capturedInputData.getUserID());
        assertEquals(userEmail, capturedInputData.getUserEmail());
        assertEquals(userPassword, capturedInputData.getUserPassword());
    }
}
