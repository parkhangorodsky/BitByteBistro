package frameworks.data_access.serialization;

import entity.*;
import entity.builder.DefaultRecipeBuilder;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UserSerializerTest {

    private UserSerializer serializer;
    private User testUser;
    private Document testDocument;

    @BeforeEach
    void setUp() {
        serializer = new UserSerializer();

        // Set up sample data for ingredients
        Ingredient ingredient = new Ingredient("ing-001", "Tomato", "kg", "Vegetable", 5.0f);

        Map<String, Nutrition> nutritionMap = new TreeMap<>();
        nutritionMap.put("Calories",  new Nutrition(
                "Calories", 200.0f, "kcal", 10.0f));

        // Set up sample data for recipes
        Recipe recipe = new DefaultRecipeBuilder("recipe-001")
                .buildName("Tomato Soup")
                .buildYield(4)
                .buildInstruction("Boil the tomatoes")
                .buildIngredientList(Collections.singletonList(ingredient))
                .buildNutritionMap(nutritionMap)
                .get();

        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(recipe);

        // Set up sample data for shopping lists
        ShoppingList shoppingList = new ShoppingList("user-001", "Weekly Groceries");
        shoppingList.setListItems(Collections.singletonList(ingredient));
        shoppingList.setEstimatedTotalCost(15.0);
        shoppingList.setRecipes(recipeList);

        Map<String, ShoppingList> shoppingListMap = new HashMap<>();
        shoppingListMap.put("list1", shoppingList);

        // Set up sample User
        testUser = new User("John Doe", "john@example.com", "password123", LocalDateTime.now());
        testUser.setRecipes(recipeList);
        testUser.setRecentlyViewedRecipes(recipeList);
        testUser.setShoppingLists(shoppingListMap);
        testUser.setPreference(Collections.singletonMap("theme", "dark"));

        // Serialize the User
        testDocument = serializer.serialize(testUser);
    }

    @Test
    void testSerialize() {
        Document serializedDocument = serializer.serialize(testUser);


        assertNotNull(serializedDocument, "Serialized document should not be null");
        assertEquals(testDocument, serializedDocument, "Serialized document does not match expected document");

        assertEquals(testUser.getUserName(), serializedDocument.getString("userName"));
        assertEquals(testUser.getUserEmail(), serializedDocument.getString("userEmail"));
        assertEquals(testUser.getUserPassword(), serializedDocument.getString("userPassword"));
        assertEquals(testUser.getCreatedAt(), serializedDocument.get("createdAt"));
        assertEquals(testUser.getPreference(), serializedDocument.get("preference", Map.class));

        // Verify lists
        assertEquals(1, serializedDocument.getList("recipes", Document.class).size());
        assertEquals(1, serializedDocument.getList("recentlyViewedRecipes", Document.class).size());
        assertEquals(1, serializedDocument.get("shoppingLists", Document.class).size());
    }

    @Test
    void testDeserialize() {
        User deserializedUser = serializer.deserialize(testDocument);

        assertNotNull(deserializedUser, "Deserialized user should not be null");

        assertEquals(testUser.getUserName(), deserializedUser.getUserName());
        assertEquals(testUser.getUserEmail(), deserializedUser.getUserEmail());
        assertEquals(testUser.getUserPassword(), deserializedUser.getUserPassword());
        assertEquals(testUser.getCreatedAt(), deserializedUser.getCreatedAt());
        assertEquals(testUser.getPreference(), deserializedUser.getPreference());

        // Verify lists
        assertEquals(testUser.getRecipes().getFirst().getName(), deserializedUser.getRecipes().getFirst().getName());
        assertEquals(testUser.getRecentlyViewedRecipes().getFirst().getName(), deserializedUser.getRecentlyViewedRecipes().getFirst().getName());
        assertEquals(testUser.getShoppingLists().size(), deserializedUser.getShoppingLists().size());
    }
}
