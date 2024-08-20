package use_cases.core_functionality.strategy.collapse;

import entity.Ingredient;
import entity.ShoppingList;
import use_cases.core_functionality.strategy.normalize.*;

import java.util.Map;

public class NormalizedCollapse implements CollapseStrategy {
    NormalizeStrategy normalizeStrategy = new StringNormalize();

    @Override
    public void collapse(ShoppingList shoppingList,  Ingredient ingredient) {
        Map<String, Ingredient> listItems = shoppingList.getListItemsAsMap();
        String normalizedGroceryName = normalizeStrategy.normalize(ingredient.getIngredientName());

        if (listItems.containsKey(normalizedGroceryName)) {
            Ingredient item = listItems.get(normalizedGroceryName);
            double more = ingredient.getQuantity();
            item.addIngredientQuantity(more);
        } else {
            listItems.put(normalizedGroceryName, ingredient);
        }
    }
}
