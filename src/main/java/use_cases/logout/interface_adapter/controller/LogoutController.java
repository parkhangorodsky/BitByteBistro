package use_cases.logout.interface_adapter.controller;

import use_cases.logout.use_case.interactor.LogoutInteractor;

public class LogoutController {
    private final LogoutInteractor interactor;

    public LogoutController(LogoutInteractor interactor) {
        this.interactor = interactor;
    }

    public void logout() {
        interactor.logout();
    }
}
