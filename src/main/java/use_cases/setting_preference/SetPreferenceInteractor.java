package use_cases.setting_preference;

import app.local.LoggedUserData;
import entity.User;
import frameworks.data_access.UserDataAccessInterface;

/**
 * Interactor class that handles the business logic for setting user preferences.
 * This class implements the use case of updating the user's preferences in the system.
 */
public class SetPreferenceInteractor implements SetPreferenceInputBoundary {

    SetPreferenceOutputBoundary presenter;
    UserDataAccessInterface userDAO;

    /**
     * Constructs a new {@code SetPreferenceInteractor} with the specified presenter and user data access object.
     *
     * @param presenter The presenter that will handle the formatting of the output data.
     * @param userDAO   The data access object used to update user preferences in the persistent storage.
     */
    public SetPreferenceInteractor(SetPreferenceOutputBoundary presenter, UserDataAccessInterface userDAO) {
        this.presenter = presenter;
        this.userDAO = userDAO;
    }

    /**
     * Executes the use case of setting user preferences.
     *
     * This method updates the preferences of the logged-in user, both in the application and in the persistent storage.
     *
     * @param inputData The input data containing the preferences to be updated.
     */
    public void execute(SetPreferenceInputData inputData) {

        User user = LoggedUserData.getLoggedInUser();
        user.updatePreference("nightMode", inputData.getNightMode());
        user.updatePreference("subtractFridgeFromGrocery", inputData.getSubtractFridgeFromGrocery());
        userDAO.updateUserPreference(user, "nightMode", inputData.getNightMode());
        userDAO.updateUserPreference(user, "subtractFridgeFromGrocery", inputData.getSubtractFridgeFromGrocery());

        presenter.updateLocalAppSetting(inputData.getNightMode(), inputData.getSubtractFridgeFromGrocery());
    }
}
