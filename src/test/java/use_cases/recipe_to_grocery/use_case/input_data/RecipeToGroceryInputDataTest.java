package use_cases.recipe_to_grocery.use_case.input_data;

import entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class RecipeToGroceryInputDataTest {

    @Test
    void testBasicConstructor() {
        User user = new User("user", "email", "password", LocalDateTime.of(2023, 8, 15, 10, 30));
        RecipeToGroceryInputData inputData = new RecipeToGroceryInputData(user);

        assertNull(inputData.getUser());
    }
}
