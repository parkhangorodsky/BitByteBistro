package use_cases.log_in.use_case.output_data;

import entity.User;

public class LoginOutputData {
    private User user;

    public LoginOutputData(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
