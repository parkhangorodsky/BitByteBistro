package testing.signup.use_cases.input_data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases.sign_up.use_case.input_data.SignUpInputData;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for SignUpInputData class.
 * This class tests the getter methods and constructor of the SignUpInputData class.
 */
public class SignupInputDataTest {

    private SignUpInputData signUpInputData;

    @BeforeEach
    void setUp() {
        signUpInputData = new SignUpInputData("userID", "user@example.com", "password");
    }

    /**
     * Test for the getUserID method.
     * Ensures that the userID is correctly returned.
     */
    @Test
    void testGetUserID() {
        assertEquals("userID", signUpInputData.getUserID());
    }

    /**
     * Test for the getUserEmail method.
     * Ensures that the userEmail is correctly returned.
     */
    @Test
    void testGetUserEmail() {
        assertEquals("user@example.com", signUpInputData.getUserEmail());
    }

    /**
     * Test for the getUserPassword method.
     * Ensures that the userPassword is correctly returned.
     */
    @Test
    void testGetUserPassword() {
        assertEquals("password", signUpInputData.getUserPassword());
    }
}
