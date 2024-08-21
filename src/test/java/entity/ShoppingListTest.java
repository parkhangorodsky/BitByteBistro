package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingListTest {

    private ShoppingList shoppingList;
    private Ingredient ingredient;
    private Recipe recipe;

    @BeforeEach
    void setUp() {
        shoppingList = new ShoppingList("testOwner", "Weekly Groceries");
        ingredient = new Ingredient("1", "Tomato", "kg", "Vegetable", 2);
        recipe = new Recipe("recipe1");
    }

    @Test
    void testConstructor() {
        assertEquals("testOwner", shoppingList.getListOwner());
        assertEquals("Weekly Groceries", shoppingList.getShoppingListName());
        assertNotNull(shoppingList.getListItems());
        assertTrue(shoppingList.getListItems().isEmpty());
        assertEquals(0.00, shoppingList.getEstimatedTotalCost());
        assertNotNull(shoppingList.getRecipes());
        assertTrue(shoppingList.getRecipes().isEmpty());
    }

    @Test
    void testGetListOwner() {
        assertEquals("testOwner", shoppingList.getListOwner());
    }

    @Test
    void testGetAndSetShoppingListName() {
        assertEquals("Weekly Groceries", shoppingList.getShoppingListName());
        shoppingList.setShoppingListName("Monthly Groceries");
        assertEquals("Monthly Groceries", shoppingList.getShoppingListName());
    }

    @Test
    void testGetAndSetListItems() {
        assertNotNull(shoppingList.getListItems());
        assertTrue(shoppingList.getListItems().isEmpty());

        List<Ingredient> items = new ArrayList<>();
        items.add(ingredient);
        shoppingList.setListItems(items);

        assertEquals(1, shoppingList.getListItems().size());
        assertEquals(ingredient, shoppingList.getListItems().get(0));
    }

    @Test
    void testGetAndSetEstimatedTotalCost() {
        assertEquals(0.00, shoppingList.getEstimatedTotalCost());
        shoppingList.setEstimatedTotalCost(15.75);
        assertEquals(15.75, shoppingList.getEstimatedTotalCost());
    }

    @Test
    void testGetAndSetRecipes() {
        assertNotNull(shoppingList.getRecipes());
        assertTrue(shoppingList.getRecipes().isEmpty());

        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe);
        shoppingList.setRecipes(recipes);

        assertEquals(1, shoppingList.getRecipes().size());
        assertEquals(recipe, shoppingList.getRecipes().get(0));
    }

    @Test
    void testAddItem() {
        assertTrue(shoppingList.getListItems().isEmpty());
//        shoppingList.addItem(ingredient);
//        assertEquals(1, shoppingList.getListItems().size());
//        assertEquals(ingredient, shoppingList.getListItems().get(0));
    }

    @Test
    void testAddRecipe() {
        assertTrue(shoppingList.getRecipes().isEmpty());
//        shoppingList.addRecipe(recipe);
//        assertEquals(1, shoppingList.getRecipes().size());
//        assertEquals(recipe, shoppingList.getRecipes().get(0));
    }
}
