package frameworks.api;

import entity.Recipe;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_cases._common.xtra.json_processor.RecipeJSONHandler;
import use_cases.search_recipe.use_case.input_data.SearchRecipeInputData;
import use_cases._common.xtra.exceptions.HttpResponseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EdamamRecipeApi implements RecipeAPI, RecipeJSONHandler {

    private static final String base_url = "https://api.edamam.com/api/recipes/v2?type=any&beta=true";

    // Load API key and id from env variable.
    private static final String API_KEY = System.getenv("EDAMAM_API_KEY");
    private static final String API_ID = System.getenv("EDAMAM_API_ID");

    @Override
    public List<Recipe> getRecipe(SearchRecipeInputData inputData) {

        try {
            String endpoint = createURL(inputData);
            JSONArray responseRecipe = getResponse(endpoint);

            List<Recipe> recipeList = new ArrayList<>();
            for (int i = 0; i < responseRecipe.length(); i++) {
                JSONObject recipeJSON = responseRecipe.getJSONObject(i).getJSONObject("recipe");
                Recipe recipe =  this.convertJSONtoRecipe(recipeJSON);
                recipeList.add(recipe);
            }

            return recipeList;

        } catch (IOException e) {
            System.out.println("IOException\n " + e.getMessage());
        } catch (JSONException e) {
            System.out.println("JSONException\n " + e.getMessage());
        } catch (HttpResponseException e) {
            System.out.println("HttpResponseException\n " + e.getMessage());
        }

        return null;
    }

    private String createURL(SearchRecipeInputData inputData) {
        String URL;
        if (!inputData.isAdvanced()) {
            URL =  createUrlByRecipeName(inputData);
        } else {
            String dietsString = optionStringBuilder(inputData.getDiet(), "diet");
            String healthString = optionStringBuilder(inputData.getHealth(), "health");
            String cusineTypeString = optionStringBuilder(inputData.getCuisineType(), "cuisineType");
            String mealTypeString = optionStringBuilder(inputData.getMealType(), "mealType");
            String dishTypeString = optionStringBuilder(inputData.getDishType(), "dishType");
            String excludedString = optionStringBuilder(inputData.getExcluded(), "excluded");

            URL =  createUrlByRecipeName(inputData)
                    + dietsString
                    + healthString
                    + cusineTypeString
                    + mealTypeString
                    + dishTypeString
                    + excludedString;
        }
        return URL;
    }

    private String createUrlByRecipeName(SearchRecipeInputData inputData) {
        return base_url + "&q=" + inputData.getRecipeName() + "&app_id=" + API_ID + "&app_key=" + API_KEY;
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
}
