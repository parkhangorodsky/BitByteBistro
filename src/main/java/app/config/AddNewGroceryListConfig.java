package app.config;

import use_cases.add_new_grocery_list.AddNewGroceryListController;
import use_cases.add_new_grocery_list.AddNewGroceryListInteractor;
import use_cases.add_new_grocery_list.AddNewGroceryListPresenter;
import use_cases.add_to_my_recipe.AddToMyRecipeInteractor;


import static app.config.ViewModelConfig.myGroceryViewModel;
import static app.config.DataAccessConfig.userDAO;


class AddNewGroceryListConfig {

    static final AddNewGroceryListPresenter presenter = new AddNewGroceryListPresenter(
            myGroceryViewModel);

    static final AddNewGroceryListInteractor interactor = new AddNewGroceryListInteractor(
            presenter,
            userDAO);

    static final AddNewGroceryListController controller = new AddNewGroceryListController(
            interactor);

}
