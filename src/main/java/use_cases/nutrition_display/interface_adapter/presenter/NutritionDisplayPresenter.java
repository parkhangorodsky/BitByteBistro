package use_cases.nutrition_display.interface_adapter.presenter;

import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.nutrition_display.interface_adapter.view_model.NutritionDisplayViewModel;
import use_cases.nutrition_display.use_case.output_data.NutritionDisplayOutputData;
import use_cases.search_recipe.interface_adapter.view_model.SearchRecipeViewModel;

/**
 * Overview: Presenter for the nutrition display use case. This class will update the nutritionDisplayViewModel and
 * fire property change to the viewManagerModel, right now, it sends an empty call and updates nothing - since the
 * organization/flow of information of this use case is not fully decided yet.
 *
 * Encapsulation: This class encapsulates the logic for updating the nutritionDisplayViewModel.
 */
public class NutritionDisplayPresenter implements NutritionDisplayOutputBoundary{

    private NutritionDisplayViewModel nutritionDisplayViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructs a NutritionDisplayPresenter with the specified view manager and search recipe view model.
     *
     * @param viewManagerModel the model managing the active view
     * @param searchRecipeViewModel the view model for displaying recipe search results
     */
    public NutritionDisplayPresenter(ViewManagerModel viewManagerModel, SearchRecipeViewModel searchRecipeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.nutritionDisplayViewModel = nutritionDisplayViewModel;
    }

    /**
     * Will prepare the view for successful recipe search results.
     * Will update the nutrition display view model with the search results and notify the view manager model.
     * Currently, does nothing.
     *
     * @param nutritionDisplayOutputData the nutrition information data for a specific recipe
     */
    @Override
    public void prepareSuccessView(NutritionDisplayOutputData nutritionDisplayOutputData) {
    }
}
