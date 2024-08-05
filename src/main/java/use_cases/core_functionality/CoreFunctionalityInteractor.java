package use_cases.core_functionality;

import entity.*;
import frameworks.data_access.UserDataAccessInterface;

import java.util.ArrayList;
import java.util.List;

public class CoreFunctionalityInteractor implements CoreFunctionalityInputBoundary{
    CoreFunctionalityPresenter presenter;
    UserDataAccessInterface userDAO;

    /**
     * Constructs an CoreFunctionalityInteractor with the given presenter and user data access object.
     *
     * @param presenter The presenter responsible for preparing the view.
     * @param userDAO   The data access object for user-related data.
     */
    public CoreFunctionalityInteractor(CoreFunctionalityPresenter presenter, UserDataAccessInterface userDAO) {
        this.presenter = presenter;
        this.userDAO = userDAO;
    }

    /**
     * Executes the action of adding a recipe to the logged-in user's grocery list.
     * If the recipe already exists in the user's grocery list, it should allow it to be added again (since people may want more than one serving).
     * It adds the recipe to the user's grocery list, updates the data access object, and prepares a success view.
     *
     * @param inputData The input data required for adding the recipe.
     */
    @Override
    public void execute(CoreFunctionalityInputData inputData) {
        User user = LoggedUserData.getLoggedInUser();
        ShoppingList shoppingList = inputData.getShoppingList();
        Recipe recipe = inputData.getRecipe();

        ShoppingList updatedShoppingList = getGroceryList(recipe, shoppingList);
        user.addRecipe(recipe);
        //userDAO.addRecipe(user, recipe);

        // UPDATE USER (LOCALLY AND IN DATABASE)
        //some way to update an existing shopping list instead of adding the updated one on top
        user.addShoppingList(updatedShoppingList);
        //userDAO.addShoppingList(user, updatedShoppingList);

        CoreFunctionalityOutputData outputData = new CoreFunctionalityOutputData(updatedShoppingList, inputData.getParentModel());
        presenter.prepareSuccessView(outputData);
    }

    public ShoppingList getGroceryList(Recipe recipe, ShoppingList shoppingList) {
        List<Ingredient> groceries = shoppingList.getListItems();
        for (Ingredient grocery : recipe.getIngredientList()) {
            if (groceries.contains(grocery)) {
                Ingredient item = groceries.get(groceries.indexOf(grocery));
                float more = grocery.getQuantity();
                item.addIngredientQuantity(more);
            } else {
                groceries.add(grocery);
            }
        }
        shoppingList.setListItems(groceries);
        return shoppingList;
    }
}
