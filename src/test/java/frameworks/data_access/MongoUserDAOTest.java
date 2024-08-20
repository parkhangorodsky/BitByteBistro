package frameworks.data_access;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import entity.Nutrition;
import entity.Recipe;
import entity.ShoppingList;
import entity.User;
import frameworks.data_access.MongoUserDAO;
import frameworks.data_access.serialization.IngredientSerializer;
import frameworks.data_access.serialization.RecipeSerializer;
import frameworks.data_access.serialization.ShoppingListSerializer;
import frameworks.data_access.serialization.UserSerializer;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MongoUserDAOTest {

    private MongoDatabase mockDatabase;

    private MongoCollection<Document> mockCollection;

    private MongoUserDAO mongoUserDAO;

    private User user;
    private Document userDocument;

    @BeforeEach
    void setUp() {
        mockCollection = mock(MongoCollection.class);
        when(mockCollection.insertOne(any(Document.class))).thenReturn(null);

        mockDatabase = mock(MongoDatabase.class);
        when(mockDatabase.getCollection("users", Document.class)).thenReturn(mockCollection);

        mongoUserDAO = new MongoUserDAO(mockDatabase);

        user = new User("John Doe", "john@example.com", "password123", LocalDateTime.now());
        UserSerializer serializer = new UserSerializer();
        userDocument = serializer.serialize(user);

    }

    @Test
    void testAddUser() {// Create a mock or real User instance


        mongoUserDAO.addUser(user);
        verify(mockCollection).insertOne(userDocument);
    }

    @Test
    void testDeleteUser() {
        User user = new User(); // Create a mock or real User instance
        user.setUserEmail("john@example.com");

        mongoUserDAO.deleteUser(user);

        verify(mockCollection).deleteOne(Filters.eq("userEmail", "john@example.com"));
    }

    @Test
    void testGetUserByEmail() {
        FindIterable mockFindIterable = mock(FindIterable.class); // Mock or create the Document object
        when(mockCollection.find(Filters.eq("userEmail", "john@example.com"))).thenReturn(mockFindIterable);
        when(mockCollection.find(Filters.eq("userEmail", "john@example.com")).first()).thenReturn(userDocument);

        User result = mongoUserDAO.getUserByEmail("john@example.com");

        verify(mockCollection, times(2)).find(Filters.eq("userEmail", "john@example.com"));
        assertEquals(user.getUserEmail(), result.getUserEmail());
        assertEquals(user.getUserName(), result.getUserName());
    }

    @Test
    void testAddRecipe() {
        Recipe recipe = new Recipe();
        recipe.setIngredientList(new ArrayList<>());// Create a mock or real Recipe instance
        recipe.setNutritionMap(new HashMap<String, Nutrition>());
        RecipeSerializer recipeSerializer = new RecipeSerializer();
        Document recipeDoc = recipeSerializer.serialize(recipe); // Mock or create the Document object

        mongoUserDAO.addRecipe(user, recipe);

        verify(mockCollection).updateOne(
                Filters.eq("userEmail", "john@example.com"),
                Updates.addToSet("recipes", recipeDoc)
        );
    }

    @Test
    void testAddShoppingList() {

        ShoppingList shoppingList = new ShoppingList(user.getUserName(), "s1");// Create a mock or real ShoppingList instance
        ShoppingListSerializer shoppingListSerializer = new ShoppingListSerializer();
        Document shoppingListDoc = shoppingListSerializer.serialize(shoppingList); // Mock or create the Document object

        mongoUserDAO.addShoppingList(user, shoppingList);

        verify(mockCollection).updateOne(
                Filters.eq("userEmail", "john@example.com"),
                Updates.set("shoppingLists." + shoppingList.getShoppingListName(), shoppingListDoc)
        );
    }

    @Test
    void testAddRecipeToShoppingList() {
        Recipe recipe = new Recipe();
        recipe.setIngredientList(new ArrayList<>());// Create a mock or real Recipe instance
        recipe.setNutritionMap(new HashMap<String, Nutrition>());
        RecipeSerializer recipeSerializer = new RecipeSerializer();
        Document recipeDoc = recipeSerializer.serialize(recipe);

        ShoppingList shoppingList = new ShoppingList(user.getUserName(), "myShoppingList");// Create a mock or real ShoppingList instance
        ShoppingListSerializer shoppingListSerializer = new ShoppingListSerializer();

        List<Document> ingredientListDoc = new ArrayList<>(); // Mock or create the Document object

        mongoUserDAO.addRecipeToShoppingList(user, shoppingList, recipe);

        verify(mockCollection).updateOne(
                Filters.eq("userEmail", "john@example.com"),
                Updates.addToSet("shoppingLists.myShoppingList.recipes", recipeDoc)
        );
        verify(mockCollection).updateOne(
                Filters.eq("userEmail", "john@example.com"),
                Updates.set("shoppingLists.myShoppingList.listItems", ingredientListDoc)
        );
    }

    @Test
    void testUpdateRecentlyViewedRecipes() {
        Recipe recipe = new Recipe();
        recipe.setIngredientList(new ArrayList<>());
        recipe.setNutritionMap(new HashMap<String, Nutrition>());

        RecipeSerializer recipeSerializer = new RecipeSerializer();

        user.setRecentlyViewedRecipes(List.of(recipe));

        List<Document> recipeListDoc = recipeSerializer.serializeRecipeList(user.getRecentlyViewedRecipes()); // Mock or create the Document object

        mongoUserDAO.updateRecentlyViewedRecipes(user);

        verify(mockCollection).updateOne(
                Filters.eq("userEmail", "john@example.com"),
                Updates.set("recentlyViewedRecipes", recipeListDoc)
        );
    }

    @Test
    void testUpdateUserPreference() {
        User user = new User(); // Create a mock or real User instance
        user.setUserEmail("john@example.com");
        String fieldName = "preferenceField";
        Object value = "preferenceValue";

        mongoUserDAO.updateUserPreference(user, fieldName, value);

        verify(mockCollection).updateOne(
                Filters.eq("userEmail", "john@example.com"),
                Updates.set("preference." + fieldName, value)
        );
    }
}
