package use_case.interactor;

import api.RecipeAPI;
import use_case.input_data.InputBoundary;
import use_case.input_data.SearchRecipeInputData;

public class RecipeSearchInteractor implements InputBoundary {
    private RecipeAPI recipeAPI;

    public RecipeSearchInteractor(RecipeAPI recipeAPI) {
        this.recipeAPI = recipeAPI;
    }
    @Override
    public void execute(SearchRecipeInputData searchRecipeInputData) {
        String queryString = searchRecipeInputData.getQueryString();

    }
}
