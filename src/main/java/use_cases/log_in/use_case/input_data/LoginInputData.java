package use_cases.log_in.use_case.input_data;

public class LoginInputData {
    private String userEmail;
    private String userPassword;

    public LoginInputData(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }
}
