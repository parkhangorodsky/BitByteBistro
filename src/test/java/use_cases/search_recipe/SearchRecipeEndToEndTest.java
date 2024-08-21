package use_cases.search_recipe;

import app.local.LocalAppSetting;
import app.local.LoggedUserData;
import entity.Recipe;
import entity.User;
import frameworks.data_access.UserDataAccessInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.add_new_grocery_list.AddNewGroceryListController;
import use_cases.add_to_my_recipe.*;
import use_cases.core_functionality.CoreFunctionalityController;
import use_cases.display_recipe_detail.DisplayRecipeDetailController;
import use_cases.filter_recipe.FilterRecipeController;
import use_cases.recently_viewed_recipes.RecentlyViewedRecipesController;
import use_cases.search_recipe.gui.view.SearchRecipeView;
import use_cases.search_recipe.interface_adapter.controller.SearchRecipeController;
import use_cases.search_recipe.interface_adapter.presenter.SearchRecipePresenter;
import use_cases.search_recipe.interface_adapter.view_model.AdvancedSearchRecipeViewModel;
import use_cases.search_recipe.interface_adapter.view_model.SearchRecipeViewModel;
import use_cases.search_recipe.use_case.interactor.SearchRecipeInteractor;
import frameworks.api.RecipeAPI;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * End-to-end test for the Add to My Recipe and Search Recipe use cases.
 */
public class SearchRecipeEndToEndTest {

    private AddToMyRecipeController addToMyRecipeController;
    private AddToMyRecipeInteractor addToMyRecipeInteractor;
    private AddToMyRecipePresenter addToMyRecipePresenter;
    private MyRecipeViewModel myRecipeViewModel;
    private User testUser;
    private Recipe testRecipe;
    private PropertyChangeFirer parentModel;

    private SearchRecipeView searchRecipeView;
    private SearchRecipeViewModel searchRecipeViewModel;
    private SearchRecipeController searchRecipeController;
    private SearchRecipeInteractor searchRecipeInteractor;
    private SearchRecipePresenter searchRecipePresenter;
    private RecipeAPI recipeAPI;

    private ViewManagerModel viewManagerModel;
    private AdvancedSearchRecipeViewModel advancedSearchRecipeViewModel;

    private DisplayRecipeDetailController displayRecipeDetailController;
    private CoreFunctionalityController coreFunctionalityController;
    private RecentlyViewedRecipesController recentlyViewedRecipesController;
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
        FilterRecipeController filterController = mock(FilterRecipeController.class);
        recentlyViewedRecipesController = mock(RecentlyViewedRecipesController.class);
        displayRecipeDetailController = mock(DisplayRecipeDetailController.class);
        coreFunctionalityController = mock(CoreFunctionalityController.class);
        addNewGroceryListController = mock(AddNewGroceryListController.class);

        // Initialize the SearchRecipe components
        recipeAPI = mock(RecipeAPI.class);
        searchRecipeViewModel = new SearchRecipeViewModel("SearchRecipeView");
        searchRecipePresenter = new SearchRecipePresenter(viewManagerModel, searchRecipeViewModel);
        searchRecipeInteractor = new SearchRecipeInteractor(searchRecipePresenter, recipeAPI);
        searchRecipeController = new SearchRecipeController(searchRecipeInteractor);

        // Set up the SearchRecipeView
        searchRecipeView = new SearchRecipeView(searchRecipeViewModel, searchRecipeController,
                displayRecipeDetailController, addToMyRecipeController,
                recentlyViewedRecipesController, addNewGroceryListController,
                coreFunctionalityController, advancedSearchRecipeViewModel, viewManagerModel);
    }

    // Tests for regular search functionality

    /**
     * Tests performing a search and adding a new recipe to the user's recipes.
     */
