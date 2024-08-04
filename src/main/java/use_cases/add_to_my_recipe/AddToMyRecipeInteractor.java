package use_cases.add_to_my_recipe;

import entity.Recipe;
import entity.User;
import frameworks.data_access.UserDataAccessInterface;

/**
 * Overview: Interactor for adding a recipe to the logged-in user's recipes.
 * Procedure: This class takes input data and add recipe to logged in user and update database.
 * Encapsulation: This class encapsulates the business logic for adding a recipe and interacts with the data access and presentation layers.
 */
public class AddToMyRecipeInteractor implements AddToMyRecipeInputBoundary {
    AddToMyRecipePresenter presenter;
    UserDataAccessInterface userDAO;

    /**
     * Constructs an AddToMyRecipeInteractor with the given presenter and user data access object.
     *
     * @param presenter The presenter responsible for preparing the view.
     * @param userDAO   The data access object for user-related data.
     */
    public AddToMyRecipeInteractor(AddToMyRecipePresenter presenter, UserDataAccessInterface userDAO) {
        this.presenter = presenter;
        this.userDAO = userDAO;
    }

    /**
     * Executes the action of adding a recipe to the logged-in user's recipes.
     * If the recipe already exists in the user's recipes, it prepares a failure view.
     * Otherwise, it adds the recipe to the user's recipes, updates the data access object, and prepares a success view.
     *
     * @param inputData The input data required for adding the recipe.
     */
    @Override
    public void execute(AddToMyRecipeInputData inputData) {
        User user = inputData.getLoggedInUser();
        Recipe newRecipe = inputData.getRecipe();

        for (Recipe recipe : user.getRecipes()) {
            if (recipe.getId().equals(newRecipe.getId())) {
                presenter.prepareFailureView("recipe already exists", inputData.getParentModel());
                return;
            }
        }

        inputData.getLoggedInUser().addRecipe(inputData.getRecipe());
        userDAO.addRecipe(user, newRecipe);

        AddToMyRecipeOutputData outputData = new AddToMyRecipeOutputData(inputData.getLoggedInUser(), inputData.getParentModel());
        presenter.prepareSuccessView(outputData);
    }
}
