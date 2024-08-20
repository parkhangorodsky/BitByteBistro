package app.config;

import use_cases.nutrition_stats.interface_adapter.controller.NutritionStatsController;
import use_cases.nutrition_stats.interface_adapter.presenter.NutritionStatsPresenter;
import use_cases.nutrition_stats.use_case.interactor.NutritionStatsInteractor;

import static app.config.DataAccessConfig.userDAO;
import static app.config.ViewModelConfig.viewManagerModel;
import static app.config.ViewModelConfig.nutritionStatsViewModel;
import static app.config.ApiConfig.nutritionAPI;


public class NutritionStatsConfig {

    static final NutritionStatsPresenter presenter = new NutritionStatsPresenter(
            viewManagerModel,
            nutritionStatsViewModel);

    static final NutritionStatsInteractor interactor = new NutritionStatsInteractor(
            presenter,
            nutritionAPI
    );

    static final NutritionStatsController controller = new NutritionStatsController(
            interactor);
}
