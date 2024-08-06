package use_cases._common.gui_common.abstractions;

import app.local.LocalAppSetting;

import java.beans.PropertyChangeListener;

public interface NightModeObject extends PropertyChangeListener {

    default void observeNight() {
        LocalAppSetting.addPropertyChangeListener(this);
    }

    default void toggleNightMode() {
        if (LocalAppSetting.isNightMode()) {setNightMode();} else {setDayMode();}
    };
    void setNightMode();
    void setDayMode();
}
