package use_cases.recipe_to_grocery.interface_adapter.view_model;

import use_cases.recipe_to_grocery.use_case.output_data.RecipeToGroceryOutputData;
import use_cases._common.interface_adapter_common.view_model.abstractions.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RecipeToGroceryViewModel extends ViewModel {

    private RecipeToGroceryOutputData groceryResult;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public RecipeToGroceryViewModel(String viewName) {
        super(viewName);
    }

    public void setGroceryResult(RecipeToGroceryOutputData groceryResult) {
        this.groceryResult = groceryResult;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public void firePropertyChanged() {
        if (groceryResult.getRecipes().isEmpty()) {
            support.firePropertyChange("no recipe", null, null);
        }
    }
}
