package use_cases._common.gui_common.abstractions;

import app.local.LocalAppSetting;

import java.beans.PropertyChangeListener;

/**
 * Interface for GUI components that need to support night mode functionality.
 * <p>
 * This interface defines methods for observing changes in night mode preference settings, toggling between night and day modes,
 * and setting the appropriate mode. Implementations of this interface should update their appearance based on
 * the current night mode setting.
 * </p>
 */
public interface NightModeObject extends PropertyChangeListener {

    /**
     * Registers this instance as a listener for changes in night mode settings.
     * <p>
     * This method uses {@link LocalAppSetting#addPropertyChangeListener(PropertyChangeListener)} to
     * listen for changes to the night mode setting. Implementations should override the {@link #propertyChange} method
     * to handle the property change events.
     * </p>
     */
    default void observeNight() {
        LocalAppSetting.addPropertyChangeListener(this);
    }

    /**
     * Toggles between night mode and day mode based on the current setting.
     * <p>
     * This method checks the current night mode setting using {@link LocalAppSetting#isNightMode()}.
     * If night mode is enabled, it calls {@link #setNightMode()}. Otherwise, it calls {@link #setDayMode()}.
     * </p>
     */
    default void toggleNightMode() {
        if (LocalAppSetting.isNightMode()) {setNightMode();} else {setDayMode();}
    };

    /**
     * Applies the night mode settings to the implementing component.
     * <p>
     * Implementations should override this method to adjust the appearance or behavior of the component
     * to match night mode requirements.
     * </p>
     */
    void setNightMode();

    /**
     * Applies the day mode settings to the implementing component.
     * <p>
     * Implementations should override this method to adjust the appearance or behavior of the component
     * to match day mode requirements.
     * </p>
     */
    void setDayMode();
}
