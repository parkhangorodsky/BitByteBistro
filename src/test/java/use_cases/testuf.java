//package use_cases;
//
//import entity.Nutrition;
//import frameworks.api.NutritionAPI;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import use_cases.nutrition_stats.interface_adapeter.presenter.NutritionStatsOutputBoundary;
//import use_cases.nutrition_stats.use_case.input_data.NutritionStatsInputData;
//import use_cases.nutrition_stats.use_case.interactor.NutritionStatsInteractor;
//import use_cases.nutrition_stats.use_case.output_data.NutritionStatsOutputData;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//public class NutritionStatsInteractorTest {
//
//    private NutritionStatsInteractor interactor;
//    private NutritionStatsOutputBoundary presenter;
//    private NutritionAPI nutritionAPI;
//
//    @BeforeEach
//    public void setUp() {
//        presenter = mock(NutritionStatsOutputBoundary.class);
//        nutritionAPI = mock(NutritionAPI.class);
//        interactor = new NutritionStatsInteractor(presenter, nutritionAPI);
//    }
//
//    @Test
//    public void testConstructor() {
//        assertNotNull(interactor);
//        assertEquals(nutritionAPI, interactor.nutritionAPI);
//        assertEquals(presenter, interactor.nutritionStatsPresenter);
//    }
//
//    @Test
//    public void testExecuteSuccess() {
//        // Arrange
//        NutritionStatsInputData inputData = new NutritionStatsInputData(3, "testRecipe");
//        List<Nutrition> nutritionList = new ArrayList<>();
//        nutritionList.add(new Nutrition("testIngredient", 100, 200, 300, 400));
//        when(nutritionAPI.getNutrition(inputData)).thenReturn(nutritionList);
//
//        // Act
//        interactor.execute(inputData);
//
//        // Assert
//        ArgumentCaptor<NutritionStatsOutputData> captor = ArgumentCaptor.forClass(NutritionStatsOutputData.class);
//        verify(presenter).prepareSuccessView(captor.capture());
//        NutritionStatsOutputData outputData = captor.getValue();
//        assertEquals(1, outputData.getNutritionList().size());
//        assertEquals(3, outputData.getNumberOfRecipes());
//    }
//
//    @Test
//    public void testExecuteEmptyResult() {
//        // Arrange
//        NutritionStatsInputData inputData = new NutritionStatsInputData(0, "emptyRecipe");
//        List<Nutrition> emptyNutritionList = new ArrayList<>();
//        when(nutritionAPI.getNutrition(inputData)).thenReturn(emptyNutritionList);
//
//        // Act
//        interactor.execute(inputData);
//
//        // Assert
//        ArgumentCaptor<NutritionStatsOutputData> captor = ArgumentCaptor.forClass(NutritionStatsOutputData.class);
//        verify(presenter).prepareSuccessView(captor.capture());
//        NutritionStatsOutputData outputData = captor.getValue();
//        assertEquals(0, outputData.getNutritionList().size());
//        assertEquals(0, outputData.getNumberOfRecipes());
//    }
//
//    @Test
//    public void testExecuteApiError() {
//        // Arrange
//        NutritionStatsInputData inputData = new NutritionStatsInputData(1, "errorRecipe");
//        when(nutritionAPI.getNutrition(inputData)).thenReturn(null);
//
//        // Act
//        interactor.execute(inputData);
//
//        // Assert
//        ArgumentCaptor<NutritionStatsOutputData> captor = ArgumentCaptor.forClass(NutritionStatsOutputData.class);
//        verify(presenter).prepareSuccessView(captor.capture());
//        NutritionStatsOutputData outputData = captor.getValue();
//        assertNull(outputData.getNutritionList());
//        assertEquals(1, outputData.getNumberOfRecipes());
//    }
//}
