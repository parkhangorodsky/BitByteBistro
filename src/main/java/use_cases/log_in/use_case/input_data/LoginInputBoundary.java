package use_cases.log_in.use_case.input_data;

/**
 * Interface representing the input boundary for the login use case.
 * This interface is used to define the method that initiates the login process.
 */
public interface LoginInputBoundary {
    /**
     * Executes the login process with the given input data.
     * This method is responsible for handling the login logic using the provided input data.
     *
     * @param loginInputData The input data containing the user's login credentials.
     */
    void execute(LoginInputData loginInputData);
}
