package use_cases.add_to_my_recipe;

import entity.Recipe;
import entity.User;
import frameworks.data_access.UserDataAccessInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddToMyRecipeInteractorTest {

    private AddToMyRecipePresenter presenter;
    private UserDataAccessInterface userDAO;
    private AddToMyRecipeInteractor interactor;

    @BeforeEach
    void setUp() {
        presenter = mock(AddToMyRecipePresenter.class);
        userDAO = mock(UserDataAccessInterface.class);
        interactor = new AddToMyRecipeInteractor(presenter, userDAO);
    }

    @Test
    void testExecute_WithExistingRecipe() {
        // Arrange
        User user = new User("testuser", "testuser@example.com", "password", LocalDateTime.now());
        Recipe existingRecipe = mock(Recipe.class);
        when(existingRecipe.getId()).thenReturn("id123");
        user.addRecipe(existingRecipe);

        Recipe newRecipe = mock(Recipe.class);

        PropertyChangeFirer parentModel = mock(PropertyChangeFirer.class);
        AddToMyRecipeInputData inputData = new AddToMyRecipeInputData(existingRecipe, user, parentModel);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(presenter).prepareFailureView(eq("recipe already exists"), eq(parentModel));
        verify(userDAO, never()).addRecipe(any(User.class), any(Recipe.class));
    }

    @Test
    void testExecute_WithNewRecipe() {
        // Arrange
        User user = mock(User.class);
        Recipe newRecipe = mock(Recipe.class);

        PropertyChangeFirer parentModel = mock(PropertyChangeFirer.class);
        AddToMyRecipeInputData inputData = new AddToMyRecipeInputData(newRecipe, user, parentModel);

        // Act
        interactor.execute(inputData);

        // Assert
        ArgumentCaptor<AddToMyRecipeOutputData> outputDataCaptor = ArgumentCaptor.forClass(AddToMyRecipeOutputData.class);
        verify(presenter).prepareSuccessView(outputDataCaptor.capture());
        verify(userDAO).addRecipe(user, newRecipe);

        AddToMyRecipeOutputData outputData = outputDataCaptor.getValue();
        assertNotNull(outputData);
        assertEquals(user, outputData.getUser());
        assertEquals(parentModel, outputData.getParentModel());
    }
}
