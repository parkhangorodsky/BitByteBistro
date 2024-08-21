package use_cases.setting_preference;

import app.local.LoggedUserData;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PreferenceViewTest {

    private JFrame mockParent;
    private SetPreferenceController mockController;
    private PreferenceView preferenceView;
    private User user;

    @BeforeEach
    void setUp() {
        mockParent = mock(JFrame.class);
        when(mockParent.getSize()).thenReturn(new Dimension(100, 100));
        mockController = mock(SetPreferenceController.class);
        User user = new User("a", "a", "a", LocalDateTime.now());
        Map<String, Object> preference = new HashMap<>();
        preference.put("nightMode", true);
        preference.put("subtractFridgeFromGrocery", true);
        user.setPreference(preference);
        LoggedUserData.setLoggedInUser(user);
    }

    @Test
    void testLoadPreferenceInitializesCheckBoxes() {
        // Mock logged-in user data
        preferenceView = new PreferenceView(mockParent, mockController);

        // Verify that the checkboxes are initialized correctly
        assertFalse(preferenceView.nightModeCheckBox.isSelected());
        assertFalse(preferenceView.subtractFridgeFromGroceryCheckBox.isSelected());
    }

    @Test
    void testSetNightModeUpdatesComponents() {
        preferenceView = new PreferenceView(mockParent, mockController);
        // Simulate setting night mode
        preferenceView.setNightMode();

        // Verify the background and foreground colors are updated correctly
        assertEquals(preferenceView.mainPanel.getBackground(), preferenceView.black);
        assertEquals(preferenceView.nightModeCheckBox.getForeground(), preferenceView.neonPinkEmph);
        assertEquals(preferenceView.titleLabel.getForeground(), preferenceView.neonPurpleEmph);
    }

    @Test
    void testSetDayModeUpdatesComponents() {
        preferenceView = new PreferenceView(mockParent, mockController);
        // Simulate setting day mode
        preferenceView.setDayMode();

        // Verify the background and foreground colors are updated correctly
        assertEquals(preferenceView.mainPanel.getBackground(), preferenceView.claudeWhite);
        assertEquals(preferenceView.nightModeCheckBox.getForeground(), preferenceView.claudeBlack);
        assertEquals(preferenceView.titleLabel.getForeground(), preferenceView.claudeBlack);
    }


    @Test
    void testPropertyChangeSubtractFridgeFromGrocery() {
        preferenceView = new PreferenceView(mockParent, mockController);
        // Create a PropertyChangeEvent for subtractFridgeFromGrocery
        PropertyChangeEvent event = new PropertyChangeEvent(this, "subtractFridgeFromGrocery", false, true);

        // Call the propertyChange method with the event
        preferenceView.propertyChange(event);

        // Verify that the checkbox was updated
        assertTrue(preferenceView.subtractFridgeFromGroceryCheckBox.isSelected());
    }
}
