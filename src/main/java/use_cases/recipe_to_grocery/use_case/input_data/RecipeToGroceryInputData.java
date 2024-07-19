package use_cases.recipe_to_grocery.use_case.input_data;

public class RecipeToGroceryInputData {
    public String queryString;
    public RecipeToGroceryInputData(String queryString) {
        this.queryString = queryString;
    }

    public String getQueryString() {
        return queryString;
    }
}
