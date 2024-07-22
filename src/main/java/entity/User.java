package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user entity in the application.
 * This class encapsulates user details such as username, email, password, creation time,
 * recipes, and shopping lists.
 */
public class User {
    private String userName;
    private String userEmail;
    private String userPassword;
    private LocalDateTime createdAt;
    private List<Recipe> recipes;
    private List<ShoppingList> shoppingLists;

    /**
     * Constructs a new User with the specified details.
     *
     * @param userName The username of the user.
     * @param userEmail The email address of the user.
     * @param userPassword The password of the user.
     * @param createdAt The creation time of the user's account.
     */
    public User(String userName, String userEmail, String userPassword, LocalDateTime createdAt) {
        this.userName = userName;
        this.userEmail = userEmail; // Validate email format
        this.userPassword = userPassword; // Encrypt password
        this.createdAt = createdAt;
        this.shoppingLists = new ArrayList<>();
        this.recipes = new ArrayList<>();
    }

    /**
     * Returns the username of the user.
     *
     * @return The username of the user.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Returns the email address of the user.
     *
     * @return The email address of the user.
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Returns the password of the user.
     *
     * @return The password of the user.
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Returns the creation time of the user's account.
     *
     * @return The creation time of the user's account.
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Returns the list of shopping lists associated with the user.
     *
     * @return The list of shopping lists.
     */
    public List<ShoppingList> getShoppingLists() {
        return shoppingLists;
    }

    /**
     * Returns the list of recipes associated with the user.
     *
     * @return The list of recipes.
     */
    public List<Recipe> getRecipes() {
        return recipes;
    }

    /**
     * Sets the username of the user.
     *
     * @param userName The username to set.
     */
    public void setUserID(String userName) {
        this.userName = userName;
    }

    /**
     * Sets the email address of the user.
     *
     * @param userEmail The email address to set.
     */
    public void setEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * Sets the password of the user.
     *
     * @param userPassword The password to set.
     */
    public void setPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Updates the email address of the user.
     *
     * @param newEmail The new email address.
     */
    public void updateEmail(String newEmail) {
        setEmail(newEmail);
    }

    /**
     * Updates the password of the user.
     *
     * @param newPassword The new password.
     */
    public void updatePassword(String newPassword) {
        setPassword(newPassword);
    }

    /**
     * Adds a shopping list to the user's list of shopping lists.
     *
     * @param shoppingList The shopping list to add.
     */
    public void addShoppingList(ShoppingList shoppingList) {
        this.shoppingLists.add(shoppingList);
    }

    /**
     * Adds a recipe to the user's list of recipes.
     *
     * @param recipe The recipe to add.
     */
    public void addRecipe(Recipe recipe) {
        this.recipes.add(recipe);
    }

    /**
     * Returns a string representation of the user.
     *
     * @return A string representation of the user.
     */
    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
