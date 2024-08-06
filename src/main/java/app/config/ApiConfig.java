package app.config;

import frameworks.api.EdamamRecipeApi;
import frameworks.api.NutritionAPI;
import frameworks.api.NutritionDisplayApi;
import frameworks.api.RecipeAPI;

class ApiConfig {

    static final RecipeAPI recipeAPI = new EdamamRecipeApi();
    static final NutritionAPI nutritionAPI = new NutritionDisplayApi();

}
