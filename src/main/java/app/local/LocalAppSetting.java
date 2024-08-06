package app.local;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LocalAppSetting {

    private static boolean nightMode = false;
    private static boolean subtractFridgeFromGrocery = false;
    private static final PropertyChangeSupport support = new PropertyChangeSupport(LocalAppSetting.class);

    public static boolean isNightMode() {
        return nightMode;
    }

    public static void setNightMode(boolean nightMode) {
        boolean oldNightMode = LocalAppSetting.nightMode;
        LocalAppSetting.nightMode = nightMode;
        support.firePropertyChange("nightMode", oldNightMode, nightMode);
    }

    public static boolean isSubtractFridgeFromGrocery() {
        return subtractFridgeFromGrocery;
    }

    public static void setSubtractFridgeFromGrocery(boolean subtractFridgeFromGrocery) {
        boolean oldSubtractFridgeFromGrocery = LocalAppSetting.subtractFridgeFromGrocery;
        LocalAppSetting.subtractFridgeFromGrocery = subtractFridgeFromGrocery;
        support.firePropertyChange("subtractFridgeFromGrocery", oldSubtractFridgeFromGrocery, subtractFridgeFromGrocery);
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
}
