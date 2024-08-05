package use_cases.logout.use_case.interactor;

import entity.LoggedUserData;
import use_cases.logout.use_case.output.LogoutOutputBoundary;

/**
 * The LogoutInteractor class handles the business logic for logging out a user.
 * <p>
 * It interacts with the LoggedUserData to clear the user session and communicates
 * the result of the logout operation to the output boundary.
 * </p>
 */
public class LogoutInteractor {
    private final LogoutOutputBoundary outputBoundary;

    /**
     * Constructs a LogoutInteractor with the specified output boundary.
     *
     * @param outputBoundary The output boundary responsible for handling the result of the logout operation.
     */
    public LogoutInteractor(LogoutOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    /**
     * Executes the logout process.
     * <p>
     * This method clears the current user's session by setting the logged-in user to null.
     * If the logout process is successful, it notifies the output boundary of the success.
     * If an exception occurs, it catches the exception and notifies the output boundary of the failure.
     * </p>
     */
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
