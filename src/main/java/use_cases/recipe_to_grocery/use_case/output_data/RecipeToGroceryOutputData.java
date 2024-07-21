package use_cases.recipe_to_grocery.use_case.output_data;

import entity.ShoppingList;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

public class RecipeToGroceryOutputData implements Iterable<ShoppingList> {
    private List<ShoppingList> shoppingLists;

    public RecipeToGroceryOutputData(List<ShoppingList> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }
    public List<ShoppingList> getRecipes() {
        return shoppingLists;
    }

    @NotNull
    @Override
    public Iterator<ShoppingList> iterator() {
        return shoppingLists.iterator();
    }
}

