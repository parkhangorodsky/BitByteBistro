package use_cases.recipe_to_grocery.use_case.output_data;

import entity.ShoppingList;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Data class representing the output of converting recipes to grocery lists.
 * Provides access to a list of ShoppingList objects.
 */
public class RecipeToGroceryOutputData implements Iterable<ShoppingList> {

    private ArrayList<ShoppingList> shoppingLists;

    /**
     * Constructs a RecipeToGroceryOutputData object with the provided list of shopping lists.
     *
     * @param shoppingLists The list of ShoppingList objects representing the grocery lists.
     */
    public RecipeToGroceryOutputData(ArrayList<ShoppingList> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }

    /**
     * Retrieves the list of ShoppingList objects contained in this output data.
     *
     * @return The list of ShoppingList objects.
     */
    public ArrayList<ShoppingList> getShoppingLists() {
        return shoppingLists;
    }

    /**
     * Provides an iterator over the ShoppingList objects in this output data.
     *
     * @return An iterator over the ShoppingList objects.
     */
    @NotNull
    @Override
    public Iterator<ShoppingList> iterator() {
        return shoppingLists.iterator();
    }
}