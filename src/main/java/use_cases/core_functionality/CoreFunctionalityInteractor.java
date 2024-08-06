package use_cases.core_functionality;

import app.local.LoggedUserData;
import entity.*;
import frameworks.data_access.UserDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
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
        HashMap<String, ShoppingList> userShoppingLists = user.getShoppingLists();

        ShoppingList shoppingList = inputData.getShoppingList();
        String shoppingListName = shoppingList.getShoppingListName();
        Recipe recipe = inputData.getRecipe();

        shoppingList.addRecipe(recipe);

        if (userShoppingLists.get(shoppingListName) == null) {
            user.addShoppingList(shoppingList);
        }

        // UPDATE USER (LOCALLY AND IN DATABASE)
        //userDAO.addShoppingList(user, updatedShoppingList);

        CoreFunctionalityOutputData outputData = new CoreFunctionalityOutputData(shoppingList, inputData.getParentModel());
        presenter.prepareSuccessView(outputData);
    }


}
