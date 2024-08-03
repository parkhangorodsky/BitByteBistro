package frameworks.api;

import entity.Ingredient;
import entity.Nutrition;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_cases._common.xtra.exceptions.HttpResponseException;
import use_cases._common.xtra.json_processor.NutritionJSONHandler;
import use_cases.nutrition_stats.use_case.input_data.NutritionStatsInputData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NutritionDisplayApi implements NutritionAPI, NutritionJSONHandler {

    private static final String base_url = "https://api.edamam.com/api/nutrition-details";

    // Load API key and id from env variable.
    private static final String API_KEY = System.getenv("NUTRITION_API_KEY");
    private static final String API_ID = System.getenv("NUTRITION_API_ID");


    @Override
    public List<Nutrition> getNutrition(NutritionStatsInputData inputData) {
        try {
            String endpoint = createURL();
            JSONObject responseJSON =  getResponse(endpoint, inputData);
            return this.convertJSONtoNutritionList(responseJSON);
        } catch (IOException e) {
            System.out.println("IOException\n " + e.getMessage());
        } catch (JSONException e) {
            System.out.println("JSONException\n " + e.getMessage());
        } catch (HttpResponseException e) {
            System.out.println("HttpResponseException\n " + e.getMessage());
        }

        return null;
    }

    private String createURL() {
        return base_url + "?app_id=" + API_ID + "&app_key=" + API_KEY;
    }

    private JSONObject getResponse(String endpoint, NutritionStatsInputData inputData) throws JSONException, IOException, HttpResponseException {
        OkHttpClient client = new OkHttpClient();

        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("title", inputData.getTitle());

        List<String> ingredientList = new ArrayList<>();
        for (Ingredient ingredient : inputData.getIngredients()) {
            ingredientList.add(ingredient.toString());
        }
        jsonMap.put("ingr", ingredientList);

        JSONObject jsonRequest = new JSONObject(jsonMap);

        RequestBody body = RequestBody.create(jsonRequest.toString(), MediaType.get("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(endpoint)
                .post(body)
                .build();

        JSONObject nutritionJSONObject = new JSONObject();

        try(Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return new JSONObject(response.body().string());
            } else if (response.code() == 555) {
                nutritionJSONObject.put("error", 555);
                nutritionJSONObject.put("displayMessage", "could not retrieve nutritional information for this recipe");
                return nutritionJSONObject;
            } else {
                System.out.println("Request failed with code: " + response.code());
                System.out.println("Response message: " + response.message());
                throw new HttpResponseException("HTTP error code: " + response.code() + ", message: " + response.message() + " with URL: " + endpoint);
            }
        } catch (IOException e) {
            System.out.println("IOException\n " + e.getMessage());
            throw new IOException();
        }


    }
}
