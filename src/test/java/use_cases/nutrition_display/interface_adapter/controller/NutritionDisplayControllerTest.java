package use_cases.nutrition_display.interface_adapter.controller;

import entity.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import use_cases.nutrition_display.use_case.interactor.NutritionDisplayInputBoundary;

public class NutritionDisplayControllerTest {
    private NutritionDisplayController controller;
    private NutritionDisplayInputBoundary mockInteractor;

    @BeforeEach
    void setUp() {
        mockInteractor = Mockito.mock(NutritionDisplayInputBoundary.class);
        controller = new NutritionDisplayController(mockInteractor);
    }

    @Test
    void Execute() {
    }
}