//    @Test
//    void testSearchAndAddNewRecipe() {
//        // Mock the API response
//        List<Recipe> mockRecipes = new ArrayList<>();
//        mockRecipes.add(testRecipe);
//        when(recipeAPI.getRecipe(any(SearchRecipeInputData.class))).thenReturn(mockRecipes);
//
//        // Perform the search
//        searchRecipeView.getRecipeNameField().setText("Test Recipe");
//        searchRecipeView.getSearchButton().doClick();
//
//        // Verify the search results
//        assertTrue(searchRecipeViewModel.getRecipeSearchResult().getRecipes().contains(testRecipe));
//
//        // Simulate selecting a recipe and adding to my recipes
//        JPanel recipePanel = (JPanel) searchRecipeView.getOutputPanel().getComponent(0);
//        MouseEvent clickEvent = new MouseEvent(recipePanel, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 1, false);
//
//        // Trigger the mouse click event
//        for (MouseListener listener : recipePanel.getMouseListeners()) {
//            listener.mouseClicked(clickEvent);
//        }
//
//        // Verify the recipe is added to the user's recipes
//        assertTrue(testUser.getRecipes().contains(testRecipe));
//        verify(parentModel).firePropertyChange("added recipe");
//    }
//
////    /**
//     * Tests performing a search and attempting to add an existing recipe.
//     */
//    @Test
//    void testSearchAndAddExistingRecipe() {
//        // Mock the API response
//        List<Recipe> mockRecipes = new ArrayList<>();
//        mockRecipes.add(testRecipe);
//        when(recipeAPI.getRecipe(any(SearchRecipeInputData.class))).thenReturn(mockRecipes);
//
//        // Add the recipe to the user's recipes
//        testUser.addRecipe(testRecipe);
//
//        // Perform the search
//        searchRecipeView.getRecipeNameField().setText("Test Recipe");
//        searchRecipeView.getSearchButton().doClick();
//
//        // Verify the search results
//        assertTrue(searchRecipeViewModel.getRecipeSearchResult().getRecipes().contains(testRecipe));
//
//        // Simulate selecting a recipe and adding to my recipes
//        JPanel recipePanel = (JPanel) searchRecipeView.getOutputPanel().getComponent(0);
//        MouseEvent clickEvent = new MouseEvent(recipePanel, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 1, false);
//
//        // Trigger the mouse click event
//        for (MouseListener listener : recipePanel.getMouseListeners()) {
//            listener.mouseClicked(clickEvent);
//        }
//
//        // Verify the recipe already exists in the user's recipes
//        assertTrue(testUser.getRecipes().contains(testRecipe));
//        verify(parentModel).firePropertyChange("recipe already exists");
//    }

    /**
     * Tests performing a search with no results.
     */
//    @Test
//    void testSearchNoResults() {
//        // Mock the API response
//        List<Recipe> mockRecipes = new ArrayList<>();
//        when(recipeAPI.getRecipe(any(SearchRecipeInputData.class))).thenReturn(mockRecipes);
//
//        // Perform the search
//        searchRecipeView.getRecipeNameField().setText("Non-existent Recipe");
//        searchRecipeView.getSearchButton().doClick();
//
//        // Verify the search results
//        assertTrue(searchRecipeViewModel.getRecipeSearchResult().getRecipes().isEmpty());
//    }

    /**
     * Tests performing a search with an API failure.
     */
//    @Test
//    void testSearchApiFailure() {
//        // Mock the API response
//        when(recipeAPI.getRecipe(any(SearchRecipeInputData.class))).thenReturn(null);
//
//        // Perform the search
//        searchRecipeView.getRecipeNameField().setText("Test Recipe");
//        searchRecipeView.getSearchButton().doClick();
//
//        // Verify the search results
//        assertTrue(searchRecipeViewModel.getRecipeSearchResult().getRecipes().isEmpty());
//        verify(parentModel).firePropertyChange("api fail");
//    }

    /**
     * Tests the view initialization.
//     */
//    @Test
//    void testViewInitialization() {
//        // Act
//        searchRecipeViewModel.firePropertyChange("init");
//
//        // Assert
//        assertTrue(searchRecipeViewModel.getRecipeSearchResult().getRecipes().isEmpty());
//    }

    /**
     * Tests the night mode toggle in the view.
     */
    @Test
    void testNightModeToggle() {
        // Act
        LocalAppSetting.setNightMode(true);
        searchRecipeViewModel.firePropertyChange("nightMode");

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
        searchRecipeViewModel.firePropertyChange("nightMode");

        // Assert
        assertFalse(LocalAppSetting.isNightMode());
    }

    /**
     * Tests the advanced search button functionality.
     */
