package use_cases.search_recipe;

import entity.Recipe;
import frameworks.api.RecipeAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_cases.search_recipe.interface_adapter.presenter.SearchRecipeOutputBoundary;
import use_cases.search_recipe.use_case.input_data.SearchRecipeInputData;
import use_cases.search_recipe.use_case.interactor.SearchRecipeInteractor;
import use_cases.search_recipe.use_case.output_data.SearchRecipeOutputData;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the SearchRecipeInteractor class.
 */
public class SearchRecipeInteractorTest {

    private SearchRecipeInteractor searchRecipeInteractor;
    private RecipeAPI recipeAPI;
    private SearchRecipeOutputBoundary searchRecipePresenter;

    @BeforeEach
    void setUp() {
        recipeAPI = mock(RecipeAPI.class);
        searchRecipePresenter = mock(SearchRecipeOutputBoundary.class);
        searchRecipeInteractor = new SearchRecipeInteractor(searchRecipePresenter, recipeAPI);
    }

    /**
     * Tests a successful search with valid input data.
     */
    @Test
    void testSuccessfulSearch() {
        // Arrange
        SearchRecipeInputData inputData = new SearchRecipeInputData("pasta");
        List<Recipe> recipeList = Arrays.asList(
                new Recipe("Spaghetti Bolognese"),
                new Recipe("Pasta Carbonara")
        );

        when(recipeAPI.getRecipe(inputData)).thenReturn(recipeList);

        // Act
        searchRecipeInteractor.execute(inputData);

        // Assert
        ArgumentCaptor<SearchRecipeOutputData> outputDataCaptor = ArgumentCaptor.forClass(SearchRecipeOutputData.class);
        verify(searchRecipePresenter).prepareSuccessView(outputDataCaptor.capture());
        assertEquals(recipeList, outputDataCaptor.getValue().getRecipes());
    }

    /**
     * Tests a search with no results.
     */
    @Test
    void testNoResultsFound() {
        // Arrange
        SearchRecipeInputData inputData = new SearchRecipeInputData("unknownDish");
        List<Recipe> emptyList = Collections.emptyList();

        when(recipeAPI.getRecipe(inputData)).thenReturn(emptyList);

        // Act
        searchRecipeInteractor.execute(inputData);

        // Assert
        ArgumentCaptor<SearchRecipeOutputData> outputDataCaptor = ArgumentCaptor.forClass(SearchRecipeOutputData.class);
        verify(searchRecipePresenter).prepareSuccessView(outputDataCaptor.capture());
        assertTrue(outputDataCaptor.getValue().getRecipes().isEmpty());
    }

    /**
     * Tests a search with null results (API failure).
     */
    @Test
    void testApiFailure() {
        // Arrange
        SearchRecipeInputData inputData = new SearchRecipeInputData("pasta");
        when(recipeAPI.getRecipe(inputData)).thenReturn(null);

        // Act
        searchRecipeInteractor.execute(inputData);

        // Assert
        verify(searchRecipePresenter).prepareFailView("api fail");
    }


    /**
     * Tests an advanced search with valid criteria.
     */
    @Test
    void testAdvancedSearch() {
        // Arrange
        SearchRecipeInputData inputData = new SearchRecipeInputData(
                "pasta", Arrays.asList("nuts"), Arrays.asList("vegan"),
                Arrays.asList("gluten-free"), Arrays.asList("italian"),
                Arrays.asList("dinner"), Arrays.asList("main")
        );
        List<Recipe> recipeList = Arrays.asList(
                new Recipe("Vegan Pasta")
        );

        when(recipeAPI.getRecipe(inputData)).thenReturn(recipeList);

        // Act
        searchRecipeInteractor.execute(inputData);

        // Assert
        ArgumentCaptor<SearchRecipeOutputData> outputDataCaptor = ArgumentCaptor.forClass(SearchRecipeOutputData.class);
        verify(searchRecipePresenter).prepareSuccessView(outputDataCaptor.capture());
        assertEquals(recipeList, outputDataCaptor.getValue().getRecipes());
    }
}
