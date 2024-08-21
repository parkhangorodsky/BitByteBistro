package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NutritionTest {

    @Test
    void testConstructorWithPercentage() {
        Nutrition nutrition = new Nutrition("Protein", 25.0f, "g", 50.0f);
        assertEquals("Protein", nutrition.getLabel());
        assertEquals(25.0f, nutrition.getQuantity());
        assertEquals("g", nutrition.getUnit());
        assertEquals(50.0f, nutrition.getPercentage());
    }

    @Test
    void testConstructorWithoutPercentage() {
        Nutrition nutrition = new Nutrition("Carbohydrates", 100.0f, "g");
        assertEquals("Carbohydrates", nutrition.getLabel());
        assertEquals(100.0f, nutrition.getQuantity());
        assertEquals("g", nutrition.getUnit());
        assertNull(nutrition.getPercentage());
    }

    @Test
    void testDefaultConstructor() {
        Nutrition nutrition = new Nutrition();
        assertEquals("", nutrition.getLabel());
        assertEquals(0.0f, nutrition.getQuantity());
        assertEquals("", nutrition.getUnit());
        assertNull(nutrition.getPercentage());
    }

    @Test
    void testToStringWithPercentage() {
        Nutrition nutrition = new Nutrition("Protein", 25.0f, "g", 50.0f);
        assertEquals("Protein: 25.0 g", nutrition.toString());
    }

    @Test
    void testToStringWithoutPercentage() {
        Nutrition nutrition = new Nutrition("Carbohydrates", 100.0f, "g");
        assertEquals("Carbohydrates: 100.0 g", nutrition.toString());
    }

    @Test
    void testGetLabel() {
        Nutrition nutrition = new Nutrition("Fat", 70.0f, "g", 30.0f);
        assertEquals("Fat", nutrition.getLabel());
    }

    @Test
    void testGetQuantity() {
        Nutrition nutrition = new Nutrition("Fiber", 30.0f, "g", 10.0f);
        assertEquals(30.0f, nutrition.getQuantity());
    }

    @Test
    void testGetUnit() {
        Nutrition nutrition = new Nutrition("Vitamin C", 90.0f, "mg", 100.0f);
        assertEquals("mg", nutrition.getUnit());
    }

    @Test
    void testGetPercentage() {
        Nutrition nutrition = new Nutrition("Iron", 18.0f, "mg", 100.0f);
        assertEquals(100.0f, nutrition.getPercentage());
    }

    @Test
    void testGetPercentageNull() {
        Nutrition nutrition = new Nutrition("Sugar", 25.0f, "g");
        assertNull(nutrition.getPercentage());
    }
}
