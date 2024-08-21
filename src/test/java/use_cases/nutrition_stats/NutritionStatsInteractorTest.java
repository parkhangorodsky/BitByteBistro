package use_cases.nutrition_stats;

import entity.Ingredient;
import entity.Nutrition;
import frameworks.api.NutritionAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_cases.nutrition_stats.interface_adapter.presenter.NutritionStatsOutputBoundary;
import use_cases.nutrition_stats.use_case.input_data.NutritionStatsInputData;
import use_cases.nutrition_stats.use_case.interactor.NutritionStatsInteractor;
import use_cases.nutrition_stats.use_case.output_data.NutritionStatsOutputData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class NutritionStatsInteractorTest {
    private NutritionAPI nutritionAPI;
    private NutritionStatsOutputBoundary nutritionStatsPresenter;
    private NutritionStatsInteractor nutritionStatsInteractor;

    @BeforeEach
    void setUp() {
        nutritionAPI = mock(NutritionAPI.class);
        nutritionStatsPresenter = mock(NutritionStatsOutputBoundary.class);
        nutritionStatsInteractor = new NutritionStatsInteractor(nutritionStatsPresenter, nutritionAPI);
    }

    @Test
    void testExecuteWithValidData() {
        // Arrange
        List<Ingredient> ingredients = List.of(new Ingredient("1", "Tomato", "kg", "Vegetable", 1));
        NutritionStatsInputData inputData = new NutritionStatsInputData("Tomato Salad", ingredients, 3);
        List<Nutrition> nutritionList = List.of(new Nutrition("Calories", 300, "kcal"));
        when(nutritionAPI.getNutrition(inputData)).thenReturn(nutritionList);

        // Act
        nutritionStatsInteractor.execute(inputData);

        // Assert
        ArgumentCaptor<NutritionStatsOutputData> captor = ArgumentCaptor.forClass(NutritionStatsOutputData.class);
        verify(nutritionStatsPresenter).prepareSuccessView(captor.capture());
        NutritionStatsOutputData outputData = captor.getValue();

        assertEquals(nutritionList, outputData.getNutrition());
        assertEquals(100, outputData.getNutritionInfoAverage().get(0).getQuantity());
    }

    @Test
    void testExecuteWithEmptyNutritionList() {
        // Arrange
        List<Ingredient> ingredients = List.of(new Ingredient("1", "Tomato", "kg", "Vegetable", 1));
        NutritionStatsInputData inputData = new NutritionStatsInputData("Tomato Salad", ingredients, 0);
        when(nutritionAPI.getNutrition(inputData)).thenReturn(List.of());

        // Act
        nutritionStatsInteractor.execute(inputData);

        // Assert
        ArgumentCaptor<NutritionStatsOutputData> captor = ArgumentCaptor.forClass(NutritionStatsOutputData.class);
        verify(nutritionStatsPresenter).prepareSuccessView(captor.capture());
        NutritionStatsOutputData outputData = captor.getValue();

        assertEquals(0, outputData.getNutrition().size());
        assertEquals(0, outputData.getNutritionInfoAverage().size());
    }

//    @Test
//    void testExecuteWithNullNutritionList() {
//        // Arrange
//        List<Ingredient> ingredients = List.of(new Ingredient("1", "Tomato", "kg", "Vegetable", 1));
//        NutritionStatsInputData inputData = new NutritionStatsInputData("Tomato Salad", ingredients, 1);
//        when(nutritionAPI.getNutrition(inputData)).thenReturn(null);
//
//        // Act
//        nutritionStatsInteractor.execute(inputData);
//
//        // Assert
//        ArgumentCaptor<NutritionStatsOutputData> captor = ArgumentCaptor.forClass(NutritionStatsOutputData.class);
//        verify(nutritionStatsPresenter).prepareSuccessView(captor.capture());
//        NutritionStatsOutputData outputData = captor.getValue();
//
//        assertEquals(null, outputData.getNutrition());
//        assertEquals(0, outputData.getNutritionInfoAverage().size());
//    }
}
