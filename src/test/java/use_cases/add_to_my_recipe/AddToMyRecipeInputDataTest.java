package use_cases.add_to_my_recipe;

import entity.Recipe;
import entity.User;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class AddToMyRecipeInputDataTest {

    @Test
    void testConstructorAndGetters() {
        // Create mock objects
        Recipe recipe = mock(Recipe.class);
        User user = new User("testuser", "testuser@example.com", "password", null);
        PropertyChangeFirer parentModel = mock(PropertyChangeFirer.class);

        // Create an instance of AddToMyRecipeInputData
        AddToMyRecipeInputData inputData = new AddToMyRecipeInputData(recipe, user, parentModel);

        // Verify that the constructor and getters work as expected
        assertNotNull(inputData);
        assertEquals(recipe, inputData.getRecipe(), "The recipe should be correctly initialized");
        assertEquals(user, inputData.getLoggedInUser(), "The logged-in user should be correctly initialized");
        assertEquals(parentModel, inputData.getParentModel(), "The parent model should be correctly initialized");
    }
}
