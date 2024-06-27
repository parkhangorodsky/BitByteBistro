package interface_adapter.view_model;

import entity.Recipe;

import java.beans.PropertyChangeSupport;
import java.util.List;

public class SearchRecipeViewModel extends ViewModel {

    private List<Recipe> recipeSearchResult;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public SearchRecipeViewModel(String viewName) {
        super(viewName);
    }

    public void setRecipeSearchResult(List<Recipe> recipeSearchResult) {
        this.recipeSearchResult = recipeSearchResult;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("recipe search", null, this.recipeSearchResult);
    }
}
