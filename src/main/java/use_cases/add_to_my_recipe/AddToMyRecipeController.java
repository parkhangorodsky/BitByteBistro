package use_cases.add_to_my_recipe;

import entity.LoggedUserData;
import entity.Recipe;
import entity.User;

public class AddToMyRecipeController {
    AddToMyRecipeInteractor interator;

    public AddToMyRecipeController(AddToMyRecipeInteractor interactor) {
        this.interator = interactor;
    }

    public void execute(Recipe recipe){
        User user = LoggedUserData.getLoggedInUser();
        if (user != null) {
            AddToMyRecipeInputData inputData = new AddToMyRecipeInputData(recipe, user);
            interator.execute(inputData);
        }

    }

}
