package use_cases.log_in.use_case.output_data;

/**
 * Interface representing the output boundary for the login use case.
 * This interface defines methods to handle the presentation of the login result.
 */
public interface LoginOutputBoundary {
    /**
     * Prepares the success view when the login is successful.
     * This method is responsible for updating the view with the successful login information.
     *
     * @param loginOutputData The output data containing the user's information.
     */
    void prepareSuccessView(LoginOutputData loginOutputData);

    /**
     * Prepares the fail view when the login fails.
     * This method is responsible for updating the view with the error message.
     *
     * @param errorMessage The error message to display.
     */
    void prepareFailView(String errorMessage);
}
