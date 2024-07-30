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

    // An empty constructor for auto serialization
    public Ingredient() {}

    public String getIngredientID() {
        return ingredientID;
    }
    public String getIngredientName() {
        return ingredientName;
    }
    public String getQuantityUnit() {return quantityUnit;}
    public String getCategory() {
        return category;
    }
    public float getQuantity() {
        return quantity;
    }

    public void setIngredientID(String ingredientID) {this.ingredientID = ingredientID;}
    public void setIngredientName(String ingredientName) {this.ingredientName = ingredientName;}
    public void setQuantityUnit(String quantityUnit) {this.quantityUnit = quantityUnit;}
    public void setCategory(String category) {this.category = category;}
    public void setQuantity(float quantity) {this.quantity = quantity;}

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
        return this.getQuantity() + " " + this.getQuantityUnit() + " " + this.getIngredientName();
    }
}
