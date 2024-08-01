package app;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LocalAppSetting {
    static private boolean nightMode = false;
    static private PropertyChangeSupport support = new PropertyChangeSupport(LocalAppSetting.class);

    public static boolean isNightMode() {
        return nightMode;
    }

    public static void enableNightMode() {
        nightMode = true;
    }

    public static void disableNightMode() {
        nightMode = false;
    }

    public static void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public static void firePropertyChange(String propertyName) {
        support.firePropertyChange("nightMode", !nightMode, nightMode);
    }

}
