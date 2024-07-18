package entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class RecipeTest {

    @BeforeAll
    void setUp() {

        String name = "recipe";
        String image = "src/main/resources/images/test_image.svg";
        int yield = 7;
        String instructions = "test instructions";


        List<Grocery> ingredientList = new ArrayList<>();
        Map<String, Nutrition> nutritionMap;
        Map<String, Nutrition> totalDailyMap;

        float rating;
        float estimatedCostPerServing;
        boolean privacyStatus;

        List<String> dietLabels;
        List<String> healthLabels;
        List<String> cautions;
        List<String> tags;
        List<String> cuisineType;
        List<String> mealType;
        List<String> dishType;


        Recipe recipe = new Recipe()
    }


    @org.junit.jupiter.api.Test
    void getIngredientList() {
    }

    @org.junit.jupiter.api.Test
    void getName() {
    }

    @org.junit.jupiter.api.Test
    void getImage() {
    }
}
