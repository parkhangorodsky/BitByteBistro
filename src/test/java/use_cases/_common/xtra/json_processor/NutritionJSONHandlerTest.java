package use_cases._common.xtra.json_processor;

import entity.Nutrition;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NutritionJSONHandlerTest {

    private NutritionJSONHandler nutritionJSONHandler = new NutritionJSONHandlerIMPL();

    private class NutritionJSONHandlerIMPL implements NutritionJSONHandler {}

    @Test
    void convertJSONtoNutritionList() {
        JSONObject nutritionInfo = new JSONObject();

        JSONObject carbs = new JSONObject();
        carbs.put("label", "carbs");
        carbs.put("unit", "g");
        carbs.put("quantity", "100");

        JSONObject sodium = new JSONObject();
        sodium.put("label", "sodium");
        sodium.put("unit", "mg");
        sodium.put("quantity", "99");

        JSONObject fats = new JSONObject();
        fats.put("label", "fats");
        fats.put("unit", "g");
        fats.put("quantity", "50");

        nutritionInfo.put("calories", 1050);
        nutritionInfo.put("CHOCDF", carbs);
        nutritionInfo.put("NA", sodium);
        nutritionInfo.put("FAT", fats);

        assertNotNull(nutritionJSONHandler.convertJSONtoNutritionList(nutritionInfo));
        assertEquals(4, nutritionJSONHandler.convertJSONtoNutritionList(nutritionInfo).size());
    }
}
