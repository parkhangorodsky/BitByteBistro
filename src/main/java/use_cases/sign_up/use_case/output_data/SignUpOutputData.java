package use_cases.sign_up.use_case.output_data;

import entity.User;

public class SignUpOutputData {
    private User user;

    public SignUpOutputData(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
