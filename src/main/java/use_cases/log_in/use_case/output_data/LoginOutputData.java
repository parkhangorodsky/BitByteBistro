package use_cases.log_in.use_case.output_data;

import entity.User;

/**
 * A data transfer object (DTO) that holds information about a user.
 * This class is used to transfer data from the interactor (use case) to the presenter.
 */
public class LoginOutputData {
    private User user;

    /**
     * Constructs a new LoginOutputData object with the specified user.
     *
     * @param user The user associated with this output data.
     */
    public LoginOutputData(User user) {
        this.user = user;
    }

    /**
     * Returns the user associated with this output data.
     *
     * @return The user associated with this output data.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user associated with this output data.
     *
     * @param user The user to set.
     */
    public void setUser(User user) {
        this.user = user;
    }
}
