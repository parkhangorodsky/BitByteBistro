package use_cases.recipe_to_grocery.use_case.input_data;

import entity.User;

public class RecipeToGroceryInputData {
    private User user;

    public RecipeToGroceryInputData(User user) {
        this.user = user;
    }

    public User getUser() {return this.user;}
}





