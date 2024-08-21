package use_cases.add_to_my_recipe;

import app.local.LoggedUserData;
import entity.Recipe;
import entity.User;
import entity.builder.DefaultRecipeBuilder;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.Mockito.*;

public class AddToMyRecipeControllerTest {

    private AddToMyRecipeController controller;
    private AddToMyRecipeInteractor interactor;
    private Recipe recipe;
    private User user;
    private PropertyChangeFirer parentModel;

    @BeforeEach
    void setUp() {
        // Create mocks
        interactor = mock(AddToMyRecipeInteractor.class);
        parentModel = mock(PropertyChangeFirer.class);
        user = mock(User.class);

        recipe = new DefaultRecipeBuilder()
                .buildName("RecipeName")
                .buildYield(1)
                .buildNutritionMap(new HashMap<>())
                .get();
        LoggedUserData.setLoggedInUser(user);

        // Create controller
        controller = new AddToMyRecipeController(interactor);
    }

    @Test
    void testExecute() {
        // Execute the controller method
        controller.execute(recipe, parentModel);

        // Verify interactions
        verify(interactor).execute(any(AddToMyRecipeInputData.class));
    }

    @Test
    void testExecuteWhenUserIsNull() {
        LoggedUserData.setLoggedInUser(null);

        // Execute the controller method
        controller.execute(recipe, parentModel);

        // Verify interactions
        verifyNoInteractions(interactor);

    }
}
