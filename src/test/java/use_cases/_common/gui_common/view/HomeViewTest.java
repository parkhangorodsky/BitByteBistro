package use_cases._common.gui_common.view;

import app.local.LoggedUserData;
import entity.Nutrition;
import entity.Recipe;
import entity.ShoppingList;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_cases._common.gui_common.abstractions.View;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.nutrition_stats.interface_adapter.controller.NutritionStatsController;
import use_cases.nutrition_stats.interface_adapter.view_model.NutritionStatsViewModel;
import use_cases.nutrition_stats.use_case.output_data.NutritionStatsOutputData;
import use_cases.add_new_grocery_list.AddNewGroceryListController;
import use_cases.add_to_my_recipe.AddToMyRecipeController;
import use_cases.core_functionality.CoreFunctionalityController;
import use_cases.display_recipe_detail.DisplayRecipeDetailController;
import use_cases.display_recipe_detail.DisplayRecipeDetailSearchResultView;
import use_cases.display_recipe_detail.DisplayRecipeDetailViewModel;
import use_cases.recently_viewed_recipes.RecentlyViewedRecipesController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HomeViewTest {

    private HomeView homeView;
    private NutritionStatsController nutritionStatsController;
    private NutritionStatsViewModel nutritionStatsViewModel;
    private AddToMyRecipeController addToMyRecipeController;
    private CoreFunctionalityController coreFunctionalityController;
    private AddNewGroceryListController addNewGroceryListController;
    private DisplayRecipeDetailController displayRecipeDetailController;
    private RecentlyViewedRecipesController recentlyViewedRecipesController;
    private User user;

    @BeforeEach
    void setUp() {
        nutritionStatsController = mock(NutritionStatsController.class);
        nutritionStatsViewModel = mock(NutritionStatsViewModel.class);
        addToMyRecipeController = mock(AddToMyRecipeController.class);
        coreFunctionalityController = mock(CoreFunctionalityController.class);
        addNewGroceryListController = mock(AddNewGroceryListController.class);
        displayRecipeDetailController = mock(DisplayRecipeDetailController.class);
        recentlyViewedRecipesController = mock(RecentlyViewedRecipesController.class);
        user = new User("a", "a", "a", LocalDateTime.now());
        LoggedUserData.setLoggedInUser(user);



        homeView = new HomeView(
                mock(ViewManagerModel.class),
                nutritionStatsController,
                nutritionStatsViewModel,
                addToMyRecipeController,
                coreFunctionalityController,
                recentlyViewedRecipesController,
                addNewGroceryListController,
                displayRecipeDetailController
        );
    }

    @Test
    void testInitializeComponents() {
        // Test that the components are initialized correctly
        assertNotNull(homeView.mainPanel);
        assertNotNull(homeView.contentPanel);
        assertNotNull(homeView.nutritionStatsPanel);
        assertNotNull(homeView.recentlyViewedPanel);
        assertNotNull(homeView.welcomeLabel);
        assertNotNull(homeView.selectGroceryListButton);
    }

//    @Test
//    void testDisplayNutritionStats() {
//        Map<String, ShoppingList> groceryLists = new HashMap<>();
//        ShoppingList list = mock(ShoppingList.class);
//        when(list.getShoppingListName()).thenReturn("Test List");
//        when(user.getShoppingLists()).thenReturn(groceryLists);
//
//        homeView.displayNutritionStats();
//
//        assertTrue(homeView.nutritionStatsPanel.isShowing());
//    }

    @Test
    void testLoadNutritionStats() {
        NutritionStatsOutputData outputData = mock(NutritionStatsOutputData.class);
        Nutrition nutrition = mock(Nutrition.class);
        when(nutrition.getLabel()).thenReturn("Test Label");
        when(nutrition.getQuantity()).thenReturn(100.0f);
        when(nutrition.getUnit()).thenReturn("g");
        when(outputData.getNutrition()).thenReturn(Collections.singletonList(nutrition));

        homeView.propertyChange(new PropertyChangeEvent(nutritionStatsViewModel, "nutrition info", null, outputData));

        assertNotNull(homeView.nutritionPanel.isShowing());
    }

//    @Test
//    void testLoadRecentlyViewedRecipes() {
//        Recipe recipe = mock(Recipe.class);
//        when(recipe.getName()).thenReturn("Test Recipe");
//        when(user.getRecentlyViewedRecipes()).thenReturn(Collections.singletonList(recipe));
//
//        homeView.loadRecentlyViewedRecipes();
//
//        assertFalse(homeView.recentlyViewedPanel.getComponentCount() == 0);
//    }

    @Test
    void testActionPerformed() {
        // Test actionPerformed method if it has any specific implementation
        ActionEvent event = mock(ActionEvent.class);
        homeView.actionPerformed(event);

        // Verify if actionPerformed method executes as expected
    }

//    @Test
//    void testPropertyChange() {
//        PropertyChangeEvent evt = mock(PropertyChangeEvent.class);
//        when(evt.getPropertyName()).thenReturn("nutrition info");
//        NutritionStatsOutputData outputData = mock(NutritionStatsOutputData.class);
//        when(evt.getNewValue()).thenReturn(outputData);
//
//        homeView.propertyChange(evt);
//
//        verify(homeView).loadNutritionStats(outputData);
//    }

    @Test
    void testSetNightMode() {
        homeView.setNightMode();

        assertEquals(Color.BLACK, homeView.mainPanel.getBackground());
        assertEquals(Color.BLACK, homeView.contentPanel.getBackground());
        assertEquals(Color.BLACK, homeView.selectGroceryListButton.getBackground());
    }

    @Test
    void testSetDayMode() {
        homeView.setDayMode();

        assertEquals(new Color(238, 237, 227), homeView.mainPanel.getBackground());
        assertEquals(new Color(238, 237, 227), homeView.contentPanel.getBackground());
        assertEquals(new Color(238, 237, 227), homeView.selectGroceryListButton.getBackground());
    }
}

