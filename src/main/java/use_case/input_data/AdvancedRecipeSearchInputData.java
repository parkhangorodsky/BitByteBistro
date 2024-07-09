package use_case.input_data;

import java.util.List;
import java.util.Map;

public class AdvancedRecipeSearchInputData {

    private String queryString;
    private Map<String, Integer> ingredients;
    private List<String> excluded;
    private List<String> diet;
    private List<String> health;
    private List<String> cuisineType;
    private List<String> mealType;
    private List<String> dishType;

    public AdvancedRecipeSearchInputData(String queryString,
                                         Map<String, Integer> ingredients,
                                         List<String> excluded,
                                         List<String> diet,
                                         List<String> health,
                                         List<String> cuisineType,
                                         List<String> dishType,
                                         List<String> mealType
    ) {
        this.queryString = queryString;
        this.ingredients = ingredients;
        this.excluded = excluded;
        this.diet = diet;
        this.health = health;
        this.cuisineType = cuisineType;
        this.mealType = mealType;
        this.dishType = dishType;
    }

    public String getQueryString() {
        return queryString;
    }

    public Map<String, Integer> getIngredients() {
        return ingredients;
    }

    public List<String> getDiet() {
        return diet;
    }

    public List<String> getHealth() {
        return health;
    }

    public List<String> getCuisineType() {
        return cuisineType;
    }

    public List<String> getMealType() {
        return mealType;
    }

    public List<String> getDishType() {
        return dishType;
    }

    public List<String> getExcluded() {
        return excluded;
    }


}
