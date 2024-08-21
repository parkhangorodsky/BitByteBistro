package frameworks.data_access.serialization;

import entity.Ingredient;
import entity.Nutrition;
import entity.Recipe;
import entity.ShoppingList;
import entity.builder.DefaultRecipeBuilder;
import entity.builder.RecipeBuilder;
import org.bson.Document;
import org.bson.types.Binary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingListSerializerTest {

    private ShoppingListSerializer serializer;
    private ShoppingList testShoppingList;
    private Document testDocument;

    @BeforeEach
    void setUp() {
        serializer = new ShoppingListSerializer();

        // Set up sample data for ingredients
        Ingredient ingredient1 = new Ingredient("ing-001", "Tomato", "kg", "Vegetable", 5.0f);
        Ingredient ingredient2 = new Ingredient("ing-002", "Cheese", "grams", "Dairy", 200);

        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(ingredient1);
        ingredientList.add(ingredient2);

        Map<String, Nutrition> nutritionMap = new TreeMap<>();
        nutritionMap.put("Calories",  new Nutrition(
                "Calories", 200.0f, "kcal", 10.0f));

        nutritionMap.put("Protein", new Nutrition("Protein", 15.0f, "g"));

        // Set up sample data for recipes
        Recipe recipe1 = new DefaultRecipeBuilder("recipe-001")
                .buildName("Tomato Soup")
                .buildYield(4)
                .buildInstruction("Boil the tomatoes")
                .buildIngredientList(Collections.singletonList(ingredient1))
                .buildNutritionMap(nutritionMap)
                .get();

        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(recipe1);

        // Set up sample ShoppingList
        testShoppingList = new ShoppingList("user-001", "Weekly Groceries");
        testShoppingList.setListItems(ingredientList);
        testShoppingList.setEstimatedTotalCost(15.0);
        testShoppingList.setRecipes(recipeList);

        // Serialize the ShoppingList
        testDocument = serializer.serialize(testShoppingList);
    }

    @Test
    void testSerialize() {
        Document serializedDocument = serializer.serialize(testShoppingList);

        assertNotNull(serializedDocument, "Serialized document should not be null");
        assertEquals(testDocument, serializedDocument, "Serialized document does not match expected document");

        assertEquals("user-001", serializedDocument.getString("listOwner"));
        assertEquals("Weekly Groceries", serializedDocument.getString("shoppingListName"));
        assertEquals(15.0, serializedDocument.getDouble("estimatedTotalCost"));
        assertEquals(1, serializedDocument.getList("recipes", Document.class).size());

        // Verify ingredients
        List<Document> ingredients = serializedDocument.getList("listItems", Document.class);
        assertEquals(2, ingredients.size());
    }

    @Test
    void testDeserialize() {
        ShoppingList deserializedShoppingList = serializer.deserialize(testDocument);

        assertNotNull(deserializedShoppingList, "Deserialized shopping list should not be null");

        assertEquals(testShoppingList.getListOwner(), deserializedShoppingList.getListOwner());
        assertEquals(testShoppingList.getShoppingListName(), deserializedShoppingList.getShoppingListName());
        assertEquals(testShoppingList.getEstimatedTotalCost(), deserializedShoppingList.getEstimatedTotalCost());

        // Verify ingredients
        List<Ingredient> ingredients = deserializedShoppingList.getListItems();
        assertEquals(testShoppingList.getListItems().size(), ingredients.size());

        // Verify recipes
        List<Recipe> recipes = deserializedShoppingList.getRecipes();
        assertEquals(testShoppingList.getRecipes().size(), recipes.size());
    }

    @Test
    void testSerializeShoppingListMap() {
        Map<String, ShoppingList> shoppingListMap = new HashMap<>();
        shoppingListMap.put("list1", testShoppingList);

        Document serializedDocument = serializer.serializeShoppingListMap(shoppingListMap);

        assertNotNull(serializedDocument, "Serialized document should not be null");
        assertEquals(1, serializedDocument.size());
        assertTrue(serializedDocument.containsKey("list1"));

        Document listDocument = serializedDocument.get("list1", Document.class);
        assertEquals(testDocument, listDocument, "Serialized document for list1 does not match expected document");
    }

    @Test
    void testDeserializeShoppingListMap() {
        Document document = serializer.serializeShoppingListMap(Collections.singletonMap("list1", testShoppingList));

        Map<String, ShoppingList> deserializedMap = serializer.deserializeShoppingListMap(document);

        assertNotNull(deserializedMap, "Deserialized map should not be null");
        assertEquals(1, deserializedMap.size());
        assertTrue(deserializedMap.containsKey("list1"));

        ShoppingList deserializedShoppingList = deserializedMap.get("list1");
        assertNotNull(deserializedShoppingList, "Deserialized shopping list should not be null");

        assertEquals(testShoppingList.getListOwner(), deserializedShoppingList.getListOwner());
        assertEquals(testShoppingList.getShoppingListName(), deserializedShoppingList.getShoppingListName());
        assertEquals(testShoppingList.getEstimatedTotalCost(), deserializedShoppingList.getEstimatedTotalCost());
    }

}
