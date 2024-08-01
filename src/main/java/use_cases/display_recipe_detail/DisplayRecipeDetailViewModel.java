package use_cases.display_recipe_detail;

import entity.Recipe;
import use_cases._common.interface_adapter_common.view_model.abstractions.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DisplayRecipeDetailViewModel extends ViewModel {
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

    public void firePropertyChange() {
        support.firePropertyChange("initialized", null, this.recipe);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}
