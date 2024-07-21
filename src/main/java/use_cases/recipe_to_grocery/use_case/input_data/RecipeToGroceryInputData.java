package use_cases.recipe_to_grocery.use_case.input_data;

import entity.User;
import use_cases.log_in.interface_adapter.controller.LoginController;

public class RecipeToGroceryInputData {
    private User user;

    public RecipeToGroceryInputData(LoginController loginController) {
        this.user = loginController.getUser();
    }

    public User getUser() {return this.user;}
}


