package use_cases.add_to_my_recipe;

import entity.LoggedUserData;
import entity.Recipe;
import entity.User;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;

public class AddToMyRecipeController {
    AddToMyRecipeInteractor interactor;

    public AddToMyRecipeController(AddToMyRecipeInteractor interactor) {
        this.interactor = interactor;
    }

    public void execute(Recipe recipe, PropertyChangeFirer parentModel){
        User user = LoggedUserData.getLoggedInUser();
        if (user != null) {
            AddToMyRecipeInputData inputData = new AddToMyRecipeInputData(recipe, user, parentModel);
            interactor.execute(inputData);
        }

    }

}
