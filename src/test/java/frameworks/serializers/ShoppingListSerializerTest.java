import entity.Ingredient;
import entity.Recipe;
import entity.ShoppingList;
import entity.User;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingListSerializerTest {

    private ShoppingListSerializer serializer;
    private Ingredient ingredient;
    private Recipe recipe;
    private ShoppingList shoppingList;
    private User user;

    @BeforeEach
    void setUp() {
        serializer = new ShoppingListSerializer();
        ingredient = new Ingredient("1", "Apple", "kg", "Fruit", 2);
        recipe = new Recipe("r1");
        recipe.setName("Apple Pie");
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        shoppingList = new ShoppingList("owner@example.com", "Weekly Groceries");
        shoppingList.setListItems(ingredients);
        shoppingList.setEstimatedTotalCost(10.0);
        shoppingList.setRecipes(List.of(recipe));

        user = new User("John Doe", "john@example.com", "password123", LocalDateTime.now());
        user.addShoppingList(shoppingList);
        LoggedUserData.setLoggedInUser(user);
    }

    @Test
    void testSerialize() {
        Document doc = serializer.serialize(shoppingList);

        assertEquals("owner@example.com", doc.getString("listOwner"));
        assertEquals("Weekly Groceries", doc.getString("name"));
        assertEquals(1, doc.getList("groceries", Document.class).size());
        assertEquals(10.0, doc.getDouble("cost"));
        assertEquals(1, doc.getList("recipes", Document.class).size());
    }

    @Test
    void testDeserialize() {
        Document doc = serializer.serialize(shoppingList);
        ShoppingList deserializedList = serializer.deserialize(doc);

        assertEquals("owner@example.com", deserializedList.getListOwner());
        assertEquals("Weekly Groceries", deserializedList.getShoppingListName());
        assertEquals(1, deserializedList.getListItems().size());
        assertEquals(10.0, deserializedList.getEstimatedTotalCost());
        assertEquals(1, deserializedList.getRecipes().size());
    }
}
