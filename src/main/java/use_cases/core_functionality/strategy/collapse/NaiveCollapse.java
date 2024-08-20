package use_cases.core_functionality.strategy.collapse;

import entity.Ingredient;
import entity.ShoppingList;

import java.util.Map;

public class NaiveCollapse implements CollapseStrategy {
    @Override
    public void collapse(ShoppingList shoppingList, Ingredient ingredient) {
        Map<String, Ingredient> listItems = shoppingList.getListItemsAsMap();
        String groceryName = ingredient.getIngredientName();

        if (listItems.containsKey(groceryName)) {
            Ingredient item = listItems.get(groceryName);
            float more = ingredient.getQuantity();
            item.addIngredientQuantity(more);
        } else {
            listItems.put(groceryName, ingredient);
        }
    }
}
