package use_case.input_data;

public class SearchRecipeInputData {
    public String queryString;
    public SearchRecipeInputData(String queryString) {
        this.queryString = queryString;
    }

    public String getQueryString() {
        return queryString;
    }
}
