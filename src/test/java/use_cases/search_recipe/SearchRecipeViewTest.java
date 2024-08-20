//package use_cases;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
//import use_cases.add_new_grocery_list.AddNewGroceryListController;
//import use_cases.add_to_my_recipe.AddToMyRecipeController;
//import use_cases.core_functionality.CoreFunctionalityController;
//import use_cases.display_recipe_detail.DisplayRecipeDetailController;
//import use_cases.recently_viewed_recipes.RecentlyViewedRecipesController;
//import use_cases.search_recipe.gui.view.SearchRecipeView;
//import use_cases.search_recipe.gui.view_component.*;
//import use_cases.search_recipe.interface_adapter.controller.SearchRecipeController;
//import use_cases.search_recipe.interface_adapter.presenter.SearchRecipePresenter;
//import use_cases.search_recipe.interface_adapter.view_model.AdvancedSearchRecipeViewModel;
//import use_cases.search_recipe.interface_adapter.view_model.SearchRecipeViewModel;
//import use_cases.search_recipe.use_case.output_data.SearchRecipeOutputData;
//import entity.Recipe;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.beans.PropertyChangeEvent;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//public class SearchRecipeViewTest {
//
//    private SearchRecipeView searchRecipeView;
//    private SearchRecipeViewModel searchRecipeViewModel;
//    private SearchRecipeController searchRecipeController;
//    private ViewManagerModel viewManagerModel;
//    private AdvancedSearchRecipeViewModel advancedSearchRecipeViewModel;
//    private DisplayRecipeDetailController displayRecipeDetailController;
//    private AddToMyRecipeController addToMyRecipeController;
//    private CoreFunctionalityController coreFunctionalityController;
//    private RecentlyViewedRecipesController recentlyViewedRecipesController;
//    private AddNewGroceryListController addNewGroceryListController;
//
//    @BeforeEach
//    public void setUp() {
//        searchRecipeViewModel = mock(SearchRecipeViewModel.class);
//        searchRecipeController = mock(SearchRecipeController.class);
//        viewManagerModel = mock(ViewManagerModel.class);
//        advancedSearchRecipeViewModel = mock(AdvancedSearchRecipeViewModel.class);
//        displayRecipeDetailController = mock(DisplayRecipeDetailController.class);
//        addToMyRecipeController = mock(AddToMyRecipeController.class);
//        coreFunctionalityController = mock(CoreFunctionalityController.class);
//        recentlyViewedRecipesController = mock(RecentlyViewedRecipesController.class);
//        addNewGroceryListController = mock(AddNewGroceryListController.class);
//
//        searchRecipeView = new SearchRecipeView(searchRecipeViewModel, searchRecipeController, displayRecipeDetailController,
//                addToMyRecipeController, recentlyViewedRecipesController, addNewGroceryListController, coreFunctionalityController,
//                advancedSearchRecipeViewModel, viewManagerModel);
//    }
//
////    @Test
////    public void testConstructorAndInitialization() {
////        assertNotNull(searchRecipeView);
////        assertEquals("SearchRecipeView", searchRecipeView.getViewName());
////
////        // Verify initial setup
////        verify(searchRecipeViewModel).addPropertyChangeListener(searchRecipeView);
////    }
////
////    @Test
////    public void testActionPerformed() {
////        ActionEvent event = mock(ActionEvent.class);
////        searchRecipeView.actionPerformed(event);
////        // No specific action to verify, as actionPerformed is empty
////    }
////
////    @Test
////    public void testPropertyChangeSearchRecipe() {
////        Recipe recipe = new Recipe("testRecipeId");
////        List<Recipe> recipes = new ArrayList<>();
////        recipes.add(recipe);
////        SearchRecipeOutputData outputData = new SearchRecipeOutputData(recipes);
////        PropertyChangeEvent event = new PropertyChangeEvent(this, "search recipe", null, outputData);
////
////        searchRecipeView.propertyChange(event);
////
////        verify(searchRecipeViewModel).getRecipeSearchResult();
////        assertEquals(1, searchRecipeView.getOutputPanel().getComponentCount());
////    }
//
//    @Test
//    public void testPropertyChangeEmptyResult() {
//        PropertyChangeEvent event = new PropertyChangeEvent(this, "empty result", null, null);
//
//        searchRecipeView.propertyChange(event);
//
//        assertEquals(1, searchRecipeView.getOutputPanel().getComponentCount());
//        JPanel emptyResultPanel = (JPanel) searchRecipeView.getOutputPanel().getComponent(0);
//        JLabel emptyResultLabel = (JLabel) emptyResultPanel.getComponent(0);
//        assertEquals("No recipe found...", emptyResultLabel.getText());
//    }
//
////    @Test
////    public void testPropertyChangeApiFail() {
////        PropertyChangeEvent event = new PropertyChangeEvent(this, "api fail", null, null);
////        JFrame parentFrame = new JFrame();
////
////        searchRecipeView.addNotify(); // Needed to make getWindowAncestor return non-null
////        SwingUtilities.getWindowAncestor(searchRecipeView);
////
////        searchRecipeView.propertyChange(event);
////
////        verify(searchRecipeViewModel).firePropertyChange("api fail");
////    }
//
////    @Test
////    public void testPropertyChangeNightMode() {
////        PropertyChangeEvent event = new PropertyChangeEvent(this, "nightMode", false, true);
////
////        searchRecipeView.propertyChange(event);
////
////        assertTrue(Color.BLACK.equals(searchRecipeView.getBackground()));
////    }
//
////    @Test
////    public void testLoadSearchResult() {
////        Recipe recipe = new Recipe("testRecipeId");
////        List<Recipe> recipes = new ArrayList<>();
////        recipes.add(recipe);
////        SearchRecipeOutputData outputData = new SearchRecipeOutputData(recipes);
////
////        searchRecipeView.loadSearchResult(outputData);
////
////        assertEquals(1, searchRecipeView.getOutputPanel().getComponentCount());
////    }
//
//    @Test
//    public void testLoadEmptyResult() {
//        searchRecipeView.loadEmptyResult();
//
//        assertEquals(1, searchRecipeView.getOutputPanel().getComponentCount());
//        JPanel emptyResultPanel = (JPanel) searchRecipeView.getOutputPanel().getComponent(0);
//        JLabel emptyResultLabel = (JLabel) emptyResultPanel.getComponent(0);
//        assertEquals("No recipe found...", emptyResultLabel.getText());
//    }
//
////    @Test
////    public void testSetNightMode() {
////        searchRecipeView.setNightMode();
////        assertEquals(Color.BLACK, searchRecipeView.getBackground());
////    }
////
////    @Test
////    public void testSetDayMode() {
////        searchRecipeView.setDayMode();
////        assertEquals(SearchRecipeView.claudeWhite, searchRecipeView.getBackground());
////    }
//////
////    @Test
////    public void testAdvancedSearchButton() {
////        JButton advancedSearchButton = (JButton) searchRecipeView.getInputPanel().getComponent(0);
////        advancedSearchButton.doClick();
////
////        verify(searchRecipeController, times(1)).execute(anyString(), anyList(), anyList(), anyList(), anyList(), anyList(), anyList());
////    }
//
//    @Test
//    public void testSearchButtonAction() {
//        searchRecipeView.getRecipeNameField().setText("Pasta");
//        searchRecipeView.getSearchButton().doClick();
//
//        verify(searchRecipeController, times(1)).execute("Pasta");
//    }
//
//    @Test
//    public void testSearchTextFieldActionListener() {
//        searchRecipeView.getRecipeNameField().setText("Pasta");
//        searchRecipeView.getRecipeNameField().postActionEvent();
//
//        verify(searchRecipeController, times(1)).execute("Pasta");
//    }
//}
