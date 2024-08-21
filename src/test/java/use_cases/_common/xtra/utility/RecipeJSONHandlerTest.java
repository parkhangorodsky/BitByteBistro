package use_cases._common.xtra.utility;

import entity.Ingredient;
import entity.Nutrition;
import entity.Recipe;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static use_cases._common.xtra.utility.RecipeJSONHandler.*;

public class RecipeJSONHandlerTest {

    @Test
    public void testConvertJSONResponseToRecipe_Success() {
        // Arrange
        JSONArray responseRecipe = new JSONArray();
        JSONObject recipeIndex = new JSONObject();
        JSONObject recipeJsonObject = createSampleRecipeJSONObject();
        recipeIndex.put("recipe", recipeJsonObject);

        responseRecipe.put(recipeIndex);

        // Act
        List<Recipe> recipes = convertJSONResponseToRecipe(responseRecipe);

        // Assert
        assertNotNull(recipes, "The returned recipe list should not be null.");
        assertEquals(1, recipes.size(), "The recipe list should contain one recipe.");
        Recipe recipe = recipes.get(0);
        assertEquals("Sample Recipe", recipe.getName(), "The recipe name should be 'Sample Recipe'.");
    }

    @Test
    public void testConvertJSONResponseToRecipe_EmptyArray() {
        // Arrange
        JSONArray emptyArray = new JSONArray();

        // Act
        List<Recipe> recipes = convertJSONResponseToRecipe(emptyArray);

        // Assert
        assertNotNull(recipes, "The returned recipe list should not be null.");
        assertTrue(recipes.isEmpty(), "The recipe list should be empty.");
    }

    private JSONObject createSampleRecipeJSONObject() {
        // Create sample JSON data that mimics the structure expected by the RecipeJSONHandler
        return new JSONObject()
                .put("uri", "http://example.com/recipe/sample-uri")
                .put("label", "Sample Recipe")
                .put("yield", 4)
                .put("url", "http://example.com/recipe/instructions")
                .put("dietLabels", new JSONArray().put("Low-Carb").put("Vegan"))
                .put("healthLabels", new JSONArray().put("Sugar-Free"))
                .put("cautions", new JSONArray().put("Soy"))
                .put("tags", new JSONArray().put("Easy"))
                .put("cuisineType", new JSONArray().put("Asian"))
                .put("mealType", new JSONArray().put("Lunch"))
                .put("dishType", new JSONArray().put("Main course"))
                .put("ingredients", new JSONArray().put(
                        new JSONObject()
                                .put("foodId", "ingredient-id-1")
                                .put("food", "Sugar")
                                .put("quantity", 100)
                                .put("measure", "g")
                                .put("foodCategory", "Sweets")
                ))
                .put("totalNutrients", new JSONObject()
                        .put("ENERC_KCAL", new JSONObject()
                                .put("label", "Energy")
                                .put("quantity", 200)
                                .put("unit", "kcal"))
                        .put("FAT", new JSONObject()
                                .put("label", "Fat")
                                .put("quantity", 10)
                                .put("unit", "g")))
                .put("totalDaily", new JSONObject()
                        .put("ENERC_KCAL", new JSONObject()
                                .put("label", "Energy")
                                .put("quantity", 10)
                                .put("unit", "%"))
                        .put("FAT", new JSONObject()
                                .put("label", "Fat")
                                .put("quantity", 5)
                                .put("unit", "%")))
                .put("images", new JSONObject()
                        .put("REGULAR", new JSONObject().put("url", "http://example.com/images/sample-image.jpg"))
                        .put("THUMBNAIL", new JSONObject().put("url", "http://example.com/images/sample-image-small.jpg")));
    }
}

