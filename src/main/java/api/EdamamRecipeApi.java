package api;

import entity.Recipe;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EdamamRecipeApi implements RecipeAPI {

    private static final String base_url = "https://api.edamam.com/api/recipes/v2?type=any&beta=true&q=";

    // Load API key and id from env variable.
    private static final String API_KEY = System.getenv("EDAMAM_API_KEY");
    private static final String API_ID = System.getenv("EDAMAM_API_ID");




    @Override
    public JSONArray getRecipe(String queryString) {

        OkHttpClient client = new OkHttpClient();


        String url = base_url + queryString + "&app_id=" + API_ID + "&app_key=" + API_KEY;

        Request request = new Request.Builder()
                .url(url)
                .build();



        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                JSONObject responseBody = new JSONObject(response.body().string());
                return responseBody.getJSONArray("hits");
            } else {
                System.out.println("Request failed with code: " + response.code());
                System.out.println("Response message: " + response.message());
                throw new RuntimeException();
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            System.err.println("IOException occurred: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
