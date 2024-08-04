package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ShoppingList {

    private User listOwner;
    private String shoppingListName; // changed the name of this
    private List<Ingredient> listItems;
    private final LocalDateTime creationDate;
    private String listStatus;
    private Double estimatedTotalCost;
    private List<Recipe> recipes;


    /**
     * Requires:
     * @param listOwner owner of the grocery list
     * @param shoppingListName name of shopping list - possibly the date but not necessarily
     * @param listItems list of items in the grocery list
     */
    public ShoppingList(User listOwner, String shoppingListName, List<Ingredient> listItems) {
        this.listOwner = listOwner;
        this.shoppingListName = shoppingListName;
        this.listItems = listItems;
        this.creationDate = LocalDateTime.now();
        this.listStatus = "in progress";
        this.estimatedTotalCost = 0.00; // TODO: implement method to compute this
        this.recipes = new ArrayList<>();
    }

    public User getListOwner() {
        return listOwner;
    }

    public void setListOwner(User listOwner) {
        this.listOwner = listOwner;
    }

    public String getShoppingListName() {
        return shoppingListName;
    }

    public void setShoppingListName(String shoppingListName) {
        this.shoppingListName = shoppingListName;
    }

    public List<Ingredient> getListItems() {
        return listItems;
    }

    public void setListItems(List<Ingredient> listItems) {
        this.listItems = listItems;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    // no setCreationDate since it's final and not changeable

    public String getListStatus() {
        return listStatus;
    }

    public void setListStatus(String listStatus) {
        this.listStatus = listStatus;
    }

    public Double getEstimatedTotalCost() {
        return estimatedTotalCost;
    }

    public void setEstimatedTotalCost(Double estimatedTotalCost) {
        this.estimatedTotalCost = estimatedTotalCost;
    }

    public List<Recipe> getRecipes() {return recipes;}
    public void addRecipe(Recipe recipe) {this.recipes.add(recipe);}

}