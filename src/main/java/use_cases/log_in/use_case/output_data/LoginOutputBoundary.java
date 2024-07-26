package use_cases.log_in.use_case.output_data;

/**
 * LoginOutputBoundary is an interface that defines the methods for preparing the views
 * based on the results of the login process. Implementations of this interface will
 * handle the presentation logic for successful and failed login attempts.
 */
public interface LoginOutputBoundary {

    /**
     * Prepares the success view when the login is successful.
     * This method is called with the login output data containing the user information.
     *
     * @param loginOutputData The output data containing the user information.
     */
    void prepareSuccessView(LoginOutputData loginOutputData);

    /**
     * Prepares the fail view when the login fails.
     * This method is called with an error message describing the reason for the failure.
     *
     * @param errorMessage The error message describing the reason for the login failure.
     */
    void prepareFailView(String errorMessage);
}
