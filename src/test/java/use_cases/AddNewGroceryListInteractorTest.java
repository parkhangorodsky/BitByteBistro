package use_cases;

import app.local.LoggedUserData;
import entity.ShoppingList;
import entity.User;
import frameworks.data_access.UserDataAccessInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;
import use_cases.add_new_grocery_list.AddNewGroceryListInputData;
import use_cases.add_new_grocery_list.AddNewGroceryListInteractor;
import use_cases.add_new_grocery_list.AddNewGroceryListPresenter;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddNewGroceryListInteractorTest {

    private AddNewGroceryListInteractor interactor;
    private AddNewGroceryListPresenter presenter;
    private UserDataAccessInterface userDAO;
    private PropertyChangeFirer parentModel;
    private User testUser;

    @BeforeEach
    public void setUp() {
        presenter = mock(AddNewGroceryListPresenter.class);
        userDAO = mock(UserDataAccessInterface.class);
        interactor = new AddNewGroceryListInteractor(presenter, userDAO);
        parentModel = mock(PropertyChangeFirer.class);

        testUser = new User("testUser", "test@example.com", "password123", null);
        LoggedUserData.setLoggedInUser(testUser);
    }

    @Test
    public void testExecuteNewShoppingList() {
        String shoppingListName = "Weekly Groceries";
        AddNewGroceryListInputData inputData = new AddNewGroceryListInputData(shoppingListName, parentModel);

        interactor.execute(inputData);

        assertTrue(testUser.getShoppingLists().containsKey(testUser.getUserEmail()));
        verify(presenter).prepareSuccessView(parentModel);
    }

    @Test
    public void testExecuteExistingShoppingList() {
        String shoppingListName = "Weekly Groceries";
        ShoppingList existingList = new ShoppingList(testUser.getUserEmail(), shoppingListName);
        testUser.addShoppingList(existingList);

        AddNewGroceryListInputData inputData = new AddNewGroceryListInputData(shoppingListName, parentModel);

        interactor.execute(inputData);

        verify(presenter).prepareFailureView("grocery list already exists", parentModel);
    }
}
