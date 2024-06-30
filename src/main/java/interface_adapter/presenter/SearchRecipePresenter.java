package interface_adapter.presenter;

import entity.Recipe;
import interface_adapter.view_model.SearchRecipeViewModel;
import interface_adapter.view_model.ViewManagerModel;
import use_case.output_data.OutputBoundary;
import use_case.output_data.SearchRecipeOutputData;
import view.SearchRecipeView;

import javax.swing.text.View;

public class SearchRecipePresenter implements OutputBoundary {

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
