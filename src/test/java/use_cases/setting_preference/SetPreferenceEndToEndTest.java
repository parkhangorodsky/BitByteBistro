package use_cases.setting_preference;

import app.local.LocalAppSetting;
import app.local.LoggedUserData;
import entity.User;
import frameworks.data_access.UserDataAccessInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * End-to-end test for the set preference use case.
 */
public class SetPreferenceEndToEndTest {

    private SetPreferenceController setPreferenceController;
    private SetPreferencePresenter setPreferencePresenter;
    private SetPreferenceInteractor setPreferenceInteractor;
    private UserDataAccessInterface userDAO;
    private PreferenceView preferenceView;

    @BeforeEach
    void setUp() {
        // Initialize the mock DAO
        userDAO = mock(UserDataAccessInterface.class);

        // Set up the presenter, interactor, and controller
        setPreferencePresenter = new SetPreferencePresenter();
        setPreferenceInteractor = new SetPreferenceInteractor(setPreferencePresenter, userDAO);
        setPreferenceController = new SetPreferenceController(setPreferenceInteractor);

        // Set up the preference view
        preferenceView = new PreferenceView(new JFrame(), setPreferenceController);

        // Mock a logged-in user
        User user = new User("testUser", "test@example.com", "password123", null);
        LoggedUserData.setLoggedInUser(user);
    }

    /**
     * Tests the successful setting of preferences to night mode.
     */
    @Test
    void testSetNightModePreferenceSuccess() {
        // Arrange
        boolean nightMode = true;

        // Act
        preferenceView.nightModeCheckBox.setSelected(nightMode);
        preferenceView.applyButton.doClick();

        // Assert
        verify(userDAO).updateUserPreference(any(User.class), eq("nightMode"), eq(nightMode));
        assertEquals(nightMode, LocalAppSetting.isNightMode());
    }

    /**
     * Tests the successful setting of preferences to day mode.
     */
    @Test
    void testSetDayModePreferenceSuccess() {
        // Arrange
        boolean nightMode = false;

        // Act
        preferenceView.nightModeCheckBox.setSelected(nightMode);
        preferenceView.applyButton.doClick();

        // Assert
        verify(userDAO).updateUserPreference(any(User.class), eq("nightMode"), eq(nightMode));
        assertEquals(nightMode, LocalAppSetting.isNightMode());
    }
}
