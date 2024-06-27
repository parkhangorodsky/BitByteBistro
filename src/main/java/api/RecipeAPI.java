package api;

import entity.Recipe;
import org.json.JSONArray;

import java.util.List;

public interface RecipeAPI<E> {
    JSONArray getRecipe(String queryString);
}
