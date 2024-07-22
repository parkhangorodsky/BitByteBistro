package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String userName;
    private String userEmail;
    private String userPassword;
    private LocalDateTime createdAt;
    private List<Recipe> recipes;
    private List<ShoppingList> shoppingLists;

    // Constructor
    public User(String userName, String userEmail, String userPassword, LocalDateTime createdAt) {
        this.userName = userName;
        this.userEmail = userEmail; // Validate email format
        this.userPassword = userPassword;
        this.createdAt = createdAt;// Encrypt password
        this.shoppingLists = new ArrayList<>();
        this.recipes = new ArrayList<>();
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

    public List<ShoppingList> getShoppingLists() {return shoppingLists;}

    public List<Recipe> getRecipes() {return recipes;}

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

    public void updatePassword(String newPassword) {setPassword(newPassword);}

    public void addShoppingList(ShoppingList shoppingList) {this.shoppingLists.add(shoppingList);}

    public void addRecipe(Recipe recipe) {this.recipes.add(recipe);}


    // toString method to display user information
    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
