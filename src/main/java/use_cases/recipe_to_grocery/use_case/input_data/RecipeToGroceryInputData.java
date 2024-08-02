package use_cases.recipe_to_grocery.use_case.input_data;

import entity.LoggedUserData;
import entity.User;

/**
 * Input data class for the use case of converting recipes to a grocery list.
 * Contains the user information needed for the conversion process.
 */
public class RecipeToGroceryInputData {
    private User user;

    /**
     * Constructs a RecipeToGroceryInputData object with the specified user.
     *
     * @param user The User object representing the logged-in user.
     */
    public RecipeToGroceryInputData(User user) {
        this.user = LoggedUserData.getLoggedInUser();
    }

    /**
     * Retrieves the User object associated with this input data.
     *
     * @return The User object.
     */
    public User getUser() {
        return this.user;
    }
}