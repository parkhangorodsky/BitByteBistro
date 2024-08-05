package app.config;

import use_cases.add_to_my_recipe.AddToMyRecipeController;
import use_cases.add_to_my_recipe.AddToMyRecipeInteractor;
import use_cases.add_to_my_recipe.AddToMyRecipePresenter;

import static app.config.DataAccessConfig.userDAO;
import static app.config.ViewModelConfig.myRecipeViewModel;

class AddToMyRecipeConfig {

    static final AddToMyRecipePresenter presenter = new AddToMyRecipePresenter(
            myRecipeViewModel);

    static final AddToMyRecipeInteractor interactor = new AddToMyRecipeInteractor(
            presenter,
            userDAO);

    static final AddToMyRecipeController controller = new AddToMyRecipeController(
            interactor);

}
