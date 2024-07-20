package use_cases.sign_up.use_case.input_data;

/**
 * Interface representing the input boundary for the sign-up use case.
 * This interface defines the method that initiates the sign-up process.
 */
public interface SignUpInputBoundary {
    /**
     * Executes the sign-up process with the given input data.
     * This method is responsible for handling the sign-up logic using the provided input data.
     *
     * @param signUpInputData The input data containing the user's sign-up information.
     */
    void execute(SignUpInputData signUpInputData);
}
