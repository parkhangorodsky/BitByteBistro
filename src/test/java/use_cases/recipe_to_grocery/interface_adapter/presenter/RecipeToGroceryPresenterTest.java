package use_cases.recipe_to_grocery.interface_adapter.presenter;

import entity.ShoppingList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.recipe_to_grocery.interface_adapter.view_model.RecipeToGroceryViewModel;
import use_cases.recipe_to_grocery.use_case.output_data.RecipeToGroceryOutputData;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class RecipeToGroceryPresenterTest {

    private RecipeToGroceryViewModel recipeToGroceryViewModel;
    private ViewManagerModel viewManagerModel;
    private RecipeToGroceryPresenter presenter;

    @BeforeEach
    public void setUp() {
        // Create Mock View model
        recipeToGroceryViewModel = mock(RecipeToGroceryViewModel.class);
        // Create mock ViewManagerModel
        viewManagerModel = mock(ViewManagerModel.class);
        // Create Presenter based on the mock view models
        presenter = new RecipeToGroceryPresenter(viewManagerModel, recipeToGroceryViewModel);
    }

    @Test
    public void testPrepareSuccessView() {
        // Create a mock use case output data (shopping list result)
        ShoppingList shoppingList1 = Mockito.mock(ShoppingList.class);
        ShoppingList shoppingList2 = Mockito.mock(ShoppingList.class);
        List<ShoppingList> shoppingLists = Arrays.asList(shoppingList1, shoppingList2);
        RecipeToGroceryOutputData outputData = new RecipeToGroceryOutputData(shoppingLists);

        // Send the mock output data to the presenter
        presenter.prepareSuccessView(outputData);

        // check if setRecipeSearchResult method is called with outputData as an argument on recipeToGroceryViewModel
        verify(recipeToGroceryViewModel).setGroceryResult(outputData);
        // check if firePropertyChanged method is called on recipeToGroceryViewModel
        verify(recipeToGroceryViewModel).firePropertyChanged();
        // check if setActiveView method is called with the view name of the
        // recipeToGroceryViewModel as an argument on viewManagerModel
        verify(viewManagerModel).setActiveView(recipeToGroceryViewModel.getViewName());
        // check if firePropertyChanged is called on viewManagerModel
        verify(viewManagerModel).firePropertyChanged();
    }
}
