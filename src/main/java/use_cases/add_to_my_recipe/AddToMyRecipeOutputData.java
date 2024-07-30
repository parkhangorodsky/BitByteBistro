package use_cases.add_to_my_recipe;

import entity.User;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;
import use_cases._common.interface_adapter_common.view_model.abstractions.ViewModel;

public class AddToMyRecipeOutputData {
    User user;
    PropertyChangeFirer parentModel;

    public AddToMyRecipeOutputData(User user, PropertyChangeFirer parentModel) {
        this.user = user;
        this.parentModel = parentModel;
    }
    public User getUser() {return user;}
    public PropertyChangeFirer getParentModel() {return parentModel;}
}
