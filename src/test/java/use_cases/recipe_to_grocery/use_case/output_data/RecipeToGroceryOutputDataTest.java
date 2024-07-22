package use_cases.recipe_to_grocery.use_case.output_data;

import entity.Ingredient;
import entity.Recipe;
import entity.ShoppingList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RecipeToGroceryOutputDataTest {

    RecipeToGroceryOutputData outputData;
    List<ShoppingList> shoppingLists;

    @BeforeEach
    void setUp() {
        ShoppingList shoppingList1 = Mockito.mock(ShoppingList.class);
        ShoppingList shoppingList2 = Mockito.mock(ShoppingList.class);

        Ingredient ingredient1 = Mockito.mock(Ingredient.class);
        Ingredient ingredient2 = Mockito.mock(Ingredient.class);
        Ingredient ingredient3 = Mockito.mock(Ingredient.class);
        Ingredient ingredient4 = Mockito.mock(Ingredient.class);

        when(shoppingList1.getShoppingListName()).thenReturn("thursday");
        when(shoppingList1.getListItems()).thenReturn(Arrays.asList(ingredient1, ingredient2));

        when(shoppingList1.getShoppingListName()).thenReturn("tuesday");
        when(shoppingList1.getListItems()).thenReturn(Arrays.asList(ingredient3, ingredient4));

        shoppingLists = Arrays.asList(shoppingList1, shoppingList2);

        outputData = new RecipeToGroceryOutputData(shoppingLists);
    }

    @Test
    void iterator() {

        Iterator<ShoppingList> iterator = outputData.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), shoppingLists.get(0));
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), shoppingLists.get(1));
        assertFalse(iterator.hasNext());
    }

    @Test
    void getRecipes() {
        assertEquals(outputData.getRecipes(), shoppingLists);
    }
}
