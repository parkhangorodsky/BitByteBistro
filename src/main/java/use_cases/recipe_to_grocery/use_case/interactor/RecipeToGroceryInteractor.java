package use_cases.recipe_to_grocery.use_case.interactor;

import entity.Ingredient;
import entity.ShoppingList;
import entity.Recipe;
import entity.User;

import frameworks.api.RecipeAPI;
import frameworks.data_access.CSVDataAccessObject;

import use_cases.recipe_to_grocery.use_case.input_data.RecipeToGroceryInputData;
import use_cases.recipe_to_grocery.interface_adapter.presenter.RecipeToGroceryOutputBoundary;
import use_cases.recipe_to_grocery.use_case.output_data.RecipeToGroceryOutputData;

import use_cases.log_in.use_case.output_data.LoginOutputData;

import use_cases._common.xtra.json_processor.RecipeJSONHandler;

import java.util.ArrayList;
import java.util.List;

public class RecipeToGroceryInteractor implements RecipeToGroceryInputBoundary, RecipeJSONHandler {
    private RecipeToGroceryOutputBoundary recipeToGroceryPresenter;

    public RecipeToGroceryInteractor(RecipeToGroceryOutputBoundary recipeToGroceryPresenter, RecipeAPI recipeAPI) {
        this.recipeToGroceryPresenter = recipeToGroceryPresenter;
    }

    public User fetchUser() {
        LoginOutputData loginOutputData = SOMEHOW FETCH CURRENTLY LOGGED IN USER;
        return loginOutputData.getUser();
    }

    public List<Recipe> fetchRecipes() {
        User user = fetchUser();
        return user.getRecipes();
    }

    @Override
    public void execute(RecipeToGroceryInputData recipeToGroceryInputData) {

        List<Recipe> recipes = fetchRecipes();

        // Implement logic to convert recipes to grocery list
        ShoppingList shoppingList = getGroceryList(recipes);
        List<ShoppingList> shoppingLists = new ArrayList<>();
        shoppingLists.add(shoppingList);

        // Notify presenter or other components with outputData
        RecipeToGroceryOutputData recipeToGroceryOutputData = new RecipeToGroceryOutputData(shoppingLists);
        recipeToGroceryPresenter.prepareSuccessView(recipeToGroceryOutputData);
    }

    private ShoppingList getGroceryList(List<Recipe> recipeList) {
        List<Ingredient> groceries = new ArrayList<>();
        for (Recipe recipe : recipeList) {
            for (Ingredient grocery : recipe.getIngredientList()) {
                if (groceries.contains(grocery)) {
                    Ingredient item = groceries.get(groceries.indexOf(grocery));
                    float more = grocery.getIngredientQuantity();
                    item.addIngredientQuantity(more);
                } else {
                    groceries.add(grocery);
                }
            }
        }
        ShoppingList groceryList = new ShoppingList(listOwner, listName, groceries);
    }
}





