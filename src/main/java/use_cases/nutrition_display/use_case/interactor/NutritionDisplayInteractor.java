package use_cases.nutrition_display.use_case.interactor;

import entity.Nutrition;
import entity.Recipe;
import frameworks.api.NutritionAPI;
import org.json.JSONArray;
import org.json.JSONObject;
import use_cases._common.xtra.json_processor.JSONArrayHandler;
import use_cases._common.xtra.json_processor.NutritionJSONHandler;
import use_cases.nutrition_display.interface_adapter.presenter.NutritionDisplayOutputBoundary;
import use_cases.nutrition_display.use_case.input_data.NutritionDisplayInputData;
import use_cases.nutrition_display.use_case.output_data.NutritionDisplayOutputData;
import use_cases.search_recipe.interface_adapter.presenter.SearchRecipeOutputBoundary;
import use_cases.search_recipe.use_case.output_data.SearchRecipeOutputData;

import java.util.ArrayList;
import java.util.List;

public class NutritionDisplayInteractor implements NutritionDisplayInputBoundary, NutritionJSONHandler, JSONArrayHandler {
    private NutritionAPI nutritionAPI;
    private NutritionDisplayOutputBoundary nutritionDisplayPresenter;

    public NutritionDisplayInteractor(NutritionDisplayOutputBoundary nutritionDisplayPresenter, NutritionAPI nutritionAPI) {
        this.nutritionAPI = nutritionAPI;
        this.nutritionDisplayPresenter = nutritionDisplayPresenter;
    }
    @Override
    public void execute(NutritionDisplayInputData nutritionDisplayInputData) {
        List<Nutrition> nutritionList = nutritionAPI.getNutrition(nutritionDisplayInputData);

        NutritionDisplayOutputData nutritionDisplayOutputData = new NutritionDisplayOutputData(nutritionList);
        nutritionDisplayPresenter.prepareSuccessView(nutritionDisplayOutputData);

        // make getNutrition call convertNutritionJSON,
    }
}
