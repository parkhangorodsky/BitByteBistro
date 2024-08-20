package frameworks.data_access;

import entity.Fridge;
import entity.Recipe;
import entity.ShoppingList;
import entity.User;

import java.util.List;

/**
 * Interface for data access operations related to user data.
 * This interface defines methods for checking the existence of a user by email,
 * adding a new user, retrieving a user by email, and managing the logged-in user.
 */
public interface UserDataAccessInterface {


    /**
     * Adds a new user to the data source.
     *
     * @param user The user to add.
     */
    void addUser(User user);

    void updateUser(User user);
    void updateUserPreference(User user, String fieldName, Object value);
    void deleteUser(User user);


    /**
     * Checks if a user exists by their email address.
     *
     * @param email The email address to check.
     * @return True if the user exists, false otherwise.
     */
    boolean existsByEmail(String email);

    /**
     * Retrieves a user by their email address.
     *
     * @param email The email address to look up.
     * @return The user associated with the email address, or null if not found.
     */
    User getUserByEmail(String email);


    /**
     * Adds a recipe to the user's collection of recipes.
     * <p>
     * This method is used to add a {@link Recipe} to the user's list of recipes.
     * </p>
     *
     * @param user The user to whom the recipe is to be added.
     * @param recipe The recipe to add.
     */
    void addRecipe(User user, Recipe recipe);

    /**
     * Updates the list of recently viewed recipes for a user.
     * <p>
     * This method is used to update the recently viewed recipes list for the given {@link User}.
     * </p>
     *
     * @param user The user whose recently viewed recipes list is to be updated.
     */
    void updateRecentlyViewedRecipes(User user);

    /**
     * Adds a shopping list to the user's collection of shopping lists.
     * <p>
     * This method is used to add a {@link ShoppingList} to the user's list of shopping lists.
     * </p>
     *
     * @param user The user to whom the shopping list is to be added.
     * @param shoppingList The shopping list to add.
     */
    void addShoppingList(User user, ShoppingList shoppingList);


    /**
     * Adds a recipe to a shopping list for a user.
     * <p>
     * This method is used to add a {@link Recipe} to a specific {@link ShoppingList} of the user.
     * </p>
     *
     * @param user The user to whom the shopping list belongs.
     * @param shoppingList The shopping list to which the recipe is to be added.
     * @param recipe The recipe to add to the shopping list.
     */
    void addRecipeToShoppingList(User user, ShoppingList shoppingList, Recipe recipe);

    /**
     * Updates the contents of the user's fridge.
     * <p>
     * This method is used to modify the contents of the {@link Fridge} for the given {@link User}.
     * </p>
     *
     * @param user The user whose fridge contents are to be updated.
     * @param fridge The updated fridge object.
     */
    void updateFridge(User user, Fridge fridge);

    }
