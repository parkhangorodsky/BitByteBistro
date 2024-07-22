package use_cases.search_recipe.interface_adapter.presenter;

import use_cases.search_recipe.interface_adapter.view_model.SearchRecipeViewModel;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.search_recipe.use_case.output_data.SearchRecipeOutputData;

/**
 * Overview: Presenter for the search recipe use case.
 * Procedure: When the interactor calls the appropriate method to prepare the view,
 * this class updates the searchRecipeViewModel and fire property change to the viewManagerModel.
 * Encapsulation: This class encapsulates the logic for updating the searchRecipeViewModel.
 */
public class SearchRecipePresenter implements SearchRecipeOutputBoundary {

    private SearchRecipeViewModel searchRecipeViewModel; // View model associated with the search recipe use case.
    private ViewManagerModel viewManagerModel; // The central view model that handles which view is to be displayed.

    /**
     * Constructs a SearchRecipePresenter with the specified view manager and search recipe view model.
     *
     * @param viewManagerModel the model managing the active view
     * @param searchRecipeViewModel the view model for displaying recipe search results
     */
    public SearchRecipePresenter(ViewManagerModel viewManagerModel, SearchRecipeViewModel searchRecipeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.searchRecipeViewModel = searchRecipeViewModel;
    }

    /**
     * Prepares the view for successful recipe search results.
     * Updates the search recipe view model with the search results and notifies the view manager model.
     *
     * @param recipes the search results data
     */
    @Override
    public void prepareSuccessView(SearchRecipeOutputData recipes) {
        searchRecipeViewModel.setRecipeSearchResult(recipes);
        searchRecipeViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(searchRecipeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
