package use_cases.logout.use_case.output;

public interface LogoutOutputBoundary {
    void logoutSuccess();
    void logoutFailure(String errorMessage);
}
