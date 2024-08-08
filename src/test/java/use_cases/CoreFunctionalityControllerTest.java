package use_cases;

import entity.Recipe;
import entity.ShoppingList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;
import use_cases.core_functionality.CoreFunctionalityController;
import use_cases.core_functionality.CoreFunctionalityInputData;
import use_cases.core_functionality.CoreFunctionalityInteractor;

import static org.mockito.Mockito.*;

public class CoreFunctionalityControllerTest {

    private CoreFunctionalityController controller;
    private CoreFunctionalityInteractor interactor;
    private PropertyChangeFirer parentModel;
    private Recipe testRecipe;
    private ShoppingList testShoppingList;

    @BeforeEach
    public void setUp() {
        interactor = mock(CoreFunctionalityInteractor.class);
        controller = new CoreFunctionalityController(interactor);
        parentModel = mock(PropertyChangeFirer.class);
        testRecipe = mock(Recipe.class);
        testShoppingList = mock(ShoppingList.class);
    }

    @Test
    public void testExecute() {
        controller.execute(testShoppingList, testRecipe, parentModel);

        verify(interactor).execute(any(CoreFunctionalityInputData.class));
    }
}
