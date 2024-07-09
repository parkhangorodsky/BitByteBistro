package api;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.input_data.AdvancedRecipeSearchInputData;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EdamamRecipeApi implements RecipeAPI {

    private static final String base_url = "https://api.edamam.com/api/recipes/v2?type=any&beta=true";

    // Load API key and id from env variable.
    private static final String API_KEY = System.getenv("EDAMAM_API_KEY");
    private static final String API_ID = System.getenv("EDAMAM_API_ID");


    @Override
    public JSONArray getRecipe(String url) {

        OkHttpClient client = new OkHttpClient();

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

    @Override
    public String createURLByRecipeName(String queryString) {
        String url = base_url + "&q=" + queryString + "&app_id=" + API_ID + "&app_key=" + API_KEY;
        return url;
    }

    @Override
    public String createURLByAdvancedSearch(AdvancedRecipeSearchInputData advancedRecipeSearchInputData) {
        String queryString = "&q=" + advancedRecipeSearchInputData.getQueryString();

//        Map<String, Integer> ingredients = advancedRecipeSearchInputData.getIngredients();
//        String ingredientsString = "&ingr=";
//        for (String key : ingredients.keySet()) {
//            int max = ingredients.get(key);
//            System.out.println(key);
//            System.out.println(max);
//            ingredientsString = ingredientsString + key + "%3D" + max;
//        }
//        ingredientsString = ingredientsString.substring(0, ingredientsString.length() - 6);

        String dietsString = convertAdvancedSearchChoiceIntoString(advancedRecipeSearchInputData.getDiet(), "diet");
        String healthString = convertAdvancedSearchChoiceIntoString(advancedRecipeSearchInputData.getHealth(), "health");
        String cusineTypeString = convertAdvancedSearchChoiceIntoString(advancedRecipeSearchInputData.getCuisineType(), "cuisineType");
        String mealTypeString = convertAdvancedSearchChoiceIntoString(advancedRecipeSearchInputData.getMealType(), "mealType");
        String dishTypeString = convertAdvancedSearchChoiceIntoString(advancedRecipeSearchInputData.getDishType(), "dishType");
        String excludedString = convertAdvancedSearchChoiceIntoString(advancedRecipeSearchInputData.getExcluded(), "excluded");

        String URL = base_url
                + queryString
                + "&app_id=" + API_ID
                + "&app_key=" + API_KEY
//                + ingredientsString
                + dietsString
                + healthString
                + cusineTypeString
                + mealTypeString
                + dishTypeString
                + excludedString;

        return URL;

    }

    public String convertAdvancedSearchChoiceIntoString(List<String> choices, String type) {
        StringBuilder choicesString = new StringBuilder();
        for (String choice  : choices) {
            choicesString.append("&").append(type).append("=").append(choice);
        }
        return choicesString.toString();
    }

}
