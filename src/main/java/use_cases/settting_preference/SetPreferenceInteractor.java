package use_cases.settting_preference;

import entity.LoggedUserData;
import entity.User;
import frameworks.data_access.UserDataAccessInterface;

/**
 * The SetPreferenceInteractor class is responsible for handling the business logic
 * related to updating user preferences, specifically for the night mode setting.
 * <p>
 * It implements the SetPreferenceInputBoundary interface and interacts with the data
 * access layer to persist the updated preferences, as well as the output boundary
 * to manage the output of this use case.
 * </p>
 */
public class SetPreferenceInteractor implements SetPreferenceInputBoundary {

    private final SetPreferenceOutputBoundary presenter;
    private final UserDataAccessInterface userDAO;

    /**
     * Constructs a SetPreferenceInteractor with the specified output boundary and user data access interface.
     *
     * @param presenter The output boundary that will handle the output of the use case.
     * @param userDAO   The data access interface for persisting user preference changes.
     */
    public SetPreferenceInteractor(SetPreferenceOutputBoundary presenter, UserDataAccessInterface userDAO) {
        this.presenter = presenter;
        this.userDAO = userDAO;
    }

    /**
     * Executes the use case for setting user preferences, specifically the night mode setting.
     * <p>
     * This method retrieves the currently logged-in user, updates their night mode preference,
     * and persists the change using the user data access interface. It then informs the presenter
     * to handle the output of this use case.
     * </p>
     *
     * @param inputData The input data containing the user's preference for night mode.
     */
    @Override
    public void execute(SetPreferenceInputData inputData) {
        User user = LoggedUserData.getLoggedInUser();
        user.updatePreference("nightMode", inputData.getNightMode());
        userDAO.updateUserPreference(user, "nightMode", inputData.getNightMode());
        presenter.updateLocalAppSetting(inputData.getNightMode());
    }
}

