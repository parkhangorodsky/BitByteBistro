package entity;

import java.time.LocalDateTime;
import java.util.*;

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
    private HashMap<String, ShoppingList> shoppingLists;
    private Map<String, Object> preference;
    private List<Recipe> recentlyViewedRecipes;

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
        this.shoppingLists = new HashMap<>();
        this.recipes = new ArrayList<>();
        this.preference = new HashMap<>();
        this.recentlyViewedRecipes = new ArrayList<>();
        preference.put("nightMode", false);
    }

    // Constructor with empty argument for MongoDB
    public User() {}

    /**
     * Returns the username of the user.
     *
     * @return The username of the user.
     */
    public String getUserName() {return userName;}
    public String getUserEmail() {return userEmail;}
    public String getUserPassword() {return userPassword;}
    public LocalDateTime getCreatedAt() {return createdAt;}
    public List<Recipe> getRecipes() {return recipes;}
    public List<ShoppingList> getShoppingLists() {return new ArrayList<>(shoppingLists.values());}
    public ShoppingList getShoppingList(String name) {return shoppingLists.get(name);}
    public Map<String, Object> getPreference() {return preference;}
    public List<Recipe> getRecentlyViewedRecipes() {return recentlyViewedRecipes;}

    public void setUserName(String userName) {this.userName = userName;}
    public void setUserEmail(String userEmail) {this.userEmail = userEmail;}
    public void setUserPassword(String userPassword) {this.userPassword = userPassword;}
    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}
    public void setRecipes(List<Recipe> recipes) {this.recipes = recipes;}
    public void setShoppingLists(List<ShoppingList> shoppingLists) {
        HashMap<String, ShoppingList> shoppingListHashMap = new HashMap<>();
        for (ShoppingList shoppingList : shoppingLists) {
            shoppingListHashMap.put(shoppingList.getShoppingListName(), shoppingList);
            this.shoppingLists = shoppingListHashMap;
        }
    }
    public void setPreference(Map<String, Object> preference) {this.preference = preference;}
    public void setRecentlyViewedRecipes(List<Recipe> recentlyViewedRecipes) {this.recentlyViewedRecipes = recentlyViewedRecipes;}
    public void addRecentlyViewedRecipe(Recipe recipe) {
        if (this.recentlyViewedRecipes.size() >= 5) {
            this.recentlyViewedRecipes.remove(0);
        }
        this.recentlyViewedRecipes.add(recipe);
    }

    /**
     * Adds a shopping list to the user's list of shopping lists.
     *
     * @param shoppingList The shopping list to add.
     */
    public void addShoppingList(ShoppingList shoppingList) {this.shoppingLists.add(shoppingList);}

    /**
     * Adds a recipe to the user's list of recipes.
     *
     * @param recipe The recipe to add.
     */
    public void addRecipe(Recipe recipe) {this.recipes.add(recipe);}

    public void updatePreference(String key, Object value) {
        this.preference.put(key, value);
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
