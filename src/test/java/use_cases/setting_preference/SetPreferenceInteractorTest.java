package use_cases.setting_preference;

import app.local.LoggedUserData;
import entity.User;
import frameworks.data_access.UserDataAccessInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SetPreferenceInteractorTest {

    private SetPreferenceOutputBoundary presenter;
    private UserDataAccessInterface userDAO;
    private SetPreferenceInteractor interactor;

    @BeforeEach
    void setUp() {
        presenter = mock(SetPreferenceOutputBoundary.class);
        userDAO = mock(UserDataAccessInterface.class);
        interactor = new SetPreferenceInteractor(presenter, userDAO);
    }

    @Test
    void testExecute() {
        // Given
        boolean nightMode = true;
        SetPreferenceInputData inputData = new SetPreferenceInputData(nightMode,false);

        User mockUser = mock(User.class);
        LoggedUserData.setLoggedInUser(mockUser); // Set the mock user as the logged-in user

        // When
        interactor.execute(inputData);

        // Then
        // Verify that the user preference is updated
        verify(mockUser).updatePreference("nightMode", nightMode);

        // Verify that the userDAO update method is called
        verify(userDAO).updateUserPreference(mockUser, "nightMode", nightMode);

        // Verify that the presenter updates the local app setting
        verify(presenter).updateLocalAppSetting(nightMode, false);
    }
}

