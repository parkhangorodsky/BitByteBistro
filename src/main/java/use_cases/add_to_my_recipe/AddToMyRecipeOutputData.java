package use_cases.add_to_my_recipe;

import entity.Recipe;
import entity.User;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;
import use_cases._common.interface_adapter_common.view_model.abstractions.ViewModel;

import java.util.List;

public class AddToMyRecipeOutputData {
    List<Recipe> recipes;
    PropertyChangeFirer parentModel;

    public AddToMyRecipeOutputData(PropertyChangeFirer parentModel) {
        this.parentModel = parentModel;
    }
    public PropertyChangeFirer getParentModel() {return parentModel;}
}
