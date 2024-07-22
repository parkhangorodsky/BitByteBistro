package use_cases.log_in.use_case.input_data;

/**
 * LoginInputBoundary is an interface that defines the method for executing the login process.
 * Implementations of this interface will handle the business logic for logging in a user.
 */
public interface LoginInputBoundary {

    /**
     * Executes the login process with the specified input data.
     * This method takes a LoginInputData object containing the user's email and password,
     * and performs the login operation.
     *
     * @param loginInputData The input data for the login process, including the user's email and password.
     */
    void execute(LoginInputData loginInputData);
}
