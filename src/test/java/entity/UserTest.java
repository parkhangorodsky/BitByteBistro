package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;
    private Recipe recipe;
    private ShoppingList shoppingList;
    private LocalDateTime creationTime;

    @BeforeEach
    void setUp() {
        creationTime = LocalDateTime.now();
        user = new User("testUser", "testUser@example.com", "password123", creationTime);
        recipe = new Recipe("Test Recipe");
        shoppingList = new ShoppingList("testUser", "shoppingList");
    }

    @Test
    void testGetUserName() {
        assertEquals("testUser", user.getUserName());
    }

    @Test
    void testSetUserName() {
        user.setUserName("newUser");
        assertEquals("newUser", user.getUserName());
    }

    @Test
    void testGetUserEmail() {
        assertEquals("testUser@example.com", user.getUserEmail());
    }

    @Test
    void testSetUserEmail() {
        user.setUserEmail("newUser@example.com");
        assertEquals("newUser@example.com", user.getUserEmail());
    }

    @Test
    void testGetUserPassword() {
        assertEquals("password123", user.getUserPassword());
    }

    @Test
    void testSetUserPassword() {
        user.setUserPassword("newPassword");
        assertEquals("newPassword", user.getUserPassword());
    }

    @Test
    void testGetCreatedAt() {
        assertEquals(creationTime, user.getCreatedAt());
    }

    @Test
    void testSetCreatedAt() {
        LocalDateTime newTime = LocalDateTime.now().plusDays(1);
        user.setCreatedAt(newTime);
        assertEquals(newTime, user.getCreatedAt());
    }

    @Test
    void testGetRecipes() {
        assertTrue(user.getRecipes().isEmpty());
        user.addRecipe(recipe);
        assertEquals(1, user.getRecipes().size());
        assertEquals(recipe, user.getRecipes().get(0));
    }

    @Test
    void testSetRecipes() {
        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(recipe);
        user.setRecipes(recipeList);
        assertEquals(1, user.getRecipes().size());
        assertEquals(recipe, user.getRecipes().get(0));
    }

    @Test
    void testAddRecipe() {
        user.addRecipe(recipe);
        assertEquals(1, user.getRecipes().size());
        assertEquals(recipe, user.getRecipes().get(0));
    }

    @Test
    void testGetShoppingLists() {
        assertTrue(user.getShoppingLists().isEmpty());
        user.addShoppingList(shoppingList);
        assertEquals(1, user.getShoppingLists().size());
        assertEquals(shoppingList, user.getShoppingList(shoppingList.getShoppingListName()));
    }

    @Test
    void testSetShoppingLists() {
        Map<String, ShoppingList> shoppingListMap = new TreeMap<>();
        shoppingListMap.put("list 1", shoppingList);
        user.setShoppingLists(shoppingListMap);
        assertEquals(1, user.getShoppingLists().size());
        assertEquals(shoppingList, user.getShoppingList(shoppingList.getShoppingListName()));
    }

    @Test
    void testAddShoppingList() {
        user.addShoppingList(shoppingList);
        assertEquals(1, user.getShoppingLists().size());
        assertEquals(shoppingList, user.getShoppingList(shoppingList.getShoppingListName()));
    }

    @Test
    void testGetPreference() {
        assertEquals(1, user.getPreference().size());
        assertFalse((Boolean) user.getPreference().get("nightMode"));
    }

    @Test
    void testSetPreference() {
        Map<String, Object> preferences = new HashMap<>();
        preferences.put("nightMode", true);
        user.setPreference(preferences);
        assertEquals(1, user.getPreference().size());
        assertTrue((Boolean) user.getPreference().get("nightMode"));
    }

    @Test
    void testUpdatePreference() {
        user.updatePreference("nightMode", true);
        assertTrue((Boolean) user.getPreference().get("nightMode"));
    }

    @Test
    void testGetRecentlyViewedRecipes() {
        assertTrue(user.getRecentlyViewedRecipes().isEmpty());
        user.addRecentlyViewedRecipe(recipe);
        assertEquals(1, user.getRecentlyViewedRecipes().size());
        assertEquals(recipe, user.getRecentlyViewedRecipes().get(0));
    }

    @Test
    void testSetRecentlyViewedRecipes() {
        List<Recipe> recentRecipes = new ArrayList<>();
        recentRecipes.add(recipe);
        user.setRecentlyViewedRecipes(recentRecipes);
        assertEquals(1, user.getRecentlyViewedRecipes().size());
        assertEquals(recipe, user.getRecentlyViewedRecipes().get(0));
    }

    @Test
    void testAddRecentlyViewedRecipe() {
        Recipe recipe1 = new Recipe("Recipe1");
        Recipe recipe2 = new Recipe("Recipe2");
        Recipe recipe3 = new Recipe("Recipe3");
        Recipe recipe4 = new Recipe("Recipe4");
        Recipe recipe5 = new Recipe("Recipe5");
        Recipe recipe6 = new Recipe("Recipe6");

        recipe1.setName("1");
        recipe2.setName("2");
        recipe3.setName("3");
        recipe4.setName("4");
        recipe5.setName("5");
        recipe6.setName("6");



        user.addRecentlyViewedRecipe(recipe1);
        user.addRecentlyViewedRecipe(recipe2);
        user.addRecentlyViewedRecipe(recipe3);
        user.addRecentlyViewedRecipe(recipe4);
        user.addRecentlyViewedRecipe(recipe5);
        user.addRecentlyViewedRecipe(recipe6);

        assertEquals(5, user.getRecentlyViewedRecipes().size());
        assertFalse(user.getRecentlyViewedRecipes().contains(recipe1));
        assertEquals(recipe6, user.getRecentlyViewedRecipes().get(0));
    }

    @Test
    void testToString() {
        assertEquals("User{userName='testUser', userEmail='testUser@example.com'}", user.toString());
    }
}
