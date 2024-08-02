package use_cases.sign_up.use_case.interactor;

import entity.User;
import frameworks.data_access.UserDataAccessInterface;
import use_cases.sign_up.use_case.input_data.SignUpInputBoundary;
import use_cases.sign_up.use_case.input_data.SignUpInputData;
import use_cases.sign_up.use_case.output_data.SignUpOutputBoundary;
import use_cases.sign_up.use_case.output_data.SignUpOutputData;

import java.time.LocalDateTime;

/**
 * Interactor for handling the sign-up process.
 * This class implements the SignUpInputBoundary interface and performs the sign-up operation
 * by interacting with a data access object (DAO) and providing output to the SignUpOutputBoundary.
 */
public class SignUpInteractor implements SignUpInputBoundary {
    private final SignUpOutputBoundary signUpOutputBoundary;
    private final UserDataAccessInterface DAO;

    /**
     * Constructs a new SignUpInteractor with the specified output boundary and DAO.
     *
     * @param outputBoundary The boundary to handle the output of the sign-up process.
     * @param dao The data access object to interact with the data source.
     */
    public SignUpInteractor(SignUpOutputBoundary outputBoundary, UserDataAccessInterface dao) {
        this.signUpOutputBoundary = outputBoundary;
        this.DAO = dao;
    }

    /**
     * Executes the sign-up process with the specified input data.
     * This method validates the input data, creates a new User entity, and adds it to the repository.
     * It then prepares the output data and passes it to the output boundary.
     *
     * @param signUpInputData The input data for the sign-up process.
     */
    @Override
    public void execute(SignUpInputData signUpInputData) {
        // Validate input data (e.g., non-null, non-empty, valid email format)
        if (signUpInputData.getUserID() == null || signUpInputData.getUserID().trim().isEmpty() ||
                signUpInputData.getUserEmail() == null || signUpInputData.getUserEmail().trim().isEmpty() ||
                signUpInputData.getUserPassword() == null || signUpInputData.getUserPassword().trim().isEmpty()) {
            // Handle invalid input
            signUpOutputBoundary.prepareErrorView("Invalid input data provided. All fields are required.");
            return;
        }

        // Check if user already exists
        if (DAO.existsByEmail(signUpInputData.getUserEmail())) {
            signUpOutputBoundary.prepareErrorView("User already exists.");
            return;
        }

        // Create a new User entity
        User user = new User(signUpInputData.getUserID(),
                signUpInputData.getUserEmail(),
                signUpInputData.getUserPassword(),
                LocalDateTime.now());

        // Add user to repository
        DAO.addUser(user);

        // Prepare the output data
        SignUpOutputData signUpOutputData = new SignUpOutputData(user);

        // Pass the output data to the output boundary
        signUpOutputBoundary.prepareSuccessView(signUpOutputData);
    }
}
