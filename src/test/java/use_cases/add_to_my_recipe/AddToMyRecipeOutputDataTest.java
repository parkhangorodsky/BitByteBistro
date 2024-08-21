package use_cases.add_to_my_recipe;

import entity.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddToMyRecipeOutputDataTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        User testUser = new User("testuser", "testuser@example.com", "password", null);
        PropertyChangeFirer mockParentModel = Mockito.mock(PropertyChangeFirer.class);

        // Act
        AddToMyRecipeOutputData outputData = new AddToMyRecipeOutputData(testUser, mockParentModel);

        // Assert
        assertEquals(testUser, outputData.getUser());
        assertEquals(mockParentModel, outputData.getParentModel());
    }
}
