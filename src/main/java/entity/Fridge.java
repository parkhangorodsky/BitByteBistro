package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;

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
        String key = ingredient.getIngredientName().toLowerCase() + ingredient.getQuantityUnit().toLowerCase().trim();
        boolean found = false;

        for (Ingredient existingIngredient : this.ingredients) {
            String existingKey = existingIngredient.getIngredientName().toLowerCase() + existingIngredient.getQuantityUnit().toLowerCase().trim();
            if (existingKey.equals(key)) {
                existingIngredient.setQuantity(existingIngredient.getQuantity() + ingredient.getQuantity());
                found = true;
                break;
            }
        }

        if (!found) {
            this.ingredients.add(ingredient);
        }

        // Debug statement to check the ingredient being added
        System.out.println("Fridge: Added ingredient: " + ingredient);
        System.out.println("Fridge: Current ingredients: " + this.ingredients);
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

    public List<Ingredient> getAggregatedFridgeContents() {
        Map<String, Ingredient> aggregatedFridge = new LinkedHashMap<>();
        System.out.println("Aggregating fridge contents.");
        for (Ingredient item : this.ingredients) {
            String key = item.getIngredientName().toLowerCase() + "_" + item.getQuantityUnit().toLowerCase().trim();
            if (aggregatedFridge.containsKey(key)) {
                Ingredient aggregatedItem = aggregatedFridge.get(key);
                aggregatedItem.setQuantity(aggregatedItem.getQuantity() + item.getQuantity());
            } else {
                aggregatedFridge.put(key, new Ingredient(
                        item.getIngredientID(),
                        item.getIngredientName(),
                        item.getQuantityUnit(),
                        item.getCategory(),
                        item.getQuantity()
                ));
            }
        }
        List<Ingredient> aggregatedContents = new ArrayList<>(aggregatedFridge.values());
        System.out.println("Finished aggregating fridge contents.");
        System.out.println("Aggregated fridge contents: " + aggregatedContents);
        return aggregatedContents;
    }

    public Ingredient getIngredientByNameAndUnit(String ingredientName, String unit) {
        return ingredients.stream()
                .filter(ingredient -> ingredient.getIngredientName().equalsIgnoreCase(ingredientName)
                        && ingredient.getQuantityUnit().equalsIgnoreCase(unit))
                .findFirst()
                .orElse(null);
    }

    public boolean updateIngredientQuantityByNameAndUnit(String ingredientName, String unit, float delta) {
        Ingredient ingredient = getIngredientByNameAndUnit(ingredientName, unit);
        if (ingredient != null) {
            float newQuantity = ingredient.getQuantity() + delta;
            if (newQuantity <= 0) {
                return removeIngredient(ingredient.getIngredientID());
            } else {
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
