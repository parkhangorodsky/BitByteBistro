package use_cases.core_functionality;

import entity.*;
import frameworks.data_access.UserDataAccessInterface;

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
        ShoppingList shoppingList;
        User user = inputData.getLoggedInUser();
        Recipe newRecipe = inputData.getRecipe();
        if (!inputData.getLoggedInUser().getShoppingLists().isEmpty()) {
            shoppingList = user.getShoppingLists().getFirst();
            shoppingList.addRecipe(inputData.getRecipe());
            shoppingList.addIngredients(inputData.getShoppingListIngredients());
//        userDAO.addRecipeToShoppingList(user, newRecipe);
//        userDAO.addIngredientsToShoppingList(user, newIngredients);
        }
        else {
            shoppingList = new ShoppingList(user.getUserName(), "shopping list", inputData.getShoppingListIngredients());
            user.setShoppingList(shoppingList);
        }
        System.out.println(shoppingList);
        CoreFunctionalityOutputData outputData = new CoreFunctionalityOutputData(inputData.getLoggedInUser(), inputData.getParentModel());
        System.out.println(user.getShoppingLists().getFirst().getRecipes());
        presenter.prepareSuccessView(outputData);
    }
}
