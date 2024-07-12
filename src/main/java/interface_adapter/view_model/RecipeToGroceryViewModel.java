package interface_adapter.view_model;

import use_case.output_data.RecipeToGroceryOutputData;

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
        support.firePropertyChange("recipe to grocery", null, this.groceryResult);
    }
}
