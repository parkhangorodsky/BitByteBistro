package use_cases.logout.use_case.interactor;

import entity.LoggedUserData;
import use_cases.logout.use_case.output.LogoutOutputBoundary;

public class LogoutInteractor {
    private final LogoutOutputBoundary outputBoundary;

    public LogoutInteractor(LogoutOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    public void logout() {
        try {
            // Clear user session
            LoggedUserData.setLoggedInUser(null);
            outputBoundary.logoutSuccess();
        } catch (Exception e) {
            outputBoundary.logoutFailure("Logout failed: " + e.getMessage());
        }
    }
}
