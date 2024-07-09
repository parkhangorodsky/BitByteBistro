package entity;

import java.time.LocalDateTime;
import java.util.List;

public class ShoppingList {

    private String listOwner;
    private String shoppingListName; // changed the name of this
    private List<Grocery> listItems;
    private final LocalDateTime creationDate;
    private String listStatus;
    private Double estimatedTotalCost;

    // Tony 到此一游

    /**
     * Requires:
     * @param listOwner owner of the grocery list
     * @param shoppingListName name of shopping list - possibly the date but not necessarily
     * @param listItems list of items in the grocery list
     */
    public ShoppingList(String listOwner, String shoppingListName, List<Grocery> listItems) {
        this.listOwner = listOwner;
        this.shoppingListName = shoppingListName;
        this.listItems = listItems;
        this.creationDate = LocalDateTime.now();
        this.listStatus = "in progress";
        this.estimatedTotalCost = 0.00; // TODO: implement method to compute this
    }

    public String getListOwner() {
        return listOwner;
    }

    public void setListOwner(String listOwner) {
        this.listOwner = listOwner;
    }

    public String getShoppingListName() {
        return shoppingListName;
    }

    public void setShoppingListName(String shoppingListName) {
        this.shoppingListName = shoppingListName;
    }

    public List<Grocery> getListItems() {
        return listItems;
    }

    public void setListItems(List<Grocery> listItems) {
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
}