package frameworks.data_access.serialization;

import entity.Ingredient;
import entity.Nutrition;
import entity.Recipe;
import entity.builder.DefaultRecipeBuilder;
import entity.builder.RecipeBuilder;
import org.bson.Document;
import org.bson.types.Binary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RecipeSerializerTest {

    private RecipeSerializer serializer;
    private Recipe testRecipe;
    private Document testDocument;

    @BeforeEach
    void setUp() {
        serializer = new RecipeSerializer();

        // Sample data for the Recipe
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("ing-001", "Tomato", "kg", "Vegetable", 5.0f));

        Map<String, Nutrition> nutritionMap = new HashMap<>();
        nutritionMap.put("Calories", new Nutrition("Calories", 200, "kcal", 10.0f));

        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);

        testRecipe = new DefaultRecipeBuilder("recipe-001")
                .buildName("Tomato Soup")
                .buildYield(4)
                .buildInstruction("Blend and cook")
                .buildIngredientList(ingredients)
                .buildNutritionMap(nutritionMap)
                .buildDietLabels(List.of("Vegetarian"))
                .buildHealthLabels(List.of("Low Fat"))
                .buildCautions(List.of("None"))
                .buildTags(List.of("Soup", "Easy"))
                .buildCuisineType(List.of("Italian"))
                .buildMealType(List.of("Lunch"))
                .buildDishType(List.of("Soup"))
                .buildImage(image)
                .buildSmallImage(image)
                .get();

        testDocument = new Document()
                .append("name", "Tomato Soup")
                .append("yield", 4)
                .append("instructions", "Blend and cook")
                .append("ingredientList", new IngredientSerializer().serializeList(ingredients))
                .append("nutritionMap", new NutritionSerializer().serializeMap(nutritionMap))
                .append("id", "recipe-001")
                .append("dietLabels", List.of("Vegetarian"))
                .append("healthLabels", List.of("Low Fat"))
                .append("cautions", List.of("None"))
                .append("tags", List.of("Soup", "Easy"))
                .append("cuisineType", List.of("Italian"))
                .append("mealType", List.of("Lunch"))
                .append("dishType", List.of("Soup"))
                .append("image", serializeImage(image))
                .append("smallImage", serializeImage(image));
    }

    private byte[] serializeImage(BufferedImage image) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            javax.imageio.ImageIO.write(image, "png", baos);
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error serializing image", e);
        }
    }

    private BufferedImage deserializeImage(byte[] imageData) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(imageData)) {
            return javax.imageio.ImageIO.read(bais);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing image", e);
        }
    }

    @Test
    void testSerializeAndDeserialize() {
        Document serializedDocument = serializer.serialize(testRecipe);

        assertNotNull(serializedDocument, "Serialized document should not be null");

        Recipe deserializedRecipe = serializer.deserialize(serializedDocument);

        assertNotNull(deserializedRecipe, "Deserialized recipe should not be null");

        assertEquals(testRecipe.getName(), deserializedRecipe.getName());
        assertEquals(testRecipe.getYield(), deserializedRecipe.getYield());
        assertEquals(testRecipe.getInstructions(), deserializedRecipe.getInstructions());
        for (int i = 0; i < testRecipe.getIngredientList().size(); i++) {
            Ingredient testIngredient = testRecipe.getIngredientList().get(i);
            Ingredient deserializedIngredient = deserializedRecipe.getIngredientList().get(i);
            assertEquals(testIngredient.getCategory(), deserializedIngredient.getCategory());
            assertEquals(testIngredient.getIngredientID(), deserializedIngredient.getIngredientID());
            assertEquals(testIngredient.getIngredientName(), deserializedIngredient.getIngredientName());
            assertEquals(testIngredient.getQuantity(), deserializedIngredient.getQuantity());
            assertEquals(testIngredient.getQuantityUnit(), deserializedIngredient.getQuantityUnit());
        }

        assertEquals(testRecipe.getNutritionMap().get("Calories").getQuantity(), deserializedRecipe.getNutritionMap().get("Calories").getQuantity());
        assertEquals(testRecipe.getDietLabels(), deserializedRecipe.getDietLabels());
        assertEquals(testRecipe.getHealthLabels(), deserializedRecipe.getHealthLabels());
        assertEquals(testRecipe.getCautions(), deserializedRecipe.getCautions());
        assertEquals(testRecipe.getTags(), deserializedRecipe.getTags());
        assertEquals(testRecipe.getCuisineType(), deserializedRecipe.getCuisineType());
        assertEquals(testRecipe.getMealType(), deserializedRecipe.getMealType());
        assertEquals(testRecipe.getDishType(), deserializedRecipe.getDishType());

        BufferedImage testImage = deserializeImage( (byte[]) testDocument.get("image"));
        BufferedImage deserializedImage = deserializedRecipe.getImage();
        assertTrue(testImage.getWidth() == deserializedImage.getWidth(), "Images width should match");
        assertTrue(testImage.getHeight() == deserializedImage.getHeight(), "Images height should match");
    }

    @Test
    void testSerializeRecipeList() {
        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(testRecipe);
        recipeList.add(new DefaultRecipeBuilder("recipe-002")
                .buildName("Pasta")
                .buildYield(2)
                .buildInstruction("Cook pasta")
                .buildIngredientList(new ArrayList<>())
                .buildNutritionMap(new HashMap<>())
                .buildDietLabels(new ArrayList<>())
                .buildHealthLabels(new ArrayList<>())
                .buildCautions(new ArrayList<>())
                .buildTags(new ArrayList<>())
                .buildCuisineType(new ArrayList<>())
                .buildMealType(new ArrayList<>())
                .buildDishType(new ArrayList<>())
                .get());

        List<Document> serializedList = serializer.serializeRecipeList(recipeList);

        assertNotNull(serializedList, "Serialized list should not be null");
        assertEquals(2, serializedList.size(), "Serialized list size should be 2");
    }

    @Test
    void testDeserializeRecipeList() {
        List<Document> documentList = new ArrayList<>();
        documentList.add(testDocument);
        documentList.add(new Document()
                .append("name", "Pasta")
                .append("yield", 2)
                .append("instructions", "Cook pasta")
                .append("ingredientList", new ArrayList<>())
                .append("nutritionMap", new Document())
                .append("id", "recipe-002")
                .append("dietLabels", new ArrayList<>())
                .append("healthLabels", new ArrayList<>())
                .append("cautions", new ArrayList<>())
                .append("tags", new ArrayList<>())
                .append("cuisineType", new ArrayList<>())
                .append("mealType", new ArrayList<>())
                .append("dishType", new ArrayList<>()));

        List<Recipe> deserializedList = serializer.deserializeRecipeList(documentList);

        assertNotNull(deserializedList, "Deserialized list should not be null");
        assertEquals(2, deserializedList.size(), "Deserialized list size should be 2");
    }

    @Test
    void testSerializeEmptyList() {
        List<Recipe> emptyRecipeList = new ArrayList<>();
        List<Document> serializedList = serializer.serializeRecipeList(emptyRecipeList);

        assertNotNull(serializedList, "Serialized list should not be null");
        assertTrue(serializedList.isEmpty(), "Serialized list should be empty");
    }

    @Test
    void testDeserializeEmptyList() {
        List<Document> emptyDocumentList = new ArrayList<>();
        List<Recipe> deserializedList = serializer.deserializeRecipeList(emptyDocumentList);

        assertNotNull(deserializedList, "Deserialized list should not be null");
        assertTrue(deserializedList.isEmpty(), "Deserialized list should be empty");
    }

}
