package use_cases.core_functionality.strategy.collapse;

import entity.Ingredient;
import entity.ShoppingList;

import java.util.Map;

public class NormalizedCollapse implements CollapseStrategy{

    private String normalizeIngredientName(String name) {
        return name.toLowerCase().replace("-", " ").replace(" ", "");
    }

    @Override
    public void collapse(ShoppingList shoppingList,  Ingredient ingredient) {
        Map<String, Ingredient> listItems = shoppingList.getListItemsAsMap();
        String normalizedGroceryName = normalizeIngredientName(ingredient.getIngredientName());

        if (listItems.containsKey(normalizedGroceryName)) {
            Ingredient item = listItems.get(normalizedGroceryName);
            float more = ingredient.getQuantity();
            item.addIngredientQuantity(more);
        } else {
            listItems.put(normalizedGroceryName, ingredient);
        }
    }

    @Override
    public String normalize(String unnormalized) {
        return normalizeIngredientName(unnormalized);
    }
}
