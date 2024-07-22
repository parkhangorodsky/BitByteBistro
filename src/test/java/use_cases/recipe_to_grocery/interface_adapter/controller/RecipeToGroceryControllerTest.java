package use_cases.search_recipe.interface_adapter.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import use_cases.search_recipe.use_case.input_data.SearchRecipeInputData;
import use_cases.search_recipe.use_case.interactor.SearchRecipeInputBoundary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchRecipeControllerTest {

    private SearchRecipeController controller;
    private SearchRecipeInputBoundary mockInteractor;



    @BeforeEach
    void setUp() {
        mockInteractor = Mockito.mock(SearchRecipeInputBoundary.class);
        controller = new SearchRecipeController(mockInteractor);
    }

    @Test
    void testBasicExecute() {
        String testString = "testString";

        // Call the method under test
        controller.execute(testString);

        // Create an ArgumentCaptor for SearchRecipeInputData
        ArgumentCaptor<SearchRecipeInputData> argumentCaptor = ArgumentCaptor.forClass(SearchRecipeInputData.class);

        // Verify that the execute method of the interactor was called exactly once and capture the argument
        Mockito.verify(mockInteractor, Mockito.times(1)).execute(argumentCaptor.capture());

        // Retrieve the captured argument
        SearchRecipeInputData capturedInputData = argumentCaptor.getValue();

        // Assert that the captured argument has the expected query string
        assertEquals(testString, capturedInputData.getRecipeName());
    }

    @Test
    void testAdvancedExecute() {

        // Parameters
        String testString = "testString";
        List<String> excluded = Arrays.asList("excluded1", "excluded2");
        List<String> diet = Arrays.asList("diet1", "diet2", "diet3");
        List<String> health = Arrays.asList("health1", "health2");
        List<String> cuisineType = Arrays.asList("cuisine1", "cuisine2", "cuisine3");
        List<String> dishType = Arrays.asList("dish1");
        List<String> mealType = new ArrayList<>();

        // execute
        controller.execute(testString, excluded, diet, health, cuisineType, dishType, mealType);
        ArgumentCaptor<SearchRecipeInputData> argumentCaptor = ArgumentCaptor.forClass(SearchRecipeInputData.class);
        Mockito.verify(mockInteractor, Mockito.times(1)).execute(argumentCaptor.capture());
        SearchRecipeInputData capturedInputData = argumentCaptor.getValue();

        //asserts
        assertEquals(testString, capturedInputData.getRecipeName());
        assertEquals(diet, capturedInputData.getDiet());
        assertEquals(health, capturedInputData.getHealth());
        assertEquals(cuisineType, capturedInputData.getCuisineType());
        assertEquals(dishType, capturedInputData.getDishType());
        assertEquals(mealType, capturedInputData.getMealType());
    }
}


