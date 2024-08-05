package use_cases.add_new_grocery_list;

import use_cases.add_to_my_recipe.AddToMyRecipeInputData;

public interface AddNewGroceryListInputBoundary {
    void execute(AddNewGroceryListInputData inputData);
}
