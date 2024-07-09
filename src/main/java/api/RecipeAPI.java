package api;

import org.json.JSONArray;
import use_case.input_data.AdvancedRecipeSearchInputBoundary;
import use_case.input_data.AdvancedRecipeSearchInputData;

public interface RecipeAPI {
    JSONArray getRecipe(String url);
    String createURLByRecipeName(String queryString);
    String createURLByAdvancedSearch(AdvancedRecipeSearchInputData advancedRecipeSearchInputData);
}
