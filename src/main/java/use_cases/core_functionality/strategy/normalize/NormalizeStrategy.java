package use_cases.core_functionality.strategy.normalize;

import entity.Ingredient;
import entity.ShoppingList;


public interface NormalizeStrategy {
    String normalize(String unnormalized);
}
