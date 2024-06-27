package app;

import api.EdamamRecipeApi;
import api.RecipeAPI;
import use_case.interactor.SearchRecipeInteractor;

public class Config {
    private final RecipeAPI recipeAPI = new EdamamRecipeApi();

    public SearchRecipeInteractor searchRecipeInteractor() {
        return new SearchRecipeInteractor(recipeAPI);
    }
}
