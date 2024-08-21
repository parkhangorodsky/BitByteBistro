package use_cases.fridge_inventory.gui.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_cases.fridge_inventory.FridgeInventoryController;
import use_cases.fridge_inventory.FridgeInventoryViewModel;
import entity.Ingredient;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FridgeInventoryViewTest {

    private FridgeInventoryViewModel viewModel;
    private FridgeInventoryController controller;
    private FridgeInventoryView view;

    @BeforeEach
    public void setUp() {
        viewModel = mock(FridgeInventoryViewModel.class);
        controller = mock(FridgeInventoryController.class);
        view = new FridgeInventoryView(viewModel, controller);
    }

    @Test
    public void testAddIngredient() {
        // Set up
        String foodName = "Apple";
        String quantity = "2.5";
        String unit = "kg";
        view.foodField.setText(foodName);
        view.quantityField.setText(quantity);
        view.unitField.setText(unit);

        // Act
        view.addButton.doClick();

        // Assert
        verify(controller, times(1)).addIngredient(foodName, Float.parseFloat(quantity), unit, "");
    }

    @Test
    public void testRemoveIngredient() {
        // Set up
        String foodName = "Apple";
        String quantity = "2.5";
        String unit = "kg";
        view.foodField.setText(foodName);
        view.quantityField.setText(quantity);
        view.unitField.setText(unit);

        // Act
        view.removeButton.doClick();

        // Assert
        verify(controller, times(1)).removeIngredient(foodName, Float.parseFloat(quantity), unit);
    }

    @Test
    public void testUpdateFridgeInventory_withIngredients() {
        // Set up
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("Apple", 2.5f, "kg"),
                new Ingredient("Banana", 1.0f, "kg")
        );
        when(viewModel.getIngredients()).thenReturn(ingredients);

        // Act
        view.propertyChange(new PropertyChangeEvent(this, "update", viewModel, view));

        // Assert
        assertEquals(9, view.fridgeInventoryContainer.getComponentCount()); // 3 labels * 2 rows + header row
        JLabel foodLabel = (JLabel) view.fridgeInventoryContainer.getComponent(1); // First ingredient's food label
        assertEquals("Quantity", foodLabel.getText());
    }

    @Test
    void testNightMode() {
        view.setNightMode();
        assertEquals(view.getBackground(), Color.BLACK);
    }

    @Test
    void testDayMode() {
        view.setDayMode();
        assertEquals(view.getBackground(), Color.WHITE);
    }

    @Test
    public void testPropertyChange_updatesFridgeInventory() {
        // Set up
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("Apple", 2.5f, "kg"),
                new Ingredient("Banana", 1.0f, "kg")
        );
        when(viewModel.getIngredients()).thenReturn(ingredients);

        // Act
        PropertyChangeEvent event = new PropertyChangeEvent(viewModel, "ingredients", null, ingredients);
        view.propertyChange(event);

        // Assert
        assertEquals(9, view.fridgeInventoryContainer.getComponentCount()); // 3 labels * 2 rows + header row
        JLabel foodLabel = (JLabel) view.fridgeInventoryContainer.getComponent(1); // First ingredient's food label
        assertEquals("Quantity", foodLabel.getText());
    }

}

