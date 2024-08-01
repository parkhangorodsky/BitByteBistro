package entity;

public class LoggedUserData {
    private static User loggedInUser;

    /**
     * Retrieves the currently logged-in user.
     *
     * @return The currently logged-in user, or null if no user is logged in.
     */
    public static User getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * Sets the currently logged-in user.
     *
     * @param user The user to set as logged in.
     */
    public static void setLoggedInUser(User user) {
        loggedInUser = user;
    }

    /**
     * Logs out the currently logged-in user.
     *
     * @param user The user to log out.
     */
    public static void logout(User user) {
        if (loggedInUser != null && loggedInUser.equals(user)) {
            loggedInUser = null;
        }
    }
}
