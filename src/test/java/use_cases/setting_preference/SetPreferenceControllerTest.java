package use_cases.setting_preference;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SetPreferenceControllerTest {
    private SetPreferenceInputBoundary mockInteractor;
    private SetPreferenceController controller;

    @BeforeEach
    void setUp() {
        // Initialize the mock interactor and the controller
        mockInteractor = mock(SetPreferenceInputBoundary.class);
        controller = new SetPreferenceController(mockInteractor);
    }

    @Test
    void testExecute() {
        // Execute the method with a test value
        boolean testIsNightMode = true;
        controller.execute(testIsNightMode, false);

        // Capture the input data passed to the interactor
        ArgumentCaptor<SetPreferenceInputData> captor = ArgumentCaptor.forClass(SetPreferenceInputData.class);
        verify(mockInteractor).execute(captor.capture());

        // Assert the captured input data
        SetPreferenceInputData capturedData = captor.getValue();
        assertEquals(testIsNightMode, capturedData.getNightMode(), "The isNightMode value should be passed correctly");
    }
}