//    @Test
//    void testAdvancedSearchButton() {
//        // Click the advanced search button
//        JButton advancedSearchButton = (JButton) searchRecipeView.getInputPanel().getComponent(0);
//        advancedSearchButton.doClick();
//
//        // Verify that the advanced search view is displayed
//        AdvancedSearchView advancedSearchView = new AdvancedSearchView((JFrame) SwingUtilities.getWindowAncestor(searchRecipeView),
//                advancedSearchRecipeViewModel, searchRecipeController);
//        assertTrue(advancedSearchView.isVisible());
//    }
//
//    /**
//     * Tests the property change listener for "empty result".
//     */
//    @Test
//    void testPropertyChangeEmptyResult() {
//        // Fire property change for empty result
//        searchRecipeViewModel.firePropertyChange("empty result");
//
//        // Verify the output panel is updated with no result message
//        JPanel emptyResultPanel = (JPanel) searchRecipeView.getOutputPanel().getComponent(0);
//        JLabel emptyResultLabel = (JLabel) emptyResultPanel.getComponent(0);
//        assertEquals("No recipe found...", emptyResultLabel.getText());
//    }
//
//    /**
//     * Tests the property change listener for "search recipe".
//     */
//    @Test
//    void testPropertyChangeSearchRecipe() {
//        // Mock the API response
//        List<Recipe> mockRecipes = new ArrayList<>();
//        mockRecipes.add(testRecipe);
//        SearchRecipeOutputData searchRecipeOutputData = new SearchRecipeOutputData(mockRecipes);
//
//        // Fire property change for search recipe
//        searchRecipeViewModel.setRecipeSearchResult(searchRecipeOutputData);
//        searchRecipeViewModel.firePropertyChange("search recipe");
//
//        // Verify the output panel is updated with the recipe
//        JPanel recipePanel = (JPanel) searchRecipeView.getOutputPanel().getComponent(0);
//        JLabel recipeLabel = (JLabel) ((JPanel) recipePanel.getComponent(1)).getComponent(0);
//        assertEquals(testRecipe.getName(), recipeLabel.getText());
//    }
//
//    // Tests for advanced search functionality

    /**
     * Tests performing an advanced search and adding a new recipe to the user's recipes.
//     */
//    @Test
//    void testAdvancedSearchAndAddNewRecipe() {
//        // Mock the API response
//        List<Recipe> mockRecipes = new ArrayList<>();
//        mockRecipes.add(testRecipe);
//        when(recipeAPI.getRecipe(any(SearchRecipeInputData.class))).thenReturn(mockRecipes);
//
//        // Open advanced search view
//        JButton advancedSearchButton = (JButton) searchRecipeView.getInputPanel().getComponent(0);
//        advancedSearchButton.doClick();
//        AdvancedSearchView advancedSearchView = new AdvancedSearchView((JFrame) SwingUtilities.getWindowAncestor(searchRecipeView),
//                advancedSearchRecipeViewModel, searchRecipeController);
//
//        // Set advanced search parameters
//        advancedSearchView.getStringField().setText("Test Recipe");
//        advancedSearchView.getSearchButton().doClick();
//
//        // Verify the search results
//        assertTrue(searchRecipeViewModel.getRecipeSearchResult().getRecipes().contains(testRecipe));
//
//        // Simulate selecting a recipe and adding to my recipes
//        JPanel recipePanel = (JPanel) searchRecipeView.getOutputPanel().getComponent(0);
//        MouseEvent clickEvent = new MouseEvent(recipePanel, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 1, false);
//
//        // Trigger the mouse click event
//        for (MouseListener listener : recipePanel.getMouseListeners()) {
//            listener.mouseClicked(clickEvent);
//        }
//
//        // Verify the recipe is added to the user's recipes
//        assertTrue(testUser.getRecipes().contains(testRecipe));
//        verify(parentModel).firePropertyChange("added recipe");
//    }
//
//    /**
//     * Tests performing an advanced search and attempting to add an existing recipe.
//     */
////    @Test
////    void testAdvancedSearchAndAddExistingRecipe() {
////        // Mock the API response
////        List<Recipe> mockRecipes = new ArrayList<>();
////        mockRecipes.add(testRecipe);
////        when(recipeAPI.getRecipe(any(SearchRecipeInputData.class))).thenReturn(mockRecipes);
////
////        // Add the recipe to the user's recipes
////        testUser.addRecipe(testRecipe);
////
////        // Open advanced search view
////        JButton advancedSearchButton = (JButton) searchRecipeView.getInputPanel().getComponent(0);
////        advancedSearchButton.doClick();
////        AdvancedSearchView advancedSearchView = new AdvancedSearchView((JFrame) SwingUtilities.getWindowAncestor(searchRecipeView),
////                advancedSearchRecipeViewModel, searchRecipeController);
////
////        // Set advanced search parameters
////        advancedSearchView.getStringField().setText("Test Recipe");
////        advancedSearchView.getSearchButton().doClick();
////
////        // Verify the search results
////        assertTrue(searchRecipeViewModel.getRecipeSearchResult().getRecipes().contains(testRecipe));
////
////        // Simulate selecting a recipe and adding to my recipes
////        JPanel recipePanel = (JPanel) searchRecipeView.getOutputPanel().getComponent(0);
////        MouseEvent clickEvent = new MouseEvent(recipePanel, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 1, false);
////
////        // Trigger the mouse click event
////        for (MouseListener listener : recipePanel.getMouseListeners()) {
////            listener.mouseClicked(clickEvent);
////        }
////
////        // Verify the recipe already exists in the user's recipes
////        assertTrue(testUser.getRecipes().contains(testRecipe));
////        verify(parentModel).firePropertyChange("recipe already exists");
////    }
//
//    /**
//     * Tests performing an advanced search with no results.
//     */
//    @Test
//    void testAdvancedSearchNoResults() {
//        // Mock the API response
//        List<Recipe> mockRecipes = new ArrayList<>();
//        when(recipeAPI.getRecipe(any(SearchRecipeInputData.class))).thenReturn(mockRecipes);
//
//        // Open advanced search view
//        JButton advancedSearchButton = (JButton) searchRecipeView.getInputPanel().getComponent(0);
//        advancedSearchButton.doClick();
//        AdvancedSearchView advancedSearchView = new AdvancedSearchView((JFrame) SwingUtilities.getWindowAncestor(searchRecipeView),
//                advancedSearchRecipeViewModel, searchRecipeController);
//
//        // Set advanced search parameters
//        advancedSearchView.getStringField().setText("Non-existent Recipe");
//        advancedSearchView.getSearchButton().doClick();
//
//        // Verify the search results
//        assertTrue(searchRecipeViewModel.getRecipeSearchResult().getRecipes().isEmpty());
//    }

    /**
     * Tests performing an advanced search with an API failure.
     */
