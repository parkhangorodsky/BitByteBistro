package use_cases.core_functionality.strategy.collapse;

import entity.Ingredient;
import entity.ShoppingList;

public interface CollapseStrategy {
    void collapse(ShoppingList shoppingList, Ingredient ingredient);
    String normalize(String unnormalized);
}
