package frameworks.api;

import entity.Recipe;
import org.json.JSONArray;
import use_cases.search_recipe.use_case.input_data.SearchRecipeInputData;

import java.util.List;

public interface RecipeAPI {
    List<Recipe> getRecipe(SearchRecipeInputData inputData);
}
