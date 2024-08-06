package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingList {

    private String listOwner;
    private String shoppingListName; // changed the name of this
    private List<Ingredient> listItems;
    private Double estimatedTotalCost;
    private List<Recipe> recipes;


    /**
     * Requires:
     * @param listOwner owner of the grocery list
     * @param shoppingListName name of shopping list - possibly the date but not necessarily
     */
    public ShoppingList(String listOwner, String shoppingListName) {
        this.listOwner = listOwner;
        this.shoppingListName = shoppingListName;
        this.listItems = new ArrayList<>();
        this.estimatedTotalCost = 0.00; // TODO: implement method to compute this
        this.recipes = new ArrayList<>();
    }

    public String getListOwner() {
        return listOwner;
    }

    public String getShoppingListName() {
        return shoppingListName;
    }

    public void setShoppingListName(String shoppingListName) {
        this.shoppingListName = shoppingListName;
    }

    public List<Ingredient> getListItems() {
        List<Ingredient> listItems = new ArrayList<>();
        for (Recipe recipe : this.recipes) {
            listItems.addAll(recipe.getIngredientList());
        }
        return listItems;
    }

    public void setListItems(List<Ingredient> listItems) {
        this.listItems = listItems;
    }

    public Double getEstimatedTotalCost() {
        return estimatedTotalCost;
    }

    public void setEstimatedTotalCost(Double estimatedTotalCost) {
        this.estimatedTotalCost = estimatedTotalCost;
    }

    public List<Recipe> getRecipes() {return recipes;}

    public void setRecipes(List<Recipe> recipes) {this.recipes = recipes;}

    public void addItem(Ingredient item) {this.listItems.add(item);}

    public void addRecipe(Recipe recipe) {
        this.recipes.add(recipe);
        for (Ingredient grocery : recipe.getIngredientList()) {
            if (this.listItems.contains(grocery)) {
                int index = this.listItems.indexOf(grocery);
                Ingredient item = this.listItems.get(index);
                float more = grocery.getQuantity();
                item.addIngredientQuantity(more);
            } else {
                this.listItems.add(grocery);
            }
        }
    }

    public void addRecipe(Recipe recipe) {
        this.recipes.add(recipe);

        // Create a map to store normalized ingredient names and their corresponding list items
        Map<String, Ingredient> normalizedIngredientMap = new HashMap<>();

        // Populate the map with existing ingredients from the shopping list
        for (Ingredient item : this.listItems) {
            String normalizedName = normalizeIngredientName(item.getName());
            normalizedIngredientMap.put(normalizedName, item);
        }

        // Process each ingredient in the recipe
        for (Ingredient grocery : recipe.getIngredientList()) {
            String normalizedGroceryName = normalizeIngredientName(grocery.getName());

            if (normalizedIngredientMap.containsKey(normalizedGroceryName)) {
                // Ingredient already in the shopping list, increase its quantity
                Ingredient item = normalizedIngredientMap.get(normalizedGroceryName);
                float more = grocery.getQuantity();
                item.addIngredientQuantity(more);
            } else {
                // New ingredient, add it to the shopping list and update the map
                this.listItems.add(grocery);
                normalizedIngredientMap.put(normalizedGroceryName, grocery);
            }
        }
    }

    private String normalizeIngredientName(String name) {
        // Convert to lowercase and replace hyphens with spaces
        return name.toLowerCase().replace("-", " ");
    }

}