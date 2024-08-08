package frameworks.data_access.serialization;

import entity.Recipe;
import entity.ShoppingList;
import entity.User;
import org.bson.Document;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UserSerializerTest {
    private final UserSerializer userSerializer = new UserSerializer();

    @Test
    void testSerialize() {
        // Create a sample User
        User user = new User("John Doe", "john@example.com", "password123", LocalDateTime.now());
        user.setShoppingLists(List.of(new ShoppingList("John Doe", "Weekly Groceries")));
        user.setRecipes(List.of(new Recipe("recipe1"), new Recipe("recipe2")));
        user.setRecentlyViewedRecipes(List.of(new Recipe("recipe3"), new Recipe("recipe4")));
        user.setPreference(Map.of("theme", "dark"));

        // Serialize the user
        Document document = userSerializer.serialize(user);

        // Verify the serialization
        assertEquals("John Doe", document.getString("userName"));
        assertEquals("john@example.com", document.getString("userEmail"));
        assertEquals("password123", document.getString("userPassword"));
        assertNotNull(document.getDate("createdAt"));
        assertNotNull(document.get("shoppingList"));
        assertNotNull(document.get("preference"));
        assertNotNull(document.get("recentlyViewedRecipes"));
        assertNotNull(document.get("recipes"));
    }

    @Test
    void testDeserialize() {
        // Create a sample Document
        Document document = new Document()
                .append("userName", "John Doe")
                .append("userEmail", "john@example.com")
                .append("userPassword", "password123")
                .append("createdAt", Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .append("shoppingList", List.of(new Document("listOwner", "John Doe").append("name", "Weekly Groceries")))
                .append("preference", Map.of("theme", "dark"))
                .append("recentlyViewedRecipes", List.of(new Document("id", "recipe3"), new Document("id", "recipe4")))
                .append("recipes", List.of(new Document("id", "recipe1"), new Document("id", "recipe2")));

        // Deserialize the document
        User user = userSerializer.deserialize(document);

        // Verify the deserialization
        assertEquals("John Doe", user.getUserName());
        assertEquals("john@example.com", user.getUserEmail());
        assertEquals("password123", user.getUserPassword());
        assertNotNull(user.getCreatedAt());
        assertEquals(1, user.getShoppingLists().size());
        assertEquals(2, user.getRecipes().size());
        assertEquals(2, user.getRecentlyViewedRecipes().size());
        assertEquals("dark", user.getPreference().get("theme"));
    }

    @Test
    void testSerializeEmptyUser() {
        // Create an empty User
        User user = new User();

        // Serialize the user
        Document document = userSerializer.serialize(user);

        // Verify the serialization
        assertNull(document.getString("userName"));
        assertNull(document.getString("userEmail"));
        assertNull(document.getString("userPassword"));
        assertNull(document.getDate("createdAt"));
        assertNull(document.get("shoppingList"));
        assertNull(document.get("preference"));
        assertNull(document.get("recentlyViewedRecipes"));
        assertNull(document.get("recipes"));
    }

    @Test
    void testDeserializeEmptyDocument() {
        // Create an empty Document
        Document document = new Document();

        // Deserialize the document
        User user = userSerializer.deserialize(document);

        // Verify the deserialization
        assertNull(user.getUserName());
        assertNull(user.getUserEmail());
        assertNull(user.getUserPassword());
        assertNull(user.getCreatedAt());
        assertNull(user.getShoppingLists());
        assertNull(user.getRecipes());
        assertNull(user.getRecentlyViewedRecipes());
        assertNull(user.getPreference());
    }
}
