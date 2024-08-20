package use_cases;

import app.local.LoggedUserData;
import entity.Recipe;
import entity.User;
import frameworks.data_access.UserDataAccessInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_cases.add_to_my_recipe.AddToMyRecipePresenter;
import use_cases.recently_viewed_recipes.RecentlyViewedRecipesInputData;
import use_cases.recently_viewed_recipes.RecentlyViewedRecipesInteractor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class RecentlyViewedRecipesInteractorTest {

    private RecentlyViewedRecipesInteractor interactor;
    private UserDataAccessInterface userDAO;
    private AddToMyRecipePresenter presenter;
    private Recipe recipe;
    private List<Recipe> recentlyViewedRecipes;

    @BeforeEach
    void setUp() {
        userDAO = mock(UserDataAccessInterface.class);
        presenter = mock(AddToMyRecipePresenter.class);
        interactor = new RecentlyViewedRecipesInteractor(userDAO);

        User user = new User("John Doe", "john@example.com", "password123", LocalDateTime.now());
        recipe = new Recipe("r1");
        recipe.setName("Test Recipe");

        recentlyViewedRecipes = new ArrayList<>();
        recentlyViewedRecipes.add(recipe);

        LoggedUserData.setLoggedInUser(user);
    }

//    @Test
//    void testExecute() {
//        User user = new User("John Doe1", "john1@example.com", "password123", LocalDateTime.now());
//
//        RecentlyViewedRecipesInputData inputData = new RecentlyViewedRecipesInputData(recentlyViewedRecipes,user, recipe);
//        interactor.execute(inputData);
//
//        // Verify that the recipe was added to the user's recently viewed recipes
//        assertTrue(user.getRecentlyViewedRecipes().contains(recipe));
//
//    }

//    @Test
//    void testExecuteWithNoLoggedInUser() {
//        LoggedUserData.setLoggedInUser(null);
//        RecentlyViewedRecipesInputData inputData = new RecentlyViewedRecipesInputData(recentlyViewedRecipes, null, recipe);
//        interactor.execute(inputData);
//
//        // Verify that the DAO method to update recently viewed recipes was not called
//        verify(userDAO, never()).updateRecentlyViewedRecipes(any(User.class));
//    }

    @Test
    void testExecuteWithNullRecipe() {
        User user = new User("John Doe2", "john@example2.com", "password123", LocalDateTime.now());

        RecentlyViewedRecipesInputData inputData = new RecentlyViewedRecipesInputData(recentlyViewedRecipes, user, null);
        interactor.execute(inputData);

        // Verify that the recipe was not added to the user's recently viewed recipes
        assertFalse(user.getRecentlyViewedRecipes().contains(null));

        // Verify that the DAO method to update recently viewed recipes was called
        verify(userDAO, times(1)).updateRecentlyViewedRecipes(user);
    }
}
