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
    private Map<String, ShoppingList> shoppingLists;
    private Map<String, Object> preference;
    private List<Recipe> recentlyViewedRecipes;
    private Fridge fridge;  // Add fridge attribute

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
        this.shoppingLists = new TreeMap<>();
        this.recipes = new ArrayList<>();
        this.preference = new HashMap<>();
        this.recentlyViewedRecipes = new ArrayList<>();
        this.fridge = new Fridge();  // Initialize fridge
        preference.put("nightMode", false);
    }

    // Constructor with empty argument for MongoDB
    public User() {
        this.fridge = new Fridge();  // Initialize fridge
    }

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
    public Map<String, ShoppingList> getShoppingLists() {return shoppingLists;}
    public ShoppingList getShoppingList(String name) {return shoppingLists.get(name);}
    public Map<String, Object> getPreference() {return preference;}
    public List<Recipe> getRecentlyViewedRecipes() {return recentlyViewedRecipes;}
    public Fridge getFridge() { return fridge; }

    public void setUserName(String userName) {this.userName = userName;}
    public void setUserEmail(String userEmail) {this.userEmail = userEmail;}
    public void setUserPassword(String userPassword) {this.userPassword = userPassword;}
    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}
    public void setRecipes(List<Recipe> recipes) {this.recipes = recipes;}
    public void setShoppingLists(Map<String, ShoppingList> shoppingLists) {this.shoppingLists = shoppingLists;}
    public void setPreference(Map<String, Object> preference) {this.preference = preference;}
    public void setRecentlyViewedRecipes(List<Recipe> recentlyViewedRecipes) {this.recentlyViewedRecipes = recentlyViewedRecipes;}
    public void addRecentlyViewedRecipe(Recipe recipe) {
        boolean seenBefore = false;
        Recipe seenRecipe = null;
        for (Recipe r : this.recentlyViewedRecipes) {
            if (recipe.getName().equals(r.getName()) &&
                    recipe.getInstructions().equals(r.getInstructions())) {
                seenBefore = true;
                seenRecipe = r;
                break;
            }
        }
        if (seenBefore) {
            this.recentlyViewedRecipes.remove(seenRecipe);
        } else if (this.recentlyViewedRecipes.size() == 5) {
            this.recentlyViewedRecipes.removeLast();
        }
        this.recentlyViewedRecipes.addFirst(recipe);
    }
    public void setFridge(Fridge fridge) { this.fridge = fridge; }

    /**
     * Adds a shopping list to the user's list of shopping lists.
     *
     * @param shoppingList The shopping list to add.
     */
    public void addShoppingList(ShoppingList shoppingList) {this.shoppingLists.put(shoppingList.getShoppingListName(), shoppingList);}

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
