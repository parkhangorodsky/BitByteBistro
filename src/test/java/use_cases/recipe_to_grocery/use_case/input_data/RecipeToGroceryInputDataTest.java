package use_cases.search_recipe.use_case.input_data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchRecipeInputDataTest {

    @Test
    void testBasicConstructor() {
        String recipeName = "recipe";
        SearchRecipeInputData inputData = new SearchRecipeInputData(recipeName);

        assertFalse(inputData.isAdvanced);
        assertEquals(recipeName, inputData.getRecipeName());
        assertNull(inputData.getDiet());
        assertNull(inputData.getHealth());
        assertNull(inputData.getExcluded());
        assertNull(inputData.getDishType());
        assertNull(inputData.getMealType());
        assertNull(inputData.getCuisineType());
    }

    @Test
    void testAdvancedConstructor() {
        String recipeName = "recipe";
        List<String> excluded = Arrays.asList("Eggplant", "Oyster");
        List<String> diet = Arrays.asList("Low-Fat");
        List<String> health = Arrays.asList("Gluten-Free");
        List<String> cuisineType = Arrays.asList("American", "Korean", "Chinese");
        List<String> dishType = Arrays.asList("starter", "desert");
        List<String> mealType = new ArrayList<>();

        SearchRecipeInputData inputData = new SearchRecipeInputData(recipeName, excluded, diet, health, cuisineType, dishType, mealType);

        assertTrue(inputData.isAdvanced);
        assertEquals(recipeName, inputData.getRecipeName());
        assertEquals(diet, inputData.getDiet());
        assertEquals(health, inputData.getHealth());
        assertEquals(dishType, inputData.getDishType());
        assertEquals(mealType, inputData.getMealType());
        assertEquals(cuisineType, inputData.getCuisineType());
    }
}
