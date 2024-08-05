package use_cases.core_functionality;

import use_cases.add_to_my_recipe.MyRecipeViewModel;

public class CoreFunctionalityPresenter implements CoreFunctionalityOutputBoundary{
    MyGroceryViewModel viewModel;

    /**
     * Constructor for  CoreFunctionalityPresenter.
     *
     * @param viewModel The ViewModel that represents the state of the view.
     */
    public CoreFunctionalityPresenter(MyGroceryViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Prepares the success view when a recipe is successfully added.
     * Fires property change events to update the view and notify the parent model.
     *
     * @param outputData The output data required to update the view.
     */
    @Override
    public void prepareSuccessView(CoreFunctionalityOutputData outputData) {
        viewModel.firePropertyChange("grocery");
        outputData.getParentModel().firePropertyChange("grocery");
    }
}
