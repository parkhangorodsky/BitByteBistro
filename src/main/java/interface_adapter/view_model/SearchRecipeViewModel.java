package interface_adapter.view_model;

import use_case.output_data.SearchRecipeOutputData;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchRecipeViewModel extends ViewModel {

    private SearchRecipeOutputData recipeSearchResult;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public SearchRecipeViewModel(String viewName) {
        super(viewName);
    }

    public void setRecipeSearchResult(SearchRecipeOutputData recipeSearchResult) {
        this.recipeSearchResult = recipeSearchResult;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public void firePropertyChanged() {
        support.firePropertyChange("search recipe", null, this.recipeSearchResult);
    }
}
