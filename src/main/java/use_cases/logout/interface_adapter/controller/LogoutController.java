package use_cases.logout.interface_adapter.controller;

import use_cases.logout.use_case.interactor.LogoutInteractor;

/**
 * The LogoutController class is responsible for handling the user's logout request
 * and delegating the logout process to the appropriate interactor.
 * <p>
 * This controller acts as an intermediary between the interface adapter and the
 * business logic, represented by the LogoutInteractor.
 * </p>
 */
public class LogoutController {
    private final LogoutInteractor interactor;

    /**
     * Constructs a LogoutController with the specified LogoutInteractor.
     *
     * @param interactor The interactor that handles the business logic for logging out the user.
     */
    public LogoutController(LogoutInteractor interactor) {
        this.interactor = interactor;
    }

    /**
     * Initiates the logout process by delegating to the interactor.
     * <p>
     * This method triggers the logout use case, which performs the necessary steps
     * to log the user out of the system.
     * </p>
     */
    public void logout() {
        interactor.logout();
    }
}