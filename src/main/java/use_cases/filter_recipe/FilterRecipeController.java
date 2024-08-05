package use_cases.filter_recipe;

import app.local.LoggedUserData;
import entity.Recipe;
import use_cases.add_to_my_recipe.MyRecipeViewModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FilterRecipeController {
    MyRecipeViewModel viewModel;
    Comparator<Recipe> recipeComparator;

    public FilterRecipeController(MyRecipeViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void execute(String searchQuery) {
        List<Recipe> userRecipe = LoggedUserData.getLoggedInUser().getRecipes();
        List<Recipe> resultRecipe = new ArrayList<>();
        for (Recipe recipe : userRecipe) {
            if (recipe.getName().toLowerCase().contains(searchQuery.toLowerCase())) {
                resultRecipe.add(recipe);
            }
        }
        resultRecipe.sort(null);
        viewModel.setRecipes(resultRecipe);
        viewModel.firePropertyChange("update");
    }
}
