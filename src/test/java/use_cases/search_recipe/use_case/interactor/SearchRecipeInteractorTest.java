package use_cases.search_recipe.use_case.interactor;

import entity.Ingredient;
import entity.Recipe;
import frameworks.api.RecipeAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import entity.mock.MockIngredient;
import use_cases.search_recipe.interface_adapter.presenter.SearchRecipePresenter;
import use_cases.search_recipe.use_case.input_data.SearchRecipeInputData;
import use_cases.search_recipe.use_case.output_data.SearchRecipeOutputData;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchRecipeInteractorTest {
    RecipeAPI mockRecipeAPI;
    SearchRecipePresenter mockSearchRecipePresenter;
    SearchRecipeInteractor searchRecipeInteractor;

    @BeforeEach
    void setUp() {
        mockRecipeAPI = Mockito.mock(RecipeAPI.class);
        mockSearchRecipePresenter = Mockito.mock(SearchRecipePresenter.class);
        searchRecipeInteractor = new SearchRecipeInteractor(mockSearchRecipePresenter, mockRecipeAPI);
    }

    @Test
    void execute() {
        // Prepare mock data
        Recipe mockRecipe = Mockito.mock(Recipe.class);
        String name = "Chocolate Cake";

        Ingredient mockIngredient1 = new MockIngredient().mock;
        Ingredient mockIngredient2 = new MockIngredient().mock;
        List<Ingredient> ingredient = Arrays.asList(mockIngredient1, mockIngredient2);

        String image = "image1.jpg";

        when(mockRecipe.getName()).thenReturn(name);
        when(mockRecipe.getIngredientList()).thenReturn(ingredient);
        when(mockRecipe.getImage()).thenReturn(image);

        List<Recipe> recipeList = List.of(mockRecipe);

        // Mock the behavior of recipeAPI.getRecipe
        Mockito.when(mockRecipeAPI.getRecipe(Mockito.any(SearchRecipeInputData.class)))
                .thenReturn(recipeList);

        SearchRecipeInputData inputData = new SearchRecipeInputData("cake");

        // Execute the method under test
        searchRecipeInteractor.execute(inputData);

        // Capture the output data passed to the presenter
        ArgumentCaptor<SearchRecipeOutputData> argumentCaptor = ArgumentCaptor.forClass(SearchRecipeOutputData.class);
        verify(mockSearchRecipePresenter, times(1)).prepareSuccessView(argumentCaptor.capture());

        // Verify the data
        SearchRecipeOutputData capturedOutputData = argumentCaptor.getValue();
        List<Recipe> recipes = capturedOutputData.getRecipes();

        // Assertions
        assertEquals(1, recipes.size());
        Recipe capturedRecipe = recipes.get(0);
        assertEquals("Chocolate Cake", capturedRecipe.getName());
        assertEquals(2, capturedRecipe.getIngredientList().size());
        assertEquals(mockIngredient1, capturedRecipe.getIngredientList().get(0));
        assertEquals(mockIngredient2, capturedRecipe.getIngredientList().get(1));
        assertEquals("image1.jpg", capturedRecipe.getImage());

        // Verify interactions with the mockRecipe
        verify(mockRecipe, times(1)).getName();
        verify(mockRecipe, times(3)).getIngredientList();
        verify(mockRecipe, times(1)).getImage();
    }


}
