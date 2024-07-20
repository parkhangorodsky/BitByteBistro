package use_cases.sign_up.use_case.output_data;

import entity.User;

/**
 * Class representing the output data for the sign-up use case.
 * This class holds the user's information after a successful sign-up.
 */
public class SignUpOutputData {
    private User user;

    /**
     * Constructs a new SignUpOutputData instance with the specified user.
     *
     * @param user The user entity containing the user's information.
     */
    public SignUpOutputData(User user) {
        this.user = user;
    }

    /**
     * Returns the user entity.
     *
     * @return The user entity.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user entity.
     *
     * @param user The user entity to set.
     */
    public void setUser(User user) {
        this.user = user;
    }
}
