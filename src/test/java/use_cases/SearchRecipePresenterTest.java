package use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases.search_recipe.interface_adapter.presenter.SearchRecipePresenter;
import use_cases.search_recipe.interface_adapter.view_model.SearchRecipeViewModel;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.search_recipe.use_case.output_data.SearchRecipeOutputData;
import entity.Recipe;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class SearchRecipePresenterTest {

    private SearchRecipeViewModel searchRecipeViewModel;
    private ViewManagerModel viewManagerModel;
    private SearchRecipePresenter presenter;

    @BeforeEach
    public void setUp() {
        searchRecipeViewModel = mock(SearchRecipeViewModel.class);
        viewManagerModel = mock(ViewManagerModel.class);
        presenter = new SearchRecipePresenter(viewManagerModel, searchRecipeViewModel);
    }

    @Test
    public void testPrepareSuccessView() {
        List<Recipe> recipes = new ArrayList<>();
        SearchRecipeOutputData outputData = new SearchRecipeOutputData(recipes);

        presenter.prepareSuccessView(outputData);

        verify(searchRecipeViewModel).setRecipeSearchResult(outputData);
        verify(searchRecipeViewModel).firePropertyChange();
        verify(viewManagerModel).setActiveView(searchRecipeViewModel.getViewName());
        verify(viewManagerModel).firePropertyChanged();
    }

    @Test
    public void testPrepareFailView() {
        presenter.prepareFailView("api fail");

        verify(searchRecipeViewModel).firePropertyChange("api fail");
    }
}
