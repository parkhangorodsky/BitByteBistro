package use_cases.add_to_my_recipe;

import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;
import use_cases._common.interface_adapter_common.view_model.abstractions.ViewModel;

/**
 * Overview: Presenter for the use case of adding a recipe to the logged-in user's recipes.
 * Encapsulation: This class encapulates the logic for preparing the view for success and failure scenarios of adding recipe.
 * Procedure: If success, this class updates the MyRecipeViewModel. This class notifies the originating view the result.
 */
public class AddToMyRecipePresenter implements AddToMyRecipeOutputBoundary{
    MyRecipeViewModel viewModel;

    /**
     * Constructor for  AddToMyRecipePresenter.
     *
     * @param viewModel The ViewModel that represents the state of the view.
     */
    public AddToMyRecipePresenter(MyRecipeViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Prepares the success view when a recipe is successfully added.
     * Fires property change events to update the view and notify the parent model.
     *
     * @param outputData The output data required to update the view.
     */
    @Override
    public void prepareSuccessView(AddToMyRecipeOutputData outputData) {
        viewModel.firePropertyChange("added recipe");
        outputData.getParentModel().firePropertyChange("added recipe");
    }

    /**
     * Prepares the failure view when adding a recipe fails.
     * Fires a property change event to notify the parent model.
     *
     * @param propertyName      The name of the property change event.
     * @param parentViewModel   The parent ViewModel that will be notified of the property change.
     */
    public void prepareFailureView(String propertyName, PropertyChangeFirer parentViewModel) {
        parentViewModel.firePropertyChange(propertyName);
    }
}
