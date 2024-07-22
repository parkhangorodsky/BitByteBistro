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

/**
 * Interactor for retrieving nutritional information.
 * It handles the nutrition request based on the recipe search sent from the user through controller,
 * processes the results by calling NutritionAPI,
 * and communicates the results to the presenter.
 */
public class NutritionDisplayInteractor implements NutritionDisplayInputBoundary, JSONArrayHandler {
    private NutritionAPI nutritionAPI;
    private NutritionDisplayOutputBoundary nutritionDisplayPresenter;

    /**
     * Constructs a NutritionDisplayInteractor with the specified presenter and recipe API.
     *
     * @param nutritionDisplayPresenter the presenter to handle the output data.
     * @param nutritionAPI the API to retrieve nutritional information.
     */
    public NutritionDisplayInteractor(NutritionDisplayOutputBoundary nutritionDisplayPresenter, NutritionAPI nutritionAPI) {
        this.nutritionAPI = nutritionAPI;
        this.nutritionDisplayPresenter = nutritionDisplayPresenter;
    }

    /**
     * Executes the request for nutritional information of a specific recipe based on the input data generated
     * from the controller and, processes the JSON response from the API, and calls method in the presenter
     * to prepare the update on the view model.
     *
     * @param nutritionDisplayInputData the input data containing the search criteria
     */
    @Override
    public void execute(NutritionDisplayInputData nutritionDisplayInputData) {
        List<Nutrition> nutritionList = nutritionAPI.getNutrition(nutritionDisplayInputData);

        NutritionDisplayOutputData nutritionDisplayOutputData = new NutritionDisplayOutputData(nutritionList);
        nutritionDisplayPresenter.prepareSuccessView(nutritionDisplayOutputData);
    }
}
