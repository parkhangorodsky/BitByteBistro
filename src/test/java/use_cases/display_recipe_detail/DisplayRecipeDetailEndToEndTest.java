//package use_cases;
//
//import entity.Nutrition;
//import entity.Recipe;
//import entity.ShoppingList;
//import entity.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import use_cases.add_new_grocery_list.AddNewGroceryListController;
//import use_cases.add_to_my_recipe.AddToMyRecipeController;
//import use_cases.core_functionality.CoreFunctionalityController;
//import use_cases.display_recipe_detail.*;
//import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
//import app.local.LoggedUserData;
//
//import javax.swing.*;
//import java.awt.image.BufferedImage;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.*;
//
///**
// * End-to-end test for the Display Recipe Detail use case.
// */
//public class DisplayRecipeDetailEndToEndTest {
//
//    private DisplayRecipeDetailController displayRecipeDetailController;
//    private DisplayRecipeDetailPresenter displayRecipeDetailPresenter;
//    private DisplayRecipeDetailInteractor displayRecipeDetailInteractor;
//    private DisplayRecipeDetailViewModel displayRecipeDetailViewModel;
//    private DisplayRecipeDetailMyRecipeView displayRecipeDetailMyRecipeView;
//    private DisplayRecipeDetailSearchResultView displayRecipeDetailSearchResultView;
//
//    private CoreFunctionalityController coreFunctionalityController;
//    private AddNewGroceryListController addNewGroceryListController;
//
//    private JFrame parentFrame;
//    private Recipe testRecipe;
//    private User testUser;
//
//    @BeforeEach
//    void setUp() {
//        // Initialize the mock controllers
//        coreFunctionalityController = mock(CoreFunctionalityController.class);
//        addNewGroceryListController = mock(AddNewGroceryListController.class);
//
//        // Set up the presenter, interactor, and controller
//        displayRecipeDetailPresenter = new DisplayRecipeDetailPresenter();
//        displayRecipeDetailInteractor = new DisplayRecipeDetailInteractor(displayRecipeDetailPresenter);
//        displayRecipeDetailController = new DisplayRecipeDetailController(displayRecipeDetailInteractor);
//
//        // Set up the view model
//        displayRecipeDetailViewModel = new DisplayRecipeDetailViewModel("DisplayRecipeDetailView");
//
//        // Set up the views
//        parentFrame = new JFrame();
//        displayRecipeDetailMyRecipeView = new DisplayRecipeDetailMyRecipeView(parentFrame, displayRecipeDetailViewModel, coreFunctionalityController, addNewGroceryListController);
//        displayRecipeDetailSearchResultView = new DisplayRecipeDetailSearchResultView(parentFrame, displayRecipeDetailViewModel, coreFunctionalityController, addNewGroceryListController, mock(AddToMyRecipeController.class));
//
//        // Mock a test recipe
//        testRecipe = createTestRecipe();
//
//        // Mock a test user
//        testUser = new User("testUser", "test@example.com", "password123", null);
//        // Ensure the user preferences contain a nightMode setting
//        testUser.updatePreference("nightMode", false);
//        LoggedUserData.setLoggedInUser(testUser);
//    }
//
//    /**
//     * Tests displaying a recipe detail in My Recipe View.
//     */
//    @Test
//    void testDisplayRecipeDetailMyRecipeView() {
//        // Arrange
//        displayRecipeDetailController.execute(testRecipe, displayRecipeDetailViewModel);
//
//        // Act
//        displayRecipeDetailViewModel.firePropertyChange("initialized");
//
//        // Assert
//        assertNotNull(displayRecipeDetailMyRecipeView);
//    }
//
//    /**
//     * Tests displaying a recipe detail in Search Result View.
//     */
//    @Test
//    void testDisplayRecipeDetailSearchResultView() {
//        // Arrange
//        displayRecipeDetailController.execute(testRecipe, displayRecipeDetailViewModel);
//
//        // Act
//        displayRecipeDetailViewModel.firePropertyChange("initialized");
//
//        // Assert
//        assertNotNull(displayRecipeDetailSearchResultView);
//    }
//
//    /**
//     * Tests adding a recipe to a grocery list in My Recipe View.
//     */
//    @Test
//    void testAddRecipeToGroceryListMyRecipeView() {
//        // Arrange
//        displayRecipeDetailController.execute(testRecipe, displayRecipeDetailViewModel);
//
//        // Act
//        displayRecipeDetailViewModel.firePropertyChange("initialized");
//        displayRecipeDetailMyRecipeView.addToGroceryButton.doClick();
//
//        // Assert
//        verify(coreFunctionalityController).execute(any(ShoppingList.class), eq(testRecipe), eq(displayRecipeDetailViewModel));
//    }
//
//    /**
//     * Tests adding a recipe to "My Recipes" in Search Result View.
//     */
//    @Test
//    void testAddRecipeToMyRecipesSearchResultView() {
//        // Arrange
//        displayRecipeDetailController.execute(testRecipe, displayRecipeDetailViewModel);
//
//        // Act
//        displayRecipeDetailViewModel.firePropertyChange("initialized");
//        displayRecipeDetailSearchResultView.addToRecipesButton.doClick();
//
//        // Assert
//        verify(coreFunctionalityController, never()).execute(any(ShoppingList.class), eq(testRecipe), eq(displayRecipeDetailViewModel));
//    }
//
//    private Recipe createTestRecipe() {
//        Recipe recipe = new Recipe("testRecipe");
//        recipe.setName("Test Recipe");
//        recipe.setImage(mock(BufferedImage.class));
//        recipe.setSmallImage(mock(BufferedImage.class));
//        recipe.setYield(4);
//        recipe.setInstructions("http://example.com/instructions");
//
//        List<String> tags = new ArrayList<>();
//        tags.add("Easy");
//        recipe.setTags(tags);
//
//        List<String> cuisineType = new ArrayList<>();
//        cuisineType.add("Italian");
//        recipe.setCuisineType(cuisineType);
//
//        List<String> mealType = new ArrayList<>();
//        mealType.add("Dinner");
//        recipe.setMealType(mealType);
//
//        List<String> dishType = new ArrayList<>();
//        dishType.add("Main Course");
//        recipe.setDishType(dishType);
//
//        List<Nutrition> nutritionList = new ArrayList<>();
//        Nutrition nutrition = new Nutrition("Calories", 500, "kcal", 25);
//        Map<String, Nutrition> nutritionMap = new HashMap<>();
//        nutritionMap.put("Calories", nutrition);
//        recipe.setNutritionMap(nutritionMap);
//
//        return recipe;
//    }
//}
