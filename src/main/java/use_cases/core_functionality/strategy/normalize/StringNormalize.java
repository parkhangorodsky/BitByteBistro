package use_cases.core_functionality.strategy.normalize;

import entity.Ingredient;
import entity.ShoppingList;
import use_cases.core_functionality.strategy.collapse.CollapseStrategy;

import java.util.Map;

public class StringNormalize implements NormalizeStrategy {

    private String normalizeIngredientName(String name) {
        return name.toLowerCase().replace("-", " ").replace(" ", "");
    }

    @Override
    public String normalize(String unnormalized) {
        return normalizeIngredientName(unnormalized);
    }
}

