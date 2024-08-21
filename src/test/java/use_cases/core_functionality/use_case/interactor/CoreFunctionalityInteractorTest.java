package use_cases.core_functionality.use_case.interactor;
import app.local.LoggedUserData;
import entity.*;
import frameworks.data_access.UserDataAccessInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_cases.core_functionality.CoreFunctionalityInputData;
import use_cases.core_functionality.CoreFunctionalityInteractor;
import use_cases.core_functionality.CoreFunctionalityOutputData;
import use_cases.core_functionality.CoreFunctionalityPresenter;

import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Map;

import entity.Ingredient;
import entity.Recipe;
import entity.ShoppingList;
import entity.User;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for the CoreFunctionalityInteractor class.
 * This class tests the behavior of the interactor to ensure it correctly processes the
 * addition of a recipe to a user's shopping list, updates the data access object,
 * and communicates the results to the presenter.
 */
class CoreFunctionalityInteractorTest {

    private UserDataAccessInterface mockUserDAO;
    private CoreFunctionalityPresenter mockPresenter;
    private CoreFunctionalityInteractor interactor;

    /**
     * Set up the test environment before each test.
     * Mocks dependencies and initializes the CoreFunctionalityInteractor with mocked objects.
     */
    @BeforeEach
    void setUp() {
        mockUserDAO = Mockito.mock(UserDataAccessInterface.class);
        mockPresenter = Mockito.mock(CoreFunctionalityPresenter.class);
        interactor = Mockito.spy(new CoreFunctionalityInteractor(mockPresenter, mockUserDAO));
    }

    /**
     * Tests the execute method of CoreFunctionalityInteractor.
     * Verifies that a recipe is correctly added to the shopping list, the data access object is updated,
     * and the presenter is called to prepare the success view.
     */
    @Test
    void execute() {
        // Prepare mock data
        User mockUser = Mockito.mock(User.class);
        Recipe mockRecipe = Mockito.mock(Recipe.class);
        ShoppingList mockShoppingList = Mockito.mock(ShoppingList.class);
        Map<String, ShoppingList> mockShoppingLists = Mockito.mock(Map.class);
        Ingredient mockIngredient = Mockito.mock(Ingredient.class);

        // Mock behavior for recipe and user
        when(mockRecipe.getIngredientList()).thenReturn(List.of(mockIngredient));
        when(mockUser.getShoppingLists()).thenReturn(mockShoppingLists);

        // Create input data for the interactor
        CoreFunctionalityInputData mockInputData = Mockito.mock(CoreFunctionalityInputData.class);

        // Mock the LoggedUserData static method
        LoggedUserData.setLoggedInUser(mockUser);

        // Execute the method under test
        interactor.execute(mockInputData);

        // Verify that the addRecipe method was called with the correct arguments
        verify(interactor, times(1)).addRecipe(mockShoppingList, mockRecipe);

        // Capture the ShoppingList and Recipe arguments passed to addRecipe method
        ArgumentCaptor<ShoppingList> shoppingListCaptor = ArgumentCaptor.forClass(ShoppingList.class);
        ArgumentCaptor<Recipe> recipeCaptor = ArgumentCaptor.forClass(Recipe.class);
        verify(interactor).addRecipe(shoppingListCaptor.capture(), recipeCaptor.capture());

        // Verify the collapse strategy was used within addItem
        verify(interactor, times(1)).addItem(mockShoppingList, mockIngredient);

        // Verify that the userDAO updated the shopping list in the database
        verify(mockUserDAO, times(1)).addRecipeToShoppingList(mockUser, mockShoppingList, mockRecipe);

        // Verify that the presenter prepares the success view
        ArgumentCaptor<CoreFunctionalityOutputData> outputCaptor = ArgumentCaptor.forClass(CoreFunctionalityOutputData.class);
        verify(mockPresenter, times(1)).prepareSuccessView(outputCaptor.capture());

        // Assert that the captured output data is not null
        CoreFunctionalityOutputData capturedOutputData = outputCaptor.getValue();
        assertNotNull(capturedOutputData.getShoppingList(), "The ShoppingList in the output data should not be null.");
        assertNotNull(capturedOutputData.getParentModel(), "The ParentModel in the output data should not be null.");
    }
}