//    @Test
//    void testAdvancedSearchApiFailure() {
//        // Mock the API response
//        when(recipeAPI.getRecipe(any(SearchRecipeInputData.class))).thenReturn(null);
//
//        // Open advanced search view
//        JButton advancedSearchButton = (JButton) searchRecipeView.getInputPanel().getComponent(0);
//        advancedSearchButton.doClick();
//        AdvancedSearchView advancedSearchView = new AdvancedSearchView((JFrame) SwingUtilities.getWindowAncestor(searchRecipeView),
//                advancedSearchRecipeViewModel, searchRecipeController);
//
//        // Set advanced search parameters
//        advancedSearchView.getStringField().setText("Test Recipe");
//        advancedSearchView.getSearchButton().doClick();
//
//        // Verify the search results
//        assertTrue(searchRecipeViewModel.getRecipeSearchResult().getRecipes().isEmpty());
//        verify(parentModel).firePropertyChange("api fail");
//    }
//
//    /**
//     * Tests the view initialization for advanced search.
//     */
//    @Test
//    void testAdvancedSearchViewInitialization() {
//        // Act
//        advancedSearchRecipeViewModel.firePropertyChange("init");
//
//        // Assert
//        assertTrue(advancedSearchRecipeViewModel.getDietOptions().isEmpty());
//    }
}


