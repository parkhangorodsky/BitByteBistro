package testing.signup.use_cases.output_data;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases.sign_up.use_case.output_data.SignUpOutputData;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for SignUpOutputData class.
 * This class tests the getter and setter methods of the SignUpOutputData class.
 */
public class SignupOutputDataTest {

    private SignUpOutputData signUpOutputData;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User("userID", "user@example.com", "password", null);
        signUpOutputData = new SignUpOutputData(user);
    }

    /**
     * Test for the getUser method.
     * Ensures that the user is correctly returned.
     */
    @Test
    void testGetUser() {
        assertEquals(user, signUpOutputData.getUser());
    }

    /**
     * Test for the setUser method.
     * Ensures that the user is correctly set.
     */
    @Test
    void testSetUser() {
        User newUser = new User("newUserID", "newuser@example.com", "newpassword", null);
        signUpOutputData.setUser(newUser);
        assertEquals(newUser, signUpOutputData.getUser());
    }
}
