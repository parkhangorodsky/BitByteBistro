package frameworks.gui;
import app.config.Config;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases._common.authentication.AuthenticationViewModel;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class SwingGUITest {

    private SwingGUI swingGUI;
    private Config config;
    private ViewManagerModel mockViewManagerModel;
    private AuthenticationViewModel mockAuthenticationViewModel;
    private User user;

    @BeforeEach
    void setUp() {
        // Mocking dependencies
        config = new Config();
        mockViewManagerModel = mock(ViewManagerModel.class);
        mockAuthenticationViewModel = mock(AuthenticationViewModel.class);
        user = mock(User.class);

        swingGUI = new SwingGUI();

    }

    @Test
    void testInitialize() {
        assertDoesNotThrow(() -> swingGUI.initialize(config));
    }
}
