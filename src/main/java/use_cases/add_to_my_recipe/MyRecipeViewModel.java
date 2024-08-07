package use_cases.add_to_my_recipe;

import entity.Recipe;
import use_cases._common.interface_adapter_common.view_model.abstractions.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

/**
 * Overview: ViewModel for managing the state of the user's recipes in the view.
 * Encapsulation: This class encapsulates logic for storing data required to
 * display the MyRecipeView and firing property change upon updates.
 */
public class MyRecipeViewModel extends ViewModel {
    private List<Recipe> recipes;
    private PropertyChangeSupport support;

    public MyRecipeViewModel(String viewName) {
        super(viewName);
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void firePropertyChange(String propertyName) {
        support.firePropertyChange(propertyName, null, recipes);
    }

    public List<Recipe> getRecipes() {return recipes;}
    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }



}
