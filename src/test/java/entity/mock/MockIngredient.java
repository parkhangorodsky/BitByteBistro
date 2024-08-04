package entity.mock;

import entity.Ingredient;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class MockIngredient {
    public Ingredient mock;
    private String ingredientID;
    private String ingredientName;
    private String quantityUnit;
    private String category;
    float quantity;

    public MockIngredient() {
        mock = Mockito.mock(Ingredient.class);
        when(mock.getIngredientID()).thenReturn(ingredientID);
        when(mock.getIngredientName()).thenReturn(ingredientName);
        when(mock.getIngredientMeasure()).thenReturn(quantityUnit);
        when(mock.getIngredientQuantity()).thenReturn(quantity);
        when(mock.getIngredientCategory()).thenReturn(category);
    }

    public MockIngredient setIngredientID(String ingredientID) {
        this.ingredientID = ingredientID;
        return this;
    }

    public MockIngredient setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
        return this;
    }

    public MockIngredient setIngredientMeasure(String quantityUnit) {
        this.quantityUnit = quantityUnit;
        return this;
    }

    public MockIngredient setIngredientQuantity(float quantity) {
        this.quantity = quantity;
        return this;
    }

    public MockIngredient setIngredientCategory(String category) {
        this.category = category;
        return this;
    }



}
