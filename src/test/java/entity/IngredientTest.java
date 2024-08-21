package entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

    @Test
    void testConstructorWithDefaultQuantityUnit() {
        Ingredient ingredient = new Ingredient("1", "Tomato", "<unit>", "Vegetable", 5.0f);
        assertEquals("1", ingredient.getIngredientID());
        assertEquals("Tomato", ingredient.getIngredientName());
        assertEquals("whole", ingredient.getQuantityUnit());
        assertEquals("Vegetable", ingredient.getCategory());
        assertEquals(5.0f, ingredient.getQuantity());
    }

    @Test
    void testConstructorWithProvidedQuantityUnit() {
        Ingredient ingredient = new Ingredient("2", "Sugar", "grams", "Sweetener", 200.0f);
        assertEquals("2", ingredient.getIngredientID());
        assertEquals("Sugar", ingredient.getIngredientName());
        assertEquals("grams", ingredient.getQuantityUnit());
        assertEquals("Sweetener", ingredient.getCategory());
        assertEquals(200.0f, ingredient.getQuantity());
    }

    @Test
    void testEmptyConstructor() {
        Ingredient ingredient = new Ingredient();
        assertNull(ingredient.getIngredientID());
        assertNull(ingredient.getIngredientName());
        assertNull(ingredient.getQuantityUnit());
        assertNull(ingredient.getCategory());
        assertEquals(0.0f, ingredient.getQuantity());
    }

    @Test
    void testGettersAndSetters() {
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientID("3");
        ingredient.setIngredientName("Salt");
        ingredient.setQuantityUnit("teaspoon");
        ingredient.setCategory("Seasoning");
        ingredient.setQuantity(2.0f);

        assertEquals("3", ingredient.getIngredientID());
        assertEquals("Salt", ingredient.getIngredientName());
        assertEquals("teaspoon", ingredient.getQuantityUnit());
        assertEquals("Seasoning", ingredient.getCategory());
        assertEquals(2.0f, ingredient.getQuantity());
    }

    @Test
    void testAddIngredientQuantity() {
        Ingredient ingredient = new Ingredient("4", "Butter", "tablespoon", "Dairy", 3.0f);
        ingredient.addIngredientQuantity(2.0f);
        assertEquals(5.0f, ingredient.getQuantity());
    }

    @Test
    void testToString() {
        Ingredient ingredient = new Ingredient("5", "Flour", "cups", "Grains", 1.5f);
        assertEquals("1.5 cups Flour", ingredient.toString());
    }
}
