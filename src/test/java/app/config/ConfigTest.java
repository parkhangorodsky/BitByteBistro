package app.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConfigTest {

    private Config config;

    @BeforeEach
    void setUp() {
        config = new Config();
    }

    @Test
    void testGetViewManagerModel() {
        assertNotNull(config.getViewManagerModel(), "ViewManagerModel should not be null");
    }

    @Test
    void testGetAuthenticationViewModel() {
        assertNotNull(config.getAuthenticationViewModel(), "AuthenticationViewModel should not be null");
    }

    @Test
    void testGetLoginViewModel() {
        assertNotNull(config.getLoginViewModel(), "LoginViewModel should not be null");
    }

    @Test
    void testGetSignUpViewModel() {
        assertNotNull(config.getSignUpViewModel(), "SignUpViewModel should not be null");
    }

    @Test
    void testGetSearchRecipeViewModel() {
        assertNotNull(config.getSearchRecipeViewModel(), "SearchRecipeViewModel should not be null");
    }

    @Test
    void testGetAdvancedSearchRecipeViewModel() {
        assertNotNull(config.getAdvancedSearchRecipeViewModel(), "AdvancedSearchRecipeViewModel should not be null");
    }

    @Test
    void testGetMyRecipeViewModel() {
        assertNotNull(config.getMyRecipeViewModel(), "MyRecipeViewModel should not be null");
    }

    @Test
    void testGetMyGroceryViewModel() {
        assertNotNull(config.MyGroceryViewModel(), "MyGroceryViewModel should not be null");
    }

    @Test
    void testGetNutritionStatsViewModel() {
        assertNotNull(config.getNutritionStatsViewModel(), "NutritionStatsViewModel should not be null");
    }

    @Test
    void testGetRecipeAPI() {
        assertNotNull(config.getRecipeAPI(), "RecipeAPI should not be null");
    }

    @Test
    void testGetNutritionAPI() {
        assertNotNull(config.getNutritionAPI(), "NutritionAPI should not be null");
    }


    @Test
    void testGetGUI() {
        assertNotNull(config.getGUI(), "GUI should not be null");
    }

    @Test
    void testGetSearchRecipeController() {
        assertNotNull(config.getSearchRecipeController(), "SearchRecipeController should not be null");
    }


    @Test
    void testGetLogoutController() {
        assertNotNull(config.getLogoutController(), "LogoutController should not be null");
    }



    @Test
    void testGetFilterRecipeController() {
        assertNotNull(config.getFilterRecipeController(), "FilterRecipeController should not be null");
    }


    @Test
    void testGetDisplayRecipeDetailController() {
        assertNotNull(config.getDisplayRecipeDetailController(), "DisplayRecipeDetailController should not be null");
    }



    @Test
    void testGetNutritionStatsController() {
        assertNotNull(config.getNutritionStatsController(), "NutritionStatsController should not be null");
    }
}
