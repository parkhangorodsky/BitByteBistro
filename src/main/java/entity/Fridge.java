package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Fridge entity that contains a collection of ingredients.
 */
public class Fridge {

    private String userID; // The ID of the user who owns this fridge
    private List<Ingredient> ingredients; // A list of ingredients in the fridge

    /**
     * Constructs a Fridge object for a specific user.
     *
     * @param userID The ID of the user who owns this fridge.
     */
    public Fridge(String userID) {
        this.userID = userID;
        this.ingredients = new ArrayList<>();
    }

    // An empty constructor for serialization if needed
    public Fridge() {
        this.ingredients = new ArrayList<>();
    }

    public String getUserID() {
        return userID;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Adds an ingredient to the fridge.
     *
     * @param ingredient The ingredient to add.
     */
    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    /**
     * Removes an ingredient from the fridge by its ID.
     *
     * @param ingredientID The ID of the ingredient to remove.
     * @return true if the ingredient was successfully removed, false otherwise.
     */
    public boolean removeIngredient(String ingredientID) {
        return this.ingredients.removeIf(ingredient -> ingredient.getIngredientID().equals(ingredientID));
    }

    /**
     * Updates the quantity of an ingredient in the fridge.
     *
     * @param ingredientID The ID of the ingredient to update.
     * @param newQuantity  The new quantity of the ingredient.
     * @return true if the ingredient was found and updated, false otherwise.
     */
    public boolean updateIngredientQuantity(String ingredientID, float newQuantity) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getIngredientID().equals(ingredientID)) {
                ingredient.setQuantity(newQuantity);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Fridge{" +
                "userID='" + userID + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }
}
