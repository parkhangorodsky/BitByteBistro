package use_cases.add_new_grocery_list;

import entity.LoggedUserData;
import entity.ShoppingList;
import entity.User;
import frameworks.data_access.UserDataAccessInterface;
import use_cases.add_to_my_recipe.AddToMyRecipeOutputData;
import use_cases.add_to_my_recipe.AddToMyRecipePresenter;

public class AddNewGroceryListInteractor implements AddNewGroceryListInputBoundary{
    AddNewGroceryListPresenter presenter;
    UserDataAccessInterface userDAO;

    public AddNewGroceryListInteractor(AddNewGroceryListPresenter presenter, UserDataAccessInterface userDAO) {
        this.presenter = presenter;
        this.userDAO = userDAO;
    }
    public void execute(AddNewGroceryListInputData inputData) {
        User user = LoggedUserData.getLoggedInUser();
        ShoppingList shoppingList = new ShoppingList(user, inputData.getShoppingListName());

        for (ShoppingList userShoppingLists : user.getShoppingLists()) {
            if (userShoppingLists.getShoppingListName().equals(inputData.getShoppingListName())) {
                presenter.prepareFailureView("recipe already exists", inputData.getParentModel());
                return;
            }
        }

        user.addShoppingList(shoppingList);
        // userDAO.addRecipe(user, newRecipe); - for seonghyun to do.

        presenter.prepareSuccessView(inputData.getParentModel());
    }
}
