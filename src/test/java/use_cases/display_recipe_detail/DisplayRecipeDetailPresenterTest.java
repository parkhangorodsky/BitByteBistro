package use_cases.display_recipe_detail;

import entity.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;

public class DisplayRecipeDetailPresenterTest {

    private DisplayRecipeDetailPresenter presenter;
    private DisplayRecipeDetailViewModel viewModel;

    @BeforeEach
    void setUp() {
        presenter = new DisplayRecipeDetailPresenter();
        viewModel = mock(DisplayRecipeDetailViewModel.class);
    }

    @Test
    void testPrepareSuccessView() {
        // Arrange
        Recipe testRecipe = mock(Recipe.class);
        DisplayRecipeResultOutputData outputData = new DisplayRecipeResultOutputData(testRecipe, viewModel);

        // Act
        presenter.prepareSuccessView(outputData);

        // Verify that setRecipe was called with the correct Recipe
        verify(viewModel).setRecipe(testRecipe);

        // Verify that firePropertyChange was called with the "initialized" property name
        verify(viewModel).firePropertyChange("initialized");
    }
}
