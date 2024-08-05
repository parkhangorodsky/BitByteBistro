package use_cases.settting_preference;

import app.local.LoggedUserData;
import entity.User;
import frameworks.data_access.UserDataAccessInterface;

public class SetPreferenceInteractor implements SetPreferenceInputBoundary {

    SetPreferenceOutputBoundary presenter;
    UserDataAccessInterface userDAO;

    public SetPreferenceInteractor(SetPreferenceOutputBoundary presenter, UserDataAccessInterface userDAO) {
        this.presenter = presenter;
        this.userDAO = userDAO;

    }

    public void execute(SetPreferenceInputData inputData) {

        User user = LoggedUserData.getLoggedInUser();
        user.updatePreference("nightMode", inputData.getNightMode());
        userDAO.updateUserPreference(user, "nightMode", inputData.getNightMode());

        presenter.updateLocalAppSetting(inputData.getNightMode());
    }
}
