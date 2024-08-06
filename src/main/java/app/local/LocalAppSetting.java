package app.local;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LocalAppSetting {
    static private boolean nightMode = false;

    static private PropertyChangeSupport support = new PropertyChangeSupport(LocalAppSetting.class);

    public static boolean isNightMode() {
        return nightMode;
    }

    public static void setNightMode(boolean update) {
        nightMode = update;
    }

    public static void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public static void firePropertyChange(String propertyName) {
        support.firePropertyChange(propertyName, !nightMode, nightMode);
    }
}
