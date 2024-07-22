package use_cases.recipe_to_grocery.use_case.interactor;

import entity.Ingredient;
import entity.Recipe;
import entity.ShoppingList;
import entity.User;
import entity.mock.MockIngredient;
import frameworks.api.RecipeAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_cases.recipe_to_grocery.interface_adapter.presenter.RecipeToGroceryPresenter;
import use_cases.recipe_to_grocery.use_case.input_data.RecipeToGroceryInputData;
import use_cases.recipe_to_grocery.use_case.output_data.RecipeToGroceryOutputData;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RecipeToGroceryInteractorTest {
    RecipeAPI mockShoppingListAPI;
    RecipeToGroceryPresenter mockShoppingListToGroceryPresenter;
    RecipeToGroceryInteractor recipeToGroceryInteractor;

    @BeforeEach
    void setUp() {
        mockShoppingListAPI = Mockito.mock(RecipeAPI.class);
        mockShoppingListToGroceryPresenter = Mockito.mock(RecipeToGroceryPresenter.class);
        recipeToGroceryInteractor = new RecipeToGroceryInteractor(mockShoppingListToGroceryPresenter, mockShoppingListAPI);
    }

    @Test
    void execute() {
        // Prepare mock data
        ShoppingList mockShoppingList = Mockito.mock(ShoppingList.class);
        String name = "thursday";

        Ingredient mockIngredient1 = new MockIngredient().mock;
        Ingredient mockIngredient2 = new MockIngredient().mock;
        List<Ingredient> ingredient = Arrays.asList(mockIngredient1, mockIngredient2);
        
        User user = Mockito.mock(User.class);

        when(mockShoppingList.getShoppingListName()).thenReturn(name);
        when(mockShoppingList.getListItems()).thenReturn(ingredient);
        when(mockShoppingList.getListOwner()).thenReturn(name);
        
        RecipeToGroceryInputData inputData = new RecipeToGroceryInputData(user);

        // Execute the method under test
        recipeToGroceryInteractor.execute(inputData);

        // Capture the output data passed to the presenter
        ArgumentCaptor<RecipeToGroceryOutputData> argumentCaptor = ArgumentCaptor.forClass(RecipeToGroceryOutputData.class);
        verify(mockShoppingListToGroceryPresenter, times(1)).prepareSuccessView(argumentCaptor.capture());

        // Verify the data
        RecipeToGroceryOutputData capturedOutputData = argumentCaptor.getValue();
        List<ShoppingList> shoppingLists = capturedOutputData.getRecipes();

        // Assertions
        assertEquals(1, shoppingLists.size());
        ShoppingList capturedShoppingList = shoppingLists.getFirst();
        assertEquals("thursday", capturedShoppingList.getShoppingListName());
        assertEquals(2, capturedShoppingList.getListItems().size());
        assertEquals(mockIngredient1, capturedShoppingList.getListItems().get(0));
        assertEquals(mockIngredient2, capturedShoppingList.getListItems().get(1));

        // Verify interactions with the mockShoppingList
        verify(mockShoppingList, times(1)).getShoppingListName();
        verify(mockShoppingList, times(3)).getListItems();
    }


}
