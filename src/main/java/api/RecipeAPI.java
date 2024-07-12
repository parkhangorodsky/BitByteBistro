package api;

import org.json.JSONArray;
import use_case.input_data.SearchRecipeInputData;

public interface RecipeAPI {
    JSONArray getRecipe(SearchRecipeInputData inputData);
}
