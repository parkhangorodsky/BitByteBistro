package use_cases.add_new_grocery_list;

import app.local.LoggedUserData;
import entity.ShoppingList;
import entity.User;
import frameworks.data_access.UserDataAccessInterface;
import use_cases.add_to_my_recipe.AddToMyRecipeOutputData;
import use_cases.add_to_my_recipe.AddToMyRecipePresenter;

import java.util.HashMap;

public class AddNewGroceryListInteractor implements AddNewGroceryListInputBoundary{
    AddNewGroceryListPresenter presenter;
    UserDataAccessInterface userDAO;

    public AddNewGroceryListInteractor(AddNewGroceryListPresenter presenter, UserDataAccessInterface userDAO) {
        this.presenter = presenter;
        this.userDAO = userDAO;
    }
    public void execute(AddNewGroceryListInputData inputData) {
        User user = LoggedUserData.getLoggedInUser();
        ShoppingList shoppingList = new ShoppingList(user.getUserEmail(), inputData.getShoppingListName());

        for (HashMap.Entry<String, ShoppingList> userShoppingLists : user.getShoppingLists().entrySet()) {
            String shoppingListOwner = userShoppingLists.getKey();
            ShoppingList itemsShoppingList = userShoppingLists.getValue();
            if (itemsShoppingList.getShoppingListName().equals(inputData.getShoppingListName())) {
                presenter.prepareFailureView("grocery list already exists", inputData.getParentModel());
                return;
            }
        }

        user.addShoppingList(shoppingList);
        // userDAO.addRecipe(user, newRecipe); - for seonghyun to do.

        presenter.prepareSuccessView(inputData.getParentModel());
    }
}
