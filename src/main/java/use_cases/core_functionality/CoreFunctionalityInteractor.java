package use_cases.core_functionality;

import app.local.LoggedUserData;
import entity.*;
import frameworks.data_access.UserDataAccessInterface;
import use_cases.core_functionality.strategy.collapse.*;
import use_cases.core_functionality.strategy.normalize.*;

import java.util.Map;

/**
 * The `CoreFunctionalityInteractor` class is responsible for handling core functionality operations related to
 * managing recipes and shopping lists. It interacts with the user data access object to persist changes and uses
 * strategies for collapsing and normalizing ingredient data.
 */
public class CoreFunctionalityInteractor implements CoreFunctionalityInputBoundary {

    private CoreFunctionalityPresenter presenter;
    private UserDataAccessInterface userDAO;
    private final CollapseStrategy collapseStrategy = new NormalizedCollapse();
    private final NormalizeStrategy normalizeStrategy = new StringNormalize();

    /**
     * Constructs a `CoreFunctionalityInteractor` with the specified presenter and user data access object.
     *
     * @param presenter The presenter responsible for preparing the view with the updated data.
     * @param userDAO   The data access object for user-related data operations.
     */
    public CoreFunctionalityInteractor(CoreFunctionalityPresenter presenter, UserDataAccessInterface userDAO) {
        this.presenter = presenter;
        this.userDAO = userDAO;
    }

    /**
     * Executes the action of adding a recipe to the logged-in user's grocery list. If the recipe already exists
     * in the user's grocery list, it will be added again to account for multiple servings. The method updates
     * the user's grocery list and persists changes using the data access object. It also prepares a success view
     * using the presenter.
     *
     * @param inputData The input data required for adding the recipe, including the recipe itself, the shopping list,
     *                  and the model for property change notifications.
     */
    @Override
    public void execute(CoreFunctionalityInputData inputData) {
        User user = LoggedUserData.getLoggedInUser();
        Map<String, ShoppingList> userShoppingLists = user.getShoppingLists();

        ShoppingList shoppingList = inputData.getShoppingList();
        String shoppingListName = shoppingList.getShoppingListName();
        Recipe recipe = inputData.getRecipe();

        addRecipe(shoppingList, recipe);

        if (userShoppingLists.get(shoppingListName) == null) {
            user.addShoppingList(shoppingList);
        }

        // Update user data both locally and in the database
        // userDAO.addShoppingList(user, updatedShoppingList);
        // Use appropriate method to update an existing shopping list instead of adding a new one
        userDAO.addRecipeToShoppingList(user, shoppingList, recipe);

        CoreFunctionalityOutputData outputData = new CoreFunctionalityOutputData(shoppingList, inputData.getParentModel());
        presenter.prepareSuccessView(outputData);
    }

    /**
     * Adds an ingredient to a shopping list, using the collapse strategy to handle any existing ingredients.
     *
     * @param shoppingList The shopping list to which the ingredient will be added.
     * @param grocery      The ingredient to be added.
     */
    public void addItem(ShoppingList shoppingList, Ingredient grocery) {
        collapseStrategy.collapse(shoppingList, grocery);
    }

    /**
     * Adds a recipe to a shopping list. If the recipe is not already in the shopping list, it is added. The method
     * then iterates over the recipe's ingredients and adds each one to the shopping list.
     *
     * @param shoppingList The shopping list to which the recipe will be added.
     * @param recipe       The recipe to be added to the shopping list.
     */
    public void addRecipe(ShoppingList shoppingList, Recipe recipe) {
        if (!shoppingList.getRecipes().contains(recipe)) {
            shoppingList.getRecipes().add(recipe);
        }
        for (Ingredient grocery : recipe.getIngredientList()) {
            addItem(shoppingList, grocery);
        }
    }
}
