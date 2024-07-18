package use_cases.sign_up.use_case.interactor;

import entity.User;
import frameworks.data_access.DataAccessInterface;
import use_cases.sign_up.use_case.input_data.SignUpInputBoundary;
import use_cases.sign_up.use_case.input_data.SignUpInputData;
import use_cases.sign_up.use_case.output_data.SignUpOutputBoundary;
import use_cases.sign_up.use_case.output_data.SignUpOutputData;

import java.time.LocalDateTime;

public class SignUpInteractor implements SignUpInputBoundary {
    private final SignUpOutputBoundary signUpPresenter;
    private final DataAccessInterface DAO;

    public SignUpInteractor(SignUpOutputBoundary signUpPresenter, DataAccessInterface dao) {
        this.signUpPresenter = signUpPresenter;
        this.DAO = dao;
    }

    @Override
    public void execute(SignUpInputData signUpInputData) {
        // Validate input data (e.g., non-null, valid email format)
        if (signUpInputData.getUserID() == null || signUpInputData.getUserEmail() == null || signUpInputData.getUserPassword() == null) {
            // Handle invalid input (this can be further refined with proper error handling)
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
        signUpPresenter.prepareSuccessView(signUpOutputData);
    }
}
