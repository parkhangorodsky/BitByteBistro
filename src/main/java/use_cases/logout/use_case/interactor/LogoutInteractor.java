package use_cases.logout.use_case.interactor;

import app.local.LoggedUserData;
import app.local.LocalAppSetting;
import use_cases.logout.use_case.output.LogoutOutputBoundary;
import app.config.Config;
import entity.Fridge;

public class LogoutInteractor {
    private final LogoutOutputBoundary outputBoundary;

    public LogoutInteractor(LogoutOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    public void logout() {
        try {
            // Reset the fridge interactor with an empty fridge before clearing the user session
            Config.resetFridgeInteractor(new Fridge());

            // Clear user session
            LoggedUserData.setLoggedInUser(null);
            LocalAppSetting.clearSessionSettings();

            outputBoundary.logoutSuccess();
        } catch (Exception e) {
            outputBoundary.logoutFailure("Logout failed: " + e.getMessage());
        }
    }
}
