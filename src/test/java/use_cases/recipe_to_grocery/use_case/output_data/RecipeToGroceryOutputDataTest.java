package use_cases.recipe_to_grocery.use_case.output_data;

import entity.Ingredient;
import entity.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_cases.search_recipe.use_case.output_data.SearchRecipeOutputData;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class SearchRecipeOutputDataTest {

    SearchRecipeOutputData outputData;
    List<Recipe> recipes;

    @BeforeEach
    void setUp() {
        Recipe recipe1 = Mockito.mock(Recipe.class);
        Recipe recipe2 = Mockito.mock(Recipe.class);

        Ingredient ingredient1 = Mockito.mock(Ingredient.class);
        Ingredient ingredient2 = Mockito.mock(Ingredient.class);
        Ingredient ingredient3 = Mockito.mock(Ingredient.class);
        Ingredient ingredient4 = Mockito.mock(Ingredient.class);

        when(recipe1.getName()).thenReturn("cake");
        when(recipe1.getIngredientList()).thenReturn(Arrays.asList(ingredient1, ingredient2));
        when(recipe1.getImage()).thenReturn("image1.png");

        when(recipe2.getName()).thenReturn("fish");
        when(recipe2.getIngredientList()).thenReturn(Arrays.asList(ingredient3, ingredient4));
        when(recipe2.getImage()).thenReturn("image2.png");

        recipes = Arrays.asList(recipe1, recipe2);

        outputData = new SearchRecipeOutputData(recipes);
    }

    @Test
    void iterator() {

        Iterator<Recipe> iterator = outputData.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), recipes.get(0));
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), recipes.get(1));
        assertFalse(iterator.hasNext());
    }

    @Test
    void getRecipes() {
        assertEquals(outputData.getRecipes(), recipes);
    }
}
