package use_cases.add_to_my_recipe;

public class AddToMyRecipePresenter implements AddToMyRecipeOutputBoundary{
    @Override
    public void prepareSuccessView(AddToMyRecipeOutputData outputData) {
        System.out.println(outputData.getUser().getRecipes());
    }
}
