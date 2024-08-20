package use_cases.setting_preference;

import app.local.LoggedUserData;
import entity.User;
import frameworks.data_access.UserDataAccessInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the SetPreferenceInteractor class.
 */
public class SetPreferenceInteractorTest {

    private SetPreferenceInteractor interactor;
    private SetPreferencePresenter presenter;
    private UserDataAccessInterface userDAO;
    private User user;

    @BeforeEach
    void setUp() {
        presenter = mock(SetPreferencePresenter.class);
        userDAO = mock(UserDataAccessInterface.class);
        interactor = new SetPreferenceInteractor(presenter, userDAO);

        user = new User("testUser", "test@example.com", "password", null);
        LoggedUserData.setLoggedInUser(user);
    }

    /**
     * Tests setting the night mode preference to true.
     */
    @Test
    void testSetNightModeTrue() {
        // Arrange
        SetPreferenceInputData inputData = new SetPreferenceInputData(true, false);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(userDAO).updateUserPreference(user, "nightMode", true);
        ArgumentCaptor<Boolean> booleanCaptor = ArgumentCaptor.forClass(Boolean.class);
        verify(presenter).updateLocalAppSetting(booleanCaptor.capture(), false);
        assertEquals(true, booleanCaptor.getValue());
        assertEquals(true, user.getPreference().get("nightMode"));
    }

    /**
     * Tests setting the night mode preference to false.
     */
    @Test
    void testSetNightModeFalse() {
        // Arrange
        SetPreferenceInputData inputData = new SetPreferenceInputData(false, false);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(userDAO).updateUserPreference(user, "nightMode", false);
        ArgumentCaptor<Boolean> booleanCaptor = ArgumentCaptor.forClass(Boolean.class);
        verify(presenter).updateLocalAppSetting(booleanCaptor.capture(), false);
        assertEquals(false, booleanCaptor.getValue());
        assertEquals(false, user.getPreference().get("nightMode"));
    }

    /**
     * Tests setting the night mode preference with an unchanged value.
     */
    @Test
    void testSetNightModeUnchanged() {
        // Arrange
        user.updatePreference("nightMode", true);
        SetPreferenceInputData inputData = new SetPreferenceInputData(true, false);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(userDAO).updateUserPreference(user, "nightMode", true);
        ArgumentCaptor<Boolean> booleanCaptor = ArgumentCaptor.forClass(Boolean.class);
        verify(presenter).updateLocalAppSetting(booleanCaptor.capture(), false);
        assertEquals(true, booleanCaptor.getValue());
        assertEquals(true, user.getPreference().get("nightMode"));
    }

}
