package entity;

import java.time.LocalDateTime;

public class User {
    private String userName;
    private String userEmail;
    private String userPassword;
    private LocalDateTime createdAt;

    // Constructor
    public User(String userName, String userEmail, String userPassword, LocalDateTime createdAt) {
        this.userName = userName;
        this.userEmail = userEmail; // Validate email format
        this.userPassword = userPassword;
        this.createdAt = createdAt;// Encrypt password
    }

    // Getters
    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public LocalDateTime getCreatedAt() { return createdAt; }

    // Setters with validation
    public void setUserID(String userName) {
        this.userName = userName;
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
                "userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }

}
