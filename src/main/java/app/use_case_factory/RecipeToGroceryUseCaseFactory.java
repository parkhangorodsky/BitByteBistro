package app.use_case_factory;

import api.RecipeAPI;
import interface_adapter.controller.RecipeToGroceryController;
import interface_adapter.presenter.RecipeToGroceryPresenter;
import interface_adapter.view_model.RecipeToGroceryViewModel;
import interface_adapter.view_model.ViewManagerModel;
import use_case.interactor.RecipeToGroceryInteractor;
import view.RecipeToGroceryView;

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
