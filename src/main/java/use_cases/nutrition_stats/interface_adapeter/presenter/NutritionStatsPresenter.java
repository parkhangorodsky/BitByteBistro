package use_cases.nutrition_stats.interface_adapeter.presenter;

import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.nutrition_stats.interface_adapeter.view_model.NutritionStatsViewModel;
import use_cases.nutrition_stats.use_case.output_data.NutritionStatsOutputData;
import use_cases.search_recipe.interface_adapter.view_model.SearchRecipeViewModel;

/**
 * Overview: Presenter for the nutrition stats use case. This class will update the nutritionStatsViewModel and
 * fire property change to the viewManagerModel, right now, it sends an empty call and updates nothing - since the
 * organization/flow of information of this use case is not fully decided yet.
 *
 * Encapsulation: This class encapsulates the logic for updating the nutritionStatsViewModel.
 */
public class NutritionStatsPresenter implements NutritionStatsOutputBoundary{
    private NutritionStatsViewModel nutritionStatsViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructs a NutritionStatsPresenter with the specified view manager and search recipe view model.
     *
     * @param viewManagerModel the model managing the active view
     * @param nutritionStatsViewModel the view model for displaying recipe search results
     */
    public NutritionStatsPresenter(ViewManagerModel viewManagerModel, NutritionStatsViewModel nutritionStatsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.nutritionStatsViewModel = nutritionStatsViewModel;
    }

    /**
     * Will prepare the view for successful recipe search results.
     * Will update the nutrition display view model with the search results and notify the view manager model.
     * Currently, does nothing.
     *
     * @param nutritionalInfo the nutrition information data for a specific recipe
     */
    @Override
    public void prepareSuccessView(NutritionStatsOutputData nutritionalInfo) {
        nutritionStatsViewModel.setNutritionalStatistics(nutritionalInfo);
        nutritionStatsViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(nutritionStatsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
