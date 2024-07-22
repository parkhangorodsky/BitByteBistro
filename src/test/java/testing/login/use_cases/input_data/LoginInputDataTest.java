package testing.login.use_cases.input_data;

import org.junit.jupiter.api.Test;
import use_cases.log_in.use_case.input_data.LoginInputData;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the LoginInputData class.
 */
public class LoginInputDataTest {

    /**
     * Test to verify the constructor initializes userEmail correctly.
     */
    @Test
    public void testConstructorInitializesUserEmail() {
        String testEmail = "test@example.com";
        String testPassword = "password";

        LoginInputData inputData = new LoginInputData(testEmail, testPassword);

        assertEquals(testEmail, inputData.getUserEmail());
    }

    /**
     * Test to verify the constructor initializes userPassword correctly.
     */
    @Test
    public void testConstructorInitializesUserPassword() {
        String testEmail = "test@example.com";
        String testPassword = "password";

        LoginInputData inputData = new LoginInputData(testEmail, testPassword);

        assertEquals(testPassword, inputData.getUserPassword());
    }

    /**
     * Test to verify getUserEmail returns the correct value.
     */
    @Test
    public void testGetUserEmail() {
        String testEmail = "test@example.com";
        String testPassword = "password";

        LoginInputData inputData = new LoginInputData(testEmail, testPassword);

        assertEquals(testEmail, inputData.getUserEmail());
    }

    /**
     * Test to verify getUserPassword returns the correct value.
     */
    @Test
    public void testGetUserPassword() {
        String testEmail = "test@example.com";
        String testPassword = "password";

        LoginInputData inputData = new LoginInputData(testEmail, testPassword);

        assertEquals(testPassword, inputData.getUserPassword());
    }
}
