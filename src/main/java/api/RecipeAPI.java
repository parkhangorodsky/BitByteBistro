package api;

import org.json.JSONArray;

public interface RecipeAPI {
    JSONArray getRecipe(String url);
    String createURLByRecipeName(String queryString);
}
