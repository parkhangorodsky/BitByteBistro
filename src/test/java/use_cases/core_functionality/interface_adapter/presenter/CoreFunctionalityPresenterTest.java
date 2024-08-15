package use_cases.core_functionality.interface_adapter.presenter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;
import use_cases.core_functionality.CoreFunctionalityOutputData;
import use_cases.core_functionality.CoreFunctionalityPresenter;
import use_cases.core_functionality.MyGroceryViewModel;

import static org.mockito.Mockito.verify;

/**
 * Test class for CoreFunctionalityPresenter.
 * Ensures that the presenter's methods properly update the view model and trigger necessary property changes.
 */
class CoreFunctionalityPresenterTest {

    private CoreFunctionalityPresenter presenter; // The presenter being tested
    private MyGroceryViewModel mockViewModel; // Mocked view model
    private CoreFunctionalityOutputData mockOutputData; // Mocked output data

    @BeforeEach
    void setUp() {
        // Initialize mock objects
        mockViewModel = Mockito.mock(MyGroceryViewModel.class);
        mockOutputData = Mockito.mock(CoreFunctionalityOutputData.class);

        // Initialize the presenter with the mocked view model
        presenter = new CoreFunctionalityPresenter(mockViewModel);
    }

    /**
     * Test for prepareSuccessView method.
     * Verifies that the view model's firePropertyChange method is called when a recipe is successfully added.
     */
    @Test
    void testPrepareSuccessView() {
        // Mock the parent model to return when requested by the output data
        PropertyChangeFirer mockParentModel = Mockito.mock(PropertyChangeFirer.class);
        Mockito.when(mockOutputData.getParentModel()).thenReturn(mockParentModel);

        // Call the method under test
        presenter.prepareSuccessView(mockOutputData);

        // Verify that firePropertyChange is called on the view model with the correct property name
        verify(mockViewModel).firePropertyChange("grocery");

        // Verify that firePropertyChange is also called on the parent model with the correct property name
        verify(mockParentModel).firePropertyChange("grocery");
    }
}
