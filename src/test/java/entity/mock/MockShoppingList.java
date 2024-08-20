package entity.mock;

import entity.Ingredient;
import entity.Recipe;
import entity.ShoppingList;
import org.mockito.Mockito;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

public class MockShoppingList {
    public ShoppingList mock;
    private String listOwner;
    private String shoppingListName; // changed the name of this
    private Map<String, Ingredient> listItems;
    private Double estimatedTotalCost;
    private List<Recipe> recipes;

    public MockShoppingList() {
        mock = Mockito.mock(ShoppingList.class);
        when(mock.getListOwner()).thenReturn(listOwner);
        when(mock.getShoppingListName()).thenReturn(shoppingListName);
        when(mock.getListItemsAsMap()).thenReturn(listItems);
        when(mock.getEstimatedTotalCost()).thenReturn(estimatedTotalCost);
        when(mock.getRecipes()).thenReturn(recipes);
    }

    public MockShoppingList setListOwner(String listOwner) {
        this.listOwner = listOwner;
        return this;
    }

    public MockShoppingList setShoppingListName(String shoppingListName) {
        this.shoppingListName = shoppingListName;
        return this;
    }


    public MockShoppingList setListItems(Map<String, Ingredient> listItems) {
        this.listItems = listItems;
        return this;
    }

    public MockShoppingList setEstimatedTotalCost(Double estimatedTotalCost) {
        this.estimatedTotalCost = estimatedTotalCost;
        return this;
    }

    public MockShoppingList setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
        return this;
    }
}