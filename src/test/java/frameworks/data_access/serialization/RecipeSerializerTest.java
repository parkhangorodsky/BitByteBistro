package frameworks.data_access.serialization;

import entity.*;
import entity.builder.DefaultRecipeBuilder;
import frameworks.data_access.serialization.RecipeSerializer;
import org.bson.Document;
import org.bson.types.Binary;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RecipeSerializerTest {
    private final RecipeSerializer recipeSerializer = new RecipeSerializer();

    @Test
    void testSerialize() {
        // Create a sample Recipe
        Recipe recipe = new Recipe("test_id");
        recipe.setName("Pasta");
        recipe.setYield(4);
        recipe.setInstructions("Boil pasta and add sauce.");
        recipe.setIngredientList(Arrays.asList(new Ingredient("1", "Tomato", "whole", "Vegetable", 2.0f)));
        recipe.setNutritionMap(Map.of("Calories", new Nutrition("Calories", 200, "kcal", 10)));
        recipe.setDietLabels(Arrays.asList("Vegan", "Gluten-Free"));
        recipe.setHealthLabels(Arrays.asList("Low-Fat", "Low-Sodium"));
        recipe.setCautions(Arrays.asList("Peanuts", "Shellfish"));
        recipe.setTags(Arrays.asList("Dinner", "Quick"));
        recipe.setCuisineType(Arrays.asList("Italian", "Mediterranean"));
        recipe.setMealType(Arrays.asList("Main Course", "Side Dish"));
        recipe.setDishType(Arrays.asList("Appetizer", "Main Dish"));
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        recipe.setImage(image);
        BufferedImage smallImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
        recipe.setSmallImage(smallImage);

        // Serialize the recipe
        Document document = recipeSerializer.serialize(recipe);

        // Verify the serialization
        assertEquals("Pasta", document.getString("name"));
        assertEquals(4, document.getInteger("yield"));
        assertEquals("Boil pasta and add sauce.", document.getString("instructions"));
        assertEquals("test_id", document.getString("id"));
        assertNotNull(document.get("ingredientList"));
        assertNotNull(document.get("nutritionMap"));
        assertNotNull(document.get("dietLabels"));
        assertNotNull(document.get("healthLabels"));
        assertNotNull(document.get("cautions"));
        assertNotNull(document.get("tags"));
        assertNotNull(document.get("cuisineType"));
        assertNotNull(document.get("mealType"));
        assertNotNull(document.get("dishType"));
        assertNotNull(document.get("image"));
        assertNotNull(document.get("smallImage"));
    }

    @Test
    void testDeserialize() {
        // Create a sample Document
        Document document = new Document()
                .append("name", "Pasta")
                .append("yield", 4)
                .append("instructions", "Boil pasta and add sauce.")
                .append("ingredientList", Arrays.asList(
                        new Document("ingredientID", "1")
                                .append("ingredientName", "Tomato")
                                .append("quantity", 2.0)
                                .append("quantityUnit", "whole")
                                .append("category", "Vegetable")
                ))
                .append("nutritionMap", new Document("Calories", new Document("label", "Calories")
                        .append("unit", "kcal")
                        .append("quantity", 200.0)
                        .append("percentage", 10.0)))
                .append("id", "test_id")
                .append("dietLabels", Arrays.asList("Vegan", "Gluten-Free"))
                .append("healthLabels", Arrays.asList("Low-Fat", "Low-Sodium"))
                .append("cautions", Arrays.asList("Peanuts", "Shellfish"))
                .append("tags", Arrays.asList("Dinner", "Quick"))
                .append("cuisineType", Arrays.asList("Italian", "Mediterranean"))
                .append("mealType", Arrays.asList("Main Course", "Side Dish"))
                .append("dishType", Arrays.asList("Appetizer", "Main Dish"))
                .append("image", new Binary(new byte[0]))
                .append("smallImage", new Binary(new byte[0]));

        // Deserialize the document
        Recipe recipe = recipeSerializer.deserialize(document);

        // Verify the deserialization
        assertEquals("Pasta", recipe.getName());
        assertEquals(4, recipe.getYield());
        assertEquals("Boil pasta and add sauce.", recipe.getInstructions());
        assertEquals("test_id", recipe.getId());
        assertNotNull(recipe.getIngredientList());
        assertNotNull(recipe.getNutritionMap());
        assertNotNull(recipe.getDietLabels());
        assertNotNull(recipe.getHealthLabels());
        assertNotNull(recipe.getCautions());
        assertNotNull(recipe.getTags());
        assertNotNull(recipe.getCuisineType());
        assertNotNull(recipe.getMealType());
        assertNotNull(recipe.getDishType());
        assertNotNull(recipe.getImage());
        assertNotNull(recipe.getSmallImage());
    }

    @Test
    void testSerializeRecipeList() {
        // Create a sample Recipe
        Recipe recipe1 = new Recipe("test_id_1");
        recipe1.setName("Pasta");
        Recipe recipe2 = new Recipe("test_id_2");
        recipe2.setName("Salad");

        List<Recipe> recipeList = Arrays.asList(recipe1, recipe2);

        // Serialize the recipe list
        List<Document> documentList = recipeSerializer.serializeRecipeList(recipeList);

        // Verify the serialization
        assertEquals(2, documentList.size());
        assertEquals("Pasta", documentList.get(0).getString("name"));
        assertEquals("Salad", documentList.get(1).getString("name"));
    }

    @Test
    void testDeserializeRecipeList() {
        // Create sample Documents
        Document document1 = new Document().append("id", "test_id_1").append("name", "Pasta");
        Document document2 = new Document().append("id", "test_id_2").append("name", "Salad");

        List<Document> bsonList = Arrays.asList(document1, document2);

        // Deserialize the document list
        List<Recipe> recipeList = recipeSerializer.deserializeRecipeList(bsonList);

        // Verify the deserialization
        assertEquals(2, recipeList.size());
        assertEquals("Pasta", recipeList.get(0).getName());
        assertEquals("Salad", recipeList.get(1).getName());
    }
}
