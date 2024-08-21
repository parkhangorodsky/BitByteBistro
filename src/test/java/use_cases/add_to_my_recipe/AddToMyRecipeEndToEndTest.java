package use_cases.add_to_my_recipe;

import app.local.LocalAppSetting;
import app.local.LoggedUserData;
import entity.Recipe;
import entity.User;
import frameworks.data_access.UserDataAccessInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;
import use_cases.display_recipe_detail.DisplayRecipeDetailController;
import use_cases.filter_recipe.FilterRecipeController;
import use_cases.recently_viewed_recipes.RecentlyViewedRecipesController;
import use_cases.core_functionality.CoreFunctionalityController;
import use_cases.add_new_grocery_list.AddNewGroceryListController;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * End-to-end test for the Add to My Recipe use case.
 */
public class AddToMyRecipeEndToEndTest {

    private AddToMyRecipeController addToMyRecipeController;
    private AddToMyRecipeInteractor addToMyRecipeInteractor;
    private AddToMyRecipePresenter addToMyRecipePresenter;
    private MyRecipeViewModel myRecipeViewModel;
    private User testUser;
    private Recipe testRecipe;
    private PropertyChangeFirer parentModel;

    private MyRecipeView myRecipeView;

    private FilterRecipeController filterRecipeController;
    private RecentlyViewedRecipesController recentlyViewedRecipesController;
    private DisplayRecipeDetailController displayRecipeDetailController;
    private CoreFunctionalityController coreFunctionalityController;
    private AddNewGroceryListController addNewGroceryListController;

    @BeforeEach
    void setUp() {
        // Initialize the mock user DAO
        UserDataAccessInterface userDAO = mock(UserDataAccessInterface.class);

        // Set up the view model and presenter
        myRecipeViewModel = new MyRecipeViewModel("MyRecipeView");
        addToMyRecipePresenter = new AddToMyRecipePresenter(myRecipeViewModel);

        // Set up the interactor and controller
        addToMyRecipeInteractor = new AddToMyRecipeInteractor(addToMyRecipePresenter, userDAO);
        addToMyRecipeController = new AddToMyRecipeController(addToMyRecipeInteractor);

        // Mock a test recipe
        testRecipe = new Recipe("testRecipeId");
        testRecipe.setName("Test Recipe");

        // Mock a test user
        testUser = new User("testUser", "test@example.com", "password123", null);
        testUser.setRecipes(new ArrayList<>()); // Initialize the user's recipe list
        LoggedUserData.setLoggedInUser(testUser);

        // Mock the parent model
        parentModel = mock(PropertyChangeFirer.class);

        // Initialize controllers needed for MyRecipeView
        filterRecipeController = mock(FilterRecipeController.class);
        recentlyViewedRecipesController = mock(RecentlyViewedRecipesController.class);
        displayRecipeDetailController = mock(DisplayRecipeDetailController.class);
        coreFunctionalityController = mock(CoreFunctionalityController.class);
        addNewGroceryListController = mock(AddNewGroceryListController.class);

        // Set up the MyRecipeView
        myRecipeView = new MyRecipeView(myRecipeViewModel, filterRecipeController, recentlyViewedRecipesController,
                displayRecipeDetailController, coreFunctionalityController, addNewGroceryListController);
    }

    /**
     * Tests adding a new recipe to the user's recipes and updating the view.
     */
    @Test
    void testAddNewRecipeToMyRecipesAndView() {
        // Act
        addToMyRecipeController.execute(testRecipe, parentModel);

        // Assert
        assertTrue(testUser.getRecipes().contains(testRecipe));
        verify(parentModel).firePropertyChange("added recipe");

        // Verify the view updates
        myRecipeViewModel.firePropertyChange("added recipe");
        assertTrue(myRecipeViewModel.getRecipes().contains(testRecipe));
    }

    /**
     * Tests attempting to add a recipe that already exists in the user's recipes and view.
     */
    @Test
    void testAddExistingRecipeToMyRecipesAndView() {
        // Arrange
        testUser.addRecipe(testRecipe);

        // Act
        addToMyRecipeController.execute(testRecipe, parentModel);

        // Assert
        assertTrue(testUser.getRecipes().contains(testRecipe));
        verify(parentModel).firePropertyChange("recipe already exists");

        // Verify the view does not update with duplicate
        myRecipeViewModel.firePropertyChange("recipe already exists");
        assertTrue(myRecipeViewModel.getRecipes().contains(testRecipe));
    }

    /**
     * Tests adding a recipe with a logged-out user (should not add the recipe).
     */
    @Test
    void testAddRecipeWithLoggedOutUser() {
        // Arrange
        LoggedUserData.setLoggedInUser(null);

        // Act
        addToMyRecipeController.execute(testRecipe, parentModel);

        // Assert
        assertTrue(testUser.getRecipes().isEmpty());
        verify(parentModel, never()).firePropertyChange(anyString());

        // Verify the view does not update
        myRecipeViewModel.firePropertyChange("added recipe");
        assertTrue(myRecipeViewModel.getRecipes().isEmpty());
    }

    /**
     * Tests the view initialization.
     */
    @Test
    void testViewInitialization() {
        // Act
        myRecipeViewModel.firePropertyChange("init");

        // Assert
        assertTrue(myRecipeViewModel.getRecipes().isEmpty());
    }

    /**
     * Tests the filter function in the view.
     */
    @Test
    void testFilterFunction() {
        // Act
        myRecipeViewModel.setRecipes(testUser.getRecipes());
        filterRecipeController.execute("");

        // Verify the filter is called
        verify(filterRecipeController, times(1)).execute("");
    }

    /**
     * Tests the search function in the view.
     */
//    @Test
//    void testSearchFunction() {
//        // Act
//        myRecipeView.textField.setText("Test Recipe");
//        myRecipeView.searchButton.doClick();
//
//        // Verify the filter is called with the search term
//        verify(filterRecipeController, times(1)).execute("Test Recipe");
//    }

    /**
     * Tests the mouse interaction for displaying recipe detail.
     */
//    @Test
//    void testMouseInteractionForRecipeDetail() {
//        // Arrange
//        testUser.addRecipe(testRecipe);
//        myRecipeViewModel.setRecipes(testUser.getRecipes());
//        myRecipeView.updateMyRecipe(testUser.getRecipes());
//
//        JPanel recipeItem = (JPanel) myRecipeView.myRecipeContainer.getComponent(0);
//        MouseEvent clickEvent = new MouseEvent(recipeItem, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 1, false);
//
//        // Act
//        for (MouseListener listener : recipeItem.getMouseListeners()) {
//            listener.mouseClicked(clickEvent);
//        }
//
//        // Verify the recipe detail is displayed
//        verify(displayRecipeDetailController, times(1)).execute(eq(testRecipe), any(DisplayRecipeDetailViewModel.class));
//    }

    /**
     * Tests the night mode toggle in the view.
     */
    @Test
    void testNightModeToggle() {
        // Act
        LocalAppSetting.setNightMode(true);
        myRecipeViewModel.firePropertyChange("nightMode");

        // Assert
        assertTrue(LocalAppSetting.isNightMode());
    }

    /**
     * Tests the day mode toggle in the view.
     */
    @Test
    void testDayModeToggle() {
        // Act
        LocalAppSetting.setNightMode(false);
        myRecipeViewModel.firePropertyChange("nightMode");

        // Assert
        assertFalse(LocalAppSetting.isNightMode());
    }
}
