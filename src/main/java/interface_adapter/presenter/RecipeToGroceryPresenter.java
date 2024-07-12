package interface_adapter.presenter;

import interface_adapter.view_model.RecipeToGroceryViewModel;
import interface_adapter.view_model.ViewManagerModel;
import use_case.output_data.RecipeToGroceryOutputBoundary;
import use_case.output_data.RecipeToGroceryOutputData;

public class RecipeToGroceryPresenter implements RecipeToGroceryOutputBoundary {
    private RecipeToGroceryViewModel recipeToGroceryViewModel;
    private ViewManagerModel viewManagerModel;

    public RecipeToGroceryPresenter(ViewManagerModel viewManagerModel, RecipeToGroceryViewModel recipeToGroceryViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.recipeToGroceryViewModel = recipeToGroceryViewModel;
    }

    @Override
    public void prepareSuccessView(RecipeToGroceryOutputData recipes) {
        recipeToGroceryViewModel.setGroceryResult(recipes);
        recipeToGroceryViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(recipeToGroceryViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}



