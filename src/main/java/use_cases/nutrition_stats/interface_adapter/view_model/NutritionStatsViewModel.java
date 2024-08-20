package use_cases.nutrition_stats.interface_adapter.view_model;

import use_cases._common.interface_adapter_common.view_model.abstractions.ViewModel;
import use_cases.nutrition_stats.use_case.output_data.NutritionStatsOutputData;
import use_cases.search_recipe.use_case.output_data.SearchRecipeOutputData;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Overview: View model for the nutrition stats use case.
 * Procedure: This class holds the nutritional information and notifies listeners about changes
 * to the nutritional information.
 *
 * Encapsulation: This class encapsulates logic for notifying updates on nutritional information.
 */
public class NutritionStatsViewModel extends ViewModel {

    private NutritionStatsOutputData nutritionalStatistics;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructs a NutritionStatsViewModel with the specified view name.
     *
     * @param viewName the name of the view associated with this view model
     */
    public NutritionStatsViewModel(String viewName) {
        super(viewName);
    }


    /**
     * Sets the search result data.
     *
     * @param nutritionalStatistics the search results data
     */
    public void setNutritionalStatistics(NutritionStatsOutputData nutritionalStatistics) {
        this.nutritionalStatistics = nutritionalStatistics;
    }

    public NutritionStatsOutputData getNutritionalStatistics() {
        return nutritionalStatistics;
    }

    /**
     * Notifies all registered listeners that the search results have changed.
     * Fires a property change event with "search recipe" if there are results, otherwise fires "empty result".
     */
    public void firePropertyChanged() {
        if (!nutritionalStatistics.getNutrition().isEmpty()) {
            support.firePropertyChange("nutrition info", null, this.nutritionalStatistics);
        } else {
            support.firePropertyChange("empty result", null, null);
        }
    }

    /**
     * Adds a property change listener to this view model.
     *
     * @param listener the property change listener to be added
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
