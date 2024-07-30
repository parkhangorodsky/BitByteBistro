package entity;

import java.util.Objects;

/**
 * Represents an Ingredient entity with attributes such as ID, name, quantity unit, category, and quantity.
 */
public class Ingredient {

    private String ingredientID;     // Unique identifier for the ingredient
    private String ingredientName;   // Name of the ingredient
    private String quantityUnit;     // Unit of measurement for the quantity (e.g., grams, pieces)
    private String category;         // Category or type of the ingredient
    private float quantity;          // Quantity of the ingredient

    /**
     * Constructs an Ingredient object with the specified attributes.
     *
     * @param ingredientID   The unique identifier of the ingredient.
     * @param ingredientName The name of the ingredient.
     * @param quantityUnit   The unit of measurement for the quantity.
     * @param category       The category or type of the ingredient.
     * @param quantity       The initial quantity of the ingredient.
     */
    public Ingredient(String ingredientID, String ingredientName, String quantityUnit, String category, float quantity) {
        this.ingredientID = ingredientID;
        this.ingredientName = ingredientName;
        // Ensure default quantity unit if provided unit is "<unit>"
        if (Objects.equals(quantityUnit, "<unit>")) {
            this.quantityUnit = "whole";
        } else {
            this.quantityUnit = quantityUnit;
        }
        this.category = category;
        this.quantity = quantity;
    }


    public String getIngredientID() {
        return ingredientID;
    }

    /**
     * Retrieves the name of the ingredient.
     *
     * @return The ingredient name.
     */
    public String getIngredientName() {
        return ingredientName;
    }

    /**
     * Retrieves the unit of measurement for the quantity.
     *
     * @return The quantity unit.
     */
    public String getIngredientMeasure() {
        return quantityUnit;
    }

    /**
     * Retrieves the category or type of the ingredient.
     *
     * @return The ingredient category.
     */
    public String getIngredientCategory() {
        return category;
    }

    /**
     * Retrieves the current quantity of the ingredient.
     *
     * @return The ingredient quantity.
     */
    public float getIngredientQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the ingredient to the specified value.
     *
     * @param quantity The new quantity to set.
     */
    public void setIngredientQuantity(float quantity) {
        this.quantity = quantity;
    }

    /**
     * Increases the quantity of the ingredient by the specified amount.
     *
     * @param quantity The quantity to add.
     */
    public void addIngredientQuantity(float quantity) {
        this.quantity += quantity;
    }

    /**
     * Returns a string representation of the Ingredient object.
     *
     * @return A string representation containing quantity, unit of measure, and name of the ingredient.
     */
    @Override
    public String toString() {
        return this.getIngredientQuantity() + " " + this.getIngredientMeasure() + " " + this.getIngredientName();
    }
}
