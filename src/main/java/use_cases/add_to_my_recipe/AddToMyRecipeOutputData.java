package use_cases.add_to_my_recipe;

import entity.User;

public class AddToMyRecipeOutputData {
    User user;

    public AddToMyRecipeOutputData(User user) {
        this.user = user;
    }
    public User getUser() {return user;}
}
