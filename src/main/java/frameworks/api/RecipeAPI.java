package frameworks.api;

import org.json.JSONArray;
import use_cases.search_recipe.use_case.input_data.SearchRecipeInputData;

public interface RecipeAPI {
    JSONArray getRecipe(SearchRecipeInputData inputData);
}
