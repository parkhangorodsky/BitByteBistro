package use_cases.recipe_to_grocery.factory;

import frameworks.api.RecipeAPI;

import use_cases.recipe_to_grocery.interface_adapter.controller.RecipeToGroceryController;
import use_cases.recipe_to_grocery.interface_adapter.presenter.RecipeToGroceryPresenter;

import use_cases.recipe_to_grocery.interface_adapter.view_model.RecipeToGroceryViewModel;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.recipe_to_grocery.use_case.interactor.RecipeToGroceryInteractor;


public class RecipeToGroceryUseCaseFactory {

    private RecipeToGroceryUseCaseFactory() {}

    public static RecipeToGroceryController create(ViewManagerModel viewManagerModel, RecipeToGroceryViewModel recipeToGroceryViewModel, RecipeAPI recipeAPI) {

        // Create Presenter
        RecipeToGroceryPresenter recipeToGroceryPresenter = new RecipeToGroceryPresenter(viewManagerModel, recipeToGroceryViewModel);

        // Create UseCaseInteractor
        RecipeToGroceryInteractor recipeToGroceryInteractor = new RecipeToGroceryInteractor(recipeToGroceryPresenter, recipeAPI);

        //Create Controller
        RecipeToGroceryController recipeToGroceryController = new RecipeToGroceryController(recipeToGroceryInteractor);

        return recipeToGroceryController;
    }
}
