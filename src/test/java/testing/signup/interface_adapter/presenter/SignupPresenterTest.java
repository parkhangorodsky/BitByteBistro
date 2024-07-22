package testing.signup.interface_adapter.presenter;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.sign_up.interface_adapter.presenter.SignUpPresenter;
import use_cases.sign_up.interface_adapter.view_model.SignUpViewModel;
import use_cases.sign_up.use_case.output_data.SignUpOutputData;

import static org.mockito.Mockito.*;

/**
 * Unit tests for SignUpPresenter class.
 * This class tests the prepareSuccessView and prepareErrorView methods of the SignUpPresenter class.
 */
public class SignupPresenterTest {
    private SignUpPresenter presenter;
    private SignUpViewModel viewModel;
    private ViewManagerModel viewManagerModel;

    @BeforeEach
    void setUp() {
        viewModel = mock(SignUpViewModel.class);
        viewManagerModel = mock(ViewManagerModel.class);
        presenter = new SignUpPresenter(viewModel, viewManagerModel);
    }

    /**
     * Test for the prepareSuccessView method.
     * Ensures that the view model is updated and the view is switched to the login view.
     */
    @Test
    void testPrepareSuccessView() {
        User user = new User("userID", "user@example.com", "password", null);
        SignUpOutputData outputData = new SignUpOutputData(user);

        presenter.prepareSuccessView(outputData);

        verify(viewModel).setUserEmail(user.getUserEmail());
        verify(viewModel).setUserID(user.getUserName());
        verify(viewModel).setSuccessMessage("User signed up successfully!");
        verify(viewManagerModel).setActiveView("LoginView");
        verify(viewManagerModel).firePropertyChanged();
    }

    /**
     * Test for the prepareErrorView method.
     * Ensures that the error message is set in the view model.
     */
    @Test
    void testPrepareErrorView() {
        String errorMessage = "Error occurred";
        presenter.prepareErrorView(errorMessage);

        verify(viewModel).setErrorMessage(errorMessage);
    }
}
