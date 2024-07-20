package use_cases.search_recipe.use_case.input_data;

import java.util.List;

/**
 * Input data for searching recipes. This class encapsulates the search criteria.
 */
public class SearchRecipeInputData {

    boolean isAdvanced = false; // Indicates whether the search is advanced or not
    public String queryString; // The main query string for searching recipes
    private List<String> excluded; // List of ingredients to be excluded from the recipe
    private List<String> diet; // List of diet labels
    private List<String> health; // List of health labels
    private List<String> cuisineType; // List of cuisine type labels
    private List<String> mealType; // List of meal type labels
    private List<String> dishType; // List of dish type labels

    /**
     * Class constructor for search input data with basic search query.
     * This constructor is used for regular recipe search.
     * @param queryString The search string.
     */
    public SearchRecipeInputData(String queryString) {
        this.queryString = queryString;
    }

    /**
     * Class constructor for advanced search input data.
     * Advanced search includes more search criteria.
     * @param queryString The search string.
     * @param excluded List of ingredient names that is to be excluded from the recipe.
     * @param diet List of diet labels.
     * @param health List of health labels.
     * @param cuisineType List of cuisine type labels.
     * @param dishType List of dish type labels.
     * @param mealType List of meal type labels.
     */
    public SearchRecipeInputData(String queryString,
                                         List<String> excluded,
                                         List<String> diet,
                                         List<String> health,
                                         List<String> cuisineType,
                                         List<String> dishType,
                                         List<String> mealType
    ) {
        this.queryString = queryString;
        this.excluded = excluded;
        this.diet = diet;
        this.health = health;
        this.cuisineType = cuisineType;
        this.mealType = mealType;
        this.dishType = dishType;
        this.isAdvanced = true;
    }


    /**
     * Checks if the given input data is for advanced recipe search.
     * @return true iff the input data is advanced.
     */
    public boolean isAdvanced() {
        return isAdvanced;
    }

    // Getters
    public String getRecipeName() {
        return queryString;
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
