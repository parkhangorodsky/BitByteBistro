package use_cases.add_to_my_recipe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;
import use_cases._common.interface_adapter_common.view_model.abstractions.ViewModel;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AddToMyRecipePresenterTest {

    private MyRecipeViewModel mockViewModel;
    private PropertyChangeFirer mockParentModel;
    private AddToMyRecipePresenter presenter;

    @BeforeEach
    void setUp() {
        // Initialize mocks and the presenter before each test
        mockViewModel = Mockito.mock(MyRecipeViewModel.class);
        mockParentModel = Mockito.mock(PropertyChangeFirer.class);
        presenter = new AddToMyRecipePresenter(mockViewModel);
    }

    @Test
    void testPrepareSuccessView() {
        // Arrange
        AddToMyRecipeOutputData mockOutputData = new AddToMyRecipeOutputData(null, mockParentModel);

        // Act
        presenter.prepareSuccessView(mockOutputData);

        // Assert
        verify(mockViewModel, times(1)).firePropertyChange("added recipe");
        verify(mockParentModel, times(1)).firePropertyChange("added recipe");
    }

    @Test
    void testPrepareFailureView() {
        // Act
        presenter.prepareFailureView("error", mockParentModel);

        // Assert
        verify(mockParentModel, times(1)).firePropertyChange("error");
    }
}
