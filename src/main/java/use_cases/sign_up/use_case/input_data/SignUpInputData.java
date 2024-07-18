package use_cases.sign_up.use_case.input_data;

public class SignUpInputData {
    private String userID;
    private String userEmail;
    private String userPassword;

    public SignUpInputData(String userID, String userEmail, String userPassword) {
        this.userID = userID;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }
}
