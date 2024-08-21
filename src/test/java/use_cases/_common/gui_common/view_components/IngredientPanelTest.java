package use_cases._common.gui_common.view_components;

import app.local.LocalAppSetting;
import entity.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_cases._common.gui_common.abstractions.ThemeColoredObject;
import use_cases._common.gui_common.view_components.layouts.VerticalFlowLayout;
import use_cases._common.gui_common.view_components.round_component.RoundPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IngredientPanelTest {

    private IngredientPanel ingredientPanel;

    @BeforeEach
    void setUp() {
        Ingredient ingredient = mock(Ingredient.class);
        when(ingredient.getIngredientName()).thenReturn("salt");
        when(ingredient.getQuantity()).thenReturn(1.0);
        when(ingredient.getQuantityUnit()).thenReturn("g");

        List<Ingredient> ingredients = Collections.singletonList(ingredient);
        ingredientPanel = new IngredientPanel(ingredients);
    }

    @Test
    void testInitializeComponents() {
        // Check if the panel is initialized with the correct layout
        assertTrue(ingredientPanel.getLayout() instanceof VerticalFlowLayout);

        // Check if the label is initialized correctly
        JLabel label = (JLabel) ingredientPanel.getComponent(0);
        assertEquals("Ingredients", label.getText());

        // Check if ingredient components are added correctly
        JPanel ingredientPanelComponent = (JPanel) ingredientPanel.getComponent(1);
        assertEquals(3, ingredientPanelComponent.getComponentCount());
        JLabel nameLabel = (JLabel) ingredientPanelComponent.getComponent(0);
        JLabel quantityLabel = (JLabel) ingredientPanelComponent.getComponent(1);
        JLabel unitLabel = (JLabel) ingredientPanelComponent.getComponent(2);

        assertEquals("Salt", nameLabel.getText());
        assertEquals("1.0", quantityLabel.getText());
        assertEquals("g", unitLabel.getText());
    }

    @Test
    void testSetNightMode() {
        ingredientPanel.setNightMode();

        // Check if the panel's background and border color are set correctly
        assertEquals(Color.BLACK, ingredientPanel.getBackground());

        // Check if label color is set to neon pink
        JLabel label = (JLabel) ingredientPanel.getComponent(0);
        assertEquals( new Color(242, 0, 137), label.getForeground());  // Adjust this to the actual color used for neon pink

        // Check if ingredient labels' colors are set correctly
        JPanel ingredientPanelComponent = (JPanel) ingredientPanel.getComponent(1);
        for (Component component : ingredientPanelComponent.getComponents()) {
            if (component instanceof JLabel) {
                assertEquals( new Color(242, 0, 137), component.getForeground());  // Adjust this to the actual color used for neon pink
            }
        }
    }

    @Test
    void testSetDayMode() {
        ingredientPanel.setDayMode();

        // Check if the panel's background and border color are set correctly
        assertEquals(new Color(207, 205, 193), ingredientPanel.getBackground());

        // Check if label color is set to black
        JLabel label = (JLabel) ingredientPanel.getComponent(0);
        assertEquals(new Color(60, 56, 41), label.getForeground());

        // Check if ingredient labels' colors are set correctly
        JPanel ingredientPanelComponent = (JPanel) ingredientPanel.getComponent(1);
        for (Component component : ingredientPanelComponent.getComponents()) {
            if (component instanceof JLabel) {
                assertEquals(new Color(60, 56, 41), component.getForeground());
            }
        }
    }

    @Test
    void testPropertyChange() {
        PropertyChangeEvent evt = new PropertyChangeEvent(this, "nightMode", null, null);
        when(evt.getPropertyName()).thenReturn("nightMode");
        assertEquals(new Color(207, 205, 193), ingredientPanel.getBackground());
        LocalAppSetting.setNightMode(true);
        ingredientPanel.propertyChange(evt);
        assertEquals(Color.BLACK, ingredientPanel.getBackground());

    }
}
