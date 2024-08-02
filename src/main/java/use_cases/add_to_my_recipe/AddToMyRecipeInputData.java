package use_cases.add_to_my_recipe;

import entity.Recipe;
import entity.User;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;

import java.util.List;

public class AddToMyRecipeInputData {

    private Recipe recipe;
    private List<Recipe> recipes;
    private PropertyChangeFirer parentModel;

    public AddToMyRecipeInputData(Recipe recipe, PropertyChangeFirer parentModel) {
        this.recipe = recipe;
        this.parentModel = parentModel;
    }

    public Recipe getRecipe() {return recipe;}
    public PropertyChangeFirer getParentModel() {return parentModel;}
}
