package testing.login.interface_adapter.presenter;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.log_in.interface_adapter.presenter.LoginPresenter;
import use_cases.log_in.interface_adapter.view_model.LoginViewModel;
import use_cases.log_in.use_case.output_data.LoginOutputData;

import static org.mockito.Mockito.*;

/**
 * Test class for LoginPresenter.
 */
public class LoginPresenterTest {

    private LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;
    private LoginPresenter loginPresenter;

    /**
     * Set up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        loginViewModel = mock(LoginViewModel.class);
        viewManagerModel = mock(ViewManagerModel.class);
        loginPresenter = new LoginPresenter(loginViewModel, viewManagerModel, authenticationService);
    }

    /**
     * Test the prepareSuccessView method.
     * Ensure it clears any error messages, sets the logged-in user, and navigates to the "search recipe" view.
     */
    @Test
    void testPrepareSuccessView() {
        User user = new User("userId", "test@example.com", "password", null);
        LoginOutputData outputData = new LoginOutputData(user);

        loginPresenter.prepareSuccessView(outputData);

        verify(loginViewModel).setErrorMessage("");
        verify(viewManagerModel).setActiveView("search recipe");
        verify(viewManagerModel).firePropertyChanged();
    }

    /**
     * Test the prepareFailView method.
     * Ensure it sets the error message in the LoginViewModel.
     */
    @Test
    void testPrepareFailView() {
        String errorMessage = "Invalid email or password.";

        loginPresenter.prepareFailView(errorMessage);

        verify(loginViewModel).setErrorMessage(errorMessage);
    }
}
