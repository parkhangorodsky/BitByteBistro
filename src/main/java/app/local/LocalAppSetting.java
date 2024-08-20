package app.local;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * A class to manage application settings that can be modified and observed.
 * <p>
 * This class maintains static application settings such as night mode and whether to subtract fridge contents
 * from the grocery list. It uses the {@link PropertyChangeSupport} mechanism to notify listeners of changes to
 * these settings.
 * </p>
 */
public class LocalAppSetting {

    private static boolean nightMode = false;
    private static boolean subtractFridgeFromGrocery = false;
    private static final PropertyChangeSupport support = new PropertyChangeSupport(LocalAppSetting.class);

    /**
     * Returns the current state of night mode.
     *
     * @return {@code true} if night mode is enabled, {@code false} otherwise.
     */
    public static boolean isNightMode() {
        return nightMode;
    }


    /**
     * Sets the state of night mode and notifies listeners of the change.
     *
     * @param nightMode {@code true} to enable night mode, {@code false} to disable it.
     */
    public static void setNightMode(boolean nightMode) {
        boolean oldNightMode = LocalAppSetting.nightMode;
        LocalAppSetting.nightMode = nightMode;
        support.firePropertyChange("nightMode", oldNightMode, nightMode);
    }

    /**
     * Returns the current state of the "subtract fridge from grocery" setting.
     *
     * @return {@code true} if subtracting fridge contents from the grocery list is enabled, {@code false} otherwise.
     */
    public static boolean isSubtractFridgeFromGrocery() {
        return subtractFridgeFromGrocery;
    }

    /**
     * Sets the state of the "subtract fridge from grocery" setting.
     *
     * @param enabled {@code true} to enable subtracting fridge contents from the grocery list, {@code false} to disable it.
     */
    public static void setSubtractFridgeFromGrocery(boolean enabled) {
        subtractFridgeFromGrocery = enabled;
    }


    public static void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public static void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public static void firePropertyChange(String propertyName) {
        support.firePropertyChange(propertyName, false, true);
    }

    public static void clearSessionSettings() {
            // Clear session-specific settings like night mode, preferences, etc.
            setNightMode(false); // Reset to default or desired state
            // Reset other settings as necessary
        }
}


