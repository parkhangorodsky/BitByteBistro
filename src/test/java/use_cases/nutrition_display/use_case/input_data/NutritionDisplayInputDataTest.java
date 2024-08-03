//package use_cases.nutrition_display.use_case.input_data;
//
//import entity.Ingredient;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.mockito.Mockito.mock;
//
//public class NutritionDisplayInputDataTest {
//
//    @BeforeEach
//    public void setUp() {
//
//    }
//
//    @Test
//    void testEmptyIngredientsConstructor() {
//        String title = "title";
//        List<Ingredient> ingredients = new ArrayList<>();
//
//        NutritionDisplayInputData inputData = new NutritionDisplayInputData(title, ingredients);
//
//        assertEquals(title, inputData.getTitle());
//        assertNull(inputData.getIngredients());
//    }
//
//    @Test
//    void testCompleteConstructor() {
//        String title = "title";
//        List<Ingredient> ingredients = new ArrayList<>();
//
//        // Create mock Ingredients
//        Ingredient mockIngredient1 = mock(Ingredient.class);
//        Ingredient mockIngredient2 = mock(Ingredient.class);
//        Ingredient mockIngredient3 = mock(Ingredient.class);
//
//        ingredients.add(mockIngredient1);
//        ingredients.add(mockIngredient2);
//        ingredients.add(mockIngredient3);
//
//        NutritionDisplayInputData inputData = new NutritionDisplayInputData(title, ingredients);
//
//        assertEquals(title, inputData.getTitle());
//        assertEquals(ingredients, inputData.getIngredients());
//        assertEquals(3, inputData.getIngredients().size());
//    }
//}
