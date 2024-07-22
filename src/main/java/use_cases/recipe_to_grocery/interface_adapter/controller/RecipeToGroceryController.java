package use_cases.recipe_to_grocery.interface_adapter.controller;

import entity.User;

import use_cases._common.authentication.AuthenticationService;
import use_cases.recipe_to_grocery.use_case.input_data.RecipeToGroceryInputData;
import use_cases.recipe_to_grocery.use_case.interactor.RecipeToGroceryInputBoundary;

/**
 * Controller for handling converting recipes to a grocery list of ingredients.
 * It receives input from the view, constructs the appropriate input data, and passes it to the interactor.
 * It encapsulates the logic for handling recipe conversion and constructing appropriate data structures.
 */

public class RecipeToGroceryController {

    private final RecipeToGroceryInputBoundary recipeToGroceryInteractor;
    private final AuthenticationService authService;

    /**
     * Class constructor for RecipeToGroceryController.
     *
     * @param recipeToGroceryInteractor The use case interactor to which the controller will delegate actions.
     * @param authService               The authentication service for user authentication and session management.
     */

    public RecipeToGroceryController(RecipeToGroceryInputBoundary recipeToGroceryInteractor,
                                     AuthenticationService authService) {
        this.recipeToGroceryInteractor = recipeToGroceryInteractor;
        this.authService = authService;
    }


    /**
     * Converts a list of recipes to a shopping list of ingredients for the logged-in user.
     *
     * @param loggedInUser The logged-in User object obtained from authentication.
     */
    private void convertRecipesToGroceryList(User loggedInUser) {
        // Prepare input data
        RecipeToGroceryInputData recipeToGroceryInputData = new RecipeToGroceryInputData(loggedInUser);

        // Execute the use case
        recipeToGroceryInteractor.execute(recipeToGroceryInputData);
    }

    /**
     * Handles the authentication and conversion process. This method called by some higher-level component responsible
     * for coordinating user actions, such as a UI component (like a button click handler in a graphical interface).
     *
     * @param userEmail    The user's email address.
     * @param userPassword The user's password.
     */
    public void handleAuthenticationAndConversion(String userEmail, String userPassword) {
        // Perform authentication
        boolean isAuthenticated = authService.authenticate(userEmail, userPassword);

        if (isAuthenticated) {
            // If authenticated, retrieve logged-in user
            User loggedInUser = authService.getLoggedInUser();

            // Convert recipes to grocery list for the logged-in user
            convertRecipesToGroceryList(loggedInUser);
        } else {
            // Handle authentication failure
            System.out.println("Authentication failed. Please check your credentials.");
        }
    }

}
















