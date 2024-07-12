package interface_adapter.presenter;

import interface_adapter.view_model.SearchRecipeViewModel;
import interface_adapter.view_model.ViewManagerModel;
import use_case.output_data.SearchRecipeOutputBoundary;
import use_case.output_data.SearchRecipeOutputData;

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
