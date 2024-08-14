package use_cases.core_functionality.strategy.expand;

import entity.Ingredient;
import entity.ShoppingList;

import java.util.List;

public interface ExpandStrategy {
    List<Ingredient> expand(ShoppingList shoppingList);
}