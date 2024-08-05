package use_cases.logout.use_case.output;

/**
 * Interface for handling the result of a logout operation.
 */
public interface LogoutOutputBoundary {

    /**
     * Called when the logout operation is successful.
     */
    void logoutSuccess();

    /**
     * Called when the logout operation fails.
     *
     * @param errorMessage The error message describing the failure.
     */
    void logoutFailure(String errorMessage);
}