package use_cases.search_recipe.interface_adapter.presenter;

import entity.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.search_recipe.interface_adapter.view_model.SearchRecipeViewModel;
import use_cases.search_recipe.use_case.output_data.SearchRecipeOutputData;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SearchRecipePresenterTest {

    private SearchRecipeViewModel searchRecipeViewModel;
    private ViewManagerModel viewManagerModel;
    private SearchRecipePresenter presenter;

    @BeforeEach
    public void setUp() {
        // Create Mock View model
        searchRecipeViewModel = mock(SearchRecipeViewModel.class);
        // Create mock ViewManagerModel
        viewManagerModel = mock(ViewManagerModel.class);
        // Create Ppresenter based on the mock view models
        presenter = new SearchRecipePresenter(viewManagerModel, searchRecipeViewModel);
    }

    @Test
    public void testPrepareSuccessView() {
        // Create a mock use case output data (recipe search result)
        Recipe recipe1 = Mockito.mock(Recipe.class);
        Recipe recipe2 = Mockito.mock(Recipe.class);
        List<Recipe> recipes = Arrays.asList(recipe1, recipe2);
        SearchRecipeOutputData outputData = new SearchRecipeOutputData(recipes);

        // Send the mock output data to the presenter
        presenter.prepareSuccessView(outputData);

        // check if setRecipeSearchResult method is called with outputData as an argument on searchRecipeViewModel
        verify(searchRecipeViewModel).setRecipeSearchResult(outputData);
        // check if firePropertyChanged method is called on searchRecipeViewModel
        verify(searchRecipeViewModel).firePropertyChanged();
        // check if setActiveView method is called with the view name of the
        // searchRecipeViewModel as an argument on viewManagerModel
        verify(viewManagerModel).setActiveView(searchRecipeViewModel.getViewName());
        // check if firePropertyChanged is called on viewManagerModel
        verify(viewManagerModel).firePropertyChanged();
    }
}
