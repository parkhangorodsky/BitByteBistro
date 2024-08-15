package use_cases.core_functionality;

import app.local.LoggedUserData;
import entity.*;
import frameworks.data_access.UserDataAccessInterface;

import use_cases.core_functionality.strategy.collapse.CollapseStrategy;
import use_cases.core_functionality.strategy.collapse.NormalizedCollapse;

import java.util.Map;

public class CoreFunctionalityInteractor implements CoreFunctionalityInputBoundary{
    CoreFunctionalityPresenter presenter;
    UserDataAccessInterface userDAO;
    private CollapseStrategy collapseStrategy = new NormalizedCollapse();

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

    public void addItem(ShoppingList shoppingList, Ingredient grocery) {
        collapseStrategy.collapse(shoppingList, grocery);
    }

    public void removeItem(ShoppingList shoppingList, Ingredient grocery, int quantity) {
        Map<String, Ingredient> listItems = shoppingList.getListItemsAsMap();
        String normalizedGroceryName = normalizeIngredientName(grocery.getIngredientName());
        if (listItems.containsKey(normalizedGroceryName)) {
            Ingredient item = listItems.get(normalizedGroceryName);
            int currentQuantity = (int) item.getQuantity();
            if (currentQuantity > quantity) {
                listItems.put(grocery, currentQuantity - quantity);
            } else {
                listItems.remove(grocery); // Remove ingredient if quantity goes to zero or less
            }
        } else {
            listItems.put(normalizedGroceryName, ingredient);
        }
    }


    public void collapse(ShoppingList shoppingList,  Ingredient ingredient) {
        Map<String, Ingredient> listItems = shoppingList.getListItemsAsMap();
        String normalizedGroceryName = normalizeIngredientName(ingredient.getIngredientName());

        if (listItems.containsKey(normalizedGroceryName)) {
            Ingredient item = listItems.get(normalizedGroceryName);
            float more = ingredient.getQuantity();
            item.addIngredientQuantity(more);
        } else {
            listItems.put(normalizedGroceryName, ingredient);
        }
    }


    private void subtractIngredientFromShoppingList(ShoppingList shoppingList, Ingredient ingredient, int quantity) {
        // Logic to subtract the ingredient from the shopping list
        HashMap<Ingredient, Integer> listItems = shoppingList.getListItems();
        int currentQuantity = listItems.getOrDefault(ingredient, 0);
        if (currentQuantity > quantity) {
            listItems.put(ingredient, currentQuantity - quantity);
        } else {
            listItems.remove(ingredient); // Remove ingredient if quantity goes to zero or less
        }
        viewModel.updateShoppingList(shoppingList); // Update view model or similar mechanism
    }

    public void addRecipe(ShoppingList shoppingList, Recipe recipe) {
        if (!shoppingList.getRecipes().contains(recipe)) {
            shoppingList.getRecipes().add(recipe);
        }
        for (Ingredient grocery : recipe.getIngredientList()) {
            addItem(shoppingList, grocery);
        }
    }


}
