package use_case.input_data;

public class SearchRecipeInputData implements InputData {
    public String queryString;
    public SearchRecipeInputData(String queryString) {
        this.queryString = queryString;
    }

    public String getQueryString() {
        return queryString;
    }
}
