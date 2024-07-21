package use_cases.recipe_to_grocery.interface_adapter.presenter;

import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.recipe_to_grocery.interface_adapter.view_model.RecipeToGroceryViewModel;
import use_cases.recipe_to_grocery.use_case.output_data.RecipeToGroceryOutputData;

public class RecipeToGroceryPresenter implements RecipeToGroceryOutputBoundary {

    private RecipeToGroceryViewModel recipeToGroceryViewModel;
    private ViewManagerModel viewManagerModel;

    public RecipeToGroceryPresenter(ViewManagerModel viewManagerModel, RecipeToGroceryViewModel recipeToGroceryViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.recipeToGroceryViewModel = recipeToGroceryViewModel;
    }

    @Override
    public void prepareSuccessView(RecipeToGroceryOutputData shoppingLists) {
        recipeToGroceryViewModel.setGroceryResult(shoppingLists);
        recipeToGroceryViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(recipeToGroceryViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
