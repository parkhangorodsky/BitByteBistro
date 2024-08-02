package use_cases.nutrition_stats.use_case.interactor;

import entity.Nutrition;
import frameworks.api.NutritionAPI;
import use_cases.nutrition_stats.interface_adapeter.presenter.NutritionStatsOutputBoundary;
import use_cases.nutrition_stats.use_case.input_data.NutritionStatsInputData;
import use_cases.nutrition_stats.use_case.output_data.NutritionStatsOutputData;

import java.util.List;

/**
 * Interactor for retrieving nutritional information.
 * It handles the nutrition request based on the recipe search sent from the user through controller,
 * processes the results by calling NutritionAPI,
 * and communicates the results to the presenter.
 */
public class NutritionStatsInteractor implements NutritionStatsInputBoundary {
    private NutritionAPI nutritionAPI;
    private NutritionStatsOutputBoundary nutritionStatsPresenter;

    /**
     * Constructs a NutritionStatsInteractor with the specified presenter and recipe API.
     *
     * @param nutritionStatsPresenter the presenter to handle the output data.
     * @param nutritionAPI the API to retrieve nutritional information.
     */
    public NutritionStatsInteractor(NutritionStatsOutputBoundary nutritionStatsPresenter, NutritionAPI nutritionAPI) {
        this.nutritionAPI = nutritionAPI;
        this.nutritionStatsPresenter = nutritionStatsPresenter;
    }

    /**
     * Executes the request for nutritional information of a specific recipe based on the input data generated
     * from the controller and, processes the JSON response from the API, and calls method in the presenter
     * to prepare the update on the view model.
     *
     * @param nutritionStatsInputData the input data containing the search criteria
     */
    @Override
    public void execute(NutritionStatsInputData nutritionStatsInputData) {
        int numberOfRecipes = nutritionStatsInputData.getNumberOfRecipes();
        List<Nutrition> nutritionList = nutritionAPI.getNutrition(nutritionStatsInputData);

        NutritionStatsOutputData nutritionStatsOutputData = new NutritionStatsOutputData(nutritionList, numberOfRecipes);
        nutritionStatsPresenter.prepareSuccessView(nutritionStatsOutputData);
    }
}