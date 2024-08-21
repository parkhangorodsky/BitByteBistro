package entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.util.*;

class RecipeTest {

    @Test
    void testDefaultConstructor() {
        Recipe recipe = new Recipe();
        assertNotNull(recipe.getId());
        assertTrue(recipe.getId().startsWith("recipe_"));
    }

    @Test
    void testConstructorWithId() {
        Recipe recipe = new Recipe("test_id");
        assertEquals("test_id", recipe.getId());
    }

    @Test
    void testGettersAndSetters() {
        Recipe recipe = new Recipe("test_id");

        // Test Name
        recipe.setName("Pasta");
        assertEquals("Pasta", recipe.getName());

        // Test Image
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        recipe.setImage(image);
        assertEquals(image, recipe.getImage());

        // Test Small Image
        BufferedImage smallImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
        recipe.setSmallImage(smallImage);
        assertEquals(smallImage, recipe.getSmallImage());

        // Test Yield
        recipe.setYield(4);
        assertEquals(4, recipe.getYield());

        // Test Instructions
        recipe.setInstructions("Boil pasta and add sauce.");
        assertEquals("Boil pasta and add sauce.", recipe.getInstructions());

        // Test Ingredient List
        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient ingredient = new Ingredient("1", "Tomato", "whole", "Vegetable", 2.0f);
        ingredients.add(ingredient);
        recipe.setIngredientList(ingredients);
        assertEquals(ingredients, recipe.getIngredientList());

        // Test Nutrition Map
        Map<String, Nutrition> nutritionMap = new HashMap<>();
        Nutrition nutrition = new Nutrition("Calories", 200, "kcal", 10);
        nutritionMap.put("Calories", nutrition);
        recipe.setNutritionMap(nutritionMap);
        assertEquals(nutritionMap, recipe.getNutritionMap());

        // Test Diet Labels
        List<String> dietLabels = Arrays.asList("Vegan", "Gluten-Free");
        recipe.setDietLabels(dietLabels);
        assertEquals(dietLabels, recipe.getDietLabels());

        // Test Health Labels
        List<String> healthLabels = Arrays.asList("Low-Fat", "Low-Sodium");
        recipe.setHealthLabels(healthLabels);
        assertEquals(healthLabels, recipe.getHealthLabels());

        // Test Cautions
        List<String> cautions = Arrays.asList("Peanuts", "Shellfish");
        recipe.setCautions(cautions);
        assertEquals(cautions, recipe.getCautions());

        // Test Tags
        List<String> tags = Arrays.asList("Dinner", "Quick");
        recipe.setTags(tags);
        assertEquals(tags, recipe.getTags());

        // Test Cuisine Type
        List<String> cuisineTypes = Arrays.asList("Italian", "Mediterranean");
        recipe.setCuisineType(cuisineTypes);
        assertEquals(cuisineTypes, recipe.getCuisineType());

        // Test Meal Type
        List<String> mealTypes = Arrays.asList("Main Course", "Side Dish");
        recipe.setMealType(mealTypes);
        assertEquals(mealTypes, recipe.getMealType());

        // Test Dish Type
        List<String> dishTypes = Arrays.asList("Appetizer", "Main Dish");
        recipe.setDishType(dishTypes);
        assertEquals(dishTypes, recipe.getDishType());
    }

    @Test
    void testCompareTo() {
        Recipe recipe1 = new Recipe();
        recipe1.setName("Apple Pie");
        Recipe recipe2 = new Recipe();
        recipe2.setName("Banana Bread");

        assertTrue(recipe1.compareTo(recipe2) < 0);
        assertTrue(recipe2.compareTo(recipe1) > 0);

        recipe2.setName("Apple Pie");
        assertEquals(0, recipe1.compareTo(recipe2));
    }
}
