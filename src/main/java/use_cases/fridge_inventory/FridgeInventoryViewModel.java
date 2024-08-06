package use_cases.fridge_inventory;

import entity.Ingredient;
import use_cases._common.interface_adapter_common.view_model.abstractions.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class FridgeInventoryViewModel extends ViewModel {
    private List<Ingredient> ingredients;
    private final PropertyChangeSupport support;

    public FridgeInventoryViewModel(String viewName) {
        super(viewName);
        this.ingredients = new ArrayList<>();
        support = new PropertyChangeSupport(this);
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        firePropertyChange("update");
    }

    public void addIngredient(String ingredientName, float quantity, String unit) {
        Ingredient ingredient = new Ingredient(ingredientName, ingredientName, unit, "Food", quantity);
        this.ingredients.add(ingredient);
        firePropertyChange("update");
    }

    public void removeIngredient(String ingredientName) {
        this.ingredients.removeIf(ingredient -> ingredient.getIngredientName().equals(ingredientName));
        firePropertyChange("update");
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public void firePropertyChange(String propertyName) {
        support.firePropertyChange(propertyName, null, null);
    }
}
