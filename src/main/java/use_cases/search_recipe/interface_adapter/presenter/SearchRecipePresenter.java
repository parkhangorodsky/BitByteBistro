package use_cases.search_recipe.interface_adapter.presenter;

import use_cases.search_recipe.interface_adapter.view_model.SearchRecipeViewModel;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.search_recipe.use_case.output_data.SearchRecipeOutputData;

public class SearchRecipePresenter implements SearchRecipeOutputBoundary {

    private SearchRecipeViewModel searchRecipeViewModel;
    private ViewManagerModel viewManagerModel;

    public SearchRecipePresenter(ViewManagerModel viewManagerModel, SearchRecipeViewModel searchRecipeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.searchRecipeViewModel = searchRecipeViewModel;
    }

    @Override
    public void prepareSuccessView(SearchRecipeOutputData recipes) {
        searchRecipeViewModel.setRecipeSearchResult(recipes);
        searchRecipeViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(searchRecipeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
