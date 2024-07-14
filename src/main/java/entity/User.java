package entity;

public class User {
    private String userID;
    private String userEmail;
    private String userPassword;

    // Constructor
    public User(String userID, String userEmail, String userPassword) {
        this.userID = userID;
        setEmail(userEmail); // Validate email format
        setPassword(userPassword); // Encrypt password
    }

    // Getters
    public String getUserID() {
        return userID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    // Setters with validation
    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setEmail(String userEmail) {
            this.userEmail = userEmail;
        }

    public void setPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    // Additional methods
    public void updateEmail(String newEmail) {
        setEmail(newEmail);
    }

    public void updatePassword(String newPassword) {
        setPassword(newPassword);
    }


    // toString method to display user information
    @Override
    public String toString() {
        return "User{" +
                "userID='" + userID + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }

}