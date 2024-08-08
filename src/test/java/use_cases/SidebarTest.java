package use_cases;

import app.local.LocalAppSetting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_cases._common.gui_common.abstractions.ThemeColoredObject;
import use_cases._common.gui_common.view.Sidebar;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.logout.interface_adapter.controller.LogoutController;
import use_cases._common.gui_common.abstractions.ThemeColoredObject.*;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SidebarTest implements ThemeColoredObject {
    private Sidebar sidebar;
    private ViewManagerModel viewManagerModel;
    private LogoutController logoutController;

    @BeforeEach
    void setUp() {
        viewManagerModel = mock(ViewManagerModel.class);
        logoutController = mock(LogoutController.class);
        sidebar = new Sidebar(viewManagerModel, logoutController);
    }

    @Test
    void testInitialSetup() throws Exception {
        assertNotNull(sidebar);

        // Verify the layout and dimensions
        assertTrue(sidebar.getLayout() instanceof BorderLayout);
        assertEquals(new Dimension(250, 750), sidebar.getPreferredSize());

        // Verify panels
        assertNotNull(getPrivateField(sidebar, "mainPanel"));
        assertNotNull(getPrivateField(sidebar, "titlePanel"));
        assertNotNull(getPrivateField(sidebar, "switchPanel"));
        assertNotNull(getPrivateField(sidebar, "bottomPanel"));

        // Verify buttons
        List<String> buttonNames = Arrays.asList("homeButton", "searchButton", "myRecipeButton", "groceryListButton", "settingButton", "logoutButton");
        for (String buttonName : buttonNames) {
            assertNotNull(getPrivateField(sidebar, buttonName));
        }
    }

    @Test
    void testButtonActions() throws Exception {
        // Get buttons using reflection
        RoundButton homeButton = (RoundButton) getPrivateField(sidebar, "homeButton");
        RoundButton searchButton = (RoundButton) getPrivateField(sidebar, "searchButton");
        RoundButton myRecipeButton = (RoundButton) getPrivateField(sidebar, "myRecipeButton");
        RoundButton groceryListButton = (RoundButton) getPrivateField(sidebar, "groceryListButton");
        RoundButton settingButton = (RoundButton) getPrivateField(sidebar, "settingButton");
        RoundButton logoutButton = (RoundButton) getPrivateField(sidebar, "logoutButton");

        // Simulate button actions
        homeButton.doClick();
        verify(viewManagerModel, times(1)).setActiveView("Home");
        verify(viewManagerModel, times(1)).firePropertyChanged();

        searchButton.doClick();
        verify(viewManagerModel, times(1)).setActiveView("Search Recipe");
        verify(viewManagerModel, times(1)).firePropertyChanged();

        myRecipeButton.doClick();
        verify(viewManagerModel, times(1)).firePropertyChanged("init", "My Recipe");

        groceryListButton.doClick();
        verify(viewManagerModel, times(1)).firePropertyChanged("init", "grocery");

        settingButton.doClick();
        verify(viewManagerModel, times(1)).firePropertyChanged("pop up", "Preference");

        logoutButton.doClick();
        verify(logoutController, times(1)).logout();
    }

    @Test
    void testNightMode() throws Exception {
        // Simulate night mode property change
        PropertyChangeEvent nightModeEvent = new PropertyChangeEvent(this, "nightMode", false, true);
        sidebar.propertyChange(nightModeEvent);

        // Verify background colors in night mode
        assertEquals(Color.BLACK, ((JPanel) getPrivateField(sidebar, "mainPanel")).getBackground());
        assertEquals(Color.BLACK, ((JPanel) getPrivateField(sidebar, "titlePanel")).getBackground());
        assertEquals(Color.BLACK, ((JPanel) getPrivateField(sidebar, "switchPanel")).getBackground());
        assertEquals(Color.BLACK, ((JPanel) getPrivateField(sidebar, "bottomPanel")).getBackground());
    }

    @Test
    void testDayMode() throws Exception {
        // Simulate day mode property change
        PropertyChangeEvent dayModeEvent = new PropertyChangeEvent(this, "nightMode", true, false);
        sidebar.propertyChange(dayModeEvent);

        // Verify background colors in day mode
        assertEquals(claudeWhiteEmph, ((JPanel) getPrivateField(sidebar, "mainPanel")).getBackground());
        assertEquals(claudeWhiteEmph, ((JPanel) getPrivateField(sidebar, "titlePanel")).getBackground());
        assertEquals(claudeWhiteEmph, ((JPanel) getPrivateField(sidebar, "switchPanel")).getBackground());
        assertEquals(claudeWhiteEmph, ((JPanel) getPrivateField(sidebar, "bottomPanel")).getBackground());
    }

    private Object getPrivateField(Object object, String fieldName) throws Exception {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }

    private Object getPrivateStaticField(Class<?> clazz, String fieldName) throws Exception {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(null);
    }
}
