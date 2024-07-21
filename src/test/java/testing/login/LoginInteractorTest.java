package testing.login;

import entity.User;
import frameworks.data_access.CSVDataAccessObject;
import frameworks.data_access.DataAccessInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases.log_in.use_case.input_data.LoginInputData;
import use_cases.log_in.use_case.output_data.LoginOutputBoundary;
import use_cases.log_in.use_case.output_data.LoginOutputData;
import use_cases.log_in.use_case.interactor.LoginInteractor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class LoginInteractorTest {
    private LoginInteractor interactor;
    private LoginOutputBoundary presenter;
    private DataAccessInterface dao;

    @BeforeEach
    void setUp() {
        presenter = mock(LoginOutputBoundary.class);
        dao = spy(new CSVDataAccessObject("path/to/users.csv"));
        interactor = new LoginInteractor(presenter, dao);
    }

    @Test
    void testSuccessfulLogin() {
        User user = new User("userId", "test@example.com", "password", null);
        doReturn(user).when(dao).getUserByEmail("test@example.com");

        interactor.execute(new LoginInputData("test@example.com", "password"));

        verify(presenter).prepareSuccessView(any(LoginOutputData.class));
    }

    @Test
    void testFailedLoginWithIncorrectEmail() {
        doReturn(null).when(dao).getUserByEmail("test@example.com");

        interactor.execute(new LoginInputData("test@example.com", "password"));

        verify(presenter).prepareFailView("Invalid email or password.");
    }

    @Test
    void testFailedLoginWithIncorrectPassword() {
        User user = new User("userId", "test@example.com", "correctPassword", null);
        doReturn(user).when(dao).getUserByEmail("test@example.com");

        interactor.execute(new LoginInputData("test@example.com", "wrongPassword"));

        verify(presenter).prepareFailView("Invalid email or password.");
    }
}
