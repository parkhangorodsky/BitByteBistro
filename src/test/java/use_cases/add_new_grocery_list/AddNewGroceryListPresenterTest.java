package use_cases.add_new_grocery_list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;
import use_cases.core_functionality.MyGroceryViewModel;

import static org.mockito.Mockito.*;

public class AddNewGroceryListPresenterTest {

    private AddNewGroceryListPresenter presenter;
    private MyGroceryViewModel viewModel;
    private PropertyChangeFirer parentModel;

    @BeforeEach
    public void setUp() {
        viewModel = mock(MyGroceryViewModel.class);
        presenter = new AddNewGroceryListPresenter(viewModel);
        parentModel = mock(PropertyChangeFirer.class);
    }

    @Test
    public void testPrepareSuccessView() {
        presenter.prepareSuccessView(parentModel);

        verify(viewModel).firePropertyChange("added shoppingList");
        verify(parentModel).firePropertyChange("added shoppingList");
    }

    @Test
    public void testPrepareFailureView() {
        String propertyName = "grocery list already exists";

        presenter.prepareFailureView(propertyName, parentModel);

        verify(parentModel).firePropertyChange(propertyName);
    }
}
