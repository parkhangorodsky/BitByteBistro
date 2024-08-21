package use_cases.search_recipe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases.search_recipe.gui.view.AdvancedSearchView;
import use_cases.search_recipe.gui.view_component.AdvancedSearchInputSummarizer;
import use_cases.search_recipe.interface_adapter.controller.SearchRecipeController;
import use_cases.search_recipe.interface_adapter.view_model.AdvancedSearchRecipeViewModel;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AdvancedSearchViewTest {

    private AdvancedSearchView advancedSearchView;
    private AdvancedSearchRecipeViewModel advancedSearchRecipeViewModel;
    private SearchRecipeController searchRecipeController;
    private JFrame parentFrame;

    @BeforeEach
    public void setUp() {
        advancedSearchRecipeViewModel = mock(AdvancedSearchRecipeViewModel.class);
        searchRecipeController = mock(SearchRecipeController.class);
        parentFrame = new JFrame();

        advancedSearchView = new AdvancedSearchView(parentFrame, advancedSearchRecipeViewModel, searchRecipeController);
    }

    @Test
    public void testConstructorAndInitialization() {
        assertNotNull(advancedSearchView);
        assertNotNull(advancedSearchView.getIngredientsQuantity());
        assertNotNull(advancedSearchView.getExcludedIngredients());
        assertNotNull(advancedSearchView.getDietSelection());
        assertNotNull(advancedSearchView.getHealthSelection());
        assertNotNull(advancedSearchView.getCuisineTypeSelection());
        assertNotNull(advancedSearchView.getDishTypeSelection());
        assertNotNull(advancedSearchView.getMealTypeSelection());

        // Verify that the popup views are created
        verify(advancedSearchRecipeViewModel).getDietOptions();
        verify(advancedSearchRecipeViewModel).getHealthOptions();
        verify(advancedSearchRecipeViewModel).getCuisineTypeOptions();
        verify(advancedSearchRecipeViewModel).getDishTypeOptions();
        verify(advancedSearchRecipeViewModel).getMealTypeOptions();
    }

    @Test
    public void testStringFieldUpdate() {
        advancedSearchView.getStringField().setText("Pasta");

        assertEquals("Pasta", advancedSearchView.getRecipeName());
//
//        // Verify summary is updated
//        assertTrue(advancedSearchView.getStringField().getDocument().getListeners(DocumentListener.class).length > 0);
//        for (DocumentListener listener : advancedSearchView.getStringField().getDocument().getListeners(DocumentListener.class)) {
//            DocumentEvent event = mock(DocumentEvent.class);
//            listener.insertUpdate(event);
//        }
    }

    @Test
    public void testDisplaySummary() {
        advancedSearchView.displaySummary();

        // Verify summary is displayed
        assertEquals(0, advancedSearchView.summaryTextPanel.getComponentCount());
        new AdvancedSearchInputSummarizer(advancedSearchView, advancedSearchView.summaryTextPanel).summarize();
        assertEquals(0, advancedSearchView.summaryTextPanel.getComponentCount());
    }

    @Test
    public void testSetDayMode() {
        advancedSearchView.setDayMode();
        assertEquals(advancedSearchView.mainPanel.getBackground(), AdvancedSearchView.claudeWhite);
    }

    @Test
    public void testSetNightMode() {
        advancedSearchView.setNightMode();
        assertEquals(advancedSearchView.mainPanel.getBackground(), Color.BLACK);
    }

//    @Test
//    public void testPropertyChangeNightMode() {
//        PropertyChangeEvent event = new PropertyChangeEvent(this, "nightMode", false, true);
//        advancedSearchView.propertyChange(event);
//        verify(advancedSearchRecipeViewModel).firePropertyChange(event);
//    }

    @Test
    public void testSearchButtonAction() {
        // Mock the search action
        doNothing().when(searchRecipeController).execute(anyString(), anyList(), anyList(), anyList(), anyList(), anyList(), anyList());

        // Set some test values
        advancedSearchView.getStringField().setText("Pasta");
        advancedSearchView.getExcludedIngredients().add("nuts");
        advancedSearchView.getDietSelection().add("vegan");
        advancedSearchView.getHealthSelection().add("low-sugar");
        advancedSearchView.getCuisineTypeSelection().add("italian");
        advancedSearchView.getDishTypeSelection().add("main course");
        advancedSearchView.getMealTypeSelection().add("dinner");

        // Click the search button
        advancedSearchView.getSearchButton().doClick();

        // Verify the search action
        verify(searchRecipeController).execute("Pasta",
                advancedSearchView.getExcludedIngredients(),
                advancedSearchView.getDietSelection(),
                advancedSearchView.getHealthSelection(),
                advancedSearchView.getCuisineTypeSelection(),
                advancedSearchView.getDishTypeSelection(),
                advancedSearchView.getMealTypeSelection());

        assertFalse(advancedSearchView.isVisible());
    }

    @Test
    public void testCloseButtonAction() {
        // Click the close button
        advancedSearchView.closeButton.doClick();

        // Verify the view is closed
        assertFalse(advancedSearchView.isVisible());
    }
}
