package use_cases.log_in.use_case.output_data;

public interface LoginOutputBoundary {
    void prepareSuccessView(LoginOutputData loginOutputData);
    void prepareFailView(String errorMessage);
}
