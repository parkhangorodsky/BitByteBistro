package use_cases.recipe_to_grocery.interface_adapter.presenter;

import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.recipe_to_grocery.interface_adapter.view_model.RecipeToGroceryViewModel;
import use_cases.recipe_to_grocery.use_case.output_data.RecipeToGroceryOutputData;

/**
 * Presenter class for handling the output of converting recipes to a grocery list.
 * It updates the view model with the converted recipes and notifies the view manager about view changes.
 */
public class RecipeToGroceryPresenter implements RecipeToGroceryOutputBoundary {

    private RecipeToGroceryViewModel recipeToGroceryViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructs a RecipeToGroceryPresenter with the specified view manager model and view model.
     *
     * @param viewManagerModel      The view manager model to manage view changes.
     * @param recipeToGroceryViewModel The view model to update with grocery list results.
     */
    public RecipeToGroceryPresenter(ViewManagerModel viewManagerModel, RecipeToGroceryViewModel recipeToGroceryViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.recipeToGroceryViewModel = recipeToGroceryViewModel;
    }

    /**
     * Prepares the success view by updating the view model with the converted recipes and notifying observers.
     *
     * @param shoppingLists The output data containing converted recipes as shopping lists.
     */
    @Override
    public void prepareSuccessView(RecipeToGroceryOutputData shoppingLists) {
        recipeToGroceryViewModel.setGroceryResult(shoppingLists);
        recipeToGroceryViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(recipeToGroceryViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}