package use_cases.sign_up.use_case.output_data;

/**
 * SignUpOutputBoundary is an interface that defines the methods for preparing the views
 * after the sign-up process is completed. It provides methods to prepare the success
 * view and the error view.
 */
public interface SignUpOutputBoundary {

    /**
     * Prepares the success view when the sign-up process is successful.
     * This method formats the sign-up response from the interactor and updates
     * the SignUpViewModel accordingly.
     *
     * @param user The output data containing the user information.
     */
    void prepareSuccessView(SignUpOutputData user);

    /**
     * Prepares the error view when the sign-up process fails.
     * This method formats the error response from the interactor and updates
     * the SignUpViewModel with the error message.
     *
     * @param errorMessage The error message to display.
     */
    void prepareErrorView(String errorMessage);
}
