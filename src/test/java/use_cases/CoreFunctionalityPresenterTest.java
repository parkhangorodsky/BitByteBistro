package use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;
import use_cases.core_functionality.CoreFunctionalityOutputData;
import use_cases.core_functionality.CoreFunctionalityPresenter;
import use_cases.core_functionality.MyGroceryViewModel;

import static org.mockito.Mockito.*;

public class CoreFunctionalityPresenterTest {

    private CoreFunctionalityPresenter presenter;
    private MyGroceryViewModel viewModel;
    private PropertyChangeFirer parentModel;

    @BeforeEach
    public void setUp() {
        viewModel = mock(MyGroceryViewModel.class);
        presenter = new CoreFunctionalityPresenter(viewModel);
        parentModel = mock(PropertyChangeFirer.class);
    }

    @Test
    public void testPrepareSuccessView() {
        CoreFunctionalityOutputData outputData = new CoreFunctionalityOutputData(null, parentModel);

        presenter.prepareSuccessView(outputData);

        verify(viewModel).firePropertyChange("grocery");
        verify(parentModel).firePropertyChange("grocery");
    }
}
