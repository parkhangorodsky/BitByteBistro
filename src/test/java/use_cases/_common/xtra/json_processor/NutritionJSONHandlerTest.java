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
    void JSONStringArrayToList() {
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

        List<String> labels = new ArrayList<>();
        labels.add("CHOCDF");
        labels.add("NA");
        labels.add("FAT");

        List<Nutrition> nutritions = nutritionJSONHandler.convertJSONtoNutritionList(nutritionInfo);

        assertTrue(nutritionInfo.has("calories"));
        assertTrue(nutritionInfo.has("CHOCDF"));
        assertTrue(nutritionInfo.has("NA"));
        assertTrue(nutritionInfo.has("FAT"));

        for (String label : labels) {
            assertTrue(nutritionInfo.has(label));
            assertTrue(nutritionInfo.getJSONObject(label).has("label"));
            assertTrue(nutritionInfo.getJSONObject(label).has("unit"));
            assertTrue(nutritionInfo.getJSONObject(label).has("quantity"));
        }

        assertNotNull(nutritions);
        assertEquals(4, nutritions.size());
    }
}
