package use_cases.search_recipe.interface_adapter.view_model;

import use_cases.search_recipe.use_case.output_data.SearchRecipeOutputData;
import use_cases._common.interface_adapter_common.view_model.abstractions.ViewModel;

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
    public SearchRecipeOutputData getRecipeSearchResult() {
        return recipeSearchResult;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public void firePropertyChanged() {
        if (!recipeSearchResult.getRecipes().isEmpty()) {
            support.firePropertyChange("search recipe", null, this.recipeSearchResult);
        } else if (recipeSearchResult.getRecipes().isEmpty()) {
            support.firePropertyChange("empty result", null, null);
        }

    }
}
