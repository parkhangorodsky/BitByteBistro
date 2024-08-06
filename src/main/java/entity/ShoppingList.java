package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public void addRecipe(Recipe recipe) {this.recipes.add(recipe);}

}