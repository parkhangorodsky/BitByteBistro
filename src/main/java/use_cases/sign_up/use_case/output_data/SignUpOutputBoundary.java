package use_cases.sign_up.use_case.output_data;

/**
 * Interface representing the output boundary for the sign-up use case.
 * This interface defines methods to handle the presentation of the sign-up result.
 */
public interface SignUpOutputBoundary {
    /**
     * Prepares the success view when the sign-up is successful.
     * This method is responsible for updating the view with the successful sign-up information.
     *
     * @param user The output data containing the user's information.
     */
    void prepareSuccessView(SignUpOutputData user);

    /**
     * Prepares the error view when the sign-up fails.
     * This method is responsible for updating the view with the error message.
     *
     * @param errorMessage The error message to display.
     */
    void prepareErrorView(String errorMessage);
}
