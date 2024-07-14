package use_cases.sign_up.use_case.interactor;

import entity.User;
import frameworks.data_access.UserRepository;
import use_cases.sign_up.use_case.input_data.SignUpInputBoundary;
import use_cases.sign_up.use_case.input_data.SignUpInputData;
import use_cases.sign_up.use_case.output_data.SignUpOutputBoundary;
import use_cases.sign_up.use_case.output_data.SignUpOutputData;

public class SignUpInteractor implements SignUpInputBoundary {
    private final SignUpOutputBoundary signUpOutputBoundary;
    private final UserRepository userRepository;

    public SignUpInteractor(SignUpOutputBoundary signUpOutputBoundary, UserRepository userRepository) {
        this.signUpOutputBoundary = signUpOutputBoundary;
        this.userRepository = userRepository;
    }

    @Override
    public void execute(SignUpInputData signUpInputData) {
        // Validate input data (e.g., non-null, valid email format)
        if (signUpInputData.getUserID() == null || signUpInputData.getUserEmail() == null || signUpInputData.getUserPassword() == null) {
            // Handle invalid input (this can be further refined with proper error handling)
            return;
        }

        // Create a new User entity
        User user = new User(signUpInputData.getUserID(), signUpInputData.getUserEmail(), signUpInputData.getUserPassword());

        // Add user to repository
        userRepository.addUser(user);

        // Prepare the output data
        SignUpOutputData signUpOutputData = new SignUpOutputData(user);

        // Pass the output data to the output boundary
        signUpOutputBoundary.prepareSuccessView(signUpOutputData);
    }
}
