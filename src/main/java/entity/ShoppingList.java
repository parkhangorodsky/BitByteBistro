package entity;

import use_cases.core_functionality.strategy.normalize.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingList {

    private final String listOwner;
    private String shoppingListName; // changed the name of this
    private final Map<String, Ingredient> listItems;
    private Double estimatedTotalCost;
    private List<Recipe> recipes;
    private final NormalizeStrategy normalizeStrategy = new StringNormalize();

    /**
     * Requires:
     * @param listOwner owner of the grocery list
     * @param shoppingListName name of shopping list - possibly the date but not necessarily
     */
    public ShoppingList(String listOwner, String shoppingListName) {
        this.listOwner = listOwner;
        this.shoppingListName = shoppingListName;
        this.listItems = new HashMap<>();
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
        return new ArrayList<>(this.listItems.values());
    }

    public Map<String, Ingredient> getListItemsAsMap() {return this.listItems;}

    public void setListItems(List<Ingredient> listItems) {
        for (Ingredient ingredient : listItems) {
            String normalizedName = normalizeStrategy.normalize(ingredient.getIngredientName());
            this.listItems.put(normalizedName, ingredient);
        }
    }

    public Double getEstimatedTotalCost() {
        return estimatedTotalCost;
    }

    public void setEstimatedTotalCost(Double estimatedTotalCost) {
        this.estimatedTotalCost = estimatedTotalCost;
    }

    public List<Recipe> getRecipes() {return recipes;}

    public void setRecipes(List<Recipe> recipes) {this.recipes = recipes;}
}
