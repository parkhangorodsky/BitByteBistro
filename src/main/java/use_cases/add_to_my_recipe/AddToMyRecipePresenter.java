package use_cases.add_to_my_recipe;

import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;
import use_cases._common.interface_adapter_common.view_model.abstractions.ViewModel;

public class AddToMyRecipePresenter implements AddToMyRecipeOutputBoundary{
    MyRecipeViewModel viewModel;

    public AddToMyRecipePresenter(MyRecipeViewModel viewModel) {
        this.viewModel = viewModel;
    }
    @Override
    public void prepareSuccessView(AddToMyRecipeOutputData outputData) {
        viewModel.firePropertyChange("added recipe");
    }

    public void prepareFailureView(String propertyName, PropertyChangeFirer parentViewModel) {
        parentViewModel.firePropertyChange(propertyName);
    }
}
