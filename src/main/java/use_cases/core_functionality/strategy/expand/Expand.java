package use_cases.core_functionality.strategy.expand;

import entity.Ingredient;
import entity.ShoppingList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Expand implements ExpandStrategy{
    public List<Ingredient> expand(ShoppingList shoppingList) {
        List<Ingredient> listItems = new ArrayList<>();
        for (HashMap.Entry<String, Ingredient> item : shoppingList.getListItemsAsMap().entrySet()) {
            listItems.add(item.getValue());
        }
        return listItems;
    }
}
