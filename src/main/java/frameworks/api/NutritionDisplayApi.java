package frameworks.api;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_cases.nutrition_display.use_case.input_data.NutritionDisplayInputData;
import use_cases.search_recipe.use_case.input_data.SearchRecipeInputData;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


public class NutritionDisplayApi implements NutritionAPI{

    private static final String base_url = "https://api.edamam.com/api/nutrition-details";

    // Load API key and id from env variable.
    private static final String API_KEY = System.getenv("NUTRITION_API_KEY");
    private static final String API_ID = System.getenv("NUTRITION_API_ID");


    @Override
    public JSONObject getNutrition(NutritionDisplayInputData inputData) {

        try {
            String endpoint = createURL(inputData);
            return getResponse(endpoint);
        } catch (IOException e) {
            System.out.println("IOException\n " + e.getMessage());
        } catch (JSONException e) {
            System.out.println("JSONException\n " + e.getMessage());
        } catch (HttpResponseException e) {
            System.out.println("HttpResponseException\n " + e.getMessage());
        }

        return null;
    }

    private String createURL(NutritionDisplayInputData inputData) {
        String URL;
        return createUrlByIngredientList(inputData);
    }

    private String createUrlByIngredientList(NutritionDisplayInputData inputData) {
        return base_url + "&q=" + inputData.getIngredients() + "&app_id=" + API_ID + "&app_key=" + API_KEY;
    }

    private String optionStringBuilder(List<String> options, String type) {
        return options.stream().map(choice -> "&" + type + "=" + choice)
                .collect(Collectors.joining());
    }
    private JSONArray getResponse(String endpoint) throws JSONException, IOException, HttpResponseException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(endpoint)
                .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            JSONObject responseBody = new JSONObject(response.body().string());
            return responseBody.getJSONArray("hits");
        } else {
            System.out.println("Request failed with code: " + response.code());
            System.out.println("Response message: " + response.message());
            throw new HttpResponseException("HTTP error code: " + response.code() + ", message: " + response.message() + "with URL: " + endpoint);
        }
    }

    private class HttpResponseException extends RuntimeException {
        public HttpResponseException(String message) {
            super(message);
        }
    }
    }