package use_cases.core_functionality;

import app.local.LoggedUserData;
import entity.*;
import frameworks.data_access.UserDataAccessInterface;

import use_cases.core_functionality.strategy.collapse.*;
import use_cases.core_functionality.strategy.normalize.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CoreFunctionalityInteractor implements CoreFunctionalityInputBoundary{
    CoreFunctionalityPresenter presenter;
    UserDataAccessInterface userDAO;
    private final CollapseStrategy collapseStrategy = new NormalizedCollapse();
    private final NormalizeStrategy normalizeStrategy = new StringNormalize();

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
    public void add(CoreFunctionalityInputData inputData) {
        User user = LoggedUserData.getLoggedInUser();
        Map<String, ShoppingList> userShoppingLists = user.getShoppingLists();

        ShoppingList shoppingList = inputData.getShoppingList();
        String shoppingListName = shoppingList.getShoppingListName();
        Recipe recipe = inputData.getRecipe();

        addRecipe(shoppingList, recipe);

        if (userShoppingLists.get(shoppingListName) == null) {
            user.addShoppingList(shoppingList);
        }

        // UPDATE USER (LOCALLY AND IN DATABASE)
        //userDAO.addShoppingList(user, updatedShoppingList);
        //some way to update an existing shopping list instead of adding the updated one on top
        userDAO.addRecipeToShoppingList(user, shoppingList, recipe);

        CoreFunctionalityOutputData outputData = new CoreFunctionalityOutputData(shoppingList, inputData.getParentModel());
        presenter.prepareSuccessView(outputData);
    }

    @Override
    public void remove(CoreFunctionalityInputData inputData) {
        User user = LoggedUserData.getLoggedInUser();
        Map<String, ShoppingList> userShoppingLists = user.getShoppingLists();

        ShoppingList shoppingList = inputData.getShoppingList();
        Recipe recipe = inputData.getRecipe();

        if (removeRecipe(shoppingList, recipe) == 1) {
            userDAO.removeRecipeFromShoppingList(user, shoppingList, recipe);
            CoreFunctionalityOutputData outputData = new CoreFunctionalityOutputData(shoppingList, inputData.getParentModel());
            presenter.prepareSuccessView(outputData);
        };
    }

    public void addItem(ShoppingList shoppingList, Ingredient grocery) {
        collapseStrategy.collapse(shoppingList, grocery);
    }

    public void removeItem(ShoppingList shoppingList, Ingredient grocery) {
        Map<String, Ingredient> listItems = shoppingList.getListItemsAsMap();
        String name = normalizeStrategy.normalize(grocery.getIngredientName());
        listItems.remove(name);
        List<Ingredient> newListItems = new ArrayList<>(listItems.values());
        shoppingList.setListItems(newListItems);
    }

    public void addRecipe(ShoppingList shoppingList, Recipe recipe) {
        if (!shoppingList.getRecipes().contains(recipe)) {
            shoppingList.getRecipes().add(recipe);
        }
        for (Ingredient grocery : recipe.getIngredientList()) {
            addItem(shoppingList, grocery);
        }
    }

    private int removeRecipe(ShoppingList shoppingList, Recipe recipe) {
        if (!shoppingList.getRecipes().contains(recipe)) {
            return 0;
        }
        shoppingList.getRecipes().remove(recipe);

        for (Ingredient grocery : recipe.getIngredientList()) {
            removeItem(shoppingList, grocery);
        }
        return 1;
    }
}
