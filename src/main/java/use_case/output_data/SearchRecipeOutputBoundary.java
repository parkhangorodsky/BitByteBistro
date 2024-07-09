package use_case.output_data;

public interface SearchRecipeOutputBoundary {

    void prepareSuccessView(SearchRecipeOutputData recipes);
    void prepareEmptyResultView(SearchRecipeOutputData recipes);
}
