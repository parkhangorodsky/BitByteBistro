package use_cases.core_functionality;

import app.local.LoggedUserData;
import entity.Ingredient;
import entity.Recipe;
import entity.ShoppingList;
import entity.User;
import frameworks.data_access.UserDataAccessInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the CoreFunctionalityInteractor class.
 */
public class CoreFunctionalityInteractorTest {

    private CoreFunctionalityInteractor interactor;
    private CoreFunctionalityPresenter presenter;
    private UserDataAccessInterface userDAO;
    private User user;
    private ShoppingList shoppingList;
    private Recipe recipe;
    private PropertyChangeFirer parentModel;

    @BeforeEach
    void setUp() {
        presenter = mock(CoreFunctionalityPresenter.class);
        userDAO = mock(UserDataAccessInterface.class);
        interactor = new CoreFunctionalityInteractor(presenter, userDAO);

        user = new User("testUser", "test@example.com", "password", null);
        shoppingList = new ShoppingList( "Weekly Groceries", "Shopping List");
        recipe = new Recipe("Test Recipe");
        parentModel = mock(PropertyChangeFirer.class);

        LoggedUserData.setLoggedInUser(user);
    }

    /**
     * Tests the execution of adding a recipe to the grocery list.
     */
    @Test
    void testExecute() {
        // Arrange
        CoreFunctionalityInputData inputData = new CoreFunctionalityInputData(recipe, shoppingList, parentModel);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("id_1", "Tomato", "pcs", "vegetable", 2));
        recipe.setIngredientList(ingredients);

        // Act
        interactor.execute(inputData);

        // Assert
        ArgumentCaptor<CoreFunctionalityOutputData> outputDataCaptor = ArgumentCaptor.forClass(CoreFunctionalityOutputData.class);
        verify(presenter).prepareSuccessView(outputDataCaptor.capture());
        ShoppingList updatedShoppingList = outputDataCaptor.getValue().getShoppingList();
        assertEquals(1, updatedShoppingList.getListItems().size());
        assertEquals(ingredients.get(0), updatedShoppingList.getListItems().get(0));
    }

    /**
     * Tests adding the same recipe multiple times to ensure quantities are updated.
     */
    @Test
    void testAddSameRecipeMultipleTimes() {
        // Arrange
        CoreFunctionalityInputData inputData = new CoreFunctionalityInputData(recipe, shoppingList, parentModel);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("id_1", "Tomato", "pcs", "vegetable", 2));
        recipe.setIngredientList(ingredients);
        interactor.execute(inputData);

        // Act
        interactor.execute(inputData);

        // Assert
        ArgumentCaptor<CoreFunctionalityOutputData> outputDataCaptor = ArgumentCaptor.forClass(CoreFunctionalityOutputData.class);
        verify(presenter, times(2)).prepareSuccessView(outputDataCaptor.capture());
        ShoppingList updatedShoppingList = outputDataCaptor.getValue().getShoppingList();
        assertEquals(1, updatedShoppingList.getListItems().size());
        assertEquals(4, updatedShoppingList.getListItems().get(0).getQuantity());
    }

    /**
     * Tests adding a recipe with multiple ingredients.
     */
    @Test
    void testAddRecipeWithMultipleIngredients() {
        // Arrange
        CoreFunctionalityInputData inputData = new CoreFunctionalityInputData(recipe, shoppingList, parentModel);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("id_1", "Tomato", "pcs", "vegetable", 2));
        ingredients.add(new Ingredient("id_2", "Salt", "grams", "seasoning", 2));
        recipe.setIngredientList(ingredients);

        // Act
        interactor.execute(inputData);

        // Assert
        ArgumentCaptor<CoreFunctionalityOutputData> outputDataCaptor = ArgumentCaptor.forClass(CoreFunctionalityOutputData.class);
        verify(presenter).prepareSuccessView(outputDataCaptor.capture());
        ShoppingList updatedShoppingList = outputDataCaptor.getValue().getShoppingList();
        assertEquals(2, updatedShoppingList.getListItems().size());
        assertEquals(ingredients.get(0), updatedShoppingList.getListItems().get(0));
        assertEquals(ingredients.get(1), updatedShoppingList.getListItems().get(1));
    }

    /**
     * Tests the method getGroceryList with no existing ingredients.
     */
    @Test
    void testGetGroceryListNoExistingIngredients() {
        // Arrange
        Ingredient ingredient = new Ingredient("id_1", "Tomato", "pcs", "vegetable", 2);

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        recipe.setIngredientList(ingredients);

        // Ac
        // Assert
        assertEquals(1, shoppingList.getListItems().size());
        assertEquals(ingredient, shoppingList.getRecipes().getFirst());
    }

    /**
     * Tests the method getGroceryList with existing ingredients.
     */
    @Test
    void testGetGroceryListWithExistingIngredients() {
        // Arrange
        Ingredient ingredient = new Ingredient("id_1", "Tomato", "pcs", "vegetable", 2);

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        recipe.setIngredientList(ingredients);
        shoppingList.addItem(new Ingredient("id_1", "Tomato", "pcs", "vegetable", 2));


        // Assert
        assertEquals(2, shoppingList.getListItems().size());
        assertEquals("Tomato", shoppingList.getListItems().get(0).getIngredientName());
        assertEquals(2, shoppingList.getListItems().get(0).getQuantity());
    }
}
