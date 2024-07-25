package testing;

import entity.User;
import frameworks.data_access.CSVDataAccessObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

public class CSVDataAccessObjectTest {

    private static final String TEST_CSV_PATH = "test_users.csv";
    private CSVDataAccessObject dataAccessObject;

    @BeforeEach
    public void setUp() {
        dataAccessObject = new CSVDataAccessObject(TEST_CSV_PATH);
    }

    @AfterEach
    public void tearDown() throws Exception {
        Files.deleteIfExists(new File(TEST_CSV_PATH).toPath());
    }

    @Test
    public void testSignUpAndLogin() {
        // Sign up two different users
        User user1 = new User("User1", "user1@example.com", "password1", null);
        User user2 = new User("User2", "user2@example.com", "password2", null);

        dataAccessObject.addUser(user1);
        dataAccessObject.addUser(user2);

        assertTrue(dataAccessObject.existsByEmail("user1@example.com"));
        assertTrue(dataAccessObject.existsByEmail("user2@example.com"));

        // Log in with the first user
        boolean loginResult = dataAccessObject.authenticate("user1@example.com", "password1");
        assertTrue(loginResult);

        // Retrieve the logged-in user
        User loggedInUser = dataAccessObject.getLoggedInUser();
        assertNotNull(loggedInUser);
        assertEquals("user1@example.com", loggedInUser.getUserEmail());
        assertEquals("User1", loggedInUser.getUserName());

        // Log out the first user
        dataAccessObject.logout(loggedInUser);
        assertNull(dataAccessObject.getLoggedInUser());

        // Log in with the second user
        loginResult = dataAccessObject.authenticate("user2@example.com", "password2");
        assertTrue(loginResult);

        // Retrieve the logged-in user
        loggedInUser = dataAccessObject.getLoggedInUser();
        assertNotNull(loggedInUser);
        assertEquals("user2@example.com", loggedInUser.getUserEmail());
        assertEquals("User2", loggedInUser.getUserName());
    }
}
