package use_cases.nutrition_display.interface_adapter.presenter;

import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.nutrition_display.interface_adapter.view_model.NutritionDisplayViewModel;
import use_cases.nutrition_display.use_case.output_data.NutritionDisplayOutputData;
import use_cases.search_recipe.interface_adapter.view_model.SearchRecipeViewModel;

public class NutritionDisplayPresenter implements NutritionDisplayOutputBoundary{

    private NutritionDisplayViewModel nutritionDisplayViewModel;
    private ViewManagerModel viewManagerModel;

    public NutritionDisplayPresenter(ViewManagerModel viewManagerModel, SearchRecipeViewModel searchRecipeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.nutritionDisplayViewModel = nutritionDisplayViewModel;
    }

    @Override
    public void prepareSuccessView(NutritionDisplayOutputData nutritionDisplayOutputData) {

    }
}
