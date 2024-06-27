package api;

import entity.Recipe;

import java.util.List;

public interface RecipeAPI {
    List<Recipe> getSearchRecipeResult(String queryString);
}
