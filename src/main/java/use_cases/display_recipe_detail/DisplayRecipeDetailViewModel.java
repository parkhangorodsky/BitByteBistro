package use_cases.display_recipe_detail;

import entity.Recipe;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;
import use_cases._common.interface_adapter_common.view_model.abstractions.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DisplayRecipeDetailViewModel extends ViewModel implements PropertyChangeFirer {
    private Recipe recipe;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public DisplayRecipeDetailViewModel(String viewName) {
        super(viewName);
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void firePropertyChange(String propertyName) {
        support.firePropertyChange(propertyName, null, this.recipe);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}
