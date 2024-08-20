package entity;

import use_cases.core_functionality.strategy.CollapseStrategy;
import use_cases.core_functionality.strategy.NormalizedCollapse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingList {

    private String listOwner;
    private String shoppingListName; // changed the name of this
    private Map<String, Ingredient> listItems;
    private Double estimatedTotalCost;
    private List<Recipe> recipes;
    private CollapseStrategy collapseStrategy = new NormalizedCollapse();

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
        List<Ingredient> listItems = new ArrayList<>();
        for (HashMap.Entry<String, Ingredient> item : this.listItems.entrySet()) {
            listItems.add(item.getValue());
        }
        return listItems;
    }

    public Map<String, Ingredient> getListItemsAsMap() {return this.listItems;}

    public void setListItems(List<Ingredient> listItems) {
        for (Ingredient ingredient : listItems) {
            String normalizedName = collapseStrategy.normalize(ingredient.getIngredientName());
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

    public void addItem(Ingredient grocery) {
        collapseStrategy.collapse(this, grocery);
    }
    // still need changes in case already in

    public void addRecipe(Recipe recipe) {
        if (!this.recipes.contains(recipe)) {
            this.recipes.add(recipe);
        }
        for (Ingredient grocery : recipe.getIngredientList()) {
            this.addItem(grocery);
        }
    }



}
