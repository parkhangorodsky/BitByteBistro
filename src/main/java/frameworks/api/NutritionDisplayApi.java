package frameworks.api;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;


public class NutritionDisplayApi implements NutritionAPI{

    private static final String base_url = "https://api.edamam.com/api/recipes/v2?type=any&beta=true&q=";

    // Load API key and id from env variable.
    private static final String API_KEY = System.getenv("NUTRITION_API_KEY");
    private static final String API_ID = System.getenv("NUTRITION_API_ID");


    @Override
    public JSONObject getNutrition(JSONObject recipesJSONObject) { //think of better name

        OkHttpClient client = new OkHttpClient();

        // Define the URL of the API endpoint
        String url = "https://api.edamam.com/api/nutrition-details";

        // Convert the JSON object to a string
        String jsonString = recipesJSONObject.toString();

        // Create a request body
        RequestBody body = RequestBody.create(
                jsonString,
                MediaType.parse("application/json; charset=utf-8")
        );

        // Build the request
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return new JSONObject(response.body().string());
            } else {
                System.out.println("Request failed with code: " + response.code());
                System.out.println("Response message: " + response.message());
                System.out.println(url);
                System.out.println(jsonString);
                throw new RuntimeException();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("IOException occurred: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }


//    public String createURLByRecipeName(Nutrition) {
//        String url = base_url + queryString + "&app_id=" + API_ID + "&app_key=" + API_KEY;
//        return url;
//    }
}
