package use_cases.sign_up.use_case.input_data;

/**
 * SignUpInputBoundary is an interface that defines the input boundary for the sign-up use case.
 * Implementations of this interface will handle the execution of the sign-up process.
 */
public interface SignUpInputBoundary {
    /**
     * Executes the sign-up process with the specified input data.
     *
     * @param signUpInputData The input data for the sign-up process.
     */
    void execute(SignUpInputData signUpInputData);
}
