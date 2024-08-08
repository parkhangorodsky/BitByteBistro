package use_cases;

import org.junit.jupiter.api.Test;
import use_cases.search_recipe.use_case.output_data.SearchRecipeOutputData;
import entity.Recipe;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SearchRecipeOutputDataTest {

    @Test
    public void testConstructorAndGetters() {
        Recipe recipe1 = new Recipe("1");
        Recipe recipe2 = new Recipe("2");
        List<Recipe> recipes = Arrays.asList(recipe1, recipe2);

        SearchRecipeOutputData outputData = new SearchRecipeOutputData(recipes);

        assertEquals(recipes, outputData.getRecipes());
    }

    @Test
    public void testIterator() {
        Recipe recipe1 = new Recipe("1");
        Recipe recipe2 = new Recipe("2");
        List<Recipe> recipes = Arrays.asList(recipe1, recipe2);

        SearchRecipeOutputData outputData = new SearchRecipeOutputData(recipes);

        int count = 0;
        for (Recipe recipe : outputData) {
            count++;
        }

        assertEquals(2, count);
    }
}
