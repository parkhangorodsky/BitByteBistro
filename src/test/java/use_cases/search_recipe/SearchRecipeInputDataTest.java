package use_cases.search_recipe;

import org.junit.jupiter.api.Test;
import use_cases.search_recipe.use_case.input_data.SearchRecipeInputData;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SearchRecipeInputDataTest {

    @Test
    public void testBasicConstructor() {
        SearchRecipeInputData inputData = new SearchRecipeInputData("Pasta");

        assertFalse(inputData.isAdvanced());
        assertEquals("Pasta", inputData.getRecipeName());
        assertNull(inputData.getDiet());
        assertNull(inputData.getHealth());
        assertNull(inputData.getCuisineType());
        assertNull(inputData.getMealType());
        assertNull(inputData.getDishType());
        assertNull(inputData.getExcluded());
    }

    @Test
    public void testAdvancedConstructor() {
        List<String> excluded = Arrays.asList("nuts", "dairy");
        List<String> diet = Arrays.asList("vegan");
        List<String> health = Arrays.asList("low-sugar");
        List<String> cuisineType = Arrays.asList("italian");
        List<String> dishType = Arrays.asList("main course");
        List<String> mealType = Arrays.asList("dinner");

        SearchRecipeInputData inputData = new SearchRecipeInputData("Pasta", excluded, diet, health, cuisineType, dishType, mealType);

        assertTrue(inputData.isAdvanced());
        assertEquals("Pasta", inputData.getRecipeName());
        assertEquals(excluded, inputData.getExcluded());
        assertEquals(diet, inputData.getDiet());
        assertEquals(health, inputData.getHealth());
        assertEquals(cuisineType, inputData.getCuisineType());
        assertEquals(dishType, inputData.getDishType());
        assertEquals(mealType, inputData.getMealType());
    }
}
