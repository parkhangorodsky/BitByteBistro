package testing.login.use_cases.output_data;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases.log_in.use_case.output_data.LoginOutputData;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the LoginOutputData class.
 */
public class LoginOutputDataTest {

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User("userId", "test@example.com", "password", null);
    }

    /**
     * Test to verify the constructor initializes user correctly.
     */
    @Test
    public void testConstructorInitializesUser() {
        LoginOutputData outputData = new LoginOutputData(testUser);

        assertEquals(testUser, outputData.getUser());
    }

    /**
     * Test to verify getUser returns the correct value.
     */
    @Test
    public void testGetUser() {
        LoginOutputData outputData = new LoginOutputData(testUser);

        assertEquals(testUser, outputData.getUser());
    }

    /**
     * Test to verify setUser sets the user correctly.
     */
    @Test
    public void testSetUser() {
        LoginOutputData outputData = new LoginOutputData(null);
        outputData.setUser(testUser);

        assertEquals(testUser, outputData.getUser());
    }

    /**
     * Test to verify setUser can set the user to null.
     */
    @Test
    public void testSetUserToNull() {
        LoginOutputData outputData = new LoginOutputData(testUser);
        outputData.setUser(null);

        assertNull(outputData.getUser());
    }
}